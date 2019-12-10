package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import fr.appsolute.tp.R
import fr.appsolute.tp.ui.activity.MainActivity
import fr.appsolute.tp.ui.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_single_character.view.*

class SingleCharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private var characterID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            characterViewModel = ViewModelProvider(this, CharacterViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        characterID =
            arguments?.getInt(ARGS_KEY) ?: throw java.lang.IllegalStateException("No id found")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.getCharacterDetails(characterID) {
            (activity as? MainActivity)?.supportActionBar?.apply {
                this.title = it.name
            }
            view.apply {
                this.character_details_species.text = it.species
                this.character_details_location.text = it.location.name
                Glide.with(this)
                    .load(it.image)
                    .into(this.character_details_image_view)
            }
        }
    }


    companion object {
        const val ARGS_KEY = "KEY"
    }
}