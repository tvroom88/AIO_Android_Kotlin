package com.aio.kotlin.studylist.jetpack.roomdb.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.AioApplication
import com.aio.kotlin.studylist.jetpack.roomdb.RoomDbUser
import com.aio.kotlin.studylist.jetpack.roomdb.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomDbViewModel : ViewModel() {

    private val db by lazy { UserDatabase.getInstance(AioApplication.getAppContext()) }

    // DB에 넣는 부분
    suspend fun insertData(roomDbUser: RoomDbUser) {
        withContext(Dispatchers.IO) {
            db!!.userDao().insert(
                RoomDbUser(
                    name = roomDbUser.name,
                    age = roomDbUser.age
                )
            )
        }
    }

    /**
     * StateFlow : Case1
     * 초기 값 반드시 필요.
     */
    private var _userListWithStateFlow = MutableStateFlow<List<RoomDbUser>>(emptyList())
    val userListWithStateFlow: StateFlow<List<RoomDbUser>> = _userListWithStateFlow.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            val data = getAllData() // 실제 데이터 가져오기
            data.collect { res ->
                _userListWithStateFlow.value = res
            }
        }
    }

    fun getAllData(): Flow<List<RoomDbUser>> {
        val userData = db!!.userDao().getAll()
        return userData
    }

    /**
     * StateFlow : Case2
     * Flow에는 stateIn()이라는 기능이 있다. stateIn은 Cold인 Flow를 Hot인 StateFlow로 변환하는 값이다.
     */
    val userData: StateFlow<List<RoomDbUser>> = getAllDataForStateFlow()
        .stateIn(
            scope = viewModelScope,           // CoroutineScope
            started = SharingStarted.WhileSubscribed(1000),  // Flow 시작 시점 설정
            initialValue = emptyList()
        )

    private fun getAllDataForStateFlow(): Flow<List<RoomDbUser>> {
        val userData = db!!.userDao().getAll()
        return userData
    }


    fun deleteUserById(id:Int){
        Log.d("aaaaaaaaaaaaaaaaaa", "id : $id")

        try{
            viewModelScope.launch(Dispatchers.IO) {
                db?.userDao()?.deleteUserById(id)
            }
        }catch (e:Exception){
            Log.d("aaaaaaaaaaaaaaaaaa", "error : $e")

        }
    }
}