package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import fr.appsolute.tp.ui.widget.holder.CharacterViewHolder
import kotlinx.android.synthetic.main.fragment_episode_list.view.*
import kotlinx.android.synthetic.main.holder_episode.view.*


class EpisodeListFragment : Fragment(), OnEpisodeClickListener {

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
        return inflater.inflate(R.layout.fragment_episode_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        // We need to inject the OnCharacterClickListener in the constructor of the adapter
        episodeAdapter = EpisodeAdapter(this)
        view.episodeRecyclerView.apply {
            adapter = episodeAdapter
        }
        episodeViewModel.getCharacterDetails {
            episodeAdapter.submitList(it)
        }
    }

    override fun invoke(view: View, episode: Episode) {

    }
}


class EpisodeAdapter(
    private val onEpisodeClickListener: OnEpisodeClickListener
) : RecyclerView.Adapter<EpisodeViewHolder>() {

    private var episodes: List<Episode> = emptyList()

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position], onEpisodeClickListener)
    }

    companion object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    fun submitList(eps: List<Episode>) {
        episodes = eps
    }
}

typealias OnEpisodeClickListener = (view: View, episode: Episode) -> Unit

class EpisodeViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: Episode, onClick: OnEpisodeClickListener) {
        itemView.apply {
            this.setOnClickListener { onClick(it, model) }
            this.holder_episode_name.text = model.name
            this.holder_episode_episode.text = model.episode
        }
    }

    companion object {
        /**
         * Create a new Instance of [CharacterViewHolder]
         */
        fun create(parent: ViewGroup): EpisodeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_episode,
                parent,
                false
            )
            return EpisodeViewHolder(view)
        }
    }

}