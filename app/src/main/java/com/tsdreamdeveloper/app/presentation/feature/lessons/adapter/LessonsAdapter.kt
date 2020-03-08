package com.tsdreamdeveloper.app.presentation.feature.lessons.adapter

import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.adapter.regular.KDelegateAdapter
import com.tsdreamdeveloper.app.presentation.model.LessonDvo
import com.tsdreamdeveloper.app.utils.ext.load
import com.tsdreamdeveloper.app.utils.ext.setVisible
import kotlinx.android.synthetic.main.item_lesson.*

class LessonsAdapter : KDelegateAdapter<LessonDvo>() {

    override val layoutId: Int = R.layout.item_lesson

    override fun isForViewType(items: List<*>, position: Int): Boolean =
        items[position] is LessonDvo

    override fun onBind(item: LessonDvo, viewHolder: KViewHolder) = with(viewHolder) {
        item_lesson_title.text = item.title
        item_lesson_image.load(
            url = item.image,
            roundedCorners = 4
        )
        item_lesson_icon.setImageResource(item.icon)
        item_lesson_sub_icon_text.text = item.subIconText
        item_lesson_visited.setVisible(item.visited)
        item_lesson_sub_title.text = item.subTitle
        item_lesson_sub_title.setCompoundDrawablesWithIntrinsicBounds(item.subTitleIcon, 0, 0, 0)
    }
}