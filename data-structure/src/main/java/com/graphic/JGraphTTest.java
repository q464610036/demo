package com.graphic;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import javafx.scene.DepthTest;
import org.jgrapht.*;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.ClosestFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈孟飞
 * @date 2022/6/10
 */
public class JGraphTTest {

    private static final int ZERO = 0;

    public static void main(String[] args) throws URISyntaxException {

        //编辑一张图
        Graph<String, DefaultEdge> g = createStringGraph();
        //打印这张图
        System.out.println(g);

        //遍历图，找到指定顶点
        String startVertex = g.vertexSet().stream().filter(vertex -> vertex.equals("1")).findAny()
                .get();

        //获取当前节点的叶子结点
        List<String> leaves = findLeaves(g, "1");
        System.out.println(leaves);

        //获取当前节点的所有父节点
        List<String> predecessors = getAllPredecessor(g, "7");
        System.out.println(predecessors);

    }

    /**
     * 返回当前节点的叶子节点
     * @param graph
     * @param vertex
     * @param <V>
     * @param <E>
     * @return
     */
    private static <V, E> List<V> findLeaves(Graph<V, E> graph, V vertex) {
        Preconditions.checkNotNull(graph, "graph is null");
        if (CollectionUtils.isEmpty(graph.vertexSet())) {
            return Lists.newArrayList();
        }
        Set<V> result = new HashSet<>();
        getAllSuccessor(result, graph, vertex);
        //返回后续节点的叶子节点
        return result.stream().filter(v -> {
            return graph.outDegreeOf(v) == ZERO;
        }).collect(Collectors.toList());
    }

    /**
     * 获取当前节点所有后续节点
     * @param result
     * @param graph
     * @param vertex
     * @param <V>
     * @param <E>
     * @return
     */
    private static <V, E> void getAllSuccessor(Set<V> result, Graph<V, E> graph, V vertex){
        // 当前顶点的后续节点列表
        List<V> list = Graphs.successorListOf(graph, vertex);
        if (list.isEmpty()) {
            return;
        }
        result.addAll(list);
        //继续递归这些节点的后续节点
        list.forEach(v ->{
            getAllSuccessor(result, graph, v);
        });
    }

    /**
     * 获取当前节点的所有父节点
     * @param result
     * @param graph
     * @param vertex
     * @param <V>
     * @param <E>
     */
    private static <V, E> void getAllPredecessor(Set<V> result, Graph<V, E> graph, V vertex){
        // 当前顶点的父节点列表
        List<V> list = Graphs.predecessorListOf(graph, vertex);
        if (list.isEmpty()) {
            return;
        }
        result.addAll(list);
        //继续递归这些节点的父节点
        list.forEach(v ->{
            getAllPredecessor(result, graph, v);
        });
    }

    /**
     * 获取当前节点的所有父节点
     * @param graph
     * @param vertex
     * @param <V>
     * @param <E>
     * @return
     */
    private static <V, E> List<V> getAllPredecessor(Graph<V, E> graph, V vertex){
        Set<V> result = new HashSet<>();
        getAllPredecessor(result, graph, vertex);
        return new ArrayList<>(result);
    }

    /**
     * 找到图中的根节点
     *
     * @param <V>
     * @param <E>
     * @param graph
     * @return
     * @author kanpiaoxue
     * @CreateTime: 2019/09/03 15:53:08
     * @Description: 图中的根节点：它们的入度为 0
     */
    public static <V, E> List<V> findRoots(Graph<V, E> graph) {
        Preconditions.checkNotNull(graph, "graph is null");
        if (CollectionUtils.isEmpty(graph.vertexSet())) {
            return Lists.newArrayList();
        }
        return graph.vertexSet().stream().filter(v -> {
            return graph.inDegreeOf(v) == ZERO;
        }).collect(Collectors.toList());
    }

    //以下程序为建立一张图，jgrapht 中有各种图，总之按规范建就好。
    private static Graph<String, DefaultEdge> createStringGraph() throws URISyntaxException {


        Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);


        // add the vertices
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");
        g.addVertex("7");

        // add edges to create linking structure
        g.addEdge("1", "2");
        g.addEdge("1", "3");
        g.addEdge("1", "4");
        g.addEdge("2", "5");
        g.addEdge("3", "6");
        g.addEdge("5", "4");
        g.addEdge("6", "7");
        return g;
    }
}
