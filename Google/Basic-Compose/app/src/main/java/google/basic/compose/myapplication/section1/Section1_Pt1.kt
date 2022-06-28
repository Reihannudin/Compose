package google.basic.compose.myapplication.section1

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import google.basic.compose.myapplication.R
import google.basic.compose.myapplication.section1.data.SampleDataMessage
import google.basic.compose.myapplication.section1.ui.theme.MyApplicationTheme

class Section1_Pt1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Text("Hello World")
//            SayHello(name = "Reihannudin")

            MyApplicationTheme{
                MessageCard(Message("Reihan", "Jetpack Compose"))
            }
        }
    }
}

//make function
@Composable
fun SayHello(name : String){
    Text(text = "Hello $name")
}

data class Message(
    val author : String, 
    val body : String
    )

@Composable
fun MessageCard(msg : Message){
    Row (modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false)}
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ){
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

//make recyclerview in Compose
@Composable
fun Conversation(message : List<Message>){
    LazyColumn{
        items(message) { message ->
            MessageCard(msg = message)
        }
    }
}

/*===================================================================================*/


//
//
//@Composable
//@Preview(showBackground = true)
//fun PreviewSayHello(){
//    SayHello(name = "Jungkook")
//}

@Preview(name = "Light Mode", showBackground = true)
@Composable
@Preview(name = "Dark Mode",  uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun PreviewMessageCard(){
    MyApplicationTheme{
        MessageCard(
            msg = Message("Reihan", "Jetpack Compose")
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewConversation(){
    MyApplicationTheme {
        Conversation(message = SampleDataMessage.conversationSample)
    }
}

