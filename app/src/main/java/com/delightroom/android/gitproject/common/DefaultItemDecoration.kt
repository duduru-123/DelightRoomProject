package com.delightroom.android.gitproject.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DefaultItemDecoration(
    private val marginSize: Int,
    private val isHideTop: Boolean = false
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position == RecyclerView.NO_POSITION) return

        if (position == 0 && !isHideTop) {
            outRect.top = marginSize
        }

        outRect.bottom = marginSize
    }
}