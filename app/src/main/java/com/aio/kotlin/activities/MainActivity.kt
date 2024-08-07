package com.aio.kotlin.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.adapters.StudyListAdapter
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.ActivityMainBinding
import com.aio.kotlin.models.StudyList
import com.aio.kotlin.recyclerview.ExampleItemDecoration
import com.aio.kotlin.utils.PermissionUtils
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    private val studyListAdapter by lazy { StudyListAdapter() }
    private val permissionUtils by lazy { PermissionUtils() }

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                            val intent = Intent(applicationContext, DetailActivity::class.java)
                            intent.putExtra("data", data)
                            startActivity(intent)
                        }
                    }

                setItemList(setStudyList()) // RecyclerView에 데이터 추가
                addItemDecoration(ExampleItemDecoration(30, 60, 60))
            }
        }


    }

    private fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            StudyList(
                "DataBinding",
                "com.aio.kotlin.jetpack.binding.databinding.DataBindingExampleFragment"
            ),
            StudyList(
                "ViewBinding",
                "com.aio.kotlin.jetpack.binding.databinding.ViewBindingExampleFragment"
            ),
            StudyList("Not Adding Yet", "")
        )
    }
}
