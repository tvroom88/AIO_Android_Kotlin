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
import my.android_study.models.AndroidStudyItem

class StudyListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Todo : 잠깐 넣은 부분 지울 예정
        val studyList = dummyData()

        val view: View = inflater.inflate(R.layout.fragment_study_list, container, false)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val mAdapter = StudyListAdapter(studyList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))


        val lm = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)
        return view
    }

    //Todo : 잠깐 넣은 부분 지울 예정
    private fun dummyData(): ArrayList<AndroidStudyItem> {
        val studyList = arrayListOf<AndroidStudyItem>()
        studyList.add(AndroidStudyItem("1", ""))
        studyList.add(AndroidStudyItem("2", ""))
        studyList.add(AndroidStudyItem("3", ""))
        return studyList
    }
}