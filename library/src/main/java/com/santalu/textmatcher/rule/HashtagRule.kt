package com.santalu.textmatcher.rule

import com.santalu.textmatcher.style.HashtagStyle
import com.santalu.textmatcher.style.Style

/**
 * Created by fatih.santalu on 9/9/2019
 */

class HashtagRule(
  allowedCharacters: String? = null,
  style: Style? = HashtagStyle()
) : SimpleRule("#", allowedCharacters, style)