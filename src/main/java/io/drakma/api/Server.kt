package io.drakma.api

import com.google.gson.GsonBuilder
import io.javalin.Javalin
import com.google.gson.reflect.TypeToken
import io.drakma.DrakmaChain
import io.drakma.Transaction


object Server {

    @JvmStatic
    fun main(args: Array<String>) {
        val app = Javalin.create().start(7000)
        val gson = GsonBuilder().create()

        app.get("/blocks") { ctx ->
            ctx.json(DrakmaChain.chain)
        }

        app.post("/blocks/mine") { ctx ->
            val nonce: Int  = ctx.queryParam("nonce", "0")!!.toInt()
            val transactions: ArrayList<Transaction> =
                gson.fromJson(ctx.body(), object : TypeToken<List<Transaction>>() {}.type)

            System.out.println("nonce:" + nonce)

            val minedBlock = DrakmaChain.mineBlock(transactions, nonce)
            if(minedBlock != null) {
                System.out.println(minedBlock)
                ctx.json(minedBlock)
            } else {
                ctx.res.status = 522
                ctx.json("invalid nonce")
            }
        }
    }
}
