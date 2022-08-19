package com.samar.hitmovies.presentation.movies.component

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.ui.theme.HitMoviesTheme
import javax.annotation.Untainted


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesCardDesign(
    movie: MovieDetailDto,
    action:()->Unit,
    isDislike: Boolean = false
) {
    val configuration = LocalConfiguration.current
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
            height = if (expandableState) {
                var ratio = "1.4:1"
                when (configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> {
                        ratio = "1:1.5"
                    }
                    else -> {
                        ratio = "1.4:1"
                    }
                }
                Dimension.ratio(ratio)
            } else {
                Dimension.ratio("1:1")
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
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


                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        val noImage =
                            "https://cdn11.bigcommerce.com/s-y76tsfzldy/images/stencil/original/products/7720/20309/360_F_400243185_BOxON3h9avMUX10RsDkt3pJ8iQx72kS3__32888.1644948713.jpg"

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(movie.primaryImage?.url ?: noImage)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.clapperboard),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RectangleShape)
                                .fillMaxWidth()
                                .weight(1f)
                        )

//                        AsyncImage(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .weight(1f),
//                            model = movie.primaryImage?.url ?: noImage,
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                        )
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
                        }

                    }

                    IconButton(
                        modifier = Modifier.align(Alignment.TopEnd),
                        onClick = {
                            action.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = if(isDislike) Icons.Default.Delete else Icons.Default.ThumbUp,
                            contentDescription = "like_dislike",
                            tint = if(isDislike) Color.Red else MaterialTheme.colors.primary
                        )
                    }

                }
                if (expandableState) {
                    movie.primaryImage?.caption?.plainText?.let { capation ->
                        Text(
                            text = capation,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
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