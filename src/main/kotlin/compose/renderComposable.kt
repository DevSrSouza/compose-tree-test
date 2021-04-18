package compose

import androidx.compose.runtime.*
import domain.Node
import kotlinx.coroutines.*
import java.util.concurrent.Executors

val uiThread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
val globalSnapshotManager = GlobalSnapshotManager(uiThread)

fun renderComposable(root: Node, content: @Composable () -> Unit): Composition {
    globalSnapshotManager.ensureStarted()

    val context = DefaultMonotonicFrameClock + uiThread
    val recomposer = Recomposer(context)

    CoroutineScope(context).launch(start = CoroutineStart.UNDISPATCHED) {
        recomposer.runRecomposeAndApplyChanges()
    }

    val composition = Composition(
        applier = NodeApplier(root),
        parent = recomposer
    )
    composition.setContent(content)

    return composition
}

@Composable
fun composable(node: Node, content: @Composable () -> Unit) {
    ComposeNode<NodeWrapper, NodeApplier>(
        factory = { NodeWrapper(node) },
        update = {
            println("ComposeNode->update()")
        },
        content = content
    )
}