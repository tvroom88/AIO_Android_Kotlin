package my.android_study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import my.android_study.databinding.ActivityMainBinding
import my.android_study.fragments_main_activity.CodeExampleListFragment
import my.android_study.fragments_main_activity.StudyListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var url: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        url = intent.getStringExtra("url").toString()
        print(url)
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