package com.vojavy.sektaschool.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vojavy.sektaschool.R
import com.vojavy.sektaschool.databinding.FragmentMyChildBinding
import com.vojavy.sektaschool.information.ChildInfo
import java.util.*

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.text.format.DateUtils


class MyChildFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var date: Calendar = Calendar.getInstance()

    private var _binding: FragmentMyChildBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyChildBinding.inflate(inflater, container, false)

        with(binding) {

            //инициализация сохранённой ифнормации

            val childInfo : SharedPreferences? = activity?.getSharedPreferences("Child Info", Context.MODE_PRIVATE)

            //создание как хинтом имени ребенка

            etName.hint = childInfo?.getString("name",getString(R.string.enter_name_hint))

            //если имя ребенка есть и информация не пустая, я добавляю его имя в editView

            if (etName.hint != getString(R.string.enter_name_hint)) {
                if (childInfo != null) {
                    etName.setText(childInfo.getString("name", ""))
                }
            }

            //если дата зачатия есть и информация не пустая, я добавляю её в textview

            val i :Long = 69

            if (childInfo!= null && (childInfo.getLong("dateOfCreating", 69) != i)){
                tvEnterDate.text = (
                        DateUtils.formatDateTime(
                            activity,
                            childInfo.getLong("dateOfCreating", 0),
                            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                        )
                )

            }

            // нажатие энтера

            etName.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        etName.clearFocus()
                        etName.isCursorVisible = false
                        return true
                    }
                    return false
                }
            })

            //установка спиннера на нужную позицию, в зависимости от пола

            childInfo?.getInt("gender", 0)?.let { spGender.setSelection(it) }

            //открытие слушателя дат

            var d = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, monthOfYear)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                setInitialDateTime()
            }

            tvEnterDate.setOnClickListener {
                activity?.let {
                    DatePickerDialog(
                        it, d,
                        date[Calendar.YEAR],
                        date[Calendar.MONTH],
                        date[Calendar.DAY_OF_MONTH]
                    )
                        .show()
                }
            }

            //кнопка подтвердить

            btApplyChanges.setOnClickListener(){
                if (etName.text.isNotEmpty()) {
                    var child = ChildInfo(0 , spGender.selectedItemPosition.toByte(), etName.text.toString(), date)
                    context?.let { it1 -> child.saveChildInfo(it1) }
                } else {
                    var child = ChildInfo(
                        0,
                        spGender.selectedItemPosition.toByte(),
                        getString(R.string.enter_name_hint),
                        date
                    )
                    context?.let { it1 -> child.saveChildInfo(it1) }
                }
            }

        }
        return binding.root
    }


    companion object {
        fun newInstance() = MyChildFragment()
    }

    //инициализатор дат

    private fun setInitialDateTime() {
        binding.tvEnterDate.text = (
            DateUtils.formatDateTime(
                activity,
                date.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
            )
        )
    }
}
