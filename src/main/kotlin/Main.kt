import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        launchBlockingCalls(1000)
        launchBlockingCalls(10_000)
        launchBlockingCalls(100_000)
        launchBlockingCalls(1000_000)
    }
}

suspend fun launchBlockingCalls(amountOfCalls: Int) = measureTimeMillis {
    supervisorScope {
        repeat(amountOfCalls) {
            launch(VirtualThreadDispatcher) { Thread.sleep(1000) }
        }
    }
}.also { println("Executing $amountOfCalls blocking calls on $VirtualThreadDispatcher took ${it}ms") }
