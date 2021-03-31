package barbarich.ilya.proplayer.redux.reducer

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
        is PlayersActions.PLayersLoaded -> {
            newState = newState.copy(
                players = action.players
            )
        }
    }
    return newState
}