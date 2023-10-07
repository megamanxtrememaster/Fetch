package com.cebolledo.fetch.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cebolledo.fetch.database.entity.DataEntity
import com.cebolledo.fetch.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource


enum class State{
    LOADING,
    ERROR,
    RENDERING
}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flowObject = viewModel.getData()

        setContent {

            var stateApp by rememberSaveable{ mutableStateOf(State.RENDERING) }
            val state = flowObject.collectAsState(initial = null)

            val loading by remember{viewModel.isLoading()}
            val errorMsg by remember{viewModel.errorMessage()}

            if(loading)
                stateApp = State.LOADING
            else
                stateApp = State.RENDERING
            if(!errorMsg.isNullOrBlank())
                stateApp = State.ERROR

            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(stateApp) {
                        State.RENDERING -> {
                            LazyColumn()
                            {
                                for (element in state.value ?: listOf()) {
                                    item {
                                        RenderElement(element = element)
                                    }
                                }
                            }
                        }

                        State.ERROR -> {
                            Column(modifier = Modifier.fillMaxSize())
                            {
                                Text(text = stringResource(id = com.cebolledo.fetch.R.string.error_msg, errorMsg?:""), color = MaterialTheme.colorScheme.primary)
                            }
                        }
                        State.LOADING -> {
                            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                            {
                                Text(text = stringResource(id = com.cebolledo.fetch.R.string.loading), modifier = Modifier.padding(all = 8.dp))
                                LinearProgressIndicator(modifier = Modifier
                                    .width(100.dp)
                                    .background(Color.Cyan),
                                color = Color.LightGray,
                                trackColor = Color.DarkGray,

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
fun RenderElement(element:DataEntity) {
    Row(modifier = Modifier
        .fillMaxWidth())
    {
        Text(text = "Id: ${element.id}", modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f), color = MaterialTheme.colorScheme.primary)
        Text(text = "List Id: ${element.listId}", modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(2f), color = MaterialTheme.colorScheme.secondary)
        Text(text = "Name: ${element.name?:""}", modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(4f), color = MaterialTheme.colorScheme.secondary)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val test = DataEntity(id = 4, listId = 0, name="element name")
    RenderElement(test)

}