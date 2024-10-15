package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myrecipeapp.ui.theme.Category
import com.example.myrecipeapp.ui.theme.MainViewModel
import com.example.myrecipeapp.ui.theme.RecipeScreen

@Composable
fun recipeApp(navController:NavHostController){
    val recipeViewModel:MainViewModel= viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController=navController, startDestination = Screen.RecipeScreen.route){
        composable(route=Screen.RecipeScreen.route){
            RecipeScreen(viewstate=viewstate, navigateToDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
}
        composable(route=Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?:Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }
}