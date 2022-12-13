package com.example.petbeer


import android.app.Application
import androidx.lifecycle.*


class BeerViewModel(
    application: Application
) : AndroidViewModel(application), BeerActionListener {
    private val repo: BeerRepo = SQLiteRepo(
        beerActions = AppBd.getInstance(
            context = application
        ).beerActions
    )
   // var currentFavorites = repo.getFavorites()
    val data by repo::data
    var names = repo.getNames()
    var currentBeer = MutableLiveData<Beer?>()
    val navigateToEditScreenEvent = SingleLiveEvent<Array<String>>()
    val navigateToNewScreenEvent = SingleLiveEvent<Unit>()
    val checkEvent = SingleLiveEvent<Unit>()
    val navigateToBeerCardFragment = SingleLiveEvent<Long>()
    val navigateToSellFragment = SingleLiveEvent<Unit>()
    var contentArray: Array<String> = emptyArray()
//    val addStepEvent = SingleLiveEvent<Unit>()
//    var currentSteps = MutableLiveData<List<Step>>(null)
//    //fun getRecipeAndSteps(id: Long) = repo.getRecipeAndSteps(id)
//    val recipe = Recipe("test","test", Categories.ASIAN.name,steps =
//    listOf(Step("testStep",""))
//    )
//    val beer = Beer("Жига",11.0F,175.0F,85.0F,250.0F,0L)
//    fun firstTest() = repo.save(beer)

    fun onSaveClicked(array: Array<String>) {
        if (array[0].isBlank() || array[1].isBlank()) return

        val newbeer =
            currentBeer.value?.copy(
                name = array[0],
                ammount = array[1].toFloat(),
                priceFor0_5 = array[2].toFloat(),
                priceFor1 = array[3].toFloat(),
                priceFor1_5 = array[4].toFloat()
            )
         ?:
            Beer(
                id = BeerRepo.NEWID,
                name = array[0],
                ammount = array[1].toFloat(),
                priceFor0_5 = array[2].toFloat(),
                priceFor1 = array[3].toFloat(),
                priceFor1_5 = array[4].toFloat()
            )
        repo.save(newbeer)
        currentBeer.value = null
    }

    fun beerToMe(name:String) = repo.getBeerByName(name)

    override fun onFabClicked() {
        navigateToNewScreenEvent.call()
    }
//
//
    override fun onDeleteClicked(beer: Beer) {
        repo.delete(beer.id)
    }
//
    override fun onEditClicked(beer: Beer) {
        currentBeer.value = beer
        navigateToEditScreenEvent.value = arrayOf(beer.name,beer.ammount.toString(),
            beer.priceFor0_5.toString(),beer.priceFor1.toString(),beer.priceFor1_5.toString())

    }

    override fun onBeerClicked(id: Long) {
    navigateToBeerCardFragment.value = id

    }

    override fun onSellClicked(beer: Beer) {
        navigateToSellFragment.call()
    }
    fun onCheckClicked(){
        checkEvent.call()
    }

    }



