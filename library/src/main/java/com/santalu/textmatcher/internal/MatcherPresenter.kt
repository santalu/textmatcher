package com.santalu.textmatcher.internal

import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 */

internal interface MatcherPresenter {

  /**
   * Re-instantiates matcher with current rules and attaches to view
   */
  fun attach()

  /**
   * Releases the matcher and detaches from view
   */
  fun detach()

  /**
   * Triggers @OnMatchListener
   */
  fun notifyMatch(rule: Rule, text: String?)

  /**
   * Triggers @OnMatchClickListener
   */
  fun notifyClick(text: String)

  /**
   * Detaches and then re-attaches matcher
   */
  fun invalidateMatcher()

  /**
   * Refreshes the text without losing cursor position
   */
  fun invalidateText()

  /**
   * Adds rule into the collection if not already exist
   */
  fun addRule(rule: Rule)

  /**
   * Removes rule from to collection
   */
  fun removeRule(rule: Rule)

  /**
   * Replaces matching target in selection with given string
   *
   * Not: if there's no matching target in selection does nothing
   */
  fun replace(newText: String): Boolean
}