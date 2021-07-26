package com.apolis.testingdemo

import android.content.DialogInterface
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.testingdemo.model.Repository
import com.apolis.testingdemo.model.UiModel
import com.apolis.testingdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.repository = repository

        val button = findViewById<Button>(R.id.doSomeAction)
        button.setOnClickListener {
            viewModel.incrementCount()
        }

        val title = findViewById<TextView>(R.id.title)
        val countTextView = findViewById<TextView>(R.id.textViewCount)
        title.setOnClickListener {
            changeTitleDialog()
        }

        viewModel.liveData.observe(this, Observer {

            when(it) {

               is UiModel.TitleUpdate -> {

                   title.text = it.title

                }

                is UiModel.CountUpdate -> {

                    countTextView.text = it.count.toString()

                }
            }

        })
    }

    private fun changeTitleDialog() {

        AlertDialog.Builder(this).apply {

            setTitle("Change Title")
            val inputBox = EditText(this@MainActivity)

            val density = Resources.getSystem().displayMetrics.density
            val padding = Math.round(16 * density)

            val layout = FrameLayout(this@MainActivity)
            layout.setPadding(padding, 0, padding, 0 )
            layout.addView(inputBox)

            setView(layout)

            setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                viewModel.setTitle(inputBox.text.toString())
            }

            setNegativeButton("CANCEL", { dialogInterface: DialogInterface, i: Int ->
            })

            create().show()
        }

    }
}