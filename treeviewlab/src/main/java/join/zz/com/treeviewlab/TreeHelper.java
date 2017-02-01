package join.zz.com.treeviewlab;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import join.zz.com.treeviewlab.annotator.TreeNodeId;
import join.zz.com.treeviewlab.annotator.TreeNodeLabel;
import join.zz.com.treeviewlab.annotator.TreeNodePId;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/1/31 20:57.
 * description: You share rose get fun. *_* *_*
 */
public class TreeHelper {
    /**
     * 将用户的数据结构转换为可以辨识的树形数据结构(使用的是反射和注解)
     *
     * @param datas 需要转换的数据集
     * @param <T>   数据集中的数据类型
     * @return 返回规范树形结构之后的数据集
     */
    private static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<>();

        Node node;
        int id = -1;
        int pId = -1;
        String label = null;//需要显示的数据

        //遍历获取id，pid，label，添加到集合中
        for (T t : datas) {
            Field[] fields = t.getClass().getDeclaredFields();//获取所有的字段
            for (Field field : fields) {
                //遍历所有字段，获取添加指定注解的字段的值
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);//设置访问权限
                    id = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodePId.class) != null) {
                    field.setAccessible(true);
                    pId = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeLabel.class) != null) {
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }

        //遍历记录父子节点之间的关系
        Node n;
        Node m;
        for (int i = 0; i < nodes.size(); i++) {
            n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                m = nodes.get(j);
                if (n.pId == m.id) {
                    //n节点的父节点是m
                    n.parent = m;
                    m.children.add(n);
                } else if (n.id == m.pId) {
                    //n的子节点有m
                    n.children.add(m);
                    m.parent = n;
                }
            }
        }

        //设置图标
        for (Node x : nodes) {
            setIcon(x);
        }
        return nodes;
    }

    /**
     * 获取排序之后的Node集合
     *
     * @param datas         原始的数据集合
     * @param defaultExpand 默认展开的层级
     * @param <T>           原始数据类型
     * @return 返回已经排序并且已经转换为Node之后的数据Node集合
     * @throws IllegalAccessException
     */
    public static <T> List<Node> getSortedNodeList(List<T> datas, int defaultExpand) throws IllegalAccessException {
        List<Node> result = new ArrayList<>();
        List<Node> nodes = convertDatas2Nodes(datas);//转换为Node节点数据类型
        List<Node> rootNodes = getAllRootNodeList(nodes);//获取所有根节点
        //遍历所有的根节点，将所有根节点中对应的所有孩子节点进行排序添加到新的集合中
        for (Node n : rootNodes) {
            addToResultNodeList(result, n, defaultExpand, 1);
        }
        return result;
    }

    /**
     * 过滤可见的Node集合（即展开的节点）
     *
     * @param datas
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> datas) {
        List<Node> result = new ArrayList();
        for (Node node : datas) {
            if (node.isRootNode() || node.isParentExpand()) {
                setIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * @param result
     * @param node
     * @param defaultExpand 默认展开的层级
     * @param currentExpand 当前展开的层级
     */
    private static void addToResultNodeList(List<Node> result, Node node, int defaultExpand, int currentExpand) {
        result.add(node);
        if (defaultExpand >= currentExpand) {
            //设置默认展开层级
            node.setIsExpand(true);
        }
        //如果是叶子节点直接结束
        if (node.isLeafNode()) return;

        for (int i = 0; i < node.children.size(); i++) {
            //不是叶子节点，继续添加
            addToResultNodeList(result, node.children.get(i), defaultExpand, currentExpand + 1);
        }
    }

    /**
     * 获取所有的根节点
     *
     * @param nodes
     * @return
     */
    private static List<Node> getAllRootNodeList(List<Node> nodes) {
        List<Node> roots = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRootNode()) {
                roots.add(node);
            }
        }
        return roots;
    }

    /**
     * 设置图标
     *
     * @param node 没有图标的icon是-1
     */
    public static void setIcon(Node node) {
        if (node.children.size() > 0 && node.getIsExpand()) {
            //有孩子并且是展开的
            node.icon = R.drawable.tree_ex;
        } else if (node.children.size() > 0 && !node.getIsExpand()) {
            //有孩子但是没有展开
            node.icon = R.drawable.tree_ec;
        } else {
            node.icon = -1;
        }
    }
}
