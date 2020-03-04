package com.example.fragmentanimallist

interface Communicator {

    // This method will be used to pass the position on listview to detail fragment
    fun passListPosition(position: Int)
}