package com.samar.hitmovies.common

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samar.hitmovies.ui.theme.IconColor
import com.samar.hitmovies.util.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseComponentAct : ComponentActivity() {

    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this@BaseComponentAct)
    }


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    open fun BaseScaffold(
        title: String = "",
        navigationIcon: Boolean = false,
        color: Color = IconColor,
        floatingActionButton: @Composable () -> Unit = {},
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        isFloatingActionButtonDocked: Boolean = false,
        action: () -> Unit = {},
        content: @Composable BoxScope.() -> Unit
    ) {
        val isNetworkAvailable = connectionLiveData.observeAsState(true)
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = title)
                    },
                    navigationIcon = {
                        if (navigationIcon) {
                            IconButton(onClick = {
                                action()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "navigation"
                                )
                            }
                        }
                    },
                    backgroundColor = color,
                    contentColor = Color.White
                )
            },
            scaffoldState = scaffoldState,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedVisibility(
                    visible = !isNetworkAvailable.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.05f)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        backgroundColor = Color.White,
                        elevation = 3.dp,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "No Internet Available",
                                color = Color.Red,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    content = content
                )
            }
        }


    }

}