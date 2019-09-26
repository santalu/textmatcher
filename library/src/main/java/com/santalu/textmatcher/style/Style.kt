package com.santalu.textmatcher.style

import android.text.style.ClickableSpan
import android.view.View
import com.santalu.textmatcher.internal.MatcherView

/**
 * Created by fatih.santalu on 9/9/2019
 *
 * Customizes visual appearance or behaviour of targets
 *
 * To customize appearance of targets override [updateDrawState] method
 */

abstract class Style : ClickableSpan(), Cloneable {

  override fun onClick(view: View) {
    if (view !is MatcherView) return

    val start = view.getSelectionStart()
    val end = view.getSelectionEnd()
    if (start == -1 || end == -1) return

    val target = view.getText()?.substring(start, end)
    if (target.isNullOrEmpty()) return

    view.performClick(target)
  }

  public override fun clone(): Any {
    return super.clone()
  }
}