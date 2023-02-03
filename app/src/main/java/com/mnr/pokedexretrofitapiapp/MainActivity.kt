package com.mnr.pokedexretrofitapiapp

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mnr.pokedexretrofitapiapp.pokemondetails.PokemonDetailScreen
import com.mnr.pokedexretrofitapiapp.pokemonlist.PokemonListScreen
import com.mnr.pokedexretrofitapiapp.ui.theme.PokedexRetrofitApiAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash Screen
        //installSplashScreen()

        setContent {
            PokedexRetrofitApiAppTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen"
                ) {

                    composable("splash_screen") {

                        SplashScreen(navController = navController)
                    }

                    //POKEMON List Screen
                    composable("pokemon_list_screen") {

                        PokemonListScreen(navController = navController)
                    }

                    //POKEMON Details Screen
                    composable(
                        "pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }

                        val pokemonName = remember {

                            it.arguments?.getString("pokemonName")
                        }

                        PokemonDetailScreen(
                            navController = navController,
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {

        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {

        scale.animateTo(

            targetValue = 1f,
            animationSpec = tween(

                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.popBackStack()
        navController.navigate("pokemon_list_screen")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(

                if (isSystemInDarkTheme()) {

                    Color(0xFF202020)
                } else {
                    Color.White
                }
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_pokemon_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}
