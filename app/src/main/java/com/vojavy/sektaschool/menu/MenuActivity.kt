package com.vojavy.sektaschool.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vojavy.sektaschool.R

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().addToBackStack( "menu" ).commit()
    }

    private fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    private fun AppCompatActivity.deleteFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction{remove(fragment)}
    }

    fun replaceFragmentHelper(fragment: Fragment){
        replaceFragment(fragment, R.id.fragmet_cv_menu)
    }

    fun addFragmentHelper(fragment: Fragment){
        addFragment(fragment, R.id.fragmet_cv_menu)
    }

    fun removeFragmentHelper(fragment: Fragment){
        deleteFragment(fragment)
    }
}