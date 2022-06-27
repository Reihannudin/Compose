package udemy.boruto.compose.myapplication.domain.usecase.read_onboarding

import kotlinx.coroutines.flow.Flow
import udemy.boruto.compose.myapplication.data.repository.Repository

class ReadOnBoardingUsecase(
    private val repository : Repository
    ) {
    operator fun invoke() : Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}