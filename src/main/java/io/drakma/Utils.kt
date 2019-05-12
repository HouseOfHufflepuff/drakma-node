package io.drakma

object Utils {
    fun getDificultyString(difficulty: Int): String {
        return String(CharArray(difficulty)).replace('\u0000', '0')
    }
}