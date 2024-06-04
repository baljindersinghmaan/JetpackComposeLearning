package com.example.jetpackcomposelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var shouldShowOnBoardingScreen by remember {
                mutableStateOf(true)
            }
            JetpackComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                if (shouldShowOnBoardingScreen) {
                    OnBoardingScreen(onContinueClicked = {shouldShowOnBoardingScreen = false})
                } else {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState ( if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ))
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp).shadow(elevation = 5.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp).animateContentSize { initialValue, targetValue ->  }) {
            Column(
                modifier = Modifier
                    .weight(1f)
                   /* .padding(bottom = extraPadding.coerceAtLeast(0.dp))*/
            ) {
                Text(text = "Hello")
                Text(
                    text = name
                )
                if(expanded.value)
                    Text(text = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit")
            }
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = if (expanded.value) "Expand Less" else "Expand More")
            }
        }


    }

}

@Composable
fun OnBoardingScreen(
    onContinueClicked:() -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Writing first compose app")
        Button(modifier = Modifier.padding(top = 20.dp), onClick = onContinueClicked) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000){ "$it"}
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            items(items = names){ name ->
                Greeting(name = name)
            }
        }
        /*Column {
            for (name in names) {
                Greeting(name = name)

            }
        }*/


    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    JetpackComposeLearningTheme {
        // Greeting("Android")
        MyApp()
    }
}