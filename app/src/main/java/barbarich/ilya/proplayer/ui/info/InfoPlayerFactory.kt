package barbarich.ilya.proplayer.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import barbarich.ilya.proplayer.network.model.PlayerInfo

class InfoPlayerFactory(private val playerInfo: PlayerInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoPlayerViewModel::class.java)) {
            return InfoPlayerViewModel(playerInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}