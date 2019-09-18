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

internal class MatcherPresenter(private val view: MatcherView) {

  private val rules = mutableListOf<Rule>()
  private var matcher: TextMatcher? = null

  var matchListener: OnMatchListener? = null
  var matchClickListener: OnMatchClickListener? = null
    set(value) {
      field = value
      setMovementMethod()
    }

  /**
   * Re-instantiates matcher with current rules and attaches to view
   */
  fun attach() {
    if (rules.isNullOrEmpty()) return
    matcher = TextMatcher(rules, ::notifyMatch).apply {
      isMatchingEnabled = view.isMatchingEnabled()
    }
    view.addTextChangedListener(matcher)
  }

  /**
   * Releases the matcher and detaches from view
   */
  fun detach() {
    view.removeTextChangedListener(matcher)
    matcher = null
  }

  /**
   * Triggers @OnMatchListener
   */
  private fun notifyMatch(rule: Rule, text: String?) {
    matchListener?.invoke(rule, text)
  }

  /**
   * Triggers @OnMatchClickListener
   */
  fun notifyClick(text: String) {
    matchClickListener?.invoke(text)
  }

  private fun setMovementMethod() {
    val movementMethod =
      if (matchClickListener != null) LinkMovementMethod.getInstance() else view.getMovementMethod()
    view.setMovementMethod(movementMethod)
  }

  /**
   * Detaches and then re-attaches matcher
   */
  private fun invalidateMatcher() {
    detach()
    attach()
  }

  /**
   * Refreshes the text without losing cursor position
   */
  private fun invalidateText() {
    val selection = view.getSelectionStart()
    view.setText(view.getText())
    view.setSelection(selection)
  }

  /**
   * Adds rule into the collection if not already exist
   */
  fun addRule(rule: Rule) {
    if (rules.contains(rule)) return
    rules.add(rule)
    invalidateMatcher()
    invalidateText()
  }

  /**
   * Removes rule from to collection
   */
  fun removeRule(rule: Rule) {
    if (!rules.contains(rule)) return
    rules.remove(rule)
    invalidateMatcher()
    invalidateText()
  }

  /**
   * Replaces matching target in selection with given string
   *
   * Not: if there's no matching target in selection does nothing
   */
  fun replace(newText: String): Boolean {
    matcher?.let {
      it.isMatchingEnabled = false

      val editable = view.getEditableText()
      if (editable.isNullOrEmpty()) {
        view.setText("$newText ")
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
          // add whitespace at the end if needed
          val cursor = start + newText.length
          val following = editable.getOrNull(cursor)
          if (following == null || !following.isWhitespace()) {
            editable.insert(cursor, " ")
          }
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