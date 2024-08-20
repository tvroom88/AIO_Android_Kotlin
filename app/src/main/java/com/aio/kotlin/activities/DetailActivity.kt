package com.aio.kotlin.activities

import androidx.fragment.app.Fragment
import com.aio.kotlin.R
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityDetailBinding
import com.aio.kotlin.models.StudyList

class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>() {
    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initOnCreate() {

        val fragmentName = intent.getSerializableExtra("data") as StudyList.StudyFragmentList

        // toolbar 설정
        setToolbar(
            binding.layout.toolbar,
            binding.layout.toolbarImage,
            binding.layout.tooblarTitle,
            fragmentName.title
        )

        try {
            val fragmentClass = Class.forName(fragmentName.fragmentName)
            val fragment: Fragment = fragmentClass.newInstance() as Fragment
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_detail, fragment)
                .commit()

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
    }

}