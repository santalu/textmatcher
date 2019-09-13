package com.santalu.textmatcher.rule

import com.santalu.textmatcher.style.MentionStyle
import com.santalu.textmatcher.style.Style

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MentionRule(
  allowedCharacters: String? = null,
  style: Style? = MentionStyle()
) : SimpleRule("@", allowedCharacters, style)