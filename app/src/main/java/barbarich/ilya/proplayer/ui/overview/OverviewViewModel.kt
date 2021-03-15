package barbarich.ilya.proplayer.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.PlayerApi
import barbarich.ilya.proplayer.network.PlayerFilter
import barbarich.ilya.proplayer.network.model.PlayerApiStatus
import barbarich.ilya.proplayer.network.model.PlayerInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _selectedProperty = MutableLiveData<PlayerInfo>()
    val selectedProperty: LiveData<PlayerInfo>
        get() = _selectedProperty

    private val _allPlayers = MutableLiveData<List<PlayerInfo>>()
    val allPlayers: LiveData<List<PlayerInfo>>
        get() = _allPlayers

    private val _statusLoading = MutableLiveData<PlayerApiStatus>()
    val statusLoading: LiveData<PlayerApiStatus>
        get() = _statusLoading

    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(playerInfo: PlayerInfo) {
        _selectedProperty.value = playerInfo
    }

    fun displayPropertyDetailsComplete() {
        _selectedProperty.value = null
    }

    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPlayerProperties(PlayerFilter.SORT_BY_RATING_1_0)
    }

    private fun getPlayerProperties(filter: PlayerFilter) {
        coroutineScope.launch {
            val listResult: List<PlayerInfo>
            val getPropertiesDeferred = PlayerApi.retrofitService.getDataFromApi(filter.value)

            try {
                _statusLoading.value = PlayerApiStatus.LOADING
                listResult = when (filter) {
                    PlayerFilter.SORT_BY_RATING_1_0 -> {
                        getPropertiesDeferred.await().sortedByDescending { it.rating_1_0 }
                    }
                    PlayerFilter.SORT_BY_RATING -> {
                        getPropertiesDeferred.await().sortedByDescending { it.rating }
                    }
                    PlayerFilter.SORT_BY_NAME -> {
                        getPropertiesDeferred.await().sortedBy { it.nickName }
                    }
                }
                _statusLoading.value = PlayerApiStatus.DONE
                _allPlayers.value = listResult
            } catch (e: Exception) {
                _statusLoading.value = PlayerApiStatus.ERROR
                _allPlayers.value = ArrayList()
            }
        }
    }

    fun updateFilter(filter: PlayerFilter){
        getPlayerProperties(filter)
    }
}