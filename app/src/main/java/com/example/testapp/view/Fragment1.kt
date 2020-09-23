package com.example.testapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.model.data.CatImage
import com.example.testapp.model.service.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment() {

    private lateinit var subscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {arguments = Bundle().apply {}}
    }

    fun loadCatImage() {
        fetchCatImage()
    }

    fun refreshCatImage() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }

        fetchCatImage()
    }

    private fun fetchCatImage() {
        subscription = ServiceBuilder.buildCatImageService().getCatImage()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this.activity,t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: CatImage) {
        loadImage(response.file)
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            //.placeholder()
            .into(imageView)
    }
}