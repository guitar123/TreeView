package app.sh.com.treeview.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.sh.com.treeview.R;
import app.sh.com.treeview.bean.FileBean;
import join.zz.com.treeviewlab.Node;
import join.zz.com.treeviewlab.TreeHelper;

/**
 * author: 樊浩鹏
 * Created by fhp on 2016/11/13 12:53.
 * description: You share rose get fun. *_* *_*
 */
public class BaseTreeViewRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context mContext;
    public List<Node> mAllNodeList;
    public List<Node> mVisibleNodeList;

    public static final int TYPE_ROOT_NODE = 0;
    public static final int TYPE_CHILD_NODE = 1;

    public BaseTreeViewRecycleAdapter(Context context, List<FileBean> dataList, int defaultExpand) throws IllegalAccessException {
        this.mContext = context;
        mAllNodeList = TreeHelper.getSortedNodeList(dataList, defaultExpand);
        mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);
    }

    @Override
    public int getItemViewType(int position) {
        if (mVisibleNodeList.get(position).isRootNode()) {
            //根节点
            return TYPE_ROOT_NODE;
        } else {
            //子节点
            return TYPE_CHILD_NODE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROOT_NODE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_parent_recycle_view_tree, parent, false);
            return new ParentViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_child_recycle_view_tree, parent, false);
            return new ChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ParentViewHolder) {
            ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
            parentViewHolder.pTv.setText(mVisibleNodeList.get(position).label);

        } else if (holder instanceof ChildViewHolder) {
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            childViewHolder.cTv.setText(mVisibleNodeList.get(position).label);
        }
    }

    @Override
    public int getItemCount() {
        return mVisibleNodeList.size();
    }

    /**
     * 点击之后展开或者收缩列表
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node node = mVisibleNodeList.get(position);
        if (node != null) {
            if (node.isLeafNode()) return;

            node.setIsExpand(!node.getIsExpand());
            mVisibleNodeList = TreeHelper.filterVisibleNodes(mAllNodeList);
            notifyDataSetChanged();
        }

    }


    /**
     * 父节点的viewHolder
     */
    public class ParentViewHolder extends RecyclerView.ViewHolder {
        public TextView pTv;

        public ParentViewHolder(View itemView) {
            super(itemView);
            pTv = (TextView) itemView.findViewById(R.id.pTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //根节点的点击事件
                    //进行收缩
                    expandOrCollapse(getAdapterPosition());
                }
            });

        }
    }

    /**
     * 子节点额viewholder
     */
    public class ChildViewHolder extends RecyclerView.ViewHolder {
        public TextView cTv;

        public ChildViewHolder(View itemView) {
            super(itemView);
            cTv = (TextView) itemView.findViewById(R.id.pTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //子节点的点击事件
                    Toast.makeText(mContext, mVisibleNodeList.get(getAdapterPosition()).label, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
