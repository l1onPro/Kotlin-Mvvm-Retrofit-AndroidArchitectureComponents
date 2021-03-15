package barbarich.ilya.proplayer.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerInfo (
    var id: String,
    var nick_name: String,
    var fullname: String,
    var age: Int,
    val image_small_avatar: String,
    val image_big: String,
    var rating_1_0: Float,
    var rating_2_0: Float,
    var dpr: Float,
    var kast: Float,
    var impact: Float,
    var adr: Float,
    var kdp: Float,
    var rating: Float,
    var description: String
) :  Parcelable