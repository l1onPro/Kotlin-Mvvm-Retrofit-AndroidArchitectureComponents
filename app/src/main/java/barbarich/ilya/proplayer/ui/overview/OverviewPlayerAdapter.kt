package barbarich.ilya.proplayer.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import barbarich.ilya.proplayer.databinding.ListOverviewItemBinding
import barbarich.ilya.proplayer.network.model.PlayerInfo

class OverviewPlayerAdapter(
    private val onUserClick: (Int) -> Unit
) : ListAdapter<PlayerInfo, OverviewPlayerAdapter.OverViewHolder>(PLAYER_COMPARATOR) {

    class OverViewHolder(private var binding: ListOverviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(playerInfo: PlayerInfo) {
            binding.player = playerInfo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewHolder =
        OverViewHolder(ListOverviewItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: OverViewHolder, position: Int) {
        val player = getItem(position)
        holder.itemView.setOnClickListener { onUserClick(position) }
        holder.bind(player)
    }

    companion object {
        private val PLAYER_COMPARATOR = object : DiffUtil.ItemCallback<PlayerInfo>() {
            override fun areItemsTheSame(oldItem: PlayerInfo, newItem: PlayerInfo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PlayerInfo, newItem: PlayerInfo): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}