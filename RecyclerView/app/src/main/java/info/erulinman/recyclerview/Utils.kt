package info.erulinman.recyclerview

import android.content.Context

fun Float.fromDpToPixel(context: Context) =
    this * context.resources.displayMetrics.density