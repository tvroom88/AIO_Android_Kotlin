package my.android_study.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.android_study.R
import my.android_study.WebViewActivity
import my.android_study.data.AndroidStudyList
import my.android_study.databinding.FragmentStudyListItemBinding
import my.android_study.models.AndroidStudyItem

class StudyListAdapter() : RecyclerView.Adapter<StudyListAdapter.ViewHolder>() {

    val dataSet = AndroidStudyList.androidStudyList

    class ViewHolder(view: View, dataSet: List<AndroidStudyItem>) : RecyclerView.ViewHolder(view) {

        val itemBinding: FragmentStudyListItemBinding;
        init {
            itemBinding = FragmentStudyListItemBinding.bind(itemView);

            //item 클릭시 웹뷰로 넘어감.
            view.setOnClickListener {
                val pos = absoluteAdapterPosition
                val nextIntent = Intent(view.context, WebViewActivity::class.java)
                nextIntent.putExtra("url", dataSet[pos].url);
                view.context.startActivity(nextIntent)
            }
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
