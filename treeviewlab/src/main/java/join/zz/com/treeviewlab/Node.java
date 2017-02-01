package join.zz.com.treeviewlab;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/2/1 11:55.
 * description: You share rose get fun. *_* *_*
 */

public class Node {

    public Node() {
    }

    public Node(int id, int pId, String label) {
        this.id = id;
        this.pId = pId;
        this.label = label;
    }

    public int id;
    public int pId = 0;//根节点pId为0

    public Node parent;//父节点
    public List<Node> children = new ArrayList<>();//存放所有孩子节点

    private int level;//树的层级
    public int icon;//图标，最里层是不用显示图标的,   -1表示最里层没有图标

    private boolean isExpand = false;//是否是展开的，不允许直接进行设置

    public String label;//需要显示的数据

    /**
     * 获取当前节点是否是展开状态
     *
     * @return
     */
    public boolean getIsExpand() {
        return isExpand;
    }

    /**
     * 设置当前结点是否展开（如果是收缩，会将其中的所有节点都设置为收缩）
     */
    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
        //将所有的子节点都设置为false
        if (!isExpand) {
            for (Node child : children) {
                child.setIsExpand(false);
            }
        }
    }

    /**
     * 是否是根节点
     *
     * @return
     */
    public boolean isRootNode() {
        return parent == null;
    }

    /**
     * 当前结点的父节点是否是展开的
     *
     * @return
     */
    public boolean isParentExpand() {
        if (parent == null) {
            //是根节点
            return false;
        } else {
            return parent.isExpand;
        }
    }

    /**
     * 是否是叶子节点（叶子节点没有子节点只有父节点，所以不需要显示图标）
     *
     * @return
     */
    public boolean isLeafNode() {
        return children.size() == 0;
    }

    /**
     * 获取当前节点的层级，根节点层级是0
     *
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

}
