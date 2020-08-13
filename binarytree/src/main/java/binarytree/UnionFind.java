package binarytree;

/**
 * 并查集，JAVA实现
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/08 10:40
 */
public class UnionFind {

    //使用数组存储每一个元素的上级节点
    private int[] parent;

    //初始化并查集
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    //查找
    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    //合并
    public void union(int x, int y) {
            int rootX=find(x);
            int rootY=find(y);
            parent[rootX]=rootY;
    }
}
