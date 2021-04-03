package barbarich.ilya.proplayer.redux.action

import android.util.Log
import barbarich.ilya.proplayer.network.PlayerApi
import barbarich.ilya.proplayer.network.model.PlayerApiStatus
import barbarich.ilya.proplayer.network.model.PlayerFilter
import barbarich.ilya.proplayer.network.model.PlayerInfo
import barbarich.ilya.proplayer.redux.store.store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.rekotlin.Action

class PlayerRequest {
    class FetchPlayers : Request() {
        private var viewModelJob = Job()
        private val coroutineScope = CoroutineScope( Dispatchers.Main + viewModelJob )

        override fun execute() {
            coroutineScope.launch {
                val listResult: List<PlayerInfo>
                val filter = PlayerFilter.SORT_BY_NAME
                val getPropertiesDeferred = PlayerApi.retrofitService.getDataFromApi(filter.value)
                try {
                    //Log.d("Test", "PlayerRequest -> execute -> getDateRetrofit")
                    listResult = when (filter) {
                        PlayerFilter.SORT_BY_RATING_1_0 -> {
                            getPropertiesDeferred.await().sortedByDescending { it.rating_1_0 }
                        }
                        PlayerFilter.SORT_BY_RATING -> {
                            getPropertiesDeferred.await().sortedByDescending { it.rating }
                        }
                        PlayerFilter.SORT_BY_NAME -> {
                            getPropertiesDeferred.await().sortedBy { it.nick_name }
                        }
                    }
                    //Log.d("Test", "lsistResult = ${listResult}")
                    store.dispatch(Success(listResult))
                    //Log.d("Test", "PlayerRequest -> execute -> Success execute getDateRetrofit")

                } catch (e: Exception) {
                    //Log.d("Test", "PlayerRequest -> execute ->fail getDateRetrofit")
                    store.dispatch(Failure(e))
                }
            }
        }

        data class Success(val players: List<PlayerInfo>) : Action

        data class Failure(val t: Throwable) : Action
    }
}