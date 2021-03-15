package barbarich.ilya.proplayer.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.model.PlayerInfo

class InfoPlayerViewModel(playerInfo: PlayerInfo ) : ViewModel() {
    private val _selectedPlayer = MutableLiveData<PlayerInfo>()
    val selectedPlayer: LiveData<PlayerInfo>
        get() = _selectedPlayer

    init {
        _selectedPlayer.value = playerInfo
    }
}