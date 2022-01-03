package com.rai.irregularverbs.adapters


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rai.irregularverbs.R

import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.constants.MenuType.LIST
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.databinding.ImageItemBinding
import com.rai.irregularverbs.databinding.ListItemBinding
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat



class ListVerbAdapter(val type: Int) : ListAdapter<IrregularVerbs, RecyclerView.ViewHolder>(DiffCallback)  {

    override fun onCreateViewHolder(
        parent: ViewGroup,

        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (type) {
            IMAGE -> ImageViewHolder.from(parent)
            LIST -> ListVerbViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${type}")
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListVerbViewHolder -> {
                val item = getItem(position)
                holder.bind(item,position)
            }
            is ImageViewHolder -> {
                val item = getItem(position)
                holder.bind(item,position)
            }

        }

    }


    class ListVerbViewHolder(
        private var binding: ListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        val rootView = binding.root

        fun bind(irregularVerbs: IrregularVerbs, position: Int) {
            binding.form1.text = irregularVerbs.form1
            //binding.form1.setTextColor()
            binding.form2.text = irregularVerbs.form2
            binding.form3.text = irregularVerbs.form3
            if(position % 2 == 0)
            {
                rootView.setBackgroundResource(R.color.primaryColor)
            } else
            {
                rootView.setBackgroundResource(R.color.secondaryColor)
                binding.form1.setTextColor(Color.parseColor("#feea15"))
                binding.form2.setTextColor(Color.parseColor("#feea15"))
                binding.form3.setTextColor(Color.parseColor("#feea15"))
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ListVerbViewHolder(binding)
            }
        }
    }

    class ImageViewHolder(
        private var binding: ImageItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        val rootView = binding.root

        fun bind(irregularVerbs: IrregularVerbs,position: Int) {
            binding.form1.text = irregularVerbs.form1
            binding.form2.text = irregularVerbs.form2
            binding.form3.text = irregularVerbs.form3
            binding.example1.text = irregularVerbs.example1
            binding.example2.text = irregularVerbs.example2
            binding.example3.text = irregularVerbs.example3
            val context = rootView.context
            val resId = context.getResources().getIdentifier("${irregularVerbs.form1}_verb", "drawable", context.getPackageName())
            binding.imageView.setImageResource(resId)
            if(position % 2 == 0)
            {
                rootView.setBackgroundResource(R.color.primaryColor)
            } else
            {
                rootView.setBackgroundResource(R.color.secondaryColor)
                binding.form1.setTextColor(Color.parseColor("#feea15"))
                binding.form2.setTextColor(Color.parseColor("#feea15"))
                binding.form3.setTextColor(Color.parseColor("#feea15"))
                binding.example1.setTextColor(Color.parseColor("#feea15"))
                binding.example2.setTextColor(Color.parseColor("#feea15"))
                binding.example3.setTextColor(Color.parseColor("#feea15"))
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
                return ImageViewHolder(binding)
            }
        }
    }



    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<IrregularVerbs>() {
            override fun areItemsTheSame(oldItem: IrregularVerbs, newItem: IrregularVerbs): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: IrregularVerbs, newItem: IrregularVerbs): Boolean {
                return oldItem == newItem
            }
        }
    }



}