package year15

import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    //part1()
    part2()
}

private fun part1() {
    val generatedHash = "ckczppom"
    var count = 0
    while (true) {
        val md5Promo = (generatedHash + count.toString()).toMd5()

        val first5Char = md5Promo.substring(0, 5)

        if (first5Char == "00000") {
            println(count)
            break
        } else {
            count++
        }

    }

}

private fun part2() {
    val secretKey = "ckczppom"
    val baseBytes = secretKey.toByteArray(Charsets.UTF_8)

    val found = AtomicBoolean(false)
    val globalCounter = AtomicInteger(0)
    val result = AtomicInteger(-1)

    val numCores = Runtime.getRuntime().availableProcessors()
    val threads = mutableListOf<Thread>()

    repeat(numCores) {
        threads.add(thread {
            val md = MessageDigest.getInstance("MD5")

            while (!found.get()) {
                val count = globalCounter.getAndIncrement()

                val countBytes = count.toString().toByteArray(Charsets.UTF_8)

                md.reset()
                md.update(baseBytes)
                md.update(countBytes)
                val digest = md.digest()

                val isMatch = digest[0].toInt() == 0 && digest[1].toInt() == 0 && digest[2].toInt() == 0

                if (isMatch) {
                    if (found.compareAndSet(false, true)) {
                        result.set(count)
                    }
                    break
                }
            }
        })
    }

    for (t in threads) {
        t.join()
    }

    println("${result.get()}")
}

private fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}