package app.sh.com.treeview.utils.listener;

import join.zz.com.treeviewlab.Node;

/**
 * author: 樊浩鹏
 * Created by fhp on 2016/11/13 13:03.
 * description: You share rose get fun. *_* *_*
 */
public interface RecycleClickItemListener {
    /**
     * 点击事件
     *
     * @param position 点击的adapter中的位置
     * @param node     节点对象
     */
    void onRecycleItemCLick(int position, Node node);
}
