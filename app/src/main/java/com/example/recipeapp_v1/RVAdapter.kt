package com.example.recipeapp_v1

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp_v1.databinding.ItemBinding

class RVAdapter: RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {


    }

    private val diffCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<Data>
        get() = differ.currentList
        set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
      holder.binding.tvTitle.text = item.title
      holder.binding.tvAuthor.text = item.author
      holder.binding.tvIng.text = item.ingredients
      holder.binding.tvInst.text = item.instructions







        ///Todo: try to figure out why its flickering
        
        holder.binding.root.setOnClickListener {
            if(holder.binding.clExpand.visibility == GONE){
                holder.binding.clExpand.visibility = VISIBLE
            }else{
                holder.binding.clExpand.visibility = GONE
            }

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}