package com.aio.kotlin.activities

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.adapters.StudyListAdapter
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.ActivityAndroidStudyBinding
import com.aio.kotlin.models.StudyList
import com.aio.kotlin.studylist.backgroundwork.multithread.MultiThreadFragment
import com.aio.kotlin.studylist.backgroundwork.rx.RxJavaBasicFragment
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import com.aio.kotlin.studylist.recyclerview.RecyclerViewExampleFragment
import com.aio.kotlin.utils.PermissionUtils

class AndroidStudyActivity : ViewBindingBaseActivity<ActivityAndroidStudyBinding>() {

    private val studyListAdapter by lazy { StudyListAdapter() }
    private val permissionUtils by lazy { PermissionUtils() }

    // ViewBinding 연결
    override fun getViewBinding(): ActivityAndroidStudyBinding {
        return ActivityAndroidStudyBinding.inflate(layoutInflater)
    }

    override fun initOnCreate() {
        setStudyList() // 임시로 목록을 세팅한다.
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
                        override fun onItemClick(data: StudyList, itemPosition: Int) {
                            // 클린 이벤트에 필요한 내용
                            if (data is StudyList.StudyFragmentList) {
                                val intent = Intent(applicationContext, DetailActivity::class.java)
                                intent.putExtra("data", data)
                                startActivity(intent)
                            } else if (data is StudyList.StudyActivityList) {
                                val intent = Intent(applicationContext, data.classInfo.java)
                                startActivity(intent)
                            }
                        }
                    }

                setItemList(setStudyList()) // RecyclerView에 데이터 추가
                addItemDecoration(ExampleItemDecoration(30, 60, 60))
            }
        }
    }

    private fun setStudyList(): MutableList<StudyList> {

        return mutableListOf(
            StudyList.StudyFragmentList(
                "DataBinding",
                DataBindingExampleFragment().getFullFragmentName()
            ),
            StudyList.StudyFragmentList(
                "ViewBinding",
                ViewBindingExampleFragment().getFullFragmentName()
            ),
            StudyList.StudyFragmentList(
                "RecyclerView",
                RecyclerViewExampleFragment().getFullFragmentName()
            ),
            StudyList.StudyFragmentList(
                "Thread",
                MultiThreadFragment().getFullFragmentName()
            ),
            StudyList.StudyFragmentList(
                "Activity",
                RxJavaBasicFragment().getFullFragmentName()
            ),
        )
    }
}
