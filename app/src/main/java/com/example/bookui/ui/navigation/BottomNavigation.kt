package com.example.bookui.ui.navigation

import androidx.compose.ui.unit.dp
import com.example.bookui.R



sealed class BottomNavigation(
    val route :String,
    val title : String,
    val selectedIcon : Int,
    val unselectedIcon : Int
){
    object HomeScreen : BottomNavigation(
        route = "home_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_home_solid,
        unselectedIcon =R.drawable.ic_home_line
    )

    object WhiteListScreen : BottomNavigation(
        route = "white_list_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_white_list_solid,
        unselectedIcon = R.drawable.ic_whitelist_line
    )

    object BagScreen : BottomNavigation(
        route = "bag_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_bag_solid,
        unselectedIcon = R.drawable.ic_bag_line
    )

    object ProfileScreen : BottomNavigation(
        route = "profile_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_profile_solid,
        unselectedIcon = R.drawable.ic_profile_line
    )
}

