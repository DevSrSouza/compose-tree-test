package compose

import domain.Node

internal class NodeWrapper internal constructor(internal val realNode: Node) {

    fun insert(index: Int, instance: NodeWrapper) {
        println("NodeWrapper.insert()")
        realNode.children.add(index, instance.realNode)
    }

    fun remove(index: Int, count: Int) {
        println("NodeWrapper.remove()")
        repeat(count) {
            realNode.children.removeAt(index)
        }
    }

    fun move(from: Int, to: Int, count: Int) {
        println("NodeWrapper.move()")
        if (from > to) {
            repeat(count) {
                realNode.children.add(to + it, realNode.children[from + it])
                //realNode.insertBefore(realNode.childNodes[from + it]!!, realNode.childNodes[to + it])
            }
        } else {
            repeat(count) {
                realNode.children.add(to, realNode.children[from])
                //realNode.insertBefore(realNode.childNodes[from]!!, realNode.childNodes[to])
            }
        }
    }
}