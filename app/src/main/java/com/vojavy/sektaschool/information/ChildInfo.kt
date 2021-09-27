package com.vojavy.sektaschool.information

import android.content.Context
import android.content.SharedPreferences
import java.util.*


class ChildInfo(private val childNum: Byte, private val gender: Byte, val name: String, private val dateOfCreating: Calendar)  {

    fun  getChildInfo(){
        //копирование даты из бд

    }


    fun saveChildInfo(context: Context) {

            //сохранение во внутреннюю память информации о ребёнке

            val settings = context.getSharedPreferences("Child Info", Context.MODE_PRIVATE)
            val editor = settings.edit()


            editor.putInt("childNum", childNum.toInt())
            editor.putString("name", name)
            editor.putInt("gender", gender.toInt())
            editor.putLong("dateOfCreating", dateOfCreating.timeInMillis)

            editor.apply()

            //запрос в Модуль работы с бд для сохранения

    }
}