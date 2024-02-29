package com.example.testing.Views.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testing.APIInterface
import com.example.testing.Customer
import com.example.testing.R
import com.example.testing.Repositories.PersonRepositoryImpl
import com.example.testing.ViewModels.PersonViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val baseUrl = "http://10.0.2.2:8080/"

    private lateinit var button: Button
    private lateinit var addButton: Button
    private lateinit var getButton:Button
    private lateinit var nameBox: EditText
    private lateinit var ageBox: EditText
    private lateinit var occBox: EditText

    private val repo = PersonRepositoryImpl()


    private val personViewModel: PersonViewModel by lazy {
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
            getButton = findViewById(R.id.getButton)


            addButton.setOnClickListener {


                if(nameBox.text.isNullOrBlank() || ageBox.text.isNullOrBlank() || occBox.text.isNullOrBlank()){
                    AlertDialog.Builder(this)
                        .setTitle("INFO")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setMessage("None of the fields can be empty")
                        .show()
                }

                else {
                    AlertDialog.Builder(this)
                        .setTitle("INFO")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setMessage("User: ${nameBox.text}\n\nAge: ${ageBox.text}\n\nOccupation: ${occBox.text} has been created")
                        .show()
                }
            }

            getButton.setOnClickListener {
                getMyData()
            }
        }
    }

    private fun getMyData() {
        val retroFitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(APIInterface::class.java)


        val retrofitData =retroFitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<Customer>?> {
            override fun onResponse(call: Call<List<Customer>?>, response: Response<List<Customer>?>) {
                val body = response.body()!!
                val builder = StringBuilder()


                    for(data in body){
                        builder.append("Id: ${data.id}\nName: ${data.firstName} ${data.lastName}\nEmail: ${data.email}")
                        builder.append("\n\n")
                    }

                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("GET")
                        .setMessage(builder)
                        .setIcon(R.drawable.ic_launcher_background)
                        .show()
                }

            override fun onFailure(call: Call<List<Customer>?>, t: Throwable) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("INFO")
                    .setMessage(t.message)
                    .show()
            }
        })
    }
}