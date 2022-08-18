package com.samar.hitmovies.presentation.movies.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.ui.theme.HitMoviesTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesCardDesign(
    movie: MovieDetailDto,
) {
    var expandableState by remember { mutableStateOf(false) }
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
            height = if (expandableState){
                Dimension.ratio("1:2")
            }else{
                Dimension.ratio("1:1.4")
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        constraintSet = constraintSet
    ) {
        Card(
            onClick = {
                expandableState = !expandableState
                //clickable.invoke(movie)
            },
            modifier = Modifier
                .fillMaxSize()
                .layoutId("genreCard")
                .animateContentSize( // Animation
                    animationSpec = tween(
                        durationMillis = 400, // Animation Speed
                        easing = LinearOutSlowInEasing // Animation Type
                    )
                ),
            shape = RoundedCornerShape(5),
            elevation = 18.dp,
            border = BorderStroke(width = 0.5.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(gradientGrayWhite)
                ) {

                    if (movie.primaryImage?.url != null) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                model = movie.primaryImage.url,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                loading = {
                                    BasicAnimation()
                                }

                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 2.dp)
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = movie.titleText.text,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        shadow = Shadow(color = Color.White)
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                /**
                                if (movie.releaseYear?.year != null) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = movie.releaseYear.year.toString(),
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            shadow = Shadow(color = Color.White)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }**/
                            }

                        }

                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                                contentScale = ContentScale.FillBounds,
                                painter = painterResource(id = R.drawable.clapperboard),
                                contentDescription = "no image"
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 2.dp)
                            ) {

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = movie.titleText.text,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        shadow = Shadow(color = Color.White)
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                /**
                                if (movie.releaseYear?.year != null) {
                                    var endYear = ""
                                    movie.releaseYear.endYear?.let {
                                        endYear = " - ${movie.releaseYear.endYear}"
                                    }
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = movie.releaseYear.year.toString() + endYear,
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            shadow = Shadow(color = Color.White)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                **/
                            }
                        }
                    }
                }
                if (expandableState) {
                    movie.primaryImage?.caption?.plainText?.let { capation ->
                        Text(
                            text = capation,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 5.dp),
                            style = MaterialTheme.typography.overline,
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GenresCardDesignPreview() {
    HitMoviesTheme {
//        GenresCardDesign()
    }
}