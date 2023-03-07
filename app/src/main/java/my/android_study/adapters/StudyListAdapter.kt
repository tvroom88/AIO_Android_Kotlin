package my.android_study.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.android_study.R
import my.android_study.WebViewActivity
import my.android_study.data.AndroidStudyList
import my.android_study.models.AndroidStudyItem


class StudyListAdapter() : RecyclerView.Adapter<StudyListAdapter.ViewHolder>() {
    val dataSet = AndroidStudyList.androidStudyList

    class ViewHolder(view: View, dataSet: List<AndroidStudyItem>) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.study_item_title)

            //item 클릭시 웹뷰로 넘어감.
            view.setOnClickListener {
                val pos = absoluteAdapterPosition
                val nextIntent = Intent(view.context, WebViewActivity::class.java)
                Log.e("hi", pos.toString())
                nextIntent.putExtra("url", dataSet[pos].url);
                view.context.startActivity(nextIntent)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_study_list_item, viewGroup, false)

        return ViewHolder(view, dataSet)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].title
    }

    // Return the size of your dataset (invoked by the layout manager)

    override fun getItemCount() = dataSet.size
}
