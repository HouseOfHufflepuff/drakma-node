package io.drakma

import io.drakma.utils.Utils

class Transaction(
    var data: String = ""
) {
    var transactionHash: String = Utils.calculateHash(data)

}