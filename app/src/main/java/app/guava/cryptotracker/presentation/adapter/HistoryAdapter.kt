package app.guava.cryptotracker.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.guava.cryptotracker.R
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.databinding.ItemRateHistoryBinding

open class HistoryAdapter(
    private var coinRange: List<CryptoRange>

) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRateHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rate_history,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return coinRange.size
    }

    class ViewHolder(val binding: ItemRateHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val range = coinRange[position]

        Log.d(HistoryAdapter::class.java.name, "onBindViewHolder:-->>--->>> ${range.cryptoName}")

        holder.binding.apply {
            this.range = range
        }
    }
}