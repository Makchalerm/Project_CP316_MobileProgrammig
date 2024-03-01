package com.example.calculatorcalorie.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorcalorie.API.AdapterAPI
import com.example.calculatorcalorie.API.webService
import com.example.calculatorcalorie.R
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AboutFragment() : Fragment() {
    lateinit var recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //api
        recyclerView = requireView().findViewById(R.id.rowFood)
        recyclerView!!.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Makchalerm/calorie/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                val postAPI = retrofit.create<webService>(webService::class.java)
        val response = postAPI.getPosts()
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe{
                recyclerView!!.adapter = activity?.let { it1 -> AdapterAPI(it, it1) }
            }
    }
}





