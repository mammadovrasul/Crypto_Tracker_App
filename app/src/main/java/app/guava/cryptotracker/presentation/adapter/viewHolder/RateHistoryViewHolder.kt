package app.guava.cryptotracker.presentation.adapter.viewHolder

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.databinding.ItemRateHistoryBinding

class RateHistoryViewHolder(
    val binding: ItemRateHistoryBinding,
    val context: Context
) : RecyclerView.ViewHolder(binding.root)

object RateRangeDiffCallback : DiffUtil.ItemCallback<CryptoRange>() {
    override fun areItemsTheSame(oldItem: CryptoRange, newItem: CryptoRange): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CryptoRange, newItem: CryptoRange): Boolean {
        return oldItem == newItem
    }
}