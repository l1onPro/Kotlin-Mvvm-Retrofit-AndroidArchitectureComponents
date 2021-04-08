package barbarich.ilya.proplayer.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barbarich.ilya.proplayer.network.model.PlayerFilter
import barbarich.ilya.proplayer.network.model.PlayerInfo
import barbarich.ilya.proplayer.redux.action.PlayerRequest
import barbarich.ilya.proplayer.redux.action.SelectedPlayerRequest
import barbarich.ilya.proplayer.redux.state.PlayersState
import barbarich.ilya.proplayer.redux.store.store
import org.rekotlin.StoreSubscriber

class OverviewViewModel : ViewModel(), StoreSubscriber<PlayersState> {
    private val _allPlayers = MutableLiveData<List<PlayerInfo>>()
    val allPlayers: LiveData<List<PlayerInfo>>
        get() = _allPlayers

    private val _statusLoading = MutableLiveData<PlayersState.Status>()
    val statusLoading: LiveData<PlayersState.Status>
        get() = _statusLoading

    init {
        store.subscribe(this) { state -> state.select {it.players} }
        store.dispatch(PlayerRequest.FetchPlayers())
    }

    override fun onCleared() {
        super.onCleared()
        store.unsubscribe(this)
    }

    override fun newState(state: PlayersState) {
        _allPlayers.value = state.players
        _statusLoading.value = state.status
    }

    fun updateIdSelectPlayer(id: Int) {
        store.dispatch(SelectedPlayerRequest.SelectPlayer(id))
    }

    fun updateFilter(filter: PlayerFilter) {
        store.dispatch(PlayerRequest.FetchPlayers(filter))
    }
}