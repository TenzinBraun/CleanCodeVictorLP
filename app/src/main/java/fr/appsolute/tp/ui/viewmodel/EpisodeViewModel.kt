package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.repository.CharacterRepository
import fr.appsolute.tp.data.repository.EpisodeRepository


class EpisodeViewModel(private val episodeRepository: EpisodeRepository) : ViewModel() {


    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpisodeViewModel(EpisodeRepository.instance) as T
        }
    }

}