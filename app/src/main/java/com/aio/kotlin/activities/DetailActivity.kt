package com.aio.kotlin.activities

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import androidx.fragment.app.Fragment
import com.aio.kotlin.R
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.base.fragment.web.BaseWebFragment
import com.aio.kotlin.databinding.ActivityDetailBinding
import com.aio.kotlin.models.StudyList
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.navigation.NavigationBarView


/**
 * fragment 3개
 * 1) WebView : 이론설명
 * 2) 실습 : 이거는 이미지와 실습화면을 같이 보여주는 방식으로. 2개를 나눌수도 있고 같이 할 수도 있다.
 */
class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>(),
    OnClickListener {

    private lateinit var fragmentName: StudyList.StudyFragmentList
    private var currentFragmentId: Int = 0 // Bottom Navigation을 중복해서 클릭 되는것을 막기 위한 부분

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    @SuppressLint("RestrictedApi", "ResourceAsColor")
    override fun initOnCreate() {
        fragmentName = intent.getSerializableExtra("data") as StudyList.StudyFragmentList

        // toolbar 설정
        setToolbar(
            binding.layout.toolbar,
            binding.layout.toolbarImage,
            binding.layout.tooblarTitle,
            fragmentName.title
        )


        goToWebFragment(fragmentName)
        currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id
        binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setOnClickListener(this)
        binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setOnClickListener(this)
    }

    private fun goToWebFragment(fragmentList: StudyList.StudyFragmentList) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_detail, BaseWebFragment().newInstance(fragmentList.urlString))
                .commit()

            changeTextColor(currentFragmentId)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
    }

    private fun goToNativeFragment(fragmentList: StudyList.StudyFragmentList) {
        try {
            val fragmentClass = Class.forName(fragmentList.fragmentName)
            val fragment: Fragment = fragmentClass.newInstance() as Fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_detail, fragment)
                .commit()

            changeTextColor(currentFragmentId)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun changeTextColor(num: Int) {
        if (num == binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id) {
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setTextColor(R.color.black)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setTextColor(R.color.white)
        } else if (num == binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id) {
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setTextColor(R.color.white)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setTextColor(R.color.black)
        }
    }

    override fun onClick(v: View?) {
        if(currentFragmentId==v?.id){
            return
        }
        when (v?.id) {
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id -> {
                currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id
                goToWebFragment(fragmentName)
            }

            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id -> {
                currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id
                goToNativeFragment(fragmentName)
            }
        }
    }

}