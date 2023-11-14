package com.heydieandroid.myfirstcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heydieandroid.myfirstcompose.ui.theme.MyFirstComposeTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()

                }
            }
        }
    }
}
data class User(val name:String, val age:Int){}
val data = listOf<User>(User("Heydie", 38),User("Helsa", 17))

@Composable
fun TextName(user:User) {
        Row {
            Text(
                text = user.name
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = user.age.toString()
            )
        }
}

@Preview(showBackground = true)
@Composable
fun prevText() {
    LazyColumn(content = {
        items(data) {
            TextName(user = it)
        }
    })


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val list = remember {
        mutableStateListOf<String>()
    }

    var name by remember {
        mutableStateOf("")
    }

    var selectedName by remember {
        mutableStateOf(false)
    }


    var index by remember {
        mutableStateOf(0)
    }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            OutlinedTextField(
                modifier = modifier
                    .heightIn(50.dp)
                    .widthIn(200.dp)
                    .padding(15.dp),
                value = name,
                onValueChange = {name = it},
                placeholder = {Text(text = "todo...")},
                label = { Text(text = "Form Todo")},
            )

        }

        Button(
            onClick =
            {
                if(selectedName) {
                    list[index] = name
                    index = 0
                    selectedName = false
                    name = ""
                } else {
                    list.add(name)
                    name = ""
                }

            }
        ) {
            if(selectedName) {
                Text(text = "Edit")
            } else {
                Text(text = "Add")
            }

        }

        LazyColumn(content = {
            items(list.size, key = {it}) { idx ->
                Card(Modifier.width(200.dp).clickable {
                    name = list[idx]
                    selectedName = true
                    index = idx
                }) {
                    Text(text = list[idx], Modifier.padding(20.dp))
                }
            }
        }, verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(10.dp))



    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MyFirstComposeTheme {
            Greeting()
        }
    }

}
