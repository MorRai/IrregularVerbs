package com.rai.irregularverbs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rai.irregularverbs.R
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.databinding.FlashcardItemBinding

class FlashcardAdapter : ListAdapter<IrregularVerbs, FlashcardAdapter.FlashcardViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val viewHolder = FlashcardViewHolder(
            FlashcardItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )

        return viewHolder
    }

     class FlashcardViewHolder(private var binding: FlashcardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private val backSide = binding.backSide.root
        private val frontSide = binding.frontSide.root
        private val flipView = binding.flipView
        fun bind(irregularVerbs: IrregularVerbs) {
            binding.backSide.form1.text = irregularVerbs.form1
            binding.backSide.form2.text = irregularVerbs.form2
            binding.backSide.form3.text = irregularVerbs.form3
            binding.frontSide.form1.text = irregularVerbs.form1
        }
        init {
            backSide.setOnClickListener {
                flipView.flipDuration = 5
                flipView.flipTheView()
            }
            frontSide.setOnClickListener {
                flipView.flipDuration = 500
                //flipView.isAutoFlipBack = true
               // flipView.autoFlipBackTime=2000
                flipView.flipTheView()
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