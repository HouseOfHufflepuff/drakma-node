package io.drakma

class Transaction(
    var data: String = ""
) {
    var transactionHash: String = Utils.calculateHash(data)

}