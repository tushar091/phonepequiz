package com.example.phonepequiz.utils

import java.util.*

public fun jumbleSequence(name: String): List<Char> {
    val chars = mutableListOf<Char>()

    for (ch in name.toCharArray()) {
        chars.add(ch)
    }
    chars.shuffle()
    return chars
}