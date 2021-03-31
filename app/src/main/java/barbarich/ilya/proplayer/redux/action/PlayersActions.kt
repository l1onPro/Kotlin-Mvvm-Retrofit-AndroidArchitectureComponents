package barbarich.ilya.proplayer.redux.action

import barbarich.ilya.proplayer.network.model.PlayerInfo
import org.rekotlin.Action

class PlayersActions {

    data class PLayersLoaded(val players: List<PlayerInfo>) : Action

}