package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMvvmAdvancedExampleBinding

/**
 * 프로젝트 주제 : 포켓몬
 * 프로젝트 구조 : Three Layer Architecture 혹은 MVVM (UI Layer - Domain Layer - Data Layer)
 *
 * url : https://pokeapi.co/api/v2/pokemon
 * https://pokeapi.co/api/v2/pokemon?offset=10&limit=10  > 10개 뒤에 10개만
 *
 * data :
 *  - network (Api - Retrofit으로 네트워크 통신) - LiveData, Flow 사용예정
 *  - database (Room DB & Dao 를 통해 데이터베이스 사용) - LiveData, Flow 사용예정
 *  - entity : (Dto, Entity - 네트워크 통신이랑 DB에서 가져올 객체 class)
 *  - mapper : (Dto와 Entity를 프로젝트에서 사용될 객체로 변경)
 *  - datasource : (Local, Remote) - DB와 Network에 접근하는 메소드
 *  - Repository : Local & Remote DataSource 를 통합해서 Repository에 넣어준다.
 *
 *  domain : UseCase (사용할지 말지 미정)
 *  ui :
 *  - Fragment & ViewModel (LiveData & Flow)
 *  - RecyclerView를 이용할 예정
 * 
 * Data Stream : LiveData, Flow 사용
 */
class MvvmAdvancedExample :
    DataBindingBaseFragment<FragmentMvvmAdvancedExampleBinding>(R.layout.fragment_mvvm_advanced_example) {

    private lateinit var mvvmAdvancedViewModel: MvvmAdvancedViewModel

    override fun initContentInOnViewCreated() {
        mvvmAdvancedViewModel = ViewModelProvider(this)[MvvmAdvancedViewModel::class.java]
        binding?.viewModel = mvvmAdvancedViewModel
    }
}