package com.santalu.textmatcher

import androidx.core.text.getSpans
import androidx.core.text.toSpannable
import com.santalu.textmatcher.internal.MatcherView
import com.santalu.textmatcher.rule.Rule
import com.santalu.textmatcher.style.Style

/**
 * Created by fatih.santalu on 9/9/2019
 */

typealias OnMatchListener = (Rule, String?) -> Unit

typealias OnMatchClickListener = (String) -> Unit

inline fun <reified T : Style> MatcherView.findMatches(): List<String>? {
  val text = getEditableText() ?: getText()?.toSpannable()
  return text?.let { editable ->
    val matches = mutableListOf<String>()
    editable.getSpans<T>()
      .forEach {
        val targetStart = editable.getSpanStart(it)
        val targetEnd = editable.getSpanEnd(it)
        val target = editable.substring(targetStart, targetEnd)
        matches.add(target)
      }
    matches
  }
}