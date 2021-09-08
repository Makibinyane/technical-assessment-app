package com.example.technical_assessment_app.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.technical_assessment_app.R
import com.example.technical_assessment_app.databinding.FragmentHomeBinding
import com.example.technical_assessment_app.viewmodel.UserViewModel
import java.math.BigInteger


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var homeFragmentBinding: FragmentHomeBinding


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        homeFragmentBinding = binding

        //handle first, second and third button clicks
        homeFragmentBinding.btnFirst.setOnClickListener {
            viewModel.checkNameOddLetters(homeFragmentBinding.txtName.text.trim().toString())
            viewModel.checkSurnameEvenLetters(homeFragmentBinding.txtSurname.text.trim().toString())
            viewModel.get256Data()

        }
        homeFragmentBinding.btnSecond.setOnClickListener {
            viewModel.displayBinary()
            viewModel.get256Data()

        }

        homeFragmentBinding.btnThird.setOnClickListener {
            val hexString: String = BigInteger(viewModel.getBinaryResult(), 2).toString(16)
            Log.d("DATA", "Hex: $hexString")
            viewModel.get256Data()
        }
    }
}