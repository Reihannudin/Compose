package google.basic.state.compose.myapplication.wellness.model

data class WellnessTask(
    val id : Int,
    val label : String,
    var checked: Boolean = false
)
