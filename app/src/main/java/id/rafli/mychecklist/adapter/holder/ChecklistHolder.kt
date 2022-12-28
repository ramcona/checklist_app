package id.rafli.mychecklist.adapter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import id.rafli.mychecklist.databinding.ItemChecklistBinding
import id.rafli.mychecklist.model.Checklist

class ChecklistHolder(var item:ItemChecklistBinding): RecyclerView.ViewHolder(item.root) {

    val context: Context = itemView.context

    fun setData(data: Checklist){
        item.tvName.text = data.name
    }
}