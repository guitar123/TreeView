package join.zz.com.treeviewlab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import join.zz.com.treeviewlab.Node;
import join.zz.com.treeviewlab.TreeHelper;
import join.zz.com.treeviewlab.listener.OnTreeNodeClickListener;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/2/1 18:38.
 * description: You share rose get fun. *_* *_*
 */

public abstract class TreeRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context mContext;
    public List<Node> mAllNodeList = null;//所有的Node集合
    public List<Node> mVisibleNodeList = null;//所有显示的Node集合

    public OnTreeNodeClickListener mListener;

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
    public TreeRecycleViewAdapter(Context context, List<T> dataList, int defaultExpand) throws IllegalAccessException {
        this.mContext = context;
        mAllNodeList = TreeHelper.getSortedNodeList(dataList, defaultExpand);
        mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NodeViewHolder) {
            bindNodeViewHolder((NodeViewHolder) holder, position);
        }
    }

    private void bindNodeViewHolder(NodeViewHolder holder, int position) {
        Node node = mVisibleNodeList.get(position);
        //设置缩进
        holder.itemView.setPadding(node.getLevel() * 30, 3, 3, 3);
        onBindNodeViewHolder(holder, node, position);
    }

    /**
     * 绑定节点数据
     */
    public abstract void onBindNodeViewHolder(NodeViewHolder holder, Node node, int position);

    @Override
    public int getItemCount() {
        return mVisibleNodeList == null ? 0 : mVisibleNodeList.size();
    }


    /**
     * 点击之后展开或者收缩列表
     */
    protected void expandOrCollapse(int adapterPosition) {
        Node node = mVisibleNodeList.get(adapterPosition);
        if (node != null) {
            if (node.isLeafNode()) return;

            node.setIsExpand(!node.getIsExpand());
            //过滤出可见的节点
            mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);
            notifyDataSetChanged();
        }
    }


    /**
     * 节点ViewHolder
     */
    public class NodeViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

        public NodeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //展开或者收缩
            expandOrCollapse(getAdapterPosition());
            if (mListener != null) {
                mListener.onClickTreeNode(mVisibleNodeList.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }


}
