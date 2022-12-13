package com.example.petbeer


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
class BeerEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    val name: String,
    val ammount: Float,
    val priceFor1: Float,
    val priceFor0_5: Float,
    val priceFor1_5: Float

){}
fun BeerEntity.toModel() = Beer(
    id = id,
    name = name,
    ammount = ammount,
    priceFor0_5 = priceFor0_5,
    priceFor1 = priceFor1,
    priceFor1_5 = priceFor1_5
)

fun Beer.toEntity() = BeerEntity(
    id = id,
    name = name,
    ammount = ammount,
    priceFor0_5 = priceFor0_5,
    priceFor1 = priceFor1,
    priceFor1_5 = priceFor1_5

)