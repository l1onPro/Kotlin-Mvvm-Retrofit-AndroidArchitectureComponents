package barbarich.ilya.proplayer.redux.reducer

import barbarich.ilya.proplayer.redux.action.PlayerRequest
import barbarich.ilya.proplayer.redux.action.SelectedPlayerRequest
import barbarich.ilya.proplayer.redux.state.AppState
import barbarich.ilya.proplayer.redux.state.PlayersState
import barbarich.ilya.proplayer.redux.state.SelectPlayerState
import org.rekotlin.Action

fun appReducer(action: Action, state: AppState?): AppState {
    requireNotNull(state)

    return AppState(
            players = playerReducer(action, state),
            selectedPlayerId = selectPlayerReducer(action, state)
    )
}

fun playerReducer(action: Action, state: AppState): PlayersState {

    var newState = state.players

    when(action) {
        is PlayerRequest.FetchPlayers -> {
            newState = newState.copy(
                status = PlayersState.Status.LOADING
            )
        }

        is PlayerRequest.FetchPlayers.Success -> {
            newState = newState.copy(
                status = PlayersState.Status.DONE,
                players = action.players
            )
        }
        is PlayerRequest.FetchPlayers.Failure -> {
            newState = newState.copy(
                status = PlayersState.Status.ERROR
            )
        }
    }
    return newState
}

fun selectPlayerReducer(action: Action, state: AppState) : SelectPlayerState {

    var newState = state.selectedPlayerId

    when(action) {
        is SelectedPlayerRequest.SelectPlayer.Success -> {
            newState = newState.copy(
                    id = action.playerId
            )
        }
        is SelectedPlayerRequest.SelectPlayer.Failure -> {

        }
    }
    return  newState
}