package com.santalu.textmatcher.internal

import android.text.Editable
import android.text.TextWatcher
import android.text.method.MovementMethod
import com.santalu.textmatcher.OnMatchClickListener
import com.santalu.textmatcher.OnMatchListener
import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 */

interface MatcherView {

  /**
   * Used to attach matcher
   */
  fun addTextChangedListener(watcher: TextWatcher?)

  /**
   * Used to detach matcher
   */
  fun removeTextChangedListener(watcher: TextWatcher?)

  /**
   * @optional: Used to retrieve match result
   */
  fun setOnMatchListener(listener: OnMatchListener?) {}

  /**
   * Used to retrieve matching target click events
   */
  fun setOnMatchClickListener(listener: OnMatchClickListener?)

  /**
   * Used to add [rule] to matcher's collection
   */
  fun addRule(rule: Rule)

  /**
   * Used to remove the [rule] from matcher's collection
   */
  fun removeRule(rule: Rule)

  /**
   * Used to retrieve non-editable text from view
   */
  fun getText(): CharSequence?

  /**
   * Used to retrieve editable text from view
   */
  fun getEditableText(): Editable?

  fun setText(text: CharSequence?)

  /**
   * @optional: Used to replace matching target on selection
   */
  fun replace(newText: String): Boolean = false

  /**
   * Used to retrieve current selected string's start position
   */
  fun getSelectionStart(): Int

  /**
   * Used to retrieve current selected string's end position
   */
  fun getSelectionEnd(): Int

  fun setSelection(index: Int)

  /**
   * Used to trigger click event for matching target's
   */
  fun performClick(text: String)

  /**
   * Used to enable/disable click events
   */
  fun setMovementMethod(movement: MovementMethod?)

  /**
   * Used as a default movement method if click on matching target's is disabled
   */
  fun getMovementMethod(): MovementMethod?

  /**
   * Determines whether this view needs matching
   * If value is set to false, matcher will only do styling on matching targets
   */
  fun isMatchingEnabled(): Boolean = true
}