package com.davidm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.davidm.utils.DashboardLocalMapper
import kotlinx.android.synthetic.main.purchase_list_item.view.*

class DashboardNestedListAdapter :
    RecyclerView.Adapter<DashboardNestedListAdapter.DashboardNestedListViewHolder>() {

    var data = listOf<DashboardLocalMapper.LocalPurchase>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class DashboardNestedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCounterParty: TextView = itemView.transactionTitle
        val mDate: TextView = itemView.shimmerDate
        val mAmount: TextView = itemView.shimmerAmount
        val mSpendingCategory: ImageView = itemView.spendingCategoryBox
        val purchaseListItem: ConstraintLayout = itemView.purchaseCardContainer
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardNestedListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.purchase_list_item, parent, false)
        return DashboardNestedListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DashboardNestedListViewHolder, position: Int) {
        val item = data[position]
        holder.purchaseListItem.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.translate_recyclerview)
        holder.mCounterParty.text = item.counterPartyName
        holder.mSpendingCategory.background.setTint(
            ContextCompat.getColor(
                holder.itemView.context,
                item.spendingCategoryColor
            )
        )
        holder.mSpendingCategory.setImageResource(item.spendingCategoryDrawable)
        holder.mSpendingCategory.setColorFilter(
            ContextCompat.getColor(
                holder.itemView.context,
                item.spendingCategoryColor
            )
        )
        holder.mDate.text = item.date
        holder.mAmount.text = item.amount
        holder.mAmount.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                item.amountColor
            )
        )


    }
}