package com.tfandkusu.learnaac

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoodViewModel : ViewModel() {
    var count = 0
    /**
     * 表示済みフラグ
     */
    var showFlag = false
    val countLiveData = MutableLiveData<Int>()
    val progress = MutableLiveData<Boolean>()

}