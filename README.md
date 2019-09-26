# TextMatcher

[![](https://www.jitpack.io/v/santalu/textmatcher.svg)](https://www.jitpack.io/#santalu/textmatcher)
[![Build Status](https://travis-ci.org/santalu/textmatcher.svg?branch=master)](https://travis-ci.org/santalu/textmatcher)

A simple text watcher that matches specific targets like `mention` or `hashtag` in a string by defining `rules`

## How It Works

#### What is [`Style`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/style/Style.kt)

Style is basically a clickable span which lets you customize visual attributes of targets. 

It's also used to differentiate between targets. So it's strongly recommended to have a different style for each single rule.

Basic example: 

```
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
```

There are 3 built-in styles for easy implementation. This can be changed in future according to needs.

1. [`SimpleStyle`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/style/SimpleStyle.kt) is a open class which takes `textColor`, `isUnderline`, `isBold` and `typeface` as parameter to let you easily change these attributes without creating or overriding any method. For further customizations you can still override the `updateDrawState` method

2. [`MentionStyle`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/style/MentionStyle.kt) extends from `SimpleStyle` and intended to use only for mentions

3. [`HashtagStyle`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/style/HashtagStyle.kt) extends from `SimpleStyle` and intended to use only for hashtags

#### What is [`Rule`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/rule/Rule.kt)

`Rule` is simply definition of target boundaries and matching conditions. It's also used to apply styles for matching targets.

There are 3 built-in rules for easy implementation. This can be changed in future according to needs.

1. [`SimpleRule`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/rule/SimpleRule.kt) is a open class which takes `prefixes`, `allowedCharacters` and `style` as parameter which matches targets starting with `prefixes` and ending with space or any word breaking character.

2. [`MentionRule`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/rule/MentionRule.kt) extends from `SimpleRule` and matches targets which starts with `@`

3. [`HashtagRule`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/rule/HashtagRule.kt) extends from `SimpleRule` and matches targets which starts with `#`

#### Widgets

There are 3 built-in widgets for easy implementation. This can be changed in future according to needs.

1. [`MatcherTextView`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/widget/MatcherTextView.kt)

2. [`MatcherEditText`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/widget/MatcherEditText.kt)

3. [`MatcherInputEditText`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/widget/MatcherInputEditText.kt)

## Usage

#### 1. Define [`Style`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/style/Style.kt)

    val mentionStyle = MentionStyle(
      textColor = ContextCompat.getColor(this, R.color.colorPrimary),
      isBold = true
    )

#### 2. Define and Register [`Rule`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/rule/Rule.kt)

    val mentionRule = MentionRule(
      allowedCharacters = "._-",
      style = mentionStyle
    )
    
    editText.addRule(mentionRule)

#### 3. Register [`Listeners`](https://github.com/santalu/textmatcher/blob/master/library/src/main/java/com/santalu/textmatcher/Commons.kt)

To receive matching results on typing:

    editText.setOnMatchListener { rule, text ->
    }

To receive matching target's click event:

    editText.setOnMatchClickListener { text->
    }

## Gradle
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```
dependencies {
  implementation 'com.github.santalu:textmatcher:1.0.5'
}
```
## License
```
Copyright 2019 Fatih Santalu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
