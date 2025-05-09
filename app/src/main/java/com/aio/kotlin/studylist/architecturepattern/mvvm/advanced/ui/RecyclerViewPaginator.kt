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

    // 스크롤 되는 중일때 호출
//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//    }

    /**
     * 참고 : https://hodie.tistory.com/53
     * onScrollStateChanged : 스크롤이 끝났을 때 호출
     * newState(스크롤 상태)
     * 1) SCROLL_STATE_SETTING : 위든 아래든 스크롤이 끝까지 간 상태
     * 2) SCROLL_STATE_IDLE -> 현재 스크롤을 하지 않는 상태
     * 3) SCROLL_STATE_DRAGGING -> 스크롤을 하고 있는 상태
     */
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        // 스크롤 상태가 IDLE일 때 (즉, 스크롤이 끝났을 때)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.layoutManager?.let {

                // 1. 화면에 보여지는 마지막 아이템
                val lastVisibleItemPosition = (it as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                // 2. 실제 들어가 있는 아이템 숫자
                val itemTotalCount = it.itemCount - 1

                Log.d("RecyclerViewPaginator", "last : $lastVisibleItemPosition, real : $itemTotalCount")

                // 로딩 중이 아니고 마지막 아이템에 도달했으면
                if (!isLoading() && lastVisibleItemPosition == itemTotalCount) {
                    if (isFirstTimeCall) {
                        isFirstTimeCall = false
                        loadMore(++currentPage)
//                        loadMore(0)
                    }

                }
            }
        }

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            isFirstTimeCall = true;
        }
    }

}