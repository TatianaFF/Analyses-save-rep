package com.example.myapp.screens.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapp.R
import com.example.myapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_fragment)
        val config = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, config)

//        applicationContext.openFileOutput("exp1.txt", Context.MODE_PRIVATE).use {
//            it.write("text of file".toByteArray())
//        }

//        val path = this.getExternalFilesDir(null)
//        val folder = File(path, "folder")
//        folder.mkdirs()
//        Toast.makeText(this, folder.exists().toString(), Toast.LENGTH_SHORT).show()
//        val file = File(folder, "file_name.txt")
//        file.appendText("data")


    }
}