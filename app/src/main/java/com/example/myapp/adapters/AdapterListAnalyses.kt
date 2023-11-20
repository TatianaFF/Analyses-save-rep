package com.example.myapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.databinding.ItemAnalysesBinding

class AdapterListAnalyses(
    val context: Context
): RecyclerView.Adapter<AdapterListAnalyses.ListAnalysesViewHolder>() {

    private var listNamesAnalyses = emptyList<String>()
    var onItemClick: ((Int) -> Unit)? = null
    var onDelClick: ((Int) -> Unit)? = null

    inner class ListAnalysesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemAnalysesBinding.bind(view)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
            binding.imgBtnDelAnalysis.setOnClickListener {
                onDelClick?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnalysesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_analyses, parent, false)
        return ListAnalysesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAnalysesViewHolder, position: Int) {
        val nameCurrent = listNamesAnalyses[position]

        with(holder) {
            binding.tvAnalysisFiles.text = nameCurrent
        }
    }

    override fun getItemCount(): Int {
        return listNamesAnalyses.size
    }

    fun submitList(names: List<String>) {
        listNamesAnalyses = names
        notifyDataSetChanged()
    }
}