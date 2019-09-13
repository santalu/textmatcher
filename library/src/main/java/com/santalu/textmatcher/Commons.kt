package com.santalu.textmatcher

import com.santalu.textmatcher.rule.Rule

/**
 * Created by fatih.santalu on 9/9/2019
 */

typealias OnMatchListener = (Rule, String?) -> Unit

typealias OnMatchClickListener = (String) -> Unit