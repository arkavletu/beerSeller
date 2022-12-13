package com.example.petbeer

interface BeerActionListener {
    fun onFabClicked()
    fun onDeleteClicked(beer:Beer)
    fun onEditClicked(beer: Beer)
    fun onBeerClicked(id:Long)
    fun onSellClicked(beer: Beer)
}