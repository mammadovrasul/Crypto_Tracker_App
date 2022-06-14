package app.guava.cryptotracker.presentation.adapter.viewHolder

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.guava.cryptotracker.databinding.ItemRateBinding
import app.guava.cryptotracker.domain.model.other.Rate

class RateViewHolder(
    val binding: ItemRateBinding,
    val context: Context
) : RecyclerView.ViewHolder(binding.root)

object RateDiffCallback : DiffUtil.ItemCallback<Rate>() {
    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }
}