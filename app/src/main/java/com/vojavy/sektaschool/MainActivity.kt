package com.vojavy.sektaschool

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vojavy.sektaschool.databinding.ActivityMainBinding
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.vojavy.sektaschool.articles.Article
import com.vojavy.sektaschool.articles.ArticleAdapter
import com.vojavy.sektaschool.information.ChildInfo
import com.vojavy.sektaschool.menu.MenuActivity
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter = ArticleAdapter()

    var articleServerList = arrayListOf<Article>()




//Данные надо брать из БД, и вообще не тут
    var article1 = Article("Как ухаживать за детьми", 1, "БлаблаблаблаблаблабБлаблаблаблаблаблабБлаблаблаблаблаблаб", 1)
    var article2 = Article("Он и она", 2, "asd", 2)
    var article3 = Article("Testting", 3, " asd", 3)
    var article4 = Article("Выбор коляски", 1, " Нужно брать тупо лучшую", 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //затычка для проверки RV
        articleServerList.add(article1)
        articleServerList.add(article2)
        articleServerList.add(article3)
        articleServerList.add(article4)
        adapter.addAll(articleServerList)

        init()


        //Кнопка меню

        binding.btMenu.setOnClickListener(){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()

        //Тут я меняю TV с именем, полом и т.д.
        val settings : SharedPreferences = this.getSharedPreferences("Child Info", Context.MODE_PRIVATE)
        if(settings.contains("name") && settings.getString("name", "")!= getString(R.string.enter_name_hint)) {
            //сделать проверку на то, какого размера string, чтобы можно было менять размер шрифта
            binding.tvNamedite.text = settings.getString("name", "")
        }else {
            binding.tvNamedite.text = getString(R.string.infomation_is_null)
        }


          if(settings.contains("gender")) {
              when (settings.getInt("gender", 0)) {
                 0 -> binding.tvGender.text = getString(R.string.infomation_is_null)
                 1 -> binding.tvGender.text = getString(R.string.woman)
                 2 -> binding.tvGender.text = getString(R.string.man)
              }
          }else {
              binding.tvGender.text = getString(R.string.infomation_is_null)
          }

        childDate(this)

    }

// Инициализация адаптера RV
    private fun init() = with(binding){
        rvArticles.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        rvArticles.adapter = adapter
    }

    //получаем дату ребенка

    private fun childDate(context: Context){
        val dateHolder : SharedPreferences = context.getSharedPreferences("Child Info", Context.MODE_PRIVATE)
        val date : Long = dateHolder.getLong("dateOfCreating", 0)
        if (date != 0.toLong()){
            magicWithDates(date)
        } else{
            binding.ivExample.setImageResource(R.drawable.kkk)
            binding.tvExample.text = getString(R.string.infomation_is_null)
        }
        //в зависимости от даты, работа и создание всяких приколов (он по размеру как большое яйцо или яичко поменьше, и т.д.)
    }

    //обрабатываем дату

    private fun magicWithDates(date: Long){
        /** Я ваще хуй значет чё там по срокам и процессу формирования плода.
         * Пока что проверка на 9 месяцев и всё мне похуй идите нахуй.
         * Мне вообще лень гуглить и по хорошему, я пизжу сам с собой,
         * чисто потому что эту хуету никто не увидит, одиночесво сволочь(
          */
        var calendarStartDate = Calendar.getInstance()
        val calendarCurrent = Calendar.getInstance()
        calendarStartDate.set((Date(date).year)+1900,Date(date).month,Date(date).date, 0, 0, 1)

        Log.v("ada", calendarCurrent.get(Calendar.YEAR).toString())
        Log.v("ada", "asd")

        /*
        var j : String = calendarCurrent.get(Calendar.YEAR).toString()
        var jj : String = calendarStartDate.get(Calendar.YEAR).toString()

        if ((j.toInt() - jj.toInt())!= 0){
         calendarTest.add(Calendar.MONTH, - calendarStartDate.get(Calendar.MONTH))
        }
        val monthCount: String = calendarTest.get(Calendar.YEAR).toString() + " " + calendarTest.get(Calendar.MONTH)


         Log.v("ada", monthCount)
         */

        when (calendarDaysBetween(calendarStartDate, calendarCurrent)){
            in 0..10 -> {
                binding.ivExample.setImageResource(R.drawable.ic_wishnia)
                binding.tvExample.text = "Tresnicka"
                binding.tvVahavaluebl.text = "1 g"
                binding.tvVyshkavalue.text = "1 sm"
            }
            else -> {
                binding.ivExample.setImageResource(R.drawable.pngegg)
                binding.tvExample.text = "Vodní meloun"
                binding.tvVahavaluebl.text = "1000 g"
                binding.tvVyshkavalue.text = "50 sm"
            }
        }
    }
    private fun calendarDaysBetween(startCal: Calendar, endCal: Calendar): Long {

        // Create copies so we don't update the original calendars.
        val start = Calendar.getInstance()
        start.timeZone = startCal.timeZone
        start.timeInMillis = startCal.timeInMillis
        val end = Calendar.getInstance()
        end.timeZone = endCal.timeZone
        end.timeInMillis = endCal.timeInMillis

        // Set the copies to be at midnight, but keep the day information.
        start[Calendar.HOUR_OF_DAY] = 0
        start[Calendar.MINUTE] = 0
        start[Calendar.SECOND] = 0
        start[Calendar.MILLISECOND] = 0
        end[Calendar.HOUR_OF_DAY] = 0
        end[Calendar.MINUTE] = 0
        end[Calendar.SECOND] = 0
        end[Calendar.MILLISECOND] = 0

        // At this point, each calendar is set to midnight on
        // their respective days. Now use TimeUnit.MILLISECONDS to
        // compute the number of full days between the two of them.
        return TimeUnit.MILLISECONDS.toDays(
            abs(end.timeInMillis - start.timeInMillis)
        )
    }
}