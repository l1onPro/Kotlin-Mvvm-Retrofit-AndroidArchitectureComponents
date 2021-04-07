package barbarich.ilya.proplayer.redux.action

import android.util.Log
import barbarich.ilya.proplayer.redux.store.store
import org.rekotlin.Action

class SelectedPlayerRequest {

    class SelectPlayer(val selectIdPlayer: Int) : Request() {
        override fun execute() {
            if (selectIdPlayer >= 0 && selectIdPlayer < store.state.players.players.size) {
                Log.d("Test", "playerId = ${selectIdPlayer}")
                store.dispatch(Success(selectIdPlayer))
            }
            else {
                Log.d("Test", "Failure = Unknown player id")
                store.dispatch(Failure(Throwable("Unknown player id")))
            }

        }

        data class Success(val playerId: Int) : Action

        data class Failure(val t: Throwable) : Action
    }
}