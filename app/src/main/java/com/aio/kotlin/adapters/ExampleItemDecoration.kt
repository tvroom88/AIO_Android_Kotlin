package com.aio.kotlin.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class StudySubListItemDecoration(
    private val itemSpacing: Int = 0,
    private val firstItemTopSpacing: Int = 0,
    private val lastItemBottomSpacing: Int = 0
) : RecyclerView.ItemDecoration() {

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
        outRect.bottom = if(position == itemCount - 1) lastItemBottomSpacing else 0

        outRect.left = itemSpacing
        outRect.right = itemSpacing
        
    }
}