package com.rai.irregularverbs.adapters


import android.annotation.SuppressLint
import android.graphics.Color
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
import java.util.*


class ListVerbAdapter(private val type: Int) :
    ListAdapter<IrregularVerbs, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,

        viewType: Int,
    ): RecyclerView.ViewHolder {
        return when (type) {
            IMAGE -> ImageViewHolder.from(parent)
            LIST -> ListVerbViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListVerbViewHolder -> {
                val item = getItem(position)
                holder.bind(item, position)
            }
            is ImageViewHolder -> {
                val item = getItem(position)
                holder.bind(item, position)
            }
        }
    }

    class ListVerbViewHolder(
        private var binding: ListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val rootView = binding.root

        @SuppressLint("ResourceAsColor")
        fun bind(irregularVerbs: IrregularVerbs, position: Int) {
            binding.form1.text = irregularVerbs.form1
            binding.form2.text = irregularVerbs.form2
            binding.form3.text = irregularVerbs.form3
            val country = Locale.getDefault().country
            if (country == "RU") {
                binding.translate.text = irregularVerbs.ru
            }
            if (position % 2 == 0) {
                rootView.setBackgroundResource(R.color.primaryColor)
            } else {
                rootView.setBackgroundResource(R.color.secondaryColor)
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
        private var binding: ImageItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val rootView = binding.root

        fun bind(irregularVerbs: IrregularVerbs, position: Int) {
            binding.form1.text = irregularVerbs.form1
            binding.form2.text = irregularVerbs.form2
            binding.form3.text = irregularVerbs.form3
            binding.example1.text = irregularVerbs.example1
            binding.example2.text = irregularVerbs.example2
            binding.example3.text = irregularVerbs.example3
            val country = Locale.getDefault().country
            if (country == "RU") {
                binding.translate.text = irregularVerbs.ru
            }
            val context = rootView.context
            val resId = context.resources.getIdentifier("${irregularVerbs.form1}_verb",
                "drawable",
                context.packageName)
            binding.imageView.setImageResource(resId)
            if (position % 2 == 0) {
                rootView.setBackgroundResource(R.color.primaryColor)

            } else {
                rootView.setBackgroundResource(R.color.secondaryColor)
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
            override fun areItemsTheSame(
                oldItem: IrregularVerbs,
                newItem: IrregularVerbs,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: IrregularVerbs,
                newItem: IrregularVerbs,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}