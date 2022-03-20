package com.example.newsjetpackretrofit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsjetpackretrofit.MockData
import com.example.newsjetpackretrofit.MockData.getTimeAgo

import com.example.newsjetpackretrofit.NewsData
import com.example.newsjetpackretrofit.R

@Composable
fun DetailScreen(newsData: NewsData,scrollState: ScrollState,navController:NavController) {

    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = {navController.popBackStack()})
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
            //Todo 1 Remove the  Button and add an Image and set newsData.image as resource
            Image(painter = painterResource(id = newsData.image), contentDescription = "")
            //Todo 3: add a Row then use the InfoWithIcon composable to show author and published date
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(icon = Icons.Default.DateRange, info = MockData.stringToDate(newsData.publishedAt).getTimeAgo() )
            }
            //Todo 4 add two Text for news title and news descriptionm
            Text(text = newsData.title, fontWeight = FontWeight.Bold)
            Text(text = newsData.description, modifier = Modifier.padding(top = 16.dp))
        }
    }





}

/** Todo 2: create composable for the detail top bar with TopAppBar
 * containing a title and the navigationIcon
 */
@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
            }
        })
}


//Todo 2 create a reusable function for displaying author and published date
@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(
                id = R.color.purple_500
            )
        )
        Text(text = info)
    }
}

//Todo 6: provide scrollState remember value for previewing
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        NewsData(
            2,
            author = "Namita Singh",
            title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
            description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
            publishedAt = "2021-11-04T04:42:40Z"
        ), rememberScrollState(),rememberNavController()
    )
}