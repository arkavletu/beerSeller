package com.example.petbeer


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeerActions {

    @Query("SELECT * FROM beer ORDER BY id DESC")
    fun getAll(): LiveData<List<BeerEntity>>

    @Query("SELECT * FROM beer WHERE name = :name")
    fun getBeerByName(name:String): BeerEntity

    @Query("SELECT name FROM beer")
    fun getNames(): LiveData<Array<String>>
//
    @Insert
    fun insert(beer: BeerEntity)
//
////    @Insert
////    fun insertStep(step: StepEntity)
////
////    @Query("SELECT * FROM steps WHERE recipeId = :id")//order by?
////    fun getStepsForRecipe(id: Long): LiveData<List<StepEntity>>
//
    @Query("UPDATE beer SET ammount = :ammount WHERE id = :id")
    fun updateContentById(id: Long, ammount: Float)
//
//
//
////    @Query("UPDATE steps SET recipeId = :id WHERE id = 0")
////    fun updateStepRecipeId(id: Long)
//
        fun save(beer: Beer) =
        if (beer.id == 0L) insert(beer.toEntity()) else updateContentById(beer.id, beer.ammount)
//
//    @Query("""
//        UPDATE recipes SET
//        isFavorite = CASE WHEN isFavorite THEN 0 ELSE 1 END
//        WHERE id = :id
//        """)
//    fun addToFavorites(id: Long)
//
    @Query("DELETE FROM beer WHERE id = :id")
    fun removeById(id: Long)
//
//    @Query("SELECT*FROM recipes WHERE id = :id")
//    fun getById(id:Long): RecipeEntity
//
//    @Query("UPDATE recipes SET steps = :steps WHERE id = :id")
//    fun addStep(steps:List<Step>,id:Long)
//
//    @Query("SELECT*FROM recipes WHERE category = :category")
//    fun selectOneCategory(category: String):LiveData<List<RecipeEntity>>
//
//    @Query("SELECT*FROM recipes WHERE category = :category AND isFavorite = 1")
//    fun selectOneCategoryFromFavorites(category: String):LiveData<List<RecipeEntity>>


}