package io.drakma

import org.apache.commons.codec.digest.DigestUtils

object Utils {
    fun getDificultyString(difficulty: Int): String {
        return String(CharArray(difficulty)).replace('\u0000', '0')
    }

    fun calculateHash(str : String) : String {
        val input = str.toByteArray()
        return DigestUtils.sha256Hex(input)
    }
}