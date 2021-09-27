package com.vojavy.sektaschool.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vojavy.sektaschool.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding:FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val childInfo : SharedPreferences? = activity?.getSharedPreferences("Child Info", Context.MODE_PRIVATE)

        with(binding){

            btDelInformation.setOnClickListener(){
                childInfo?.edit()?.clear()?.apply()
                Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}