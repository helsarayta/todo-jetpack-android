package com.heydieandroid.myfirstcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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