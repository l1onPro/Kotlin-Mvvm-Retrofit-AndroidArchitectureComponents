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

    private val _navigateToSelectedProperty = MutableLiveData<PlayerInfo>()
    val navigateToSelectedProperty: LiveData<PlayerInfo>
        get() = _navigateToSelectedProperty

    private val _properties = MutableLiveData<List<PlayerInfo>>()
    val properties: LiveData<List<PlayerInfo>>
        get() = _properties

    private val _status = MutableLiveData<PlayerApiStatus>()
    val status: LiveData<PlayerApiStatus>
        get() = _status

    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(playerInfo: PlayerInfo) {
        _navigateToSelectedProperty.value = playerInfo
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
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
                _status.value = PlayerApiStatus.LOADING
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
                _status.value = PlayerApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = PlayerApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun updateFilter(filter: PlayerFilter){
        getPlayerProperties(filter)
    }
}