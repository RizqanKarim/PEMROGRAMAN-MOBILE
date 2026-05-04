package com.example.laprak3_xml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laprak3_xml.databinding.ItemCarBinding

class CarAdapter(
    private var carList: List<Car>,
    private val onWebClick: (Car) -> Unit,
    private val onDetailClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        with(holder.binding) {
            imgCar.setImageResource(car.imageResId)
            tvBrandYear.text = "${car.brand} - ${car.year}"
            tvModelEngine.text = "${car.model} | ${car.engine}"

            btnWeb.setOnClickListener { onWebClick(car) }
            btnDetail.setOnClickListener { onDetailClick(car) }
        }
    }

    override fun getItemCount(): Int = carList.size

    fun updateData(newList: List<Car>) {
        this.carList = newList
        notifyDataSetChanged()
    }
}