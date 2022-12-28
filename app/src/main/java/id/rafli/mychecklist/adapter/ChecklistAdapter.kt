package id.rafli.mychecklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.rafli.mychecklist.R
import id.rafli.mychecklist.adapter.holder.ChecklistHolder
import id.rafli.mychecklist.model.Checklist

class ChecklistAdapter(var list:ArrayList<Checklist> = ArrayList()): RecyclerView.Adapter<ChecklistHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistHolder {
        return  ChecklistHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_checklist, parent, false))
    }

    override fun onBindViewHolder(holder: ChecklistHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newListData:List<Checklist>){
        list.addAll(newListData)
        notifyDataSetChanged()
    }

    fun clearData(){
        list.clear()
        notifyDataSetChanged()
    }

}