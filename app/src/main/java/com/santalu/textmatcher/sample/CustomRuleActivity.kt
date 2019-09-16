package com.santalu.textmatcher.sample

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import com.santalu.textmatcher.findMatches
import com.santalu.textmatcher.rule.SimpleRule
import com.santalu.textmatcher.style.Style
import kotlinx.android.synthetic.main.activity_custom_rule.editText
import kotlinx.android.synthetic.main.activity_custom_rule.showAllButton
import kotlinx.android.synthetic.main.activity_custom_rule.textView

/**
 * Created by fatih.santalu on 9/9/2019
 */

class CustomRuleActivity : AppCompatActivity(R.layout.activity_custom_rule) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val customStyle = CustomStyle()
    val customRule = SimpleRule("@#", "_", customStyle)
    editText.addRule(customRule)
    textView.addRule(customRule)

    editText.setOnMatchListener { rule, text ->
      textView.text = if (text.isNullOrEmpty()) {
        getString(R.string.no_match)
      } else {
        "Match $text"
      }
    }

    textView.setOnMatchClickListener { showToast(it) }
    editText.setOnMatchClickListener { showToast(it) }

    showAllButton.setOnClickListener {
      val matches = editText.findMatches<CustomStyle>()
      textView.text = matches?.joinToString() ?: getString(R.string.no_match)
    }
  }
}

class CustomStyle : Style() {

  override fun updateDrawState(ds: TextPaint) {
    super.updateDrawState(ds)
    ds.apply {
      isUnderlineText = false
      color = Color.DKGRAY
      typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
    }
  }
}