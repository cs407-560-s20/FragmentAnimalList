package com.example.fragmentanimallist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_animal_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class AnimalDetailsFragment : Fragment() {

    private val TAG = "AnimalDetailsFragment"

    private val ANIMAL_NAME_LIST = listOf("Dog", "Cat", "Bear", "Rabbit")

    // Source wikipedia
    private val ANIMAL_DETAILS = listOf(

        "Dogs' typical lifespan varies widely among breeds, but for most the median longevity, " +
                "the age at which half the dogs in a population have died and half are still alive, " +
                "ranges from 10 to 13 years. Individual dogs may live well beyond the median of their breed.",

        "Cats' average lifespan of has risen in recent decades. In the early 1980s, " +
                "it was about seven years, rising to 9.4 years in 1995 15.1 years in 2018. " +
                "Some cats have been reported as surviving into their 30s, with the oldest known cat, " +
                "Creme Puff, dying at a verified age of 38",

        "Bears can live up to 25 years of age in the wild. The oldest wild brown bear on record was " +
                "nearly 37 years old.The oldest recorded female in captivity was nearly 40 years old, " +
                "while males in captivity have been verified to live up to 47 years, with one " +
                "captive male possibly attaining 50 years of age.",

        "Rabbits (Spayed or neutered) kept indoors with proper care may have a lifespan of 8 to 12 " +
                "years, with mixed-breed rabbits typically living longer than purebred specimens, " +
                "and dwarf breeds having longer average lifespans than larger breeds." +
                "The world record for longest-lived rabbit is 18 years."
    )


    // Create an instance of our ViewModel
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_details, container, false)


        //ViewModelProvider returns an existing ViewModel if one exists,
        // or it creates a new one if it does not already exist.
        // Get an instance of our ViewModel by passing context and MainViewModel class,
        // then put the value (position) in liveData
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        var position = 0 // default value to select the first index if no position

        // this observer will be updated when the value of position changes
        viewModel.position.observe(requireActivity(), Observer {
            Log.d(TAG, "position: $it")
            position = it
        })

        // Set the text to tetviews after getting the selected position
        view.animal_name.text = ANIMAL_NAME_LIST[position]
        view.animal_details.text = ANIMAL_DETAILS[position]

        // Based on the index of position selected, set the corresponding image
        val imageId = when(position){
            0 -> R.drawable.dog
            1 -> R.drawable.cat
            2 -> R.drawable.bear
            else -> R.drawable.rabbit
        }

        view.animal_image.setImageResource(imageId)

        return view
    }

}
