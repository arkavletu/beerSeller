package com.example.petbeer


import androidx.lifecycle.LiveData
import androidx.lifecycle.map

class SQLiteRepo(
    val beerActions: BeerActions
): BeerRepo {
    override var data = beerActions.getAll().map { entities ->
        entities.map { it.toModel() }
    }


//    override fun getStepsForRecipe(id: Long): LiveData<List<Step>> =
//        recipeActions.getStepsForRecipe(id).map { entities ->
//            entities.map { it.toStepModel() }
//        }

//
//    override fun like(rId: Long) {
//        recipeActions.addToFavorites(rId)
//    }
//
    override fun delete(rId: Long) {
        beerActions.removeById(rId)
        //recipeActions.removeByRecId(rId)
    }

    override fun getNames(): LiveData<Array<String>> =
        beerActions.getNames()

    override fun getBeerByName(name: String): Beer =
        beerActions.getBeerByName(name).toModel()


    //
    override fun save(beer: Beer) {
        if (beer.id == BeerRepo.NEWID) beerActions.insert(beer.toEntity())
        else beerActions.updateContentById(beer.id,beer.ammount)
        //return beer.toEntity()
    }
//
//    override fun getFavorites(): LiveData<List<Recipe>> =
//        recipeActions.getFavorites().map{
//            it.map {
//                it.toModel()
//            }
//        }
//
//
//    override fun getFiltered(category:String): LiveData<List<Recipe?>> =
//        recipeActions.selectOneCategory(category).map{
//            it.map {
//                it.toModel()
//            }
//        }
//    override fun getFilteredFavorites(category:String): LiveData<List<Recipe?>> =
//        recipeActions.selectOneCategoryFromFavorites(category).map{
//            it.map {
//                it.toModel()
//            }
//        }
//
//    override fun addStep(steps: List<Step>,id:Long){
//        recipeActions.addStep(steps,id)
//    }
//
//    override fun get(id: Long): RecipeEntity =
//        recipeActions.getById(id)
//

}