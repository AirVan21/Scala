import scala.collection.mutable.ArrayBuffer

trait Graph {
  type GraphNode <: Node
  type GraphEdge <: Edge

  class Node
  case class Edge(start : GraphNode, end : GraphNode)

  def addNode(node : GraphNode): Unit = {
    nodes += node
  }

  def addEdge(edge : GraphEdge): Unit = {
    edges += edge
  }

  def nodeCount(): Int = {
    nodes.size
  }

  def edgeCount(): Int = {
    edges.size
  }

  def areConnected(fst : GraphNode, snd : GraphNode): Boolean = {
    edges.exists(edge => edge.start == fst && edge.end == snd || edge.start == snd && edge.end == fst)
  }

  val nodes = ArrayBuffer[GraphNode]()
  val edges = ArrayBuffer[GraphEdge]()
}

class BasicGraph extends Graph {
  override type GraphNode = Node
  override type GraphEdge = Edge
}

class ColoredGraph extends Graph {
  case class ColoredNode(color : Int) extends Node

  def hasColor(color : Int) : Boolean = {
    nodes.exists(node => node.color == color)
  }

  override type GraphNode = ColoredNode
  override type GraphEdge = Edge
}

class DirectedGraph extends Graph {
  class DirectedEdge(start : GraphNode, end : GraphNode) extends Edge(start, end)

  override def areConnected(fst : GraphNode, snd : GraphNode): Boolean = {
    edges.exists(edge => edge.start == fst && edge.end == snd)
  }

  override type GraphNode = Node
  override type GraphEdge = DirectedEdge
}