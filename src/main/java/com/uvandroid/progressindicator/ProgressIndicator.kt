package com.uvandroid.progressindicator

import android.content.Intent.ShortcutIconResource

class ProgressIndicator(
    val indicatorCount: Int = 0,
    val currentIndicator: Int = 0,
    val selectedIconResource: Int,
    val completedIconResource: Int,
    val undoneIconResource: Int
) {
}