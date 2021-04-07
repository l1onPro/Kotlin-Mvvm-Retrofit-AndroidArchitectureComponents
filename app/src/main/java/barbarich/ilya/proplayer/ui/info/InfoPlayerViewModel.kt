package barbarich.ilya.proplayer.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.model.PlayerInfo
import barbarich.ilya.proplayer.redux.state.AppState
import barbarich.ilya.proplayer.redux.store.store
import org.rekotlin.StoreSubscriber

class InfoPlayerViewModel: ViewModel(), StoreSubscriber<AppState> {
    private var _selectedPlayer = MutableLiveData<PlayerInfo>()
    val selectedPlayer: LiveData<PlayerInfo>
        get() = _selectedPlayer

    init {
        store.subscribe(this) { state -> state.select { it } }
    }

    override fun onCleared() {
        super.onCleared()
        store.unsubscribe(this)
    }

    override fun newState(state: AppState) {
        val id = state.selectedPlayerId.id
        _selectedPlayer.value = state.players.players[id]
    }
}