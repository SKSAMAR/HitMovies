package com.samar.hitmovies.presentation.genres.component
/**
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.domain.model.Genre
import com.samar.hitmovies.ui.theme.HitMoviesTheme
import kotlin.random.Random


@Composable
fun GenresCardDesign(
    genre: Genre,
    clickable: (Genre) -> Unit
) {


    val colorGray = Color.Gray
    val colorWhite = Color.White
    val gradientGrayWhite = Brush.verticalGradient(0f to colorGray, 1000f to colorWhite)
    val constraintSet = ConstraintSet {
        val genreCard = createRefFor("genreCard")
        constrain(genreCard) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.ratio("1:1.3")
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        constraintSet = constraintSet
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(15),
            elevation = 18.dp,
            border = BorderStroke(width = 0.5.dp, color = Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientGrayWhite)
            ) {
                BasicAnimation(
                    animation = genreAnimList[(genreAnimList.indices).random()],
                    modifier = Modifier
                        .size(200.dp)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp),
                    text = genre.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 2
                )
            }
        }
    }
}

val genreAnimList = listOf(
    R.raw.loading,
    R.raw.genre_icon,
    R.raw.genre_icon_2,
    R.raw.genre_icon_3,
    R.raw.genre_icon_4,
    R.raw.genre_icon_5
)

@Preview(showBackground = true)
@Composable
fun GenresCardDesignPreview() {
    HitMoviesTheme {
//        GenresCardDesign()
    }
}
 **/