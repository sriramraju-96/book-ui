package com.example.bookui.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookui.R
import com.example.bookui.data.PopularBookRepo
import com.example.bookui.ui.theme.Eden
import com.example.bookui.ui.theme.LightningYellow

@Composable
fun HomeScreen(
    onBookClicked : (bookId : Long) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(contentPadding = PaddingValues(top = 32.dp, bottom = 70.dp)) {
            item {
                RecentScreen()
                PopularBook(onBookClicked)
                RecommendedBook()
            }
        }
    }
}


@Composable
fun RecentScreen() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(200.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Recent Book",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(0.5f)
            )
            Text(
                text = "View all",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.5f)

            )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.atomic_habits),
                    contentDescription = "",
                    modifier = Modifier.clip(RoundedCornerShape(4.dp))
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Atomic Habits",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "James Clear",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun PopularBook(onBookClicked: (bookId: Long) -> Unit) {
    val popularBooks = PopularBookRepo.getPopularBooks()

    Column(
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Popular Books",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View all",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(popularBooks) { book ->
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .height(280.dp)
                        .width(150.dp)
                        .clickable { onBookClicked(book.id) }
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = book.image),
                            contentDescription = book.title,
                            modifier = Modifier.clip(RoundedCornerShape(6.dp))
                        )
                        Text(
                            text = book.title,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                        Text(
                            text = book.author,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendedBook() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Recommended",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View all",
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.sebuah_seni_untuk_bersikap_bodo_amat),
                contentDescription = "Sebuah seni untuk bersikap bodo amat",
                modifier = Modifier.clip(RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sebuah Seni untuk Bersikap Bodo Amat",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Mark Manson",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(modifier = Modifier.padding(top = 32.dp)) {
                    for (i in 1..5) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_star_rate_24),
                            contentDescription ="Rating",
                            tint = LightningYellow

                        )
                    }
                }
            }
        }
    }


}


