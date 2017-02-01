package join.zz.com.treeviewlab.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import join.zz.com.treeviewlab.Node;
import join.zz.com.treeviewlab.TreeHelper;
import join.zz.com.treeviewlab.listener.OnTreeNodeClickListener;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/2/1 16:48.
 * description: You share rose get fun. *_* *_*
 * <p>
 * ListView树形控件Adapter
 */

public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<Node> mAllNodeList = null;//所有的Node集合
    protected List<Node> mVisibleNodeList = null;//所有显示的Node集合

    protected OnTreeNodeClickListener mListener;

    /**
     * 设置点击可见节点的监听
     */
    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * @param dataList      数据集合
     * @param defaultExpand 默认展开层级
     * @throws IllegalAccessException
     */
    public TreeListViewAdapter(Context context, ListView listView, List<T> dataList, int defaultExpand) throws IllegalAccessException {
        this.mContext = context;
        mAllNodeList = TreeHelper.getSortedNodeList(dataList, defaultExpand);
        mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);

        //设置条目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //展开或者收缩
                expandOrCollapse(position);
                if (mListener != null) {
                    mListener.onClickTreeNode(mVisibleNodeList.get(position), position);
                }
            }
        });
    }

    /**
     * 点击之后展开或者收缩列表
     */
    private void expandOrCollapse(int position) {
        Node node = mVisibleNodeList.get(position);
        if (node != null) {
            if (node.isLeafNode()) return;

            node.setIsExpand(!node.getIsExpand());
            //过滤出可见的节点
            mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mVisibleNodeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodeList.get(position);
        convertView = getNodeView(node, position, convertView, parent);
        //设置缩进
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }


    public abstract View getNodeView(Node node, int position, View convertView, ViewGroup parent);
}
