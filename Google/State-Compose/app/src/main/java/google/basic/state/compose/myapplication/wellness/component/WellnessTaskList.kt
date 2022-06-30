package google.basic.state.compose.myapplication.wellness.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import google.basic.state.compose.myapplication.wellness.items.WellnessTaskItem
import google.basic.state.compose.myapplication.wellness.model.WellnessTask


@Composable
fun WellnessTaskList(
    modifier : Modifier = Modifier,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    list : List<WellnessTask>
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}


