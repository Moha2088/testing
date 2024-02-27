package com.example.testing.Views.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testing.R
import com.example.testing.ViewModels.PersonViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var button: Button
    private lateinit var addButton: Button
    private lateinit var nameBox: EditText
    private lateinit var ageBox: EditText
    private lateinit var occBox: EditText

    private val personViewModel:PersonViewModel by lazy{
        ViewModelProvider(this).get(PersonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        button.text = getString(R.string.Add_User)
        button.setOnClickListener {
            setContentView(R.layout.newlayout)
            nameBox = findViewById(R.id.nameBox)
            ageBox = findViewById(R.id.ageBox)
            occBox = findViewById(R.id.occBox)
            addButton = findViewById(R.id.addButton)


            addButton.setOnClickListener {
                AlertDialog.Builder(this)
                    .setTitle("INFO")
                    .setMessage("User: ${nameBox.text}\n\nAge: ${ageBox.text}\n\nOccupation:" +
                            " ${occBox.text} has been created")
                    .show()
            }
        }
    }
}