package com.samar.hitmovies.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalToolbarApi::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val movie = viewModel.movieDetailDto
    val toolbarState = rememberCollapsingToolbarScaffoldState()
    Column {
        movie?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (movie.primaryImage?.url != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 200f
                                )
                            )
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .fillMaxSize(),
                            model = movie.primaryImage.url,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            loading = {
                                BasicAnimation()
                            }
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
                        ) {
                            MoreContent(movie = movie)
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = movie.titleText.text,
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 28.sp,
                                    shadow = Shadow(color = Color.White)
                                ),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                    }

                } else {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black
                                        ),
                                        startY = 400f
                                    )
                                )
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds,
                                painter = painterResource(id = R.drawable.clapperboard),
                                contentDescription = "no image"
                            )

                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(10.dp)
                            ) {
                                MoreContent(movie = movie)
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = movie.titleText.text,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 28.sp,
                                        shadow = Shadow(color = Color.White)
                                    ),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoreContent(movie: MovieDetailDto?){
    Column{
        movie?.releaseYear?.let { releaseYear ->
            Row(
                modifier = Modifier
                    .padding(start = 18.dp, end = 18.dp, top = 9.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                releaseYear.year?.let {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RectangleShape
                            )
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Started",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.overline
                        )
                        Text(
                            text = releaseYear.year.toString(),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                releaseYear.endYear?.let {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RectangleShape
                            )
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ended",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.overline
                        )
                        Text(
                            text = releaseYear.endYear.toString(),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
        movie?.let { releaseYear ->
            Row(
                modifier = Modifier
                    .padding(start = 18.dp, end = 18.dp, top = 9.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                releaseYear.titleType?.text?.let {titleType->
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RectangleShape
                            )
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Content Type",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.overline
                        )
                        Text(
                            text = titleType,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                releaseYear.releaseDate?.let {releaseDate->
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RectangleShape
                            )
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Release Date (D-M-Y)",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.overline
                        )
                        Text(
                            text = "${releaseDate.day}-${releaseDate.month}-${releaseDate.year}",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}