package com.dan.architecurecomponentkotlin.ui.common

import android.annotation.SuppressLint
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class DataBoundListAdapter<T, V : ViewDataBinding> : RecyclerView.Adapter<DataBoundViewHolder<V>>() {

    private var items: List<T>? = null
    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private var dataVersion = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {

        bind(holder.binding, items!![position])
        holder.binding.executePendingBindings()
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    fun replace(update: List<T>?) {
        dataVersion++
        when {
            items == null -> {
                if (update == null) {
                    return
                }
                items = update
                notifyDataSetChanged()
            }
            update == null -> {
                val oldSize = items!!.size
                items = null
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> {
                val startVersion = dataVersion
                val oldItems = items
                object : AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                    override fun doInBackground(vararg voids: Void): DiffUtil.DiffResult {
                        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                            override fun getOldListSize(): Int {
                                return oldItems!!.size
                            }

                            override fun getNewListSize(): Int {
                                return update.size
                            }

                            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                val oldItem = oldItems!![oldItemPosition]
                                val newItem = update[newItemPosition]
                                return this@DataBoundListAdapter.areItemsTheSame(oldItem, newItem)
                            }

                            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                val oldItem = oldItems!![oldItemPosition]
                                val newItem = update[newItemPosition]
                                return this@DataBoundListAdapter.areContentsTheSame(oldItem, newItem)
                            }
                        })
                    }

                    override fun onPostExecute(diffResult: DiffUtil.DiffResult) {
                        if (startVersion != dataVersion) {
                            // ignore update
                            return
                        }
                        items = update
                        diffResult.dispatchUpdatesTo(this@DataBoundListAdapter)

                    }
                }.execute()
            }
        }
    }

    protected abstract fun bind(binding: V, item: T)

    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }
}
