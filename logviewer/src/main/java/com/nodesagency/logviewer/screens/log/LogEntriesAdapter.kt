package com.nodesagency.logviewer.screens.log

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nodesagency.logviewer.data.model.LogEntry
import com.nodesagency.logviewer.screens.log.utilities.SeverityToColorConverter
import com.nodesagency.logviewer.screens.log.views.LogItemView

internal class LogEntriesAdapter(
    private val severityToColorConverter: SeverityToColorConverter
) : PagedListAdapter<LogEntry, LogEntriesAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val logItemView: LogItemView) : RecyclerView.ViewHolder(logItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val logItemView = LogItemView(parent.context)

        logItemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return ViewHolder(logItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    private fun ViewHolder.bind(logEntry: LogEntry, position: Int) {
        logItemView.lineNumber = position
        logItemView.tag = logEntry.tag
        logItemView.message = logEntry.message

        severityToColorConverter
            .getColorForSeverityId(logEntry.severityId)
            ?.let(logItemView::setBackgroundColor)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<LogEntry>() {
    override fun areItemsTheSame(oldItem: LogEntry, newItem: LogEntry) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LogEntry, newItem: LogEntry) = oldItem == newItem
}