package udemy.boruto.compose.myapplication.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import udemy.boruto.compose.myapplication.ui.theme.*


@Composable
fun ShimmerEffect(){
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(count = 5){
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem(){
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha : Float){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEMS_HEIGHT),
        color = if(isSystemInDarkTheme())
            Color.Black else ShimmeerLightGrey,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column (
           modifier = Modifier
               .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ){
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .alpha(alpha = alpha)
                    .height(NAME_PLACEHOLDER_HEIGHT),
                color = if(isSystemInDarkTheme())
                    ShimmerDarkGrey else ShimmerMediumGrey,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ){}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(3){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha = alpha)
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if(isSystemInDarkTheme())
                        ShimmerDarkGrey else ShimmerMediumGrey,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ){}
                Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            }
            Row(modifier = Modifier.fillMaxWidth()){
                repeat(5){
                    Surface(
                        modifier = Modifier
                            .alpha(alpha = alpha)
                            .size(RATING_PLACEHOLDER_HEIGHT),
                        color = if(isSystemInDarkTheme())
                            ShimmerDarkGrey else ShimmerMediumGrey,
                        shape = RoundedCornerShape(size = EXTRA_SMALL_PADDING)
                    ){}
                    Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
                }
            }
        }
    }
}


@Composable
@Preview
fun ShimmerEffectPreview(){
    AnimatedShimmerItem()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerEffectDarkPreview(){
    AnimatedShimmerItem()
}