package com.example.petbeer

import androidx.lifecycle.LiveData

interface BeerRepo {
    val data: LiveData<List<Beer>>
    fun save(beer: Beer)
    fun delete(id:Long)
    fun getNames(): LiveData<Array<String>>
    fun getBeerByName(name:String):Beer
    companion object{
        const val NEWID = 0L
    }
}