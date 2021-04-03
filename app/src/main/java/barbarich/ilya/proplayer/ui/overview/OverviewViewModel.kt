package barbarich.ilya.proplayer.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.model.PlayerFilter
import barbarich.ilya.proplayer.network.model.PlayerInfo

class OverviewViewModel : ViewModel() {

    private val _selectedProperty = MutableLiveData<PlayerInfo>()
    val selectedProperty: LiveData<PlayerInfo>
        get() = _selectedProperty

    private val _allPlayers = MutableLiveData<List<PlayerInfo>>()
    val allPlayers: LiveData<List<PlayerInfo>>
        get() = _allPlayers

    fun displayPropertyDetails(playerInfo: PlayerInfo) {
        _selectedProperty.value = playerInfo
    }

    fun updateFilter(filter: PlayerFilter){
        //getPlayerProperties(filter)
    }
}