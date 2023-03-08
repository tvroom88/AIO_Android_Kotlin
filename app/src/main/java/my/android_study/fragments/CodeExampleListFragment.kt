package my.android_study.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import my.android_study.adapters.CodeExampleListAdapter
import my.android_study.databinding.FragmentCodeExampleListBinding

class CodeExampleListFragment : Fragment() {

    private var binding: FragmentCodeExampleListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCodeExampleListBinding.inflate(inflater, container, false)

        val mRecyclerView = binding!!.exampleRecyclerView
        val mAdapter = CodeExampleListAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        val lm = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)

        val view = binding!!.root
        return view
    }
}