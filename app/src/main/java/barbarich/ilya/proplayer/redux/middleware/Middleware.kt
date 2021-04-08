package barbarich.ilya.proplayer.redux.middleware

import barbarich.ilya.proplayer.redux.action.Request
import org.rekotlin.Middleware
import org.rekotlin.StateType

val appMiddleware: Middleware<StateType> = { _, _ ->
    { next ->
        { action ->
            (action as? Request)?.execute()
            next(action)
        }
    }
}