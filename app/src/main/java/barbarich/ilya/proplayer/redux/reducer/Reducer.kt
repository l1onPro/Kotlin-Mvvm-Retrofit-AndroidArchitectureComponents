package barbarich.ilya.proplayer.redux.reducer

import barbarich.ilya.proplayer.redux.action.PlayerRequest
import barbarich.ilya.proplayer.redux.action.PlayersActions
import barbarich.ilya.proplayer.redux.state.AppState
import barbarich.ilya.proplayer.redux.state.PlayersState
import org.rekotlin.Action

fun appReducer(action: Action, state: AppState?): AppState {
    requireNotNull(state)
    return AppState(
        players = playerReducer(action, state)
    )
}

fun playerReducer(action: Action, state: AppState): PlayersState {

    var newState = state.players

    when(action) {
        is PlayerRequest.FetchPlayers -> {
            newState = newState.copy(
                status = PlayersState.Status.PENDING
            )
        }

        is PlayerRequest.FetchPlayers.Success -> {
            newState = newState.copy(
                status = PlayersState.Status.IDLE,
                players = action.players
            )
        }
        is PlayerRequest.FetchPlayers.Failure -> {
            newState = newState.copy(
                status = PlayersState.Status.IDLE
            )
        }
    }
    return newState
}