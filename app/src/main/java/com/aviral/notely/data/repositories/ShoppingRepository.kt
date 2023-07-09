package com.aviral.notely.data.repositories

import com.aviral.notely.data.ShoppingItem
import com.aviral.notely.data.database.ShoppingDatabase

class ShoppingRepository(
    private val db: ShoppingDatabase
) {

    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingItems()

}