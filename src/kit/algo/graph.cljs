(ns kit.algo.graph
  (:require
    [clojure.set :as set]))

(defprotocol Graph
  "All graphs conform to this protocol"
  (nodes [_]
    "Returns the set of nodes in the graph")
  (edges [_]
    "Returns a map from nodes to all linked nodes")
  (adjecant-to [_ node]
    "Returns a set of nodes for which an incoming edge from node exists")
  (adjecent [_ node x]
    "Returns true when there is an edge from node to x"))

(deftype DirectedGraph [node-set edge-map]
  Graph
  (nodes [_]
    node-set)
  (edges [_]
    edge-map)
  (adjecant-to [_ node]
    (get edge-map node))
  (adjecent [g node x]
    (contains? (adjecant-to g node) x)))

(defn make [nodes edges]
  (DirectedGraph. nodes edges))

(defn- visit [g marked node]
  (lazy-seq
    (->> (adjecant-to g node)
         (remove marked)
         (mapcat #(visit g (conj marked node) %))
         (cons node))))

(defn topsort
  "Topological sort of a graph given by a set of nodes and a partial
   function from the set of nodes onto itself"
  [g]
  (loop [result '()
         nodes  (nodes g)]
    (if (not-empty nodes)
      (let [node   (first nodes)
            marked (set result)
            sorted (concat (visit g marked node) result)]
        (recur sorted (apply disj nodes sorted)))
      result)))
