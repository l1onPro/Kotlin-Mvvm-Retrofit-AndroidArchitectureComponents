package barbarich.ilya.proplayer.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import barbarich.ilya.proplayer.databinding.FragmentInfoPlayerBinding

class InfoFragment : Fragment() {
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentInfoPlayerBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val infoPlayer = InfoFragmentArgs.fromBundle(arguments!!).selectedPlayer
        val viewModelFactory = InfoPlayerFactory(infoPlayer)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(InfoPlayerViewModel::class.java)
        return binding.root
    }
}