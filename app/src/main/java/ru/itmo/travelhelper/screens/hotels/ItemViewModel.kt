//package ru.itmo.travelhelper.screens.hotels
//
//import android.view.View
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class ItemViewModel : ViewModel() {
//    private val mutableSelectedItem = MutableLiveData<String>()
//    val selectedItem: LiveData<String> get() = mutableSelectedItem
//
//    fun selectItem(item: View) {
//        mutableSelectedItem.value = item
//    }
//}