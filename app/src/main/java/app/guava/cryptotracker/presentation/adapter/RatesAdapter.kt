package app.guava.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.ItemRateBinding
import app.guava.cryptotracker.domain.model.other.Rate
import app.guava.cryptotracker.presentation.adapter.viewHolder.RateDiffCallback
import app.guava.cryptotracker.presentation.adapter.viewHolder.RateViewHolder

class RatesAdapter : ListAdapter<Rate, RateViewHolder>(RateDiffCallback) {

    lateinit var onHolderClickListener: OnHolderClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding: ItemRateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_rate, parent, false
        )

        return RateViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RateViewHolder, index: Int) {
        val rate = getItem(index)

        holder.binding.apply {
            this.onClickListener = onHolderClickListener
            this.rate = rate
        }
    }

    interface OnHolderClickListener {
        fun onHolderItemClick(item: Rate)
    }
}