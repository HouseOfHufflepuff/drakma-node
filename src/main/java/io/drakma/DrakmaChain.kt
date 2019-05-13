package io.drakma


object DrakmaChain {
    val chain = mutableListOf<Block>()
    val latestBlock: Block
        get() = chain.last()

    init {
        val genesisTransaction = ArrayList<Transaction>()
        genesisTransaction.add(Transaction("The Times 11-May-2019 Chancellor on brink of second bailout for banks"))
        chain.add(Block(0, "0", genesisTransaction))
    }

    fun mineBlock(transactions: ArrayList<Transaction>, nonce: Int): Block? {
        if(isValidProof(nonce)) {
            val block = Block(chain.size, latestBlock.hash, transactions)
            addNewBlock(block)
            return block
        }
        return null
    }

    private fun addNewBlock(block: Block) {
        if (isNewBlockValid(block)) chain.add(block)
    }

    private fun isValidProof(nonce: Int): Boolean {
        var isValidProof = false
        var targetLeadingZeroes = Utils.getDificultyString(Constants.difficulty)
        val nonceHash = Utils.calculateHash(
            latestBlock.hash +
                    Integer.toString(nonce)
        )
        if(nonceHash.substring(0, Constants.difficulty).equals(targetLeadingZeroes)) {
            isValidProof = true
        }
        return isValidProof
    }

    private fun isNewBlockValid(newBlock: Block): Boolean =
        ((newBlock.index == latestBlock.index + 1) || (newBlock.previousHash == latestBlock.hash))

}