package com.aio.kotlin.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class ExampleItemDecoration(
    private val itemSpacing: Int = 0,
    private val firstItemTopSpacing: Int = 0,
    private val lastItemBottomSpacing: Int = 0
) : RecyclerView.ItemDecoration() {
    constructor(
        itemSpacing: Int,
    ) : this(itemSpacing, itemSpacing, itemSpacing)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val itemCount = state.itemCount


        // 첫 번째 항목 위에 추가 간격을 설정
        outRect.top = if (position == 0) firstItemTopSpacing else itemSpacing
        outRect.bottom = if(position == itemCount - 1) lastItemBottomSpacing else itemSpacing

        outRect.left = itemSpacing
        outRect.right = itemSpacing
        
    }
}