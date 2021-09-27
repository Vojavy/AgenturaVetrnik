package com.vojavy.sektaschool.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vojavy.sektaschool.databinding.FragmentMenuBinding
import com.vojavy.sektaschool.MainActivity
import com.vojavy.sektaschool.settings.SettingsFragment


class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            cvArticles.setOnClickListener() {

            }

            cvCalendar.setOnClickListener(){

            }

            cvMainMenu.setOnClickListener() {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }

            cvMainMenuContactSupport.setOnClickListener(){

            }

            cvSettings.setOnClickListener(){
                (activity as MenuActivity?)?.replaceFragmentHelper(SettingsFragment())
            }

            cvAboutCompany.setOnClickListener(){

            }
            cvMyChild.setOnClickListener(){
                (activity as MenuActivity?)?.replaceFragmentHelper(MyChildFragment())
            }

        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MenuFragment()
    }

    //переключение фрагметов



}