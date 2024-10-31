package com.aio.kotlin.studylist.backgroundwork.rx.edittext

import android.util.Log
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxAndEtBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxAndEtFragment : ViewBindingBaseFragment<FragmentRxAndEtBinding>() {

    private lateinit var disposable: Disposable
    private lateinit var debounceButtonDisposable: Disposable
    private lateinit var throttleButtonDisposable: Disposable

    private var mDebounceButtonClicks = 0
    private var mThrottleButtonClicks = 0

    override fun getViewBinding(): FragmentRxAndEtBinding {
        return FragmentRxAndEtBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {

        val editTextChangeObservable = binding.etRxEdit.textChanges()

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: String) {
                binding.tvRxEditResult.text = t  // EditText 내용에 따른 TextView 변경
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        }

        binding.tvRxEditClickWoDebounce.setOnClickListener {
            Log.d("DebounceClick", "Button clicked! 123")

        }

        // Observable
        editTextChangeObservable
            .skip(1) // 처음 방출되는 빈 값을 건너뜁니다.
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() } // CharSequence를 String으로 변환
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

        debounceButtonDisposable = binding.bntRxEditWoDebounce.clicks()// Debounce
            .debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("DebounceClick", "Button clicked!")
                binding.tvRxEditClickWoDebounce.text = (++mDebounceButtonClicks).toString()
            }, { it.printStackTrace() })

        throttleButtonDisposable = binding.bntRxEditWoThrottle.clicks() // throttleFirst
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("ThrottleClick", "Button clicked!")
                binding.tvRxEditClickWoThrottle.text = (++mThrottleButtonClicks).toString()
            }, { it.printStackTrace() })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        throttleButtonDisposable.dispose()
        debounceButtonDisposable.dispose()
    }
}
