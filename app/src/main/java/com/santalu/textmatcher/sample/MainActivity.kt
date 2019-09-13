package com.santalu.textmatcher.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_main.customRuleButton
import kotlinx.android.synthetic.main.activity_main.multiRuleButton
import kotlinx.android.synthetic.main.activity_main.simpleTextButton
import kotlinx.android.synthetic.main.activity_main.singleRuleButton

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MainActivity : AppCompatActivity(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    singleRuleButton.setOnClickListener {
      startActivity<SingleRuleActivity>()
    }

    multiRuleButton.setOnClickListener {
      startActivity<MultiRuleActivity>()
    }

    customRuleButton.setOnClickListener {
      startActivity<CustomRuleActivity>()
    }

    simpleTextButton.setOnClickListener {
      startActivity<SimpleTextActivity>()
    }
  }
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
  val intent = Intent(this, T::class.java).apply { putExtras(bundleOf(*params)) }
  startActivity(intent)
}