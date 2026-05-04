package com.example.laprak3_xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.laprak3_xml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carId = arguments?.getInt("carId")
        val car = carList.find { it.id == carId }

        if (car != null) {
            binding.imgDetail.setImageResource(car.imageResId)
            binding.tvDetailTitle.text = "${car.brand} ${car.model}"
            binding.tvDetailYear.text = "Year: ${car.year}"
            binding.tvDetailDescription.text = car.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}