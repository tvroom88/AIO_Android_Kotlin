package my.android_study.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.android_study.R
import my.android_study.adapters.CodeExampleListAdapter
import my.android_study.adapters.StudyListAdapter

class CodeExampleListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_code_example_list, container, false)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.exampleRecyclerView)
        val mAdapter = CodeExampleListAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        val lm = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)
        return view

    }
}