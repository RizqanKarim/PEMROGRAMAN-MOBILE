package com.example.laprak3_xml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laprak3_xml.databinding.ItemCarBinding

class CarAdapter(
    private val carList: List<Car>,
    private val onWebClick: (String) -> Unit,
    private val onDetailClick: (Int) -> Unit
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

            btnWeb.setOnClickListener { onWebClick(car.webUrl) }
            btnDetail.setOnClickListener { onDetailClick(car.id) }
        }
    }

    override fun getItemCount(): Int = carList.size
}