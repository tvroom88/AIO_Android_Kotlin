package com.aio.kotlin.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aio.kotlin.R
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityDetailBinding
import com.aio.kotlin.jetpack.binding.databinding.DataBindingExampleFragment
<<<<<<< HEAD
import com.aio.kotlin.models.StudyList
=======
import com.aio.kotlin.recyclerview.RecyclerViewExampleFragment
>>>>>>> parent of c563f95 (1. 메인에 RecyclerView 진행중..)

class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>() {
    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

<<<<<<< HEAD
        val fragmentName = intent.getSerializableExtra("data") as StudyList

        try {
            val fragmentClass = Class.forName(fragmentName.fragmentName)
            val fragment: Fragment = fragmentClass.newInstance() as Fragment
            // 이제 fragment 객체를 사용하여 트랜잭션을 수행할 수 있습니다.

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fcv_detail, fragment)
                    .commit()
            }

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
=======
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_detail, RecyclerViewExampleFragment())
                .commit()
>>>>>>> parent of c563f95 (1. 메인에 RecyclerView 진행중..)
        }


    }
}