package com.example.foodorderingapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.foodorderingapp.R
import com.example.foodorderingapp.model.FragmentType
import com.example.foodorderingapp.view.adapters.DishAdapter
import com.example.foodorderingapp.viewmodel.MyViewModel

class AppetizerFragment : Fragment() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
    }
    private lateinit var lv: ListView
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_appetizer, container, false)
        btnNext = rootView.findViewById(R.id.btnAppetizerNext)

        val adapter = DishAdapter(
            FragmentType.APPETIZER,
            this,
            viewModel,
            requireContext(),
            R.layout.item_dish
        )
        lv = rootView.findViewById(R.id.lvAppetizer)
        lv.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_appetizerFragment2_to_mainDishFragment2)
        }
    }
}