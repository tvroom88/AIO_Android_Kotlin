package my.android_study.fragments_code_examples.mvc_pattern

import MvcModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.android_study.R
import my.android_study.databinding.FragmentMvcPatternBinding
import java.util.*

class MvcPatternFragment : Fragment(), Observer, View.OnClickListener  {

    private lateinit var binding: FragmentMvcPatternBinding

    private var myModel: MvcModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMvcPatternBinding.inflate(inflater, container, false)

        myModel = MvcModel()
        myModel!!.addObserver(this);

        binding.button.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);

        val view = binding.root
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button ->  myModel?.setValueAtIndex(0)
            R.id.button2 -> myModel?.setValueAtIndex(1)
            R.id.button3 -> myModel?.setValueAtIndex(2)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun update(arg0: Observable, arg1: Any?) {
        binding.button.text = "Count:${myModel!!.getValueAtIndex(0)}"
        binding.button2.text = "Count:${myModel!!.getValueAtIndex(1)}"
        binding.button3.text = "Count:${myModel!!.getValueAtIndex(2)}"
    }
}