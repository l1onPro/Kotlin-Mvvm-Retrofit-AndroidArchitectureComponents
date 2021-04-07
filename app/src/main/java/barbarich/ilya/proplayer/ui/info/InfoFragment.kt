package barbarich.ilya.proplayer.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import barbarich.ilya.proplayer.databinding.FragmentInfoPlayerBinding
import barbarich.ilya.proplayer.redux.state.AppState
import barbarich.ilya.proplayer.redux.store.store
import org.rekotlin.StoreSubscriber

class InfoFragment : Fragment(), StoreSubscriber<AppState> {
    private val viewModel: InfoPlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentInfoPlayerBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) { state -> state.select { it } }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    override fun newState(state: AppState) {
        viewModel.setSelectedPlayer(state.players.players[state.selectedPlayerId.id])
    }
}


