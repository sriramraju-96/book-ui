package com.example.bookui.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookui.ui.navigation.BottomNavigation

val ICON_SIZE = 24.dp

@Composable
fun AnimateIcon(
    modifier: Modifier = Modifier,
    iconSize: Dp = ICON_SIZE,
    scale: Float = 1f,
    currentRoute: String?,
    item: BottomNavigation,
    onClick: () -> Unit
) {

    val animatedScale: Float by animateFloatAsState(
        targetValue = scale,
        animationSpec = TweenSpec(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        )
    )

    IconButton(
        onClick = onClick,
        modifier = modifier.size(iconSize)
    ) {
        Icon(
            painter = painterResource(id = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon),
            contentDescription = item.title,
            modifier = Modifier.scale( if(currentRoute == item.route) 1.5f else 1f)
        )
    }
}