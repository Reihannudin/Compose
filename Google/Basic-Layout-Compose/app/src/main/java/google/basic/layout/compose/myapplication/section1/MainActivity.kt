package google.basic.layout.compose.myapplication.section1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import google.basic.layout.compose.myapplication.section1.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                LayoutCodeLabs()
            }
        }
    }
}


@Composable
fun PhotographerCard(modifier : Modifier = Modifier){
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .clickable(onClick = {})
            .padding(16.dp)){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically)
        ){
            Text("Andrian Raihannudin", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minute ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

//make reCompose function
@Composable
fun LayoutCodeLabs(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text="LayoutsCodeLabs")
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                       Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
      BodyContent(
          Modifier
              .padding(innerPadding)
              .padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier : Modifier = Modifier){
    Column (
        modifier = modifier
    ){
        Text(text="Hi There!!")
        Text(text="Thanks for going through the Layouts codelab")
    }
}

@Composable
fun SimpleList(){
//    Right Way
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        items(100){
            Text("Item #$it")
        }
    }
}

@Composable
fun ImageListItem(index : Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}
@Composable
fun ImageList(){
    val scrollStateImg = rememberLazyListState()
    LazyColumn(state = scrollStateImg){
        items(100){
            ImageListItem(index = it)
        }
    }
}


@Composable
fun ScrollingList(){
    val listSize = 100
    val scrollStateList = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column{
        Row{
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollStateList.animateScrollToItem(0)
                    }
                }) {
                Text(text = "Scroll to the Top")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollStateList.animateScrollToItem(100)
                    }
                }
            ){
                Text(text = "Scrool to the Bottom")
            }
        }
        LazyColumn(state = scrollStateList){
            items(listSize){
                ImageListItem(index = it)
            }
        }
    }
}
/*
make top bar
TopAppBar(
    title = {
        Text(text = "Page title", maxLines = 2)
    },
    navigationIcon = {
        Icon(myNavIcon)
    }
)
* */



//@Composable
//@Preview(showBackground = true)
//fun PhotographerCardPreview(){
//    MaterialTheme{
//        PhotographerCard()
//    }
//}
//
//
//@Composable
//@Preview
//fun LayoutCodelabsPreview(){
//    MaterialTheme{
//        LayoutCodeLabs()
//    }
//}

@Composable
@Preview(showBackground = true)
fun SimpleListPreview(){
    MaterialTheme{
        SimpleList()
    }
}

@Composable
@Preview(showBackground = true)
fun ImageListPreview(){
    MaterialTheme{
        ScrollingList()
    }
}