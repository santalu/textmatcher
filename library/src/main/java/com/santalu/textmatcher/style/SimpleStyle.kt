package com.santalu.textmatcher.style

import android.graphics.Typeface
import android.text.TextPaint
import androidx.annotation.ColorInt

/**
 * Created by fatih.santalu on 9/9/2019
 *
 * To customize more override @updateDrawState method
 */

open class SimpleStyle(
  @ColorInt val textColor: Int? = null,
  val isUnderline: Boolean = false,
  val isBold: Boolean = false,
  val typeface: Typeface? = null
) : Style() {

  override fun updateDrawState(ds: TextPaint) {
    super.updateDrawState(ds)
    ds.isUnderlineText = isUnderline

    textColor?.let { ds.color = it }

    if (typeface != null) {
      ds.typeface = typeface
    }

    if (isBold) {
      ds.typeface = Typeface.create(typeface, Typeface.BOLD)
    }
  }
}