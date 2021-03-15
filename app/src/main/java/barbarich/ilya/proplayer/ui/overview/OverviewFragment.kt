package barbarich.ilya.proplayer.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import barbarich.ilya.proplayer.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.player = viewModel

        binding.playerList.adapter = OverviewPlayerAdapter { playerInfo ->
            viewModel.displayPropertyDetails(playerInfo)
            //parentFragmentManager.beginTransaction()
            //    .remove(this)
            //    .replace(android.R.id.content, InfoFragment())
            //    .commitAllowingStateLoss()
        }

        setHasOptionsMenu(true)

        return binding.root
    }
}