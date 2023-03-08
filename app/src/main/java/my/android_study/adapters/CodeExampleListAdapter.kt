package my.android_study.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.android_study.R
import my.android_study.data.CodeExampleList
import my.android_study.databinding.FragmentStudyListItemBinding
import my.android_study.models.CodeExampleItem

class CodeExampleListAdapter() : RecyclerView.Adapter<CodeExampleListAdapter.ViewHolder>() {
    val dataSet = CodeExampleList.androidStudyList

    class ViewHolder(view: View, dataSet: List<CodeExampleItem>) : RecyclerView.ViewHolder(view) {
        val itemBinding: FragmentStudyListItemBinding;

        init {
            itemBinding = FragmentStudyListItemBinding.bind(itemView);
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_study_list_item, viewGroup, false)
        return ViewHolder(view, dataSet)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemBinding.studyItemTitle.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size
}