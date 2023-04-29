package com.example.testapp

import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.testapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var selectedColor: ColorObject
    lateinit var colorAdapter: ArrayAdapter<String>
    lateinit var lineAdapter: ArrayAdapter<Int>

    var colorList = listOf("Black", "White", "Blue", "Green", "Red", "Orange", "Yellow")
    var maxLinesList = listOf(1, 2, 3)
    // var ActionList = listOf("Dismiss", "Next Activity")

    var snackbarText: String? = null
    var snackbarDuration: Int? = null
    var snackbarBackground: String? = null
    var snackbarTextColor: String? = null
    var snackbarMaxLines: Int? = null
    // var snackbarAction: String? = null
    var snackbarActionColor: String? = null

    var TAG = "MAINACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        setupEditTextListeners()
        setupButton()

        //loadColorSpinner()
    }

    private fun setupButton() {
        binding.button.setOnClickListener {
            val snack = Snackbar.make(it, snackbarText ?: "Nothing", snackbarDuration ?: Snackbar.LENGTH_SHORT)

            snack.setBackgroundTint(rgb(28, 12, 34))
            snack.setTextColor(rgb(250, 0, 180))
            snack.setTextMaxLines(snackbarMaxLines ?: 1)
            snack.setActionTextColor(rgb(0, 120, 240))

            snack.show()
        }
    }

    private fun setupEditTextListeners() {
        binding.ETSnackbarText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                snackbarText = binding.ETSnackbarText.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d(TAG, "SnackbarText: $snackbarText")
            }

        })

        binding.ETSnackbarDuration.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                snackbarDuration = binding.ETSnackbarDuration.text.toString().toInt()
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d(TAG, "SnackbarDuration: $snackbarDuration")
            }

        })
    }

    private fun setupSpinners() {
        colorAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, colorList)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        lineAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, maxLinesList)
        lineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Background
        binding.SpinnerBackground.adapter = colorAdapter
        binding.SpinnerBackground.onItemSelectedListener = object:OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                snackbarBackground = colorAdapter.getItem(p2)
                Log.d(TAG, "Background color: $snackbarBackground")
                //Toast.makeText(applicationContext, "Background "+ colorAdapter.getItem(p2), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        // TextColor
        binding.SpinnerTextColor.adapter = colorAdapter
        binding.SpinnerTextColor.onItemSelectedListener = object:OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                snackbarTextColor = colorAdapter.getItem(p2)
                Log.d(TAG, "TextColor: $snackbarTextColor")
                //Toast.makeText(applicationContext, "Textcolor "+ colorAdapter.getItem(p2), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        // Max lines
        binding.SpinnerMaxLines.adapter = lineAdapter
        binding.SpinnerMaxLines.onItemSelectedListener = object:OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                snackbarMaxLines = lineAdapter.getItem(p2)
                Log.d(TAG, "Maxlines: $snackbarMaxLines")
                //Toast.makeText(applicationContext, "Textcolor "+ lineAdapter.getItem(p2), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        // ActionColor
        binding.SpinnerActionColor.adapter = colorAdapter
        binding.SpinnerActionColor.onItemSelectedListener = object:OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                snackbarActionColor = colorAdapter.getItem(p2)
                Log.d(TAG, "ActionColor: $snackbarActionColor")
                //Toast.makeText(applicationContext, "Actioncolor "+ colorAdapter.getItem(p2), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
    }

    private fun loadColorSpinner()
    {
        selectedColor = ColorList().defaultColor
        binding.SpinnerBackground.apply {
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
                {
                    selectedColor = ColorList().basicColors()[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }


}