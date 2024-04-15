package com.example.androidassessmentbynasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassessmentbynasim.R
import com.hanatour.openchat.model.Data


class RecyclerViewListAdapter internal constructor(
    context: Context?,
    private val mData: List<Data>
) :
    RecyclerView.Adapter<RecyclerViewListAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mData[position]
        holder.atvId.text = contact.id.toString()
        holder.atvTitle.text = contact.title.toString()
        holder.llMain.setOnClickListener{
            mClickListener?.onItemClick(contact)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var atvId: AppCompatTextView
        var atvTitle: AppCompatTextView
        var llMain: LinearLayout

        init {
            atvId = itemView.findViewById<AppCompatTextView>(R.id.atvId)
            atvTitle = itemView.findViewById<AppCompatTextView>(R.id.atvTitle)
            llMain =  itemView.findViewById<LinearLayout>(R.id.llMain)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Data {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(data: Data)
    }
}