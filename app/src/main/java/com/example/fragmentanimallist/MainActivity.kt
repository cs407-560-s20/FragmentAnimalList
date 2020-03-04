package com.example.fragmentanimallist

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the List fragment
        // main_container is the id of the framelayout in the activity_main.xml
        // this container is used to show fragment_animal_list and fragment_animal_details in portrait mode
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, AnimalListFragment())
            .addToBackStack(null)
            .commit()


        // If we are in landscape orientation, Load the Detail fragment into the details_container
        // so that both fragments are shown side by side
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_container, AnimalDetailsFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}
