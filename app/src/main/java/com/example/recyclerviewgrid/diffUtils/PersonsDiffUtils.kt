package com.example.recyclerviewgrid.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerviewgrid.data.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.userID == newItem.userID
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

}

