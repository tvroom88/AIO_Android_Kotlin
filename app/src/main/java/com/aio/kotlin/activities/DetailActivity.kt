package com.aio.kotlin.activities

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.util.Log
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
import dagger.hilt.android.AndroidEntryPoint


/**
 * fragment 3개
 * 1) WebView : 이론설명
 * 2) 실습 : 이거는 이미지와 실습화면을 같이 보여주는 방식으로. 2개를 나눌수도 있고 같이 할 수도 있다.
 */
@AndroidEntryPoint
class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>(),
    OnClickListener {

    private lateinit var fragmentName: StudyList.StudyFragmentList
    private var currentFragmentId: Int = 0 // Bottom Navigation을 중복해서 클릭 되는것을 막기 위한 부분
    private var pageNum:Int = 0

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    @SuppressLint("RestrictedApi", "ResourceAsColor")
    override fun initOnCreate() {
        fragmentName = intent.getSerializableExtra("data") as StudyList.StudyFragmentList
        pageNum = fragmentName.numOfMenu

        // toolbar 설정
        setToolbar(
            binding.layout.toolbar,
            binding.layout.toolbarImage,
            binding.layout.tooblarTitle,
            fragmentName.title
        )

        when (pageNum) {
            1 -> {
                binding.BnvDetailWithTwoBtn.visibility = View.GONE // 어쩌피 하나만 있다면 가리면 됨.
                binding.BnvDetailWithOneBtn.visibility = View.GONE
            }
            2 -> {
                currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id
                binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setOnClickListener(this)
                binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setOnClickListener(this)
            }
            3 -> {
                binding.BnvDetailWithThreeBtn.visibility = View.VISIBLE
                binding.BnvDetailWithTwoBtn.visibility = View.GONE
                currentFragmentId = binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.id
                binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setOnClickListener(this)
                binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setOnClickListener(this)
                binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setOnClickListener(this)
            }
        }

        goToWebFragment(fragmentName)
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

    private fun goToSecondWebFragment(fragmentList: StudyList.StudyFragmentList) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_detail, BaseWebFragment().newInstance(fragmentList.secondUrlString))
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

        // bottom 버튼이 2개일 경우
        if (num == binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id) {
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else if (num == binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id) {
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        // bottom 버튼이 3개일 경우
        if (num == binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.id) {
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else if (num == binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.id) {
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setTextColor(ContextCompat.getColor(this, R.color.white))
        }else if (num == binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.id) {
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setBackgroundResource(R.drawable.btn_detail_non_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setBackgroundResource(R.drawable.btn_detail_pressed)
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    override fun onClick(v: View?) {
        if(currentFragmentId==v?.id){
            return
        }

        when (v?.id) {
            // Bottom Navigation Button이 2개일때
            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id -> {
                currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavLeft.id
                goToWebFragment(fragmentName)
            }

            binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id -> {
                currentFragmentId = binding.bnvDetailWithTwoBtnInside.btnDetailBtmNavRight.id
                goToNativeFragment(fragmentName)
            }

            // Bottom Navigation Button이 3개일때
            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.id -> {
                currentFragmentId = binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavLeft.id
                goToWebFragment(fragmentName)
            }

            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.id -> {
                currentFragmentId = binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavMiddle.id
                goToSecondWebFragment(fragmentName)
            }

            binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.id -> {
                currentFragmentId = binding.bnvDetailWithThreeBtnInside.btnDetailBtmNavRight.id
                goToNativeFragment(fragmentName)
            }
        }
    }

}