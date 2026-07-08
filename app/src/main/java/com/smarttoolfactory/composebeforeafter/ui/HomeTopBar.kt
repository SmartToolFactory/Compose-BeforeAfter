package com.smarttoolfactory.composebeforeafter.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HomeTopBar(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    SecondaryTabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                modifier =
                    Modifier.tabIndicatorOffset(
                        selectedTabIndex = pagerState.currentPage,
                    ),
                height = 4.dp,
            )
        },
    ) {
        tabList.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}
