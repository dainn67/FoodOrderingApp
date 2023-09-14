package com.example.foodorderingapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.foodorderingapp.R
import com.example.foodorderingapp.view.adapters.ResultAdapter
import com.example.foodorderingapp.viewmodel.MyViewModel

class ResultFragment : Fragment() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
    }
    private lateinit var lv: ListView
    private lateinit var tvTotal: TextView
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_result, container, false)

        btnNext = rootView.findViewById(R.id.btnResultOrder)
        btnBack = rootView.findViewById(R.id.btnResultBack)

        val adapter = ResultAdapter(
            requireContext(),
            R.layout.item_result,
            viewModel.getResultList(),
            viewModel.getResultMap()
        )
        lv = rootView.findViewById(R.id.lvRes)
        lv.adapter = adapter

        tvTotal = rootView.findViewById(R.id.tvTotal)

        var price = 0.0
        for(dish in viewModel.getResultList()){
            price += viewModel.getResultMap()[dish]?.times(dish.getPrice()) ?: 0.0
        }
        tvTotal.text = "Total Money: $${viewModel.getTotal()}"

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener {
            Toast.makeText(requireContext(), "Order successfully", Toast.LENGTH_SHORT).show()
            btnNext.text = "Reorder"
            btnNext.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_resultFragment2_to_appetizerFragment2)
                viewModel.resetAll()
            }
        }

        btnBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_resultFragment2_to_dessertFragment2)
        }
    }
}