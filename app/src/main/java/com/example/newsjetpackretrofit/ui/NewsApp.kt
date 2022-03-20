package com.example.newsjetpackretrofit.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState

import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsjetpackretrofit.BottomMenuScreen
import com.example.newsjetpackretrofit.Components.BottomMenu
import com.example.newsjetpackretrofit.MockData
import com.example.newsjetpackretrofit.ui.screen.DetailScreen
import com.example.newsjetpackretrofit.ui.screen.Sources
import com.example.newsjetpackretrofit.ui.screen.TopNews

@Composable
fun  NewsApp(){
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController,scrollState)
}


//Todo 1: create a mainScreen composable
@Composable
fun MainScreen(navController : NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        //Todo 9: set Navigation in the MainScreen
        Navigation(navController=navController, scrollState=scrollState)
    }
}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState){


    NavHost(navController = navController, startDestination = "TopNewsNav"){

        bottomNavigation(navController = navController)
        composable("TopNewsNav"){

            TopNews(navController = navController)



        }

        composable("DetailNav/{newsId}",

            arguments = listOf(navArgument("newsId"){type= NavType.IntType})
            ){

            navBackStackEntry->
            val id =navBackStackEntry.arguments?.getInt("newsId")
            val newsData=MockData.getNews(id)
            DetailScreen(newsData,scrollState,navController)
        }
    }
}

//Todo 12: create bottom navigation function for the three options
fun NavGraphBuilder.bottomNavigation(navController: NavController) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController)
    }
    composable(BottomMenuScreen.Categories.route) {
        Categories()
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}