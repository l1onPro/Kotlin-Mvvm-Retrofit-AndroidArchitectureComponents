package barbarich.ilya.proplayer.redux.state

import barbarich.ilya.proplayer.network.model.PlayerInfo
import org.rekotlin.StateType

data class AppState(
    val players: PlayersState = PlayersState()
) : StateType

data class PlayersState (
    val status: Status = Status.IDLE,
    val players: List<PlayerInfo> = listOf()
) : StateType {
    enum class Status {IDLE, PENDING}
}
