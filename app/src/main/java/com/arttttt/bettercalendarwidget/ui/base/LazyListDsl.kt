package com.arttttt.bettercalendarwidget.ui.base

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable

inline fun LazyListScope.items(
    items: List<ListItem>,
    noinline key: (item: ListItem) -> Any = { item -> item.key },
    noinline contentType: (item: ListItem) -> Any? = { item -> item::class },
    crossinline itemContent: @Composable LazyItemScope.(item: ListItem) -> Unit,
) {
    items(
        items = items,
        key = key,
        contentType = contentType,
        itemContent = itemContent,
    )
}

inline fun LazyListScope.itemsIndexed(
    items: List<ListItem>,
    noinline key: (index: Int, item: ListItem) -> Any = { _, item -> item.key },
    noinline contentType: (index: Int, item: ListItem) -> Any? = { _, item -> item::class },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: ListItem) -> Unit,
) {
    itemsIndexed(
        items = items,
        key = key,
        contentType = contentType,
        itemContent = itemContent,
    )
}