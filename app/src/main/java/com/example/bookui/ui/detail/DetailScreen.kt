package com.example.bookui.ui.detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookui.data.PopularBookRepo
import com.example.bookui.R
import com.example.bookui.data.PopularBook
import com.example.bookui.ui.components.BookAppTopBar
import com.example.bookui.ui.theme.*

@Composable
fun DetailScreen(
    bookId: Long,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavController

) {

    val book = PopularBookRepo.getPopularBook(bookId)

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            BookAppTopBar(
                title = {
                    Text(
                        "Book Detail",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        color = MaterialTheme.colors.onSurface,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                                contentDescription = "",
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .height(50.dp)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_more_vert_24),
                            contentDescription = "more"
                        )
                    }
                },

            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 30.dp, start = 16.dp, end = 16.dp)
            ) {
                item {
                    Book(book = book)
                    TitlePrice(book = book)
                    Info(book = book)
                    Description(book = book)
                    Action()
                }
            }

        }
    }

}


@Composable
fun Book(book: PopularBook) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = book.image),
            contentDescription = book.title,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(10.dp))

        )
    }
}

@Composable
fun TitlePrice(book: PopularBook) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = book.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = book.author,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        }
        Text(
            text = "Free",
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Info(book: PopularBook) {
    Card(
        modifier = Modifier.padding(top = 24.dp),
        backgroundColor = if (MaterialTheme.colors.isLight) Zircon else MaastrichtBlue,
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ratings",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )
                Row {
                    Text(
                        text = book.rating.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_star_rate_24),
                        contentDescription = "Rating",
                        tint = LightningYellow,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(if (MaterialTheme.colors.isLight) Jumbo else Casper)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Number of pages",
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )

                Text(
                    text = "${book.numberOfPage} Pages",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(if (MaterialTheme.colors.isLight) Jumbo else Casper)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Language",
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )
                Text(
                    book.language,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}

@Composable
fun Description(book: PopularBook) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            "Description",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.description,
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
            lineHeight = 25.sp
        )
    }
}


@Composable
fun Action() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { },
            elevation = 0.dp,
            backgroundColor = if (MaterialTheme.colors.isLight) Zircon else OxfordBlue,

            ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_whitelist_solid),
                contentDescription = "wishList",
                modifier = Modifier.padding(16.dp),
                tint = if (MaterialTheme.colors.isLight) Periwinkle else Orient,
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "Start Reading",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = if (MaterialTheme.colors.isLight) Color.White else Zuccini
            )
        }
    }
}


