package barbarich.ilya.proplayer.extention

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import barbarich.ilya.proplayer.R
import barbarich.ilya.proplayer.redux.state.PlayersState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions().override(100)
                    .placeholder(R.drawable.loading_anim)
                    .error(R.drawable.ic_baseline_error_24)
            )
            .into(imgView)
    }
}

@BindingAdapter("status")
fun bindStatus(progressBar: ProgressBar, playerStateStatus: PlayersState.Status? ){
    when(playerStateStatus) {
        PlayersState.Status.LOADING -> progressBar.visibility = View.VISIBLE
        PlayersState.Status.DONE -> progressBar.visibility = View.GONE
        PlayersState.Status.ERROR -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("errorImage")
fun bindErrorImage(statusErrorImage: ImageView, playerApiStatus: PlayersState.Status?){
    when(playerApiStatus){
        PlayersState.Status.ERROR -> {
            statusErrorImage.visibility = View.VISIBLE
            statusErrorImage.setImageResource(R.drawable.ic_baseline_error_24)
        }
    }
}