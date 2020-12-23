package com.google.developers.teacup.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.developers.teacup.data.Tea
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * A RecyclerView ViewHolder that displays a Tea.
 */
class TeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val context = itemView.context
    private lateinit var tea: Tea

    /**
     * Attach values to views.
     */
    fun bindTo(tea: Tea, clickListener: (Tea) -> Unit) {
        this.tea = tea
        itemView.run {
            setOnClickListener { clickListener(tea) }
            txt_title.text = tea.name
            txt_origin.text = tea.origin
            txt_steep_time.text = tea.steepTimeMs.toString()
        }
    }

    fun getTea(): Tea = tea
}
