package com.example.petbeer

import android.text.Editable

interface TextWatcher {
    fun afterTextChanged(text: Editable)
}