package com.aviral.notely.ui.shoppingList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aviral.notely.R
import com.aviral.notely.data.ShoppingItem
import com.aviral.notely.data.database.ShoppingDatabase
import com.aviral.notely.data.repositories.ShoppingRepository
import com.aviral.notely.other.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)

        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val recyclerView = findViewById<RecyclerView>(R.id.rvShoppingItems)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {

                        viewModel.upsert(item)

                    }
                }).show()
        }

    }

}