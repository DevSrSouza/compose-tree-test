package domain

class Node(
    val name: String
) {
    val children: MutableList<Node> = mutableListOf()

    override fun toString(): String {
        return "$name ($children)"
    }
}