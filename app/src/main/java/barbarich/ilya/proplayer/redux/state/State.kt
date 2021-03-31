package barbarich.ilya.proplayer.redux.state

import barbarich.ilya.proplayer.network.model.PlayerInfo

data class AppState(
    val players: PlayersState = PlayersState()
)

data class PlayersState (
    val players: List<PlayerInfo> = listOf()
)