package com.smarttoolfactory.composebeforeafter.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HomeTopBar(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions: List<TabPosition> ->
            TabRowDefaults.SecondaryIndicator(
                modifier =
                    Modifier.tabIndicatorOffset(
                        tabPositions[pagerState.currentPage],
                    ),
                height = 4.dp,
            )
        },
    ) {
        // Add tabs for all of our pages
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
