package io.drakma


import io.drakma.utils.Utils
import java.util.*

class Block(val index: Int,
            val previousHash: String,
            val transactions: ArrayList<Transaction>) {


    val timestamp: Long = Date().time
    val merkleRoot = MerkleTree.getMerkleRoot(transactions)
    val hash = Utils.calculateHash(index.toString() + previousHash + timestamp + merkleRoot)

}