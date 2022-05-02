package com.example.bookui.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookui.R
import com.example.bookui.ui.components.BookAppTopBar
import com.example.bookui.ui.detail.DetailScreen
import com.example.bookui.ui.dummy.WhiteListScreen
import com.example.bookui.ui.home.HomeScreen
import com.example.bookui.ui.navigation.BottomNavigation
import com.example.bookui.ui.theme.*

@Composable
fun MainScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentroute = navBackStackEntry?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (currentroute != "detail_screen/{book_id}") {
                BookAppTopBar(
                    title = {
                        Text(
                            text = "BookHub",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 20.dp),
                        )
                    },
                    navigationIcon = {
                        Box(modifier = Modifier.height(50.dp)) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_menu_icon),
                                    contentDescription = "menu_icon",
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    },
                    actions = {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(end = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search_icon),
                                contentDescription = "search_icon",
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    },


                    )
            }
        },
        bottomBar = {
            if (currentroute != "detail_screen/{book_id}") {
                BottomNavigationBar(navController = navController, currentRoute = currentroute)
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home_screen") {
            composable(
                route = "home_screen"
            ) {
                HomeScreen(
                    onBookClicked = {
                        navController.navigate("detail_screen/$it")
                    }
                )
            }
            composable(
                route = "detail_screen/{book_id}",
                arguments = listOf(navArgument("book_id") {
                    type = NavType.LongType
                })
            ) {
                val bookId = it.arguments?.getLong("book_id")

                DetailScreen(
                    bookId = bookId!!,
                    navController = navController
                )
            }
            composable(
                route = BottomNavigation.WhiteListScreen.route
            ) {
                WhiteListScreen()
            }

            composable(
                route = BottomNavigation.BagScreen.route
            ) {
                WhiteListScreen()
            }

            composable(
                route = BottomNavigation.ProfileScreen.route
            ) {
                WhiteListScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
) {
    val screen = listOf(
        BottomNavigation.HomeScreen,
        BottomNavigation.WhiteListScreen,
        BottomNavigation.BagScreen,
        BottomNavigation.ProfileScreen
    )

    BottomNavigation(
        modifier = Modifier
            .padding(bottom = 14.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(18.dp)),
        backgroundColor = if (MaterialTheme.colors.isLight) BottomColor else Daintree,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    ) {
        screen.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    val animatedScale by animateFloatAsState(
                        targetValue = if (currentRoute == item.route) 1.3f else 1f,
                        animationSpec = TweenSpec(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )


                    Icon(
                        painter = painterResource(id = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon),
                        contentDescription = item.title,
                        modifier = Modifier.scale(animatedScale)
                    )
                },
                //selectedContentColor = if (MaterialTheme.colors.isLight) NileBlue else MaterialTheme.colors.primary,
                //unselectedContentColor = if (MaterialTheme.colors.isLight) NileBlue else Color.White,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true

                    }

                },

                )

        }
    }
}

