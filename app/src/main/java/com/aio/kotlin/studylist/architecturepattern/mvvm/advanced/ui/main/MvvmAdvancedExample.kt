package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.main

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMvvmAdvancedExampleBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiStatus
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.RecyclerViewPaginator
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 프로젝트 주제 : 포켓몬
 * 프로젝트 구조 : Three Layer Architecture 혹은 MVVM (UI Layer - Domain Layer - Data Layer)
 *
 * url : https://pokeapi.co/api/v2/pokemon
 * 데이터 url : https://pokeapi.co/api/v2/pokemon?offset=10&limit=10  > 10개 뒤에 10개만
 * 이미지 url : https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png
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
 *
 * 참고 :
 * (1) https://velog.io/@jmseb3/Android-Sealed-Class-%EB%A5%BC-Retrofit-%ED%86%B5%EC%8B%A0with-Hilt-flow
 * - ViewModel에서 StateFlow의 HotStream을 어떤 방식으로 사용할지 참고 가능
 */
@AndroidEntryPoint
class MvvmAdvancedExample :
    DataBindingBaseFragment<FragmentMvvmAdvancedExampleBinding>(R.layout.fragment_mvvm_advanced_example) {

    private lateinit var mvvmAdvancedViewModel: MvvmAdvancedViewModel
    private val pokemonAdapter by lazy { PokemonAdapter() }

    override fun initContentInOnViewCreated() {
        mvvmAdvancedViewModel = ViewModelProvider(this)[MvvmAdvancedViewModel::class.java]
        binding?.viewModel = mvvmAdvancedViewModel

        initView()
        initObserver()
    }

    private fun initView() {
        binding?.apply {
            rvMvvmAdvancedPokemon.let {
                it.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                it.adapter = pokemonAdapter.apply {
                    setItemList(null) // RecyclerView에 데이터 추가
                }
                it.addItemDecoration(ExampleItemDecoration(30, 60, 60))

                /**
                 *     private val isLoading: () -> Boolean,
                 *     private val loadMore: (Int) -> Unit,
                 *     private val onLast: () -> Boolean = { true }
                 */
                it.addOnScrollListener(
                    RecyclerViewPaginator(
                        it,
                        { progressBarIsVisible() },
                        { mvvmAdvancedViewModel.fetchNextPokemonList() },
                        { false }
                    )
                )
            }
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mvvmAdvancedViewModel.pokemonList.collect {
                        when (it.status) {
                            PokemonUiStatus.LOADING -> {
                                showProgressBar()
                            }

                            PokemonUiStatus.SUCCESS -> {
                                showLoadedData(it.data)
                                hideProgressBar()
                            }

                            PokemonUiStatus.ERROR -> {
                                Log.d("aaaaaa", "error : ${it.message}")
                            }
                            PokemonUiStatus.RELOAD -> {}
                        }
                    }
                }
                launch {
                    mvvmAdvancedViewModel.pokemonFetchingIndex.collect {
                        Log.d("pagepage", "page : $it")
                        mvvmAdvancedViewModel.fetchPokemonList()
                    }

                }
            }
        }
    }


    private fun showProgressBar() {
        binding?.pbMvvmAdvancedLoading?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding?.pbMvvmAdvancedLoading?.visibility = View.INVISIBLE
    }

    private fun progressBarIsVisible(): Boolean {
        return binding?.pbMvvmAdvancedLoading?.isVisible ?: false
    }

    private fun showLoadedData(data: List<Pokemon>?) {
        binding?.apply {
            if (data != null) {
                pokemonAdapter.setItemList(data.toMutableList())
            } else {
                pokemonAdapter.setItemList(null)
            }
        }
    }
}