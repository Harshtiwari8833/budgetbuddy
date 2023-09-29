package com.example.budgetbuddy.ui.budget

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbuddy.R
import com.example.budgetbuddy.util.category.Category
import com.example.budgetbuddy.util.category.CustomDialogNewCategory

class CategoryAdapter2(
    private val arrayList: ArrayList<Category>,
    val context: Context,
    private val clickListener: ClickListener,
    private val viewModel : SharedViewModel
) : RecyclerView.Adapter<CategoryAdapter2.ViewHolder>(){

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val image: ImageView = item.findViewById(R.id.image)
        val text: TextView = item.findViewById(R.id.category)
        val setBudget : TextView = item.findViewById(R.id.setBudget)
        val linearLayout : LinearLayout = item.findViewById(R.id.foodBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(R.drawable.category)
        holder.text.text = arrayList[position].category

        viewModel.getBudget().observe(holder.itemView.context as LifecycleOwner){
            if (arrayList[position].category == it.first)
            {
                holder.setBudget.text = it.second
            }
        }

        if (position == arrayList.size - 1) {
            holder.image.setImageResource(R.drawable.profile)
            holder.linearLayout.visibility = View.GONE
            holder.itemView.setOnClickListener {
                val customDialogNewCategory = CustomDialogNewCategory(context)
                customDialogNewCategory.show()
            }
        }
        else {
            holder.linearLayout.setOnClickListener {
                clickListener.onItemClick(arrayList[position].category)
            }
        }
    }

    interface ClickListener{
        fun onItemClick(category : String?)
    }
}