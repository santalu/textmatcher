package com.santalu.textmatcher.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.santalu.textmatcher.OnMatchClickListener
import com.santalu.textmatcher.internal.MatcherPresenter
import com.santalu.textmatcher.internal.MatcherView
import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MatcherTextView : AppCompatTextView, MatcherView {

  private val presenter = MatcherPresenter(this)

  constructor(context: Context?) : super(context)

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
      super(context, attrs, defStyleAttr)

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.detach()
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

  override fun setSelection(index: Int) {
    // do nothing
  }

  override fun isMatchingEnabled(): Boolean {
    return false
  }
}