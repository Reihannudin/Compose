package udemy.boruto.compose.myapplication.domain.usecase.get_all_heroes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import udemy.boruto.compose.myapplication.data.repository.Repository
import udemy.boruto.compose.myapplication.domain.models.Hero

class GetAllHeroesUsecase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}