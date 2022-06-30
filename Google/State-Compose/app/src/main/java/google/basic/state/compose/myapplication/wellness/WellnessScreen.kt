package google.basic.state.compose.myapplication.wellness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import google.basic.state.compose.myapplication.state.StatefulCounter
import google.basic.state.compose.myapplication.watercount.WaterCounter
import google.basic.state.compose.myapplication.wellness.component.WellnessTaskList
import google.basic.state.compose.myapplication.wellness.model.WellnessTask
import google.basic.state.compose.myapplication.wellness.viewmodel.WellnessViewModel


@Composable
fun WellnessScreen(
    modifier : Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()){
//    WaterCounter(modifier)
    Column(modifier = modifier){
        StatefulCounter(modifier)


        WellnessTaskList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = {task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}


