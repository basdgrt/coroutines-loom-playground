import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.jetbrains.annotations.BlockingExecutor
import java.util.concurrent.Executor
import kotlin.coroutines.CoroutineContext

@BlockingExecutor
object VirtualThreadDispatcher : ExecutorCoroutineDispatcher(), Executor {
    override val executor: Executor
        get() = this

    override fun close() = throw UnsupportedOperationException()

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        Thread.startVirtualThread(block)
    }

    override fun execute(command: java.lang.Runnable) {
        Thread.startVirtualThread(command)
    }

    override fun toString() = "VirtualThreadDispatcher"
}