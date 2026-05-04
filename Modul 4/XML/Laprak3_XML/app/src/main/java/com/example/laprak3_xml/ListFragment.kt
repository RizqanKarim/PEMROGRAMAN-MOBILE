package com.example.laprak3_xml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laprak3_xml.databinding.FragmentListBinding // Sesuaikan package
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CarViewModel
    private lateinit var adapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = CarViewModelFactory("Koleksi Mobil Sport")
        viewModel = ViewModelProvider(this, factory)[CarViewModel::class.java]

        adapter = CarAdapter(emptyList<Car>(),
            onWebClick = { car -> viewModel.onWebButtonClicked(car) },
            onDetailClick = { car -> viewModel.onDetailButtonClicked(car) }
        )

        binding.rvFeaturedCars.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedCars.adapter = adapter

        binding.rvAllCars.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllCars.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.carsData.collect { listMobil ->
                        adapter.updateData(listMobil)
                    }
                }

                launch {
                    viewModel.navigateToDetail.collect { car ->
                        if (car != null) {
                            val bundle = Bundle().apply { putInt("carId", car.id) }
                            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
                            viewModel.onNavigationDone()
                        }
                    }
                }

                launch {
                    viewModel.navigateToWeb.collect { url ->
                        if (url != null) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                            viewModel.onNavigationDone()
                        }
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}