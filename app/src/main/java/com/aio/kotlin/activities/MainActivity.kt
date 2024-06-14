package com.aio.kotlin.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.adapters.StudyListAdapter
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.ActivityMainBinding
import com.aio.kotlin.models.StudyList
import com.aio.kotlin.recyclerview.ExampleItemDecoration

class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    private val studyListAdapter by lazy { StudyListAdapter() }

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStudyList()

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

                        }
                    }
            }
            addItemDecoration(ExampleItemDecoration(30, 60, 60))
        }
    }

    private fun setStudyList(): List<StudyList> {
        return listOf(
            StudyList("a"),
            StudyList("a"),
            StudyList("a")
        )
    }
}
