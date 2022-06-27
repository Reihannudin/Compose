package udemy.boruto.compose.myapplication.presentation.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import udemy.boruto.compose.myapplication.domain.usecase.UseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase :UseCases
):ViewModel() {

    val getAllHeroes = useCase.getAllHeroesUsecase()

}