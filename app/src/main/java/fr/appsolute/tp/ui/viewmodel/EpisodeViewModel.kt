package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.repository.CharacterRepository
import fr.appsolute.tp.data.repository.EpisodeRepository
import kotlinx.coroutines.launch


class EpisodeViewModel(private val episodeRepository: EpisodeRepository) : ViewModel() {


    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpisodeViewModel(EpisodeRepository.instance) as T
        }
    }


    fun getCharacterDetails(onSuccess: OnSuccess<List<Episode>>) {
        viewModelScope.launch {
            episodeRepository.getEpisodes()?.run(onSuccess)
        }
    }

}
