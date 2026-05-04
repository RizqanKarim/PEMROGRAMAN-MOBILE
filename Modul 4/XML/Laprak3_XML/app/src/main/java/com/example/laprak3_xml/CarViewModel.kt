package com.example.laprak3_xml

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class CarViewModel(private val category: String) : ViewModel() {

    private val _carList = MutableStateFlow<List<Car>>(emptyList())
    val carsData: StateFlow<List<Car>> = _carList

    private val _navigateToDetail = MutableStateFlow<Car?>(null)
    val navigateToDetail: StateFlow<Car?> = _navigateToDetail

    private val _navigateToWeb = MutableStateFlow<String?>(null)
    val navigateToWeb: StateFlow<String?> = _navigateToWeb

    init {
        loadData()
    }

    private fun loadData() {
        _carList.value = carList
        Timber.d("Data mobil kategori '$category' berhasil dimuat!")
    }

    fun onWebButtonClicked(car: Car) {
        Timber.tag("EVENT_CLICK").d("Tombol Web ditekan untuk mobil: ${car.brand} ${car.model}")
        _navigateToWeb.value = car.webUrl
    }

    fun onDetailButtonClicked(car: Car) {
        Timber.tag("EVENT_CLICK").d("Tombol Detail ditekan")
        Timber.i("Berpindah ke halaman Detail -> ID: ${car.id}, Brand: ${car.brand}, Model: ${car.model}")
        _navigateToDetail.value = car
    }

    fun onNavigationDone() {
        _navigateToDetail.value = null
        _navigateToWeb.value = null
    }
}

class CarViewModelFactory(private val categoryName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarViewModel(categoryName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}