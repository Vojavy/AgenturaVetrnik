package com.vojavy.sektaschool.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vojavy.sektaschool.R
import com.vojavy.sektaschool.databinding.FragmentDatesBinding
import com.vojavy.sektaschool.information.ChildInfo


class DatesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    private var _binding: FragmentDatesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatesBinding.inflate(inflater, container, false)
        with(binding){

        }

        return binding.root
    }

    companion object {
        fun newInstance() = DatesFragment()
    }
}