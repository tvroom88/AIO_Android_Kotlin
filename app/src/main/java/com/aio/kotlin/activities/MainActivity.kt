package com.aio.kotlin.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.adapters.StudyListAdapter
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.ActivityMainBinding
import com.aio.kotlin.models.StudyList
import com.aio.kotlin.recyclerview.ExampleItemDecoration
import com.aio.kotlin.recyclerview.RecyclerViewExampleFragment

class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    private val studyListAdapter by lazy { StudyListAdapter() }

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("key", data)

                        }
                    }


                setItemList(setStudyList()) // RecyclerView에 데이터 추가
            }

            addItemDecoration(ExampleItemDecoration(30, 60, 60))
        }
    }

    private fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            StudyList("aa", RecyclerViewExampleFragment()),
            StudyList("bb", RecyclerViewExampleFragment()),
            StudyList("cc", RecyclerViewExampleFragment())
        )
    }
}