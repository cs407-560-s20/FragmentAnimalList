package com.example.fragmentanimallist


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_animal_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class AnimalListFragment : Fragment() {

    private val TAG = "AnimalListFragment"

    // Create a list of some strings that will be shown in the listview
    private val animalList = listOf("dog", "cat", "bear", "rabbit")

    // Create an instance of our ViewModel
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_list, container, false)

        // Create an adapter with 3 parameters: activity (this), layout, list
        val myAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, animalList)

        // set the adapter to listview -- MUST USE view in the beginning, otherwise expect a crash!!!
        view.animal_list.adapter = myAdapter



        // Registering setOnItemClickListener that listens item click events in the listview
        view.animal_list.setOnItemClickListener { list, item, position, id ->

            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Log.d(TAG, "$selectedItem")
            //Toast.makeText(activity, "selectedItem: $selectedItem", Toast.LENGTH_SHORT).show();



            //ViewModelProvider returns an existing ViewModel if one exists,
            // or it creates a new one if it does not already exist.
            // Get an instance of our ViewModel using context and MainViewModel,
            // and then set the value (position) in liveData
            viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
            viewModel.position.value = position

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                // We are in portrait orientation
                // Load Detail fragment, i.e., replace listview fragment with detail fragment
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, AnimalDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
            else{
                // We are in landscape orientation
                // Load Detail fragment, i.e., replace the current detail fragment
                // with detail fragment containing the selected item's details
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.details_container, AnimalDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }


        return view
    }


}
