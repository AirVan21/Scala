import org.scalatest._

class GraphTest extends FunSuite {
  test("BasicGraph") {
    val basicGraph = new BasicGraph

    val nodeFst = new basicGraph.GraphNode
    val nodeSnd = new basicGraph.GraphNode
    val nodeThd = new basicGraph.GraphNode

    val edgeFst = new basicGraph.GraphEdge(nodeFst, nodeSnd)
    val edgeSnd = new basicGraph.GraphEdge(nodeSnd, nodeThd)

    basicGraph.addNode(nodeFst)
    basicGraph.addNode(nodeSnd)
    basicGraph.addNode(nodeThd)
    basicGraph.addEdge(edgeFst)
    basicGraph.addEdge(edgeSnd)

    assertResult(3)(basicGraph.nodeCount)
    assertResult(2)(basicGraph.edgeCount)

    assert(basicGraph.areConnected(nodeFst, nodeSnd))
    assert(!basicGraph.areConnected(nodeFst, nodeThd))
  }

  test("ColoredGraph") {
    val coloredGraph = new ColoredGraph

    val nodeFst = new coloredGraph.GraphNode(1)
    val nodeSnd = new coloredGraph.GraphNode(2)
    val edgeFst = new coloredGraph.GraphEdge(nodeFst, nodeSnd)

    coloredGraph.addNode(nodeFst)
    coloredGraph.addNode(nodeSnd)
    coloredGraph.addEdge(edgeFst)

    assertResult(2)(coloredGraph.nodeCount)
    assertResult(1)(coloredGraph.edgeCount)

    assert(coloredGraph.hasColor(1))
    assert(!coloredGraph.hasColor(42))
  }

  test("DirectedGraph") {
    val directedGraph = new DirectedGraph

    val nodeFst = new directedGraph.GraphNode
    val nodeSnd = new directedGraph.GraphNode
    val edgeFst = new directedGraph.GraphEdge(nodeFst, nodeSnd)

    directedGraph.addNode(nodeFst)
    directedGraph.addNode(nodeSnd)
    directedGraph.addEdge(edgeFst)

    assert(directedGraph.areConnected(nodeFst, nodeSnd))
    assert(!directedGraph.areConnected(nodeSnd, nodeFst))
  }
}