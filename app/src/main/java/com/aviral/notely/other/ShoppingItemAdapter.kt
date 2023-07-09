package com.aviral.notely.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aviral.notely.R
import com.aviral.notely.data.ShoppingItem
import com.aviral.notely.ui.shoppingList.ShoppingViewModel

class ShoppingItemAdapter(
    private val items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemAdapter.ShoppingViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_item, parent, false)

        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemAdapter.ShoppingViewHolder, position: Int) {

        val curShoppingItem = items[position]

        holder.tvName.text = curShoppingItem.name
        holder.tvAmount.text = "${curShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.ivPlus.setOnClickListener {

            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount++
                holder.tvAmount.text = "${curShoppingItem.amount}"

                viewModel.upsert(curShoppingItem)
            }

        }

        holder.ivMinus.setOnClickListener {

            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                holder.tvAmount.text = "${curShoppingItem.amount}"

                viewModel.upsert(curShoppingItem)
            }

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ShoppingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
            val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
            val ivPlus: ImageView = itemView.findViewById(R.id.ivPlus)
            val ivMinus: ImageView = itemView.findViewById(R.id.ivMinus)

    }
}