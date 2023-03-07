package my.android_study.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.android_study.R
import my.android_study.data.CodeExampleList
import my.android_study.models.CodeExampleItem

class CodeExampleListAdapter() : RecyclerView.Adapter<CodeExampleListAdapter.ViewHolder>() {
    val dataSet = CodeExampleList.androidStudyList

    class ViewHolder(view: View, dataSet: List<CodeExampleItem>) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.study_item_title)

            //item 클릭시 웹뷰로 넘어감.
            view.setOnClickListener {
//                val pos = absoluteAdapterPosition
//                val nextIntent = Intent(view.context, WebViewActivity::class.java)
//                view.context.startActivity(nextIntent)
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


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size

}