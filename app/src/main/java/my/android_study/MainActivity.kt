package my.android_study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.android_study.databinding.ActivityMainBinding
import my.android_study.fragments.CodeExampleListFragment
import my.android_study.fragments.StudyListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
    }

    //Bottom Navigation 하는 부분
    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, StudyListFragment())
            .commitAllowingStateLoss()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, StudyListFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, CodeExampleListFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}