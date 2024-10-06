package com.smarttoolfactory.composebeforeafter.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smarttoolfactory.composebeforeafter.demo.BeforeAfterImageDemo
import com.smarttoolfactory.composebeforeafter.demo.BeforeAfterLayoutDemo

internal val tabList =
    listOf(
        "Before/After Image",
        "Before/After Layout",
    )

@Composable
internal fun HomeContent() {
    val pagerState: PagerState =
        rememberPagerState(
            initialPage = 0,
            pageCount = { tabList.size },
        )

    Scaffold(topBar = { HomeTopBar(pagerState) }) {
        HorizontalPager(
            modifier = Modifier.padding(it),
            state = pagerState,
            userScrollEnabled = false,
        ) { page: Int ->

            when (page) {
                0 -> BeforeAfterImageDemo()
                else -> BeforeAfterLayoutDemo()
            }
        }
    }
}
