package udemy.boruto.compose.myapplication.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import udemy.boruto.compose.myapplication.data.local.database.HeroDatabase
import udemy.boruto.compose.myapplication.data.remote.BorutoApi
import udemy.boruto.compose.myapplication.data.repository.RemoteDataSourceImpl
import udemy.boruto.compose.myapplication.domain.repository.RemoteDataSource
import udemy.boruto.compose.myapplication.utils.Constraint.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit{
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideBorutoApi(retrofit : Retrofit) : BorutoApi{
        return retrofit.create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        heroDatabase: HeroDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(
            borutoApi =  borutoApi,
            heroDatabase = heroDatabase
        )
    }
}