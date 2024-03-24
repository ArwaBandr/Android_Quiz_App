package com.example.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.quiz.ui.theme.QuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizTheme {
                // A surface container using the 'background' color from the theme
                val state1 = remember {
                    mutableStateOf(0)
                }//state of int , mutable itself
                //by remeber int .intVlaue ,
                // (state, setstate) int , set
                // best practice to be in the top/ global scope

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column (modifier =Modifier.padding(16.dp)){
                        q()

                    }
                }
            }
        }
    }
}

@Composable
fun q(viewModel: QuizVeiwModel = androidx.lifecycle.viewmodel.compose.viewModel(),){
    LazyColumn(Modifier.padding(16.dp)) {
        items(viewModel.quizList){quizItem ->
            Text(text =quizItem.question)
            val selectedOption = rememberSaveable {
                mutableStateOf("")
            }
            quizItem.answerList.forEach(){answer ->
                Row (modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.checkAnswer(quizItem, answer)
                        selectedOption.value = answer
                    }, verticalAlignment = Alignment.CenterVertically){
                    RadioButton(selected = (answer == selectedOption.value), onClick = {
                        viewModel.checkAnswer(quizItem ,answer)
                        selectedOption.value =answer
                    })

                Text(text = answer)
            }}
        }
        item{
            Row(horizontalArrangement =Arrangement.Center){
            Button(onClick = {}){
                viewModel.onSubmit()
                Text(text = ("SUBMIT"))
            }
          }


    }
item{
    Text(viewModel.score.value)//score
}
}


}