package com.aio.kotlin.studylist.jetpack.roomdb.view

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.AioApplication
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.FragmentRoomDatabaseBinding
import com.aio.kotlin.studylist.backgroundwork.rx.basic.RxJavaBasicViewModel
import com.aio.kotlin.studylist.jetpack.roomdb.RoomDbUser
import com.aio.kotlin.studylist.jetpack.roomdb.UserDatabase
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import com.aio.kotlin.utils.dialog.DialogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomDbFragment : ViewBindingBaseFragment<FragmentRoomDatabaseBinding>() {

    private val dialogUtils by lazy { DialogUtils }
    private val db by lazy { UserDatabase.getInstance(AioApplication.getAppContext()) }
    private val roomDbAdapter by lazy { RoomDbAdapter() }
    private lateinit var roomDbViewModel: RoomDbViewModel


    override fun getViewBinding(): FragmentRoomDatabaseBinding =
        FragmentRoomDatabaseBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {

        roomDbViewModel = ViewModelProvider(this)[RoomDbViewModel::class.java]

        binding.btnRoomInsert.setOnClickListener {
            showNameAgeDialog()
        }

        binding.rvRoom.run {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = roomDbAdapter.apply {
                onItemClickListener = null
                onButtonClick = {
                        roomDbUser -> roomDbViewModel.deleteUserById(roomDbUser.id)
                }
            }
            addItemDecoration(ExampleItemDecoration(30, 60, 60))

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Flow
                launch {
                    roomDbViewModel.userData.collect {
                        roomDbAdapter.setItemList(it)
                    }
                }


            }
        }
    }

    private fun showNameAgeDialog() {
        val inflater = LayoutInflater.from(activityContext)
        val dialogView = inflater.inflate(R.layout.dialog_roomdb, null)

        val nameEditText: EditText = dialogView.findViewById(R.id.et_name)
        val ageEditText: EditText = dialogView.findViewById(R.id.et_age)

        AlertDialog.Builder(activityContext)
            .setTitle("이름과 나이 입력")
            .setView(dialogView)
            .setPositiveButton("확인") { dialog, _ ->
                val name = nameEditText.text.toString()
                val age = ageEditText.text.toString()
                val roomDbUser = RoomDbUser(name, age)

                if (name.isEmpty() || age.isEmpty()) {
                    dialogUtils.showSingleButtonDialog(
                        activityContext,
                        "Dialog",
                        "빈칸을 체워주세요",
                        "확인",
                        null
                    )
                } else {
                    CoroutineScope(Dispatchers.Main).launch { // 다른애 한테 일 시키
                        roomDbViewModel.insertData(roomDbUser)
                    }

                }

                dialog.dismiss()
            }
            .show()
    }
}