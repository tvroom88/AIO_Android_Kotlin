package com.aio.kotlin.activities

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
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
    NavigationBarView.OnItemSelectedListener {

    private lateinit var fragmentName: StudyList.StudyFragmentList
    private var firstCome = true

    @SuppressLint("RestrictedApi")
    private lateinit var menuView1: BottomNavigationMenuView

    // Menu1
    @SuppressLint("RestrictedApi")
    private lateinit var menuItemView1: BottomNavigationItemView
    private lateinit var newMenuView1: View
    private lateinit var textview1: TextView

    // Menu2
    @SuppressLint("RestrictedApi")
    private lateinit var menuItemView2: BottomNavigationItemView
    private lateinit var newMenuView2: View
    private lateinit var textview2: TextView

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



        // BottomNavigationView 새로 만들기
        binding.bottomNavigationView.menu.apply {
            clear()
            add(Menu.NONE, 0, Menu.NONE, "설명")
            if (fragmentName.numOfMenu == 2) {
                add(Menu.NONE, 1, Menu.NONE, "실습")
            } else if (fragmentName.numOfMenu == 3) {
                add(Menu.NONE, 1, Menu.NONE, "코드 예제")
                add(Menu.NONE, 2, Menu.NONE, "결과")
            }

        }

        binding.bottomNavigationView.setOnItemSelectedListener(this)

        val bottomNav = binding.bottomNavigationView
        menuView1 = bottomNav.getChildAt(0) as BottomNavigationMenuView

        // menu1 부분
        menuItemView1 = menuView1.getChildAt(0) as BottomNavigationItemView
        menuItemView1.removeAllViews()
        newMenuView1 = LayoutInflater.from(this)
            .inflate(R.layout.bottom_menu_item_common, binding.bottomNavigationView, false)

        newMenuView1.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbarColor))
        textview1 = newMenuView1.findViewById(R.id.tv_menu)
        textview1.setTextColor(ContextCompat.getColor(this, R.color.white))
        textview1.text = "설명"
        menuItemView1.addView(newMenuView1)

        // menu2 부분
        menuItemView2 = menuView1.getChildAt(1) as BottomNavigationItemView
        menuItemView2.removeAllViews()
        newMenuView2 = LayoutInflater.from(this)
            .inflate(R.layout.bottom_menu_item_common, binding.bottomNavigationView, false)

        newMenuView2.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        textview2 = newMenuView2.findViewById(R.id.tv_menu)
        textview2.setTextColor(ContextCompat.getColor(this, R.color.non_pressed))
        textview2.text = "적용"
        menuItemView2.addView(newMenuView2)

        binding.bottomNavigationView.setSelectedItemId(0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (!firstCome && binding.bottomNavigationView.selectedItemId == item.itemId) { // 이미 선택된 항목이면 아무 작업도 하지 않음 (중복 클릭 방지)
            return false
        }
        changeTextColor(item.itemId)
        when (item.itemId) {
            0 -> goToWebFragment(fragmentName)
            1 -> goToNativeFragment(fragmentName)
            2 -> {}
            else -> return false
        }
        return true
    }

    private fun goToWebFragment(fragmentList: StudyList.StudyFragmentList) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_detail, BaseWebFragment().newInstance(fragmentList.urlString))
                .commit()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
        firstCome = false
    }

    private fun goToNativeFragment(fragmentList: StudyList.StudyFragmentList) {
        try {

            val fragmentClass = Class.forName(fragmentList.fragmentName)
            val fragment: Fragment = fragmentClass.newInstance() as Fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_detail, fragment)
                .commit()
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
        if (num == 0) {
            textview1.setTextColor(ContextCompat.getColor(this, R.color.backgroundColor))
            textview2.setTextColor(ContextCompat.getColor(this, R.color.non_pressed))
            newMenuView1.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbarColor))
            newMenuView2.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        } else if (num == 1) {
            textview1.setTextColor(ContextCompat.getColor(this, R.color.non_pressed))
            textview2.setTextColor(ContextCompat.getColor(this, R.color.backgroundColor))
            newMenuView1.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            newMenuView2.setBackgroundColor(ContextCompat.getColor(this, R.color.toolbarColor))
        }
    }

}