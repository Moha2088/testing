package com.example.testing

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.Controllers.PostController
import com.example.testing.models.Person
import java.util.Date

class MainActivity : AppCompatActivity() {

    /*  val repo:PersonRepository = PersonRepository()*/

    private lateinit var button: Button
    private lateinit var addButton: Button
    private lateinit var nameBox: EditText
    private lateinit var ageBox: EditText
    private var postController = PostController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.text = "Add User"
        button.setOnClickListener {
            setContentView(R.layout.newlayout)
            nameBox = findViewById(R.id.nameBox)
            ageBox = findViewById(R.id.ageBox)
            addButton = findViewById(R.id.addButton)

            addButton.setOnClickListener {
                try {
                    val user = Person(nameBox.text.toString(), ageBox.text.toString().toInt())
                    /*   postController.getPost("https://jsonplaceholder.typicode.com/posts/1")*/
                    val dialog = AlertDialog.Builder(this)
                        .setTitle("User creation successful")
                        .setMessage(
                            "User with name: ${user.name}\n age: ${user.age} has been created!\n\nTime: ${
                                Date()
                                    .toString()
                                    .substring(0, 19)
                            }"
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