package com.aio.kotlin.activities

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
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
                onItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<StudyList> {
                        override fun onItemClick(
                            binding: ViewDataBinding,
                            data: StudyList,
                            itemPosition: Int
                        ) {
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
//                                        if (binding.cvMainHiddenItems.visibility == View.VISIBLE) {
//                                            binding.cvMainHiddenItems.visibility = View.GONE
//                                            binding.ivRecyclerDropdown.animate().apply {
//                                                duration = 1000
//                                                rotation(0f)
//                                            }
//                                        } else {
//                                            binding.cvMainHiddenItems.visibility = View.VISIBLE
//                                            binding.ivRecyclerDropdown.animate().apply {
//                                                duration = 1000
//                                                rotation(180f)
//                                            }
//                                        }
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
}
