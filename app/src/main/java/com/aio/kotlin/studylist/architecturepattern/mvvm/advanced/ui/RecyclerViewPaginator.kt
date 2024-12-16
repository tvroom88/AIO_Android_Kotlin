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

    var currentPage: Int = 0 // 현재 페이지
    var nowLoading:Boolean = false
    private var isFirstTimeCall = true

    init {
        recyclerView.addOnScrollListener(this)
        Log.d("ddddddddd", "start ")
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

//        recyclerView.layoutManager?.let {
//            // 화면에 보이는 마지막 아이템의 position
//            val lastVisibleItemPosition = (it as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//            val itemTotalCount = it.itemCount -1
//
//            Log.d("ddddd", "lastVisibleItemPosition : $lastVisibleItemPosition")
//            Log.d("ddddd", "itemTotalCount : $itemTotalCount")
//
//            if(isLoading()){ // 현재 로딩중일때
//                return
//            }
//
//            // 스크롤이 끝에 도달했는지 확인
//            if (lastVisibleItemPosition == itemTotalCount) {
//                loadMore(++currentPage)
//            }
//        }
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
                        Log.d("ddddd", "currentPage : $currentPage")
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