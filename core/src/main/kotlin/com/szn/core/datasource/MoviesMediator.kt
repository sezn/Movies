package com.szn.core.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.szn.core.Constants
import com.szn.core.datastore.DataStoreManager
import com.szn.core.db.AppDatabase
import com.szn.core.extensions.isOnline
import com.szn.core.network.API
import com.szn.core.network.model.Movie
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class MoviesMediator @Inject constructor(private val api: API,
                                         private val database: AppDatabase,
                                         private val dataStore: DataStoreManager,
                                         private val what: String?
                                         ): RemoteMediator<Int, Movie>() {

    private val videoDao = database.movieDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    lastItem.id
                }
            }

            Log.w("Mediator", "loadType $loadType $loadKey $what")

            // Retrofit's Coroutine CallAdapter dispatches on a worker thread.
//            val response = api.getMovies(null).results
            var response = if(what.equals(Constants.UPCOMMINGS)) {
                api.getUpcomingMovies().results
            } else if(what.equals(Constants.POPULARS)){
                api.getMovies("sort_by=vote_average.desc").results
            }else {
                api.getMovies(null).results
            }


            if (response != null)
                dataStore.setLastUpdated(System.currentTimeMillis())

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
//                    videoDao.clear()
                }

                videoDao.insertAll(response)
                // Insert new videos into database, which invalidates the current PagingData,
                // allowing Paging to present the updates in the DB.
                /*response?.map {
                    videoDao.insert(it)
                }*/
            }

            MediatorResult.Success(
                endOfPaginationReached = !response.isNullOrEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    /**
     * check whether cached data is out of date and decide whether to trigger a remote refresh.
     */
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES)
        val del = System.currentTimeMillis() - dataStore.lastUpdated()
        val network = isOnline(dataStore.context)
        return if ((del < cacheTimeout) || !network)
        {
            //Need to refresh cached data
            Log.i("Mediator", "No Need to refresh... $del < $cacheTimeout ")
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            Log.i("Mediator", "Need to refresh... $del > $cacheTimeout ")
            // Cached data is up-to-date, so there is no need to re-fetch
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
}