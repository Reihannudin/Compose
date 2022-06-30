package google.basic.state.compose.myapplication.watercount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import google.basic.state.compose.myapplication.wellness.items.WellnessTaskItems

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}



/*

@Composable
fun WaterCounter(){
    Column(modifier = Modifier.padding(16.dp)){
        var count by rememberSaveable { mutableStateOf(0)}
        if(count > 0){
            var showTask by rememberSaveable { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItems(
                    onClose = { showTask = false},
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses.")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Button(
                onClick = { count++ },
                modifier = Modifier.padding(top = 8.dp),
                enabled = count < 10
            ){
                Text("Add one")
            }
            Button(
                onClick = { count-- },
                modifier = Modifier.padding(top = 8.dp),
                enabled = count > 0
            ){
                Text("Min one")
            }
        }
        Button(
            onClick = {count = 0}, Modifier.padding(start = 8.dp)
        ){
            Text(text = "Clear water count")
        }
    }
}*/
