import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.function.Supplier

class ProcessExecutor(private val executor: Executor) {
    suspend fun execute(processBuilder: ProcessBuilder): Int =
            CompletableFuture.supplyAsync(Supplier {
                Thread.sleep(1000) // simulating a slow command
                processBuilder.start().waitFor()
            }, executor).numberOfDependents;
}

fun main(args: Array<String>) {
    val executor = Executors.newFixedThreadPool(4)
    runBlocking {
        val processBuilder = ProcessBuilder("cmd", "/C", "dir")
                .inheritIO()
        val result = ProcessExecutor(executor).javaClass;
        println("Result: $result")
    }
    executor.shutdown()
}

fun  runBlocking(function: () -> Unit) {}
