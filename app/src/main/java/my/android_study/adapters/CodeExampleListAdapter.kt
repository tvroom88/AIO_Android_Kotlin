package my.android_study.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.android_study.CodeExampleActivity
import my.android_study.R
import my.android_study.WebViewActivity
import my.android_study.data.CodeExampleList
import my.android_study.databinding.FragmentStudyListItemBinding
import my.android_study.models.CodeExampleItem

class CodeExampleListAdapter() : RecyclerView.Adapter<CodeExampleListAdapter.ViewHolder>() {
    val dataSet = CodeExampleList.androidStudyList

    @SuppressLint("ClickableViewAccessibility")
    class ViewHolder(view: View, dataSet: List<CodeExampleItem>) : RecyclerView.ViewHolder(view) {
        val itemBinding: FragmentStudyListItemBinding;

        init {
            itemBinding = FragmentStudyListItemBinding.bind(itemView);
            //item 터치시 웹뷰로 넘어감.
            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //눌렀을때
                        view.setBackgroundColor(Color.parseColor("#ADD8E6"))
                        true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        true
                    }

                    MotionEvent.ACTION_UP -> { //손을 땟을때
                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        val nextIntent = Intent(view.context, CodeExampleActivity::class.java)
                        view.context.startActivity(nextIntent)
                        true
                    }

                    else -> {
                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        val nextIntent = Intent(view.context, CodeExampleActivity::class.java)
                        view.context.startActivity(nextIntent)
                        false
                    }
                }
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