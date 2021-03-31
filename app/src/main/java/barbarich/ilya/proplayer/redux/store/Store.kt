package barbarich.ilya.proplayer.redux.store

import barbarich.ilya.proplayer.redux.middleware.appMiddleware
import barbarich.ilya.proplayer.redux.reducer.appReducer
import barbarich.ilya.proplayer.redux.state.AppState
import org.rekotlin.Store

val store = Store (
    reducer = ::appReducer,
    state = AppState(),
    middleware = listOf(appMiddleware)
)