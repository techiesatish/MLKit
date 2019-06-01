package net.techiesatishktest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.techiesatishktest.R
import net.techiesatishktest.db.entity.Codes

/**
 * Created by Satish on 31/05/2019 11:17 PM.
 */

class CodesAdapter : RecyclerView.Adapter<CodesAdapter.CodeHolder>() {
    private var codes: List<Codes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.code_items, parent, false)
        return CodeHolder(itemView)
    }

    override fun onBindViewHolder(holder: CodeHolder, position: Int) {
        val currentCodes = codes[position]
        holder.textViewTitle.text = currentCodes.mTitle
        holder.textViewDescription.text = currentCodes.mTimeStamps
    }

    override fun getItemCount(): Int {
        return codes.size
    }

    fun setNotes(codes: List<Codes>) {
        this.codes = codes
        notifyDataSetChanged()
    }

    inner class CodeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        var textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)

    }
}