package my.android_study.fragments_main_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import my.android_study.adapters.StudyListAdapter
import my.android_study.databinding.FragmentStudyListBinding

class StudyListFragment : Fragment() {

    private var binding: FragmentStudyListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStudyListBinding.inflate(inflater, container, false)

        val mRecyclerView = binding!!.recyclerView
        val mAdapter = StudyListAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        val lm = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)

        val view = binding!!.root
        return view
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}