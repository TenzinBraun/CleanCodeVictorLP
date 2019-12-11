package fr.appsolute.tp.data.repository

import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.database.EpisodeDao
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.EpisodeApi
import fr.appsolute.tp.data.networking.createApi


private class EpisodeRepositoryImpl(
    private val api: EpisodeApi,
    private val dao: EpisodeDao

) : EpisodeRepository{

}


interface EpisodeRepository {

    companion object {
        fun newInstance(application: RickAndMortyApplication): EpisodeRepository {
            return EpisodeRepositoryImpl(HttpClientManager.instance.createApi(), DatabaseManager.newInstance(application).database.episodeDao)
        }
    }

}