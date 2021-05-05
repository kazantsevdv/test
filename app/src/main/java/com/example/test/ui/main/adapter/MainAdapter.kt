package com.example.test.ui.main.adapter


import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemLayoutBinding
import com.example.test.model.User
import com.example.test.ui.main.adapter.MainAdapter.DataViewHolder


class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<DataViewHolder>() {
    private lateinit var binding: ItemLayoutBinding

   inner class DataViewHolder(val  binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(user: User) {

            binding.textViewUserName.text = user.name
            binding.textViewUserEmail.text = user.email
                Glide.with( binding.container.context)
                    .load(user.avatar)
                    .into( binding.imageViewAvatar)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DataViewHolder
    {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}