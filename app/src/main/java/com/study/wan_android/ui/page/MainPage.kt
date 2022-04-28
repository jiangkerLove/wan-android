package com.study.wan_android.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.ui.page.model.MainPageModel


@Composable
fun MainPage(
    viewModel: MainPageModel = hiltViewModel(),
) {
    val articleList = remember { viewModel.articleList }
    LazyColumn(modifier = Modifier.background(color = Color(0x455F5F5F))) {
        items(count = articleList.size) { index ->
            ArticleCompose(article = articleList[index])
        }
    }

}


@Composable
fun ArticleCompose(article: ArticleModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Text(
                text = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red)
                    .padding(5.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                if (article.author.isNotEmpty()) {
                    Text(text = "作者：${article.author}", modifier = Modifier.padding(end = 5.dp))
                }
                if (article.shareUser.isNotEmpty()) {
                    Text(text = "分享人：${article.shareUser}", modifier = Modifier.padding(end = 5.dp))
                }
                Text(
                    text = "分类：${article.superChapterName}",
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = article.formatTime)
            }
        }
    }
}