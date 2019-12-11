package fr.appsolute.tp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.database.EpisodeDao
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.EpisodeApi
import fr.appsolute.tp.data.networking.createApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


private class EpisodeRepositoryImpl(
    private val api: EpisodeApi,
    private val dao: EpisodeDao

) : EpisodeRepository {

    override suspend fun getEpisodes(): List<Episode>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getEpisodes()
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body()?.results?.also {
                    dao.insertAll(it)
                } ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    }

}

interface EpisodeRepository {

    suspend fun getEpisodes(): List<Episode>?


    companion object {
        /**
         * Singleton for the interface [EpisodeRepository]
         */
        val instance: EpisodeRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            EpisodeRepositoryImpl(
                HttpClientManager.instance.createApi(),
                DatabaseManager.getInstance().database.episodeDao
            )
        }

    }

}