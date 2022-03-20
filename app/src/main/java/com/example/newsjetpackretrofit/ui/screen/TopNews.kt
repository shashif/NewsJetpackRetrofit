package com.example.newsjetpackretrofit.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsjetpackretrofit.MockData
import com.example.newsjetpackretrofit.MockData.getTimeAgo
import com.example.newsjetpackretrofit.NewsData
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun TopNews(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment= Alignment.CenterHorizontally) {

        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
//        Button(onClick = {
//
//            navController.navigate("DetailNav")
//        }) {
//            Text(text = "Go to Detail screen")
//        }


        LazyColumn{
            items(MockData.topNewsList){newsData->
                //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                TopNewsItem(newsData = newsData, onNewsClick = {
                    navController.navigate("DetailNav/${newsData.id}")
                })
            }
        }
    }
}

@Composable
fun TopNewsItem(newsData: NewsData, onNewsClick: ()->  Unit={})
{

    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }
    ) {
        Image(painter = painterResource(id = newsData.image), contentDescription = "",
            contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text =  MockData.stringToDate(newsData.publishedAt).getTimeAgo() ,color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsData.title,color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){
    TopNewsItem(  NewsData(
        2,
        author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAt = "2021-11-04T04:42:40Z"
    ))
}