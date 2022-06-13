package com.graphic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 图的基本操作
 *
 * @author 陈孟飞
 * @date 2022/6/9
 */
public class AdjacencyMatrixGraph {
    // 邻接矩阵
    private final int[][] adjacencyMatrix;
    // 顶点集集合
    private final List<String> vertices;
    // 边数
    private int edges;
    // 是否有向图，true有向图，false无向图
    private final boolean directed = true;

    public AdjacencyMatrixGraph(int vertexNum) {
        adjacencyMatrix = new int[vertexNum][vertexNum];
        vertices = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            int i1 = i + 1;
            vertices.add(i,  i1+"");
        }
        edges = 0;
    }
    public AdjacencyMatrixGraph addEdges(Integer v1, Integer v2) {
        addEdge(v1, v2, directed, 1);
        return this;
    }
    public AdjacencyMatrixGraph addEdges(Integer v1, List<Integer> v2List) {
        if (!v2List.isEmpty()) {
            addEdges(v1, v2List, directed, 1);
        }
        return this;
    }
    public AdjacencyMatrixGraph addEdges(Integer v1, Integer v2, int weight) {
        addEdge(v1, v2, directed, weight);
        return this;
    }
    /**
     * 添加边
     *
     * @param v1       顶点v1
     * @param v2       顶点v2
     * @param directed 是否有向图，true有向图，false无向图
     * @param weight   权重
     * @return
     */
    private AdjacencyMatrixGraph addEdge(Integer v1, Integer v2, boolean directed, int weight) {
        if (!directed) {
            this.adjacencyMatrix[v2 - 1][v1 - 1] = weight;
        }
        this.adjacencyMatrix[v1 - 1][v2 - 1] = weight;
        this.edges++;
        return this;
    }
    private AdjacencyMatrixGraph addEdges(Integer v1, List<Integer> v2List, boolean directed, int weight) {
        if (!directed) {
            for (Integer v2 : v2List) {
                this.adjacencyMatrix[v2 - 1][v1 - 1] = weight;
            }
        }
        for (Integer v2 : v2List) {
            this.adjacencyMatrix[v1 - 1][v2 - 1] = weight;
        }
        this.edges += v2List.size();
        return this;
    }
    public AdjacencyMatrixGraph delEdge(String v1, String v2) {
        int index1 = this.vertices.indexOf(v1);
        int index2 = this.vertices.indexOf(v2);
        return delEdge(index1, index2);
    }

    /**
     * 删除边
     *
     * @param index1
     * @param index2
     * @return
     */
    public AdjacencyMatrixGraph delEdge(int index1, int index2) {
        throwCustomizeException(index1, index2);
        this.adjacencyMatrix[index1][index2] = 0;
        this.edges--;
        return this;
    }

    private void showGraph() {
        for (int[] matrix : adjacencyMatrix) {
            System.err.println(Arrays.toString(matrix));
        }
    }
    public List<String> getNextVertexByName(String vertex) {
        int index = this.vertices.indexOf(vertex);
        return getNextVertexByIndex(index);
    }
    /**
     * 根据顶点的下标找出下个顶点
     *
     * @param index
     * @return
     */
    public List<String> getNextVertexByIndex(int index) {
        List<String> vertices = new ArrayList<>();
        throwCustomizeException(index);
        for (int i = 0; i < this.adjacencyMatrix[index].length; i++) {
            int matrix = this.adjacencyMatrix[index][i];
            if (matrix != 0) {
                vertices.add(this.vertices.get(i));
            }
        }
        return vertices;
    }
    /**
     * 抛出顶点不存在异常
     * @param indexs
     */
    private void throwCustomizeException(int... indexs) {
        for (int index : indexs) {
            if (index < 0 || index >= this.vertices.size()) {
                throw new RuntimeException("顶点不存在");
            }
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(7);
        adjacencyMatrixGraph.addEdges(1, Arrays.asList(2, 3, 4))
                .addEdges(2, Arrays.asList(5))
                .addEdges(3, Arrays.asList(6, 7))
                .addEdges(4, Collections.emptyList())
                .addEdges(5, Collections.emptyList())
                .addEdges(6, Collections.singletonList(7))
                .addEdges(7, Collections.emptyList());
        adjacencyMatrixGraph.showGraph();
        //获取顶点1的下一个顶点
        List<String> nextVertices = adjacencyMatrixGraph.getNextVertexByName("1");
        System.out.println(nextVertices);
        //删除边1-3
        adjacencyMatrixGraph.delEdge("1", "3");
        nextVertices = adjacencyMatrixGraph.getNextVertexByName("1");
        System.out.println(nextVertices);
    }
}