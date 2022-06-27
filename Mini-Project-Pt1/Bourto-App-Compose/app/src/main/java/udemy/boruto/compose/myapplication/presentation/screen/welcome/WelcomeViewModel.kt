package udemy.boruto.compose.myapplication.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import udemy.boruto.compose.myapplication.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val useCase : UseCases) : ViewModel() {

    fun saveOnBoardingState(completed : Boolean){
        viewModelScope.launch(IO) {
            useCase.saveOnBoardingUseCase(completed = completed)
        }
    }
}