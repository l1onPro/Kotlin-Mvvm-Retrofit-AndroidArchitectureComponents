package barbarich.ilya.proplayer.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.model.PlayerInfo

class InfoPlayerViewModel: ViewModel() {
    private var _selectedPlayer = MutableLiveData<PlayerInfo>()
    val selectedPlayer: LiveData<PlayerInfo>
        get() = _selectedPlayer

    fun setSelectedPlayer(playerInfo: PlayerInfo) {
        playerInfo.let {
            _selectedPlayer.value = playerInfo
        }
    }
}