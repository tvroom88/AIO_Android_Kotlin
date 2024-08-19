package com.aio.kotlin.activities

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.R
import com.aio.kotlin.adapters.StudyListAdapter
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.data.StudyListData
import com.aio.kotlin.databinding.ActivityAndroidStudyBinding
import com.aio.kotlin.databinding.ItemStudyListBinding
import com.aio.kotlin.models.StudyList
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import com.aio.kotlin.utils.PermissionUtils

class AndroidStudyActivity : ViewBindingBaseActivity<ActivityAndroidStudyBinding>() {

    private val studyListAdapter by lazy { StudyListAdapter() }
    private val permissionUtils by lazy { PermissionUtils() }

    // ViewBinding 연결
    override fun getViewBinding(): ActivityAndroidStudyBinding {
        return ActivityAndroidStudyBinding.inflate(layoutInflater)
    }

    override fun initOnCreate() {
        permissionUtils.askNotificationPermission(this, this) // 권한 요청

        binding.rvMain.run {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = studyListAdapter.apply {
                onItemClickListener =
                    object : BaseRecyclerViewAdapter.OnItemClickListener<StudyList> {
                        override fun onItemClick(
                            binding: ViewDataBinding,
                            data: StudyList,
                            itemPosition: Int
                        ) {

                            // 버튼 클릭시 애니매이션
                            makeObjectAnimator(binding.root)

                            // 클린 이벤트에 필요한 내용
                            when (data) {
                                is StudyList.StudyFragmentList -> {
                                    val intent =
                                        Intent(applicationContext, DetailActivity::class.java)
                                    intent.putExtra("data", data)
                                    startActivity(intent)
                                }

                                is StudyList.StudyActivityList -> {
                                    val intent = Intent(applicationContext, data.classInfo.java)
                                    startActivity(intent)
                                }

                                is StudyList.StudyCategory -> {
                                    if (binding is ItemStudyListBinding) { // binding 사용을 특정지어주기 위해서
                                        binding.expandable.toggleLayout()
                                    }
                                }
                            }
                        }
                    }

                val studyList = StudyListData().setStudyList() // 학습 리스트
                setItemList(studyList) // RecyclerView에 데이터 추가
            }
            addItemDecoration(ExampleItemDecoration(30, 60, 60))
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun makeObjectAnimator(view: View) {
        val animator = ObjectAnimator.ofObject(
            view,
            "cardBackgroundColor", // 애니메이션 할 속성
            ArgbEvaluator(),        // 색상 변환기
            Color.WHITE,            // 시작 색상
            view.resources.getColor(R.color.basicSubListColor, null) // 끝 색상
        )
        animator.duration = 300 // 애니메이션 지속 시간 (밀리초)
        animator.start()
    }
}
