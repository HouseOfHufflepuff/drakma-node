package io.drakma

import io.javalin.Javalin

val drakma = Blockchain
object Node {

    @JvmStatic
    fun main(args: Array<String>) {
        val app = Javalin.create().start(7000)
        app.get("/blocks") { ctx ->
            ctx.json(drakma.chain)
        }
        app.post("/blocks/mine") { ctx ->
            val nonce: Int  = ctx.queryParam("nonce", "0")!!.toInt()
            System.out.println("nonce:" + nonce)
            val minedBlock = Blockchain.mineBlock(ctx.body(), nonce)
            if(minedBlock != null) {
                ctx.json(minedBlock)
            } else {
                ctx.res.status = 522
                ctx.json("invalid nonce")
            }
        }

    }
}
