package com.uvandroid.progressindicator

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.*

@BindingMethods(
    BindingMethod(
        type = ProgressIndicatorView::class,
        attribute = "selectedIndicationPosition",
        method = "setSelectedIndicationPosition"
    ),
    BindingMethod(
        type = ProgressIndicatorView::class,
        attribute = "selectedIndicationPositionAttrChanged",
        method = "setBindingListener"
    )
)
@InverseBindingMethods(
    InverseBindingMethod(
        type = ProgressIndicatorView::class,
        attribute = "selectedIndicationPosition",
        method = "getSelectedIndicationPosition"
    )
)
class ProgressIndicatorView : LinearLayout {
    private lateinit var progressIndicator: ProgressIndicator
    private lateinit var inverseBindingListener: InverseBindingListener

    private val progressIcons: List<ImageView>
    private var selectedIndicatorPosition: Int = -1

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        inflate(context, R.layout.progress_indicators, this)

        progressIcons = listOf(
            findViewById(R.id.progress_circular_1),
            findViewById(R.id.progress_circular_2),
            findViewById(R.id.progress_circular_3),
            findViewById(R.id.progress_circular_4),
            findViewById(R.id.progress_circular_5),
            findViewById(R.id.progress_circular_6)
        )

        progressIcons.forEach { it.visibility = GONE }
    }

    fun setProgressIndicator(progress: ProgressIndicator) {
        progressIndicator = progress
        setupView()
    }

    fun setBindingListener(listener: InverseBindingListener) {
        this.inverseBindingListener = listener
    }

    fun setSelectedIndicationPosition(index: Int) {
    }

    fun getSelectedIndicationPosition(): Int {
        return selectedIndicatorPosition
    }

    private fun setupView() {
        if (progressIndicator.indicatorCount > progressIcons.size) {
            return
        }
        for (i in 0 until progressIndicator.indicatorCount) {
            progressIcons[i].visibility = VISIBLE
            progressIcons[i].setImageResource(getImageResource(i))
            progressIcons[i].tag = i

            progressIcons[i].setOnClickListener {
                selectedIndicatorPosition = it.tag as Int
                inverseBindingListener.onChange()
            }
        }

        progressIcons[progressIndicator.currentIndicator].setImageResource(R.drawable.ic_progress_current)
    }

    private fun getImageResource(i: Int): Int {
        return R.drawable.ic_progress_current
//        return when (progressIndicator.progressStatus[i]) {
//            DoneStatus.FINISHED -> R.drawable.ic_progress_done
//            else -> R.drawable.ic_progress_undone
//        }
    }

    companion object {
        @BindingAdapter("app:progressIndicator")
        @JvmStatic
        fun setProgressIndicator(
            view: ProgressIndicatorView,
            progressIndicator: ProgressIndicator
        ) {
            view.setProgressIndicator(progressIndicator)
        }
    }
}
