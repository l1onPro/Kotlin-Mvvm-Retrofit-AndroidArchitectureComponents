package barbarich.ilya.proplayer.network

import barbarich.ilya.proplayer.network.model.PlayerInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerService {

    @GET("pro.json")
    suspend fun getDataFromApi(@Query("filter") type: String): Deferred<List<PlayerInfo>>
}

object PlayerApi {
    val retrofitService: PlayerService by lazy { RetroInstance.getRetroInstance().create(PlayerService::class.java) }
}