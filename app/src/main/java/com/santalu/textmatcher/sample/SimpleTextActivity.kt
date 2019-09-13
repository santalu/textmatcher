package com.santalu.textmatcher.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.santalu.textmatcher.rule.HashtagRule
import com.santalu.textmatcher.rule.MentionRule
import com.santalu.textmatcher.style.HashtagStyle
import com.santalu.textmatcher.style.MentionStyle
import kotlinx.android.synthetic.main.activity_simple_text.textView

/**
 * Created by fatih.santalu on 9/9/2019
 */

class SimpleTextActivity : AppCompatActivity(R.layout.activity_simple_text) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val mentionStyle = MentionStyle(
      textColor = ContextCompat.getColor(this, R.color.colorPrimary),
      isBold = true
    )
    val mentionRule = MentionRule(
      allowedCharacters = "._-",
      style = mentionStyle
    )

    val hashtagStyle = HashtagStyle(
      textColor = ContextCompat.getColor(this, R.color.colorAccent),
      isBold = true
    )
    val hashtagRule = HashtagRule(
      allowedCharacters = "-_",
      style = hashtagStyle
    )

    textView.addRule(mentionRule)
    textView.addRule(hashtagRule)

    textView.setOnMatchClickListener { showToast(it) }
  }
}