package app.guava.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import app.guava.cryptotracker.R
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.databinding.ItemRateHistoryBinding
import app.guava.cryptotracker.presentation.adapter.viewHolder.RateHistoryViewHolder
import app.guava.cryptotracker.presentation.adapter.viewHolder.RateRangeDiffCallback

class RatesHistoryAdapter : ListAdapter<CryptoRange, RateHistoryViewHolder>(RateRangeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateHistoryViewHolder {
        val binding: ItemRateHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_rate_history, parent, false
        )

        return RateHistoryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RateHistoryViewHolder, index: Int) {
        val range = getItem(index)

        holder.binding.apply {
            this.range = range
        }
    }
}