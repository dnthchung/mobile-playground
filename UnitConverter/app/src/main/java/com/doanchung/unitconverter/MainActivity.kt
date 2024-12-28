package com.doanchung.unitconverter

import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doanchung.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    UnitConverter()
                }
            }
        }
    }
}

//tao ham co kha nang ket hop
@Composable
fun UnitConverter() {
    Column(
        //3 cái ny là để đưa cả cái ele ra chính giữa của màn hình.
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //here all the ui ele will be stacked bellow
        Text(text = "Unit Converter : ")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = "", onValueChange = {
            /**
             * here goes what should happen, when the value of OutlineTextFiled changes
             */
        })
        Row {
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    //Composable
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
            }
            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    //Composable
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
            }
//            Text(text = "Click Me")
        }
        Text(text = "Result : ")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}

