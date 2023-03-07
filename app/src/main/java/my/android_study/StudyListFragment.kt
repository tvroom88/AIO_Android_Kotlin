package my.android_study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.android_study.adapters.StudyListAdapter

class StudyListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_study_list, container, false)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val mAdapter = StudyListAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        val lm = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)
        return view
    }

}