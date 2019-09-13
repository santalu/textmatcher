package com.santalu.textmatcher.style

import android.graphics.Typeface

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MentionStyle(
  textColor: Int? = null,
  isUnderline: Boolean = false,
  isBold: Boolean = false,
  typeface: Typeface? = null
) : SimpleStyle(textColor, isUnderline, isBold, typeface)