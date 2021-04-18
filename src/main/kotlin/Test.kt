import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import compose.composable
import compose.renderComposable
import domain.Node
import kotlinx.coroutines.delay

val state = mutableStateOf(false)

suspend fun main() {
    val root = Node("root")

    renderComposable(root) {
        composable(Node("child1")) {
            val testState = remember { state }

            LaunchedEffect(testState) {
                println("testState: ${testState.value}")
            }

            if(testState.value) {
                composable(Node("child2")) {
                    composable(Node("child3")) {}
                }
            }

            composable(Node("child4")) {}
            composable(Node("child5")) {}
        }


        composable(Node("child6")) {}
        composable(Node("child7")) {}
    }

    println(root.children)

    delay(1000)

    state.value = true

    delay(1000)

    println(root.children)
}