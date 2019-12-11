package fr.appsolute.tp.data.networking.api


import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.model.PaginatedResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface EpisodeApi {
    @GET(GET_ALL_EPISODE_PATH)
    suspend fun getEpisodes(
    ): Response<PaginatedResult<Episode>>
    @GET(GET_SOME_EPISODE_PATH)
    suspend fun getEpisodesList(
        @Path("list-episode") list_episode: Int
    ): Response<Episode>
    companion object {
        const val GET_ALL_EPISODE_PATH = "episode/"
        const val GET_SOME_EPISODE_PATH = "episode/{list-episode}"
    }
}