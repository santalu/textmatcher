package com.santalu.textmatcher.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.santalu.textmatcher.OnMatchClickListener
import com.santalu.textmatcher.OnMatchListener
import com.santalu.textmatcher.internal.MatcherPresenterImpl
import com.santalu.textmatcher.internal.MatcherView
import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MatcherEditText : AppCompatEditText, MatcherView {

  private val presenter = MatcherPresenterImpl(this)

  constructor(context: Context?) : super(context)

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
      super(context, attrs, defStyleAttr)

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.attach()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.detach()
  }

  override fun setOnMatchListener(listener: OnMatchListener?) {
    presenter.matchListener = listener
  }

  override fun setOnMatchClickListener(listener: OnMatchClickListener?) {
    presenter.matchClickListener = listener
  }

  override fun performClick(text: String) {
    presenter.notifyClick(text)
  }

  override fun addRule(rule: Rule) {
    presenter.addRule(rule)
  }

  override fun removeRule(rule: Rule) {
    presenter.removeRule(rule)
  }

  /**
   * Replaces matching target in selection with given string
   *
   * Not: if there's no matching target in selection does nothing
   */
  override fun replace(newText: String): Boolean {
    return presenter.replace(newText)
  }
}