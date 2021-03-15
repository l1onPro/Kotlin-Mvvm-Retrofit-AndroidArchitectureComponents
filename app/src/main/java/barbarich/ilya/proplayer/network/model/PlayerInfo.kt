package barbarich.ilya.proplayer.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerInfo (
    var id: String,
    var nickName: String,
    var fullName: String,
    var age: Int,
    val urlImageSmallAvatar: String,
    val urlImageBig: String,
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