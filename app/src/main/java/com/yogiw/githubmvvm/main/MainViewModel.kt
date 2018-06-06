package com.yogiw.githubmvvm.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import android.widget.Toast
import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.source.MainDataRepository
import com.yogiw.githubmvvm.data.source.MainDataSource
import internship.gits.newsapps.util.SingleLiveEvent


class MainViewModel(application: Application, private val mainDataRepository: MainDataRepository) : AndroidViewModel(application) {

    var mainDataField: ObservableField<MainData> = ObservableField()
    internal val openRepo = SingleLiveEvent<MainData>()

    fun start(){
        getMainData()
    }

    private fun getMainData(){
        mainDataRepository.getMainData(object : MainDataSource.GetMainDataCallback{
            override fun onDataLoaded(mainData: MainData?) {
                mainDataField.set(mainData)
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show()
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(), "Data not available", Toast.LENGTH_LONG).show()
            }
        })
    }

}