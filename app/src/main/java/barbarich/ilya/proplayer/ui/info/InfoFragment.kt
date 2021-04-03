package barbarich.ilya.proplayer.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import barbarich.ilya.proplayer.databinding.FragmentInfoPlayerBinding

class InfoFragment : Fragment() {
    private val viewModel: InfoPlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentInfoPlayerBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val infoPlayer = InfoFragmentArgs.fromBundle(requireArguments()).selectedPlayer
        viewModel.setSelectedPlayer(infoPlayer)

        binding.viewModel = viewModel

        return binding.root
    }
}


