package udemy.boruto.compose.myapplication.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import udemy.boruto.compose.myapplication.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase : UseCases
) : ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted : StateFlow<Boolean> = _onBoardingCompleted

    init{
        viewModelScope.launch(IO) {
            _onBoardingCompleted.value = useCase.readOnBoardingUsecase().stateIn(viewModelScope).value
        }
    }
}