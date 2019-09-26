package com.santalu.textmatcher

import android.text.Editable
import android.text.TextWatcher
import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 *
 * Simple text watcher matches appropriate targets according to given [rules]
 */

class TextMatcher(
  val rules: List<Rule>,
  val action: OnMatchListener
) : TextWatcher {

  internal var isMatchingEnabled = true

  override fun afterTextChanged(text: Editable?) {
    if (text.isNullOrEmpty()) return

    // customize visual style of targets
    rules.forEach {
      it.applyStyle(text)
    }
  }

  override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
  }

  override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
    if (!isMatchingEnabled) return

    rules.forEach {
      if (text.isNullOrEmpty()) {
        action(it, null)
        return@forEach
      }

      val position = if (start > 0 && before > count) start - 1 else start

      // find closest target's boundaries from selection
      val targetStart = it.getTargetStart(text, position)
      val targetEnd = it.getTargetEnd(text, position)
      val target = text.substring(targetStart, targetEnd)

      if (it.isMatches(target)) {
        action(it, target)
      } else {
        action(it, null)
      }
    }
  }
}