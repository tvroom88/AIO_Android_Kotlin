package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPaginator(
    recyclerView: RecyclerView,
    private val isLoading: () -> Boolean,
    private val loadMore: (Int) -> Unit,
    private val onLast: () -> Boolean = { true }
) : RecyclerView.OnScrollListener() {

    private var currentPage: Int = 0 // 현재 페이지
    private var isFirstTimeCall = true

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        // 스크롤 상태가 IDLE일 때 (즉, 스크롤이 끝났을 때)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.layoutManager?.let {
                val lastVisibleItemPosition = (it as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = it.itemCount - 1

                // 로딩 중이 아니고 마지막 아이템에 도달했으면
                if (!isLoading() && lastVisibleItemPosition == itemTotalCount) {
                    if(isFirstTimeCall){
                        isFirstTimeCall = false
                        loadMore(++currentPage)
                    }

                }
            }
        }

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            isFirstTimeCall = true;
        }
    }

}