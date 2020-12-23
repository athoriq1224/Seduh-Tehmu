package com.google.developers.teacup.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.google.developers.teacup.R
import com.google.developers.teacup.data.Tea

/**
 * Implementation of an Paging adapter that shows list of Teas.
 */
class TeaAdapter(
    private val clickListener: (Tea) -> Unit
) : PagedListAdapter<Tea, TeaViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return TeaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeaViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bindTo(it,clickListener)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tea>() {
            override fun areItemsTheSame(oldItem: Tea, newItem: Tea): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Tea, newItem: Tea): Boolean {
                return oldItem == newItem
            }
        }
    }
}
