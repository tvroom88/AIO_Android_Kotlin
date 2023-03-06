package my.android_study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.android_study.adapters.StudyListAdapter
import my.android_study.models.AndroidStudyItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainBnv = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        initBottomNavigation(mainBnv)
    }

    //Bottom Navigation 하는 부분
    private fun initBottomNavigation(mainBnv:BottomNavigationView) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, StudyListFragment())
            .commitAllowingStateLoss()

        mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, StudyListFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, StudyListFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}