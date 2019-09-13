package com.santalu.textmatcher.internal

import android.text.method.LinkMovementMethod
import com.santalu.textmatcher.OnMatchClickListener
import com.santalu.textmatcher.OnMatchListener
import com.santalu.textmatcher.TextMatcher
import com.santalu.textmatcher.rule.Rule
import kotlin.math.max

/**
 * Created by fatih.santalu on 9/9/2019
 */

internal class MatcherPresenterImpl(private val view: MatcherView) : MatcherPresenter {

  private val rules = mutableListOf<Rule>()
  private var matcher: TextMatcher? = null

  internal var matchListener: OnMatchListener? = null
  internal var matchClickListener: OnMatchClickListener? = null
    set(value) {
      field = value
      setMovementMethod()
    }

  override fun attach() {
    if (rules.isNullOrEmpty()) return
    matcher = TextMatcher(rules, ::notifyMatch).apply {
      isMatchingEnabled = view.isMatchingEnabled()
    }
    view.addTextChangedListener(matcher)
  }

  override fun detach() {
    view.removeTextChangedListener(matcher)
    matcher = null
  }

  override fun notifyMatch(rule: Rule, text: String?) {
    matchListener?.invoke(rule, text)
  }

  override fun notifyClick(text: String) {
    matchClickListener?.invoke(text)
  }

  private fun setMovementMethod() {
    val movementMethod =
      if (matchClickListener != null) LinkMovementMethod.getInstance() else view.getMovementMethod()
    view.setMovementMethod(movementMethod)
  }

  override fun invalidateMatcher() {
    detach()
    attach()
  }

  override fun invalidateText() {
    val selection = view.getSelectionStart()
    view.setText(view.getText())
    view.setSelection(selection)
  }

  override fun addRule(rule: Rule) {
    if (rules.contains(rule)) return
    rules.add(rule)
    invalidateMatcher()
    invalidateText()
  }

  override fun removeRule(rule: Rule) {
    if (!rules.contains(rule)) return
    rules.remove(rule)
    invalidateMatcher()
    invalidateText()
  }

  override fun replace(newText: String): Boolean {
    matcher?.let {
      it.isMatchingEnabled = false

      val editable = view.getEditableText()
      if (editable.isNullOrEmpty()) {
        view.setText(newText)
        it.isMatchingEnabled = true
        return true
      }

      it.rules.forEach { rule ->
        // find closest target's boundaries from selection
        val position = max(0, view.getSelectionStart() - 1)
        val start = rule.getTargetStart(editable, position)
        val end = rule.getTargetEnd(editable, position)
        val target = editable.substring(start, end)

        if (rule.isMatches(target)) {
          editable.replace(start, end, newText)
          it.isMatchingEnabled = true
          return true
        }
      }

      it.isMatchingEnabled = true
      return false
    }

    return false
  }
}