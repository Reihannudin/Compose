package udemy.boruto.compose.myapplication.domain.usecase.save_onboarding

import udemy.boruto.compose.myapplication.data.repository.Repository

class SaveOnBoardingUsecase(
    private val repository: Repository
    ) {
    suspend operator fun invoke(completed : Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}