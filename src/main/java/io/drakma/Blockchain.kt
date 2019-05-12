package io.drakma

import org.apache.commons.codec.digest.DigestUtils

object Blockchain {
    val chain = mutableListOf<Block>()
    val latestBlock: Block
        get() = chain.last()

    init {
        chain.add(Block(0, "0", "The Times 11-May-2019 Chancellor on brink of second bailout for banks"))
    }

    fun mineBlock(data: Any, nonce: Int): Block? {
        if(isValidProof(nonce)) {
            val block = Block(chain.size, latestBlock.hash, data)
            addNewBlock(block)
            return block
        }
        return null
    }

    fun calculateHash(nonce: Int): String {
        val hash = DigestUtils.sha256Hex(
            latestBlock.hash +
                    Integer.toString(nonce)
        )
        return hash
    }

    private fun addNewBlock(block: Block) {
        if (isNewBlockValid(block)) chain.add(block)
    }

    private fun isValidProof(nonce: Int): Boolean {
        var isValidProof = false
        var targetLeadingZeroes = Utils.getDificultyString(Constants.difficulty)
        val hash = calculateHash(nonce)
        if(hash.substring(0, Constants.difficulty).equals(targetLeadingZeroes)) {
            isValidProof = true
        }
        return isValidProof
    }

    private fun isNewBlockValid(newBlock: Block): Boolean =
        ((newBlock.index == latestBlock.index + 1) || (newBlock.previousHash == latestBlock.hash))

}