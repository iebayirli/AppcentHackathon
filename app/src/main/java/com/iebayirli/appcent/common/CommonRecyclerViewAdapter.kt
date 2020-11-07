package com.iebayirli.appcent.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class CommonRecyclerViewAdapter<T>(
    @LayoutRes val resource: Int, var data: List<T>, val listener: Any? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun updateData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            resource,
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommonRecyclerViewAdapter<*>.CommonViewHolder) {
            val item = data[position]
            if (item != null) {
                holder.setupData(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size


    inner class CommonViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setupData(model: Any) {
            binding.setVariable(BR.model, model)
            binding.setVariable(BR.itemClickListener, listener)
            binding.executePendingBindings()
        }
    }

}