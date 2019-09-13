package com.santalu.textmatcher.style

import android.graphics.Typeface

/**
 * Created by fatih.santalu on 9/9/2019
 */

class HashtagStyle(
  textColor: Int? = null,
  isUnderline: Boolean = false,
  isBold: Boolean = false,
  typeface: Typeface? = null
) : SimpleStyle(textColor, isUnderline, isBold, typeface)