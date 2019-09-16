package com.santalu.textmatcher.rule

import android.text.Editable
import android.text.Spanned
import com.santalu.textmatcher.style.Style

/**
 * Created by fatih.santalu on 9/9/2019
 *
 * Simple rule which uses regex to define targets
 *
 * @prefixes: Used to determine target's start. Target's end is always considered as whitespace
 *
 * @allowedCharacters: Used to allow special characters in target definition
 *
 * @style: Used to customize visual appearances of matching targets
 */

open class SimpleRule @JvmOverloads constructor(
  val prefixes: String,
  val allowedCharacters: String? = null,
  val style: Style? = null
) : Rule() {

  private val pattern by lazy {
    val start = "(?<=\\s|^)"
    val end = "(?=[']\\p{L}+|[.,;:?!](?:\\s|\$)|\\s|\$)"
    val middle = if (allowedCharacters.isNullOrEmpty()) {
      "([$prefixes]\\p{L}*)"
    } else {
      "([$prefixes]\\p{L}+[$allowedCharacters]\\p{L}+|[$prefixes]\\p{L}*)"
    }
    Regex(start + middle + end)
  }

  override fun isMatches(target: String): Boolean {
    return pattern.matches(target)
  }

  override fun getTargetStart(text: CharSequence, start: Int): Int {
    for (index in start downTo 0) {
      text.getOrNull(index)
        ?.takeIf { prefixes.contains(it) || it.isWhitespace() }
        ?.let { return index }
    }
    return start
  }

  override fun getTargetEnd(text: CharSequence, start: Int): Int {
    val end = text.length
    for (index in start until end) {
      text.getOrNull(index)
        ?.takeIf { allowedCharacters?.contains(it) == true || it.isWhitespace() }
        ?.let { return index }
    }
    return end
  }

  override fun applyStyle(text: Editable) {
    if (style == null) return

    // clear previous styles in case targets are invalidated
    text.getSpans(0, text.length, style::class.java)
      .forEach {
        text.removeSpan(it)
      }

    // apply new styles
    pattern.findAll(text, 0)
      .forEach {
        if (it.value.length == 1) return@forEach

        val start = it.range.first
        val end = it.range.last + 1
        text.setSpan(style.clone(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
      }
  }
}