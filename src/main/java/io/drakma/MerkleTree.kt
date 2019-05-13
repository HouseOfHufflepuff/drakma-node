package io.drakma

import java.lang.StringBuilder

object MerkleTree {

    fun getMerkleRoot(transactions: ArrayList<Transaction>?) : String {
        var sb: StringBuilder = StringBuilder("")
        transactions?.forEach { transaction ->
            sb.append(transaction.transactionHash)
        }
        return sb.toString()
    }


}