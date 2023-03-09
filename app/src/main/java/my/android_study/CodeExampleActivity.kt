package my.android_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.android_study.databinding.ActivityCodeExampleBinding
import my.android_study.fragments_main_activity.CodeExampleListFragment
import my.android_study.fragments_main_activity.StudyListFragment

class CodeExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCodeExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeExampleBinding.inflate(layoutInflater)
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