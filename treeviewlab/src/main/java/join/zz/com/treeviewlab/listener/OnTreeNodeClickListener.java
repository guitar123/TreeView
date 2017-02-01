package join.zz.com.treeviewlab.listener;

import join.zz.com.treeviewlab.Node;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/2/1 18:40.
 * description: You share rose get fun. *_* *_*
 * <p>
 * 设置Node的点击回调
 */

public interface OnTreeNodeClickListener {
    /**
     * 点击的树型节点
     *
     * @param node
     * @param position
     */
    void onClickTreeNode(Node node, int position);
}
