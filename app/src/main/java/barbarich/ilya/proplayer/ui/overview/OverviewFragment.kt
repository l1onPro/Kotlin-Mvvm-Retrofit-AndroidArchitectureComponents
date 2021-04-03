package barbarich.ilya.proplayer.ui.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import barbarich.ilya.proplayer.R
import barbarich.ilya.proplayer.databinding.FragmentOverviewBinding
import barbarich.ilya.proplayer.network.model.PlayerFilter
import barbarich.ilya.proplayer.redux.action.PlayerRequest
import barbarich.ilya.proplayer.redux.state.PlayersState
import barbarich.ilya.proplayer.redux.store.store
import org.rekotlin.StoreSubscriber

class OverviewFragment : Fragment(), StoreSubscriber<PlayersState> {

    private val viewModel: OverviewViewModel by viewModels ()

    private lateinit var adapter: OverviewPlayerAdapter
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        adapter = OverviewPlayerAdapter { playerInfo ->
            viewModel.displayPropertyDetails(playerInfo)
            if (playerInfo!=null){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToInfoFragment(playerInfo))
            }
        }

        binding.playerList.adapter = adapter

        store.dispatch(PlayerRequest.FetchPlayers())

        //setHasOptionsMenu(true)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {state -> state.select {it.players} }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    override fun newState(state: PlayersState) {
        adapter.submitList(state.players)
        binding.state = state.status
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.sort_by_rating -> PlayerFilter.SORT_BY_RATING
                R.id.sort_by_rating_1_0 -> PlayerFilter.SORT_BY_RATING_1_0
                else -> PlayerFilter.SORT_BY_NAME
            }
        )
        return true
    }
}