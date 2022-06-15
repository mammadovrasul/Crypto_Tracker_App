package app.guava.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.ItemRateBinding
import app.guava.cryptotracker.domain.model.other.Rate
import app.guava.cryptotracker.presentation.interfaces.CoinItemClickListener
import app.guava.cryptotracker.presentation.interfaces.SetCoinValueClickListener


open class RatesAdapter(
    private var rate: ArrayList<Rate>,
    private var coinItemClickListener: CoinItemClickListener,
    private var setCoinValueClickListener: SetCoinValueClickListener

) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rate,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return rate.size
    }

    class ViewHolder(val binding: ItemRateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate = rate[position]

        holder.binding.apply {
            this.rate = rate

            ratesInfo.setOnClickListener {
                coinItemClickListener.getCoinHistoryInfoClickListener(rate)
            }

            setRange.setOnClickListener {
                setCoinValueClickListener.onSetCoinRangeValue(rate.cryptoName)
            }
        }

    }
}