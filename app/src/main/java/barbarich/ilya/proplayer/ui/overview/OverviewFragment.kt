package barbarich.ilya.proplayer.ui.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import barbarich.ilya.proplayer.R
import barbarich.ilya.proplayer.databinding.FragmentOverviewBinding
import barbarich.ilya.proplayer.network.model.PlayerFilter

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.playerList.adapter = OverviewPlayerAdapter { playerInfo ->
            viewModel.displayPropertyDetails(playerInfo)
            if (playerInfo!=null){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToInfoFragment(playerInfo))
            }
        }

        setHasOptionsMenu(true)

        return binding.root
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