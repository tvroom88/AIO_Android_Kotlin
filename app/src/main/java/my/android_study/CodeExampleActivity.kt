package my.android_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.android_study.databinding.ActivityCodeExampleBinding
import my.android_study.fragments_code_examples.mvc_pattern.MvcPatternFragment
import my.android_study.fragments_code_examples.WebViewFragment

class CodeExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCodeExampleBinding

    private lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        url = intent.getStringExtra("url").toString()

        initBottomNavigation()

    }

    //Bottom Navigation 하는 부분
    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, WebViewFragment(url))
            .commitAllowingStateLoss()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, WebViewFragment(url))
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MvcPatternFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }


}