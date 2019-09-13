package com.santalu.textmatcher.rule

import android.text.Editable

/**
 * Created by fatih.santalu on 9/9/2019
 *
 * Defines target boundaries and matching requirements
 *
 * It's also possible to customize visual styles of target via providing custom @Style
 */

abstract class Rule {

  /**
   * Matches given target which composed from boundary of @getTargetStart and @getTargetEnd results
   */
  abstract fun isMatches(target: String): Boolean

  /**
   * Defines start of the target string
   */
  abstract fun getTargetStart(text: CharSequence, start: Int): Int

  /**
   * Defines end of the target string
   */
  abstract fun getTargetEnd(text: CharSequence, start: Int): Int

  /**
   * Applies any style to all matching targets
   */
  abstract fun applyStyle(text: Editable)
}