package com.santalu.textmatcher.sample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.santalu.textmatcher.rule.MentionRule
import kotlinx.android.synthetic.main.activity_single_rule.button
import kotlinx.android.synthetic.main.activity_single_rule.editText
import kotlinx.android.synthetic.main.activity_single_rule.textView

/**
 * Created by fatih.santalu on 9/9/2019
 */

class SingleRuleActivity : AppCompatActivity(R.layout.activity_single_rule) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val rule = MentionRule()
    editText.addRule(rule)
    textView.addRule(rule)

    editText.setOnMatchListener { rule, text ->
      textView.text = if (text.isNullOrEmpty()) {
        getString(R.string.no_mention)
      } else {
        "mention $text"
      }
    }

    textView.setOnMatchClickListener { showToast(it) }
    editText.setOnMatchClickListener { showToast(it) }

    button.setOnClickListener {
      val success = editText.replace("@mention")
      Toast.makeText(
        this,
        if (success) R.string.target_at_selection else R.string.no_target_at_selection,
        Toast.LENGTH_SHORT
      ).show()
    }
  }
}

fun Context.showToast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}