package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import kotlinx.android.synthetic.main.fragment_character_list.view.*


class EpisodeListFragment: Fragment() {


    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            episodeViewModel = ViewModelProvider(this, EpisodeViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        // We need to inject the OnCharacterClickListener in the constructor of the adapter
        episodeAdapter = EpisodeAdapter(this)
        view.character_list_recycler_view.apply {
            adapter = EpisodeAdapter()
        }
        episodeViewModel.observe(this) {
            episodeAdapter.submitList(it)
        }
    }
}




class EpisodeAdapter{

}