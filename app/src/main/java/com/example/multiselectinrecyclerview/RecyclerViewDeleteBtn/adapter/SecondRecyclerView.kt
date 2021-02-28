package com.example.multiselectinrecyclerview.RecyclerViewDeleteBtn.adapter

import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.size
import androidx.recyclerview.widget.RecyclerView
import com.example.multiselectinrecyclerview.model.Email
import com.example.multiselectinrecyclerview.R
import com.example.multiselectinrecyclerview.RecyclerViewDeleteBtn.`interface`.SecondItemViewClickListener
import com.example.multiselectinrecyclerview.databinding.EmailItemBinding
import com.example.multiselectinrecyclerview.utils.*
import java.util.*

class SecondRecyclerView(val emails: ArrayList<Email>,val clickListener: SecondItemViewClickListener) : RecyclerView.Adapter<SecondRecyclerView.ViewHolder>() {

    val selectedItems = SparseBooleanArray()
    var selectionMode:Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = EmailItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        emails.log("OnBindViewHolder")

        holder.bindData(emails[position])

       // holder.handleCLick(clickListener, selectedItems)
        holder.handelClick(listener =clickListener )

    }

    override fun getItemCount() = emails.size

    class ViewHolder(val binding: EmailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(email: Email) {

            val hash: Int = email.user.hashCode()
            //icon
            binding.txtIcon.apply {
                text = email.user[0].toString()
                background = this.setOval(Color.rgb(hash, hash / 2, 0))
            }

            //preview
            binding.txtPreview.apply {
                text = email.preview
            }

            //user
            binding.itemTextName.apply {
                text = email.user
                setViewType(email)
            }


            //subject
            binding.txtSubject.apply {
                text = email.subject
                setViewType(email)
            }

            //date
            binding.txtDate.apply {
                text = email.date
                setViewType(email)
            }

            //star image
            binding.imgStar.apply {
                setImageResource(if (email.stared) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)
            }

            //background
            if (email.selected) {
                val color = Color.rgb(232, 240, 253)
                itemView.setGradBackground(color)
            }else{
                itemView.setGradBackground(Color.WHITE)
            }




        }


        fun handelClick(listener: SecondItemViewClickListener){

            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }

          itemView.setOnLongClickListener {
              listener.onLongClick(adapterPosition)
              return@setOnLongClickListener true
          }


        }
    }

    fun deleteEmails() {

        emails.removeAllSeletedItems()
        emails.log(DeleteItems+"Sdapter")
        notifyDataSetChanged()

    }


    fun toggleSelection(position:Int){

        //if selected -> disSelect
        //if diSelected -> select
        //currentSelectedPos = position

        if (selectedItems[position]) {
            emails.disSelectEmail(position, selectedItems)
        } else{
            emails.selectEmail(position,selectedItems)
        }
        notifyDataSetChanged()
    }


}