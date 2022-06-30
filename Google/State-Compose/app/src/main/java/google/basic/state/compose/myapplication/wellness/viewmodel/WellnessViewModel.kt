package google.basic.state.compose.myapplication.wellness.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import google.basic.state.compose.myapplication.wellness.model.WellnessTask


class WellnessViewModel  : ViewModel(){
    private val _tasks = getWellnessTask().toMutableStateList()
    val tasks : List<WellnessTask>
        get() = _tasks

    fun remove(item : WellnessTask){
        _tasks.remove(item)
    }
    fun changeTaskChecked(item: WellnessTask, checked : Boolean){
        tasks.find {
            it.id == item.id
        }?.let { task ->
            task.checked = checked
        }
    }
}

private fun getWellnessTask() = List(30) { i -> WellnessTask(i, "Task # $i") }
