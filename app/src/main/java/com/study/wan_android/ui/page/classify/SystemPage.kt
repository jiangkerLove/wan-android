package com.study.wan_android.ui.page.classify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SystemPage(
    systemModel: SystemModel = hiltViewModel()
) {

    val groupList = remember { systemModel.systemGroupList }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F5F5))
    ) {
        LazyColumn {
            items(groupList.value.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .background(color = Color.White)
                        .padding(vertical = 12.dp, horizontal = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(width = 8.dp, height = 16.dp)
                                .clip(shape = RoundedCornerShape(2.dp))
                                .background(Color(0xFFFF9100))
                        )
                        Box(modifier = Modifier.size(width = 4.dp, height = 0.dp))
                        Text(
                            text = groupList.value[index].name,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp
                        )
                    }
                    Box(modifier = Modifier.size(12.dp))
                    Row {
                        groupList.value[index].childes.forEach {
                            Text(
                                text = it.name,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = Color(0XFFF1F6FD))
                                    .padding(4.dp),
                                color = Color(0xFF6FA1F1)
                            )
                        }
                    }
                }
            }
        }
    }


}