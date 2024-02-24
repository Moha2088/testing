package com.example.testing.Views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.R
import com.example.testing.Repositories.PersonRepositoryImpl
import com.example.testing.ViewModels.PersonViewModel
import com.example.testing.models.Person

class MainActivity : AppCompatActivity() {

    /*  val repo:PersonRepository = PersonRepository()*/

    private lateinit var button: Button
    private lateinit var addButton: Button
    private lateinit var nameBox: EditText
    private lateinit var ageBox: EditText
    private lateinit var occBox:EditText

    private var personRepo:PersonRepositoryImpl = PersonRepositoryImpl()
    private var personViewModel = PersonViewModel(personRepo)

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
                if (personViewModel.personList.count() == 3) {
                        val dialog = AlertDialog.Builder(applicationContext)
                            .setTitle("Too many Users")
                            .setMessage("No more than 3 users can be created! Please wait for users to clear")
                            .setIcon(R.drawable.ic_launcher_background)
                            .setCancelable(false)
                            .show()
                }

                else {
                    try {
                        val user = Person(nameBox.text.toString(), ageBox.text.toString().toInt(),occBox.text.toString())
                        personViewModel.personList.add(user)
                        /*   postController.getPost("https://jsonplaceholder.typicode.com/posts/1")*/
                        val dialog = AlertDialog.Builder(this)
                            .setTitle("User creation successful")
                            .setMessage(
                                "User with name: ${user.name}\n age: ${user.age} and occupation: ${user.occupation} has been created!\n\nTime: ${
                                    user.creationDate
                                }\n\nNumber of users: ${personViewModel.personList.count()}"
                            )
                            .setIcon(R.drawable.ic_launcher_background)
                            .show()
                    }

                    catch (invalid: IllegalArgumentException) {
                        val dialog: AlertDialog = AlertDialog
                            .Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Argument must be of type int, ${invalid.message}")
                            .show()
                    }

                    catch (ex: Exception) {
                        val dialog: AlertDialog = AlertDialog
                            .Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Argument must be of type int, ${ex.message}")
                            .show()
                    }
                }
            }
        }
    }
}