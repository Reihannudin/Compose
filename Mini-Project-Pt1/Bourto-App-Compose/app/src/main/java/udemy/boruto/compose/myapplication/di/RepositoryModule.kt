package udemy.boruto.compose.myapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import udemy.boruto.compose.myapplication.data.repository.DataStoreOperationsImpl
import udemy.boruto.compose.myapplication.data.repository.Repository
import udemy.boruto.compose.myapplication.domain.repository.DataStoreOperations
import udemy.boruto.compose.myapplication.domain.usecase.UseCases
import udemy.boruto.compose.myapplication.domain.usecase.get_all_heroes.GetAllHeroesUsecase
import udemy.boruto.compose.myapplication.domain.usecase.read_onboarding.ReadOnBoardingUsecase
import udemy.boruto.compose.myapplication.domain.usecase.save_onboarding.SaveOnBoardingUsecase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations{
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUsecase(repository),
            readOnBoardingUsecase = ReadOnBoardingUsecase(repository),
            getAllHeroesUsecase =  GetAllHeroesUsecase(repository)
        )
    }
}