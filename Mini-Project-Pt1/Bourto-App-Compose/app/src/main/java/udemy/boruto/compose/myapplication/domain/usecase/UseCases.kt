package udemy.boruto.compose.myapplication.domain.usecase

import udemy.boruto.compose.myapplication.domain.usecase.get_all_heroes.GetAllHeroesUsecase
import udemy.boruto.compose.myapplication.domain.usecase.read_onboarding.ReadOnBoardingUsecase
import udemy.boruto.compose.myapplication.domain.usecase.save_onboarding.SaveOnBoardingUsecase

data class UseCases(
    val saveOnBoardingUseCase : SaveOnBoardingUsecase,
    val readOnBoardingUsecase: ReadOnBoardingUsecase,
    val getAllHeroesUsecase : GetAllHeroesUsecase,
)