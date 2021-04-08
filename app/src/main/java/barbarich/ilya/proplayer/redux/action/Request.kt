package barbarich.ilya.proplayer.redux.action

import org.rekotlin.Action

abstract class Request : Action {
    abstract fun execute()
}