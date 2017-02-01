package app.sh.com.treeview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.sh.com.treeview.R;
import join.zz.com.treeviewlab.Node;
import join.zz.com.treeviewlab.adapter.TreeRecycleViewAdapter;

/**
 * author: 樊浩鹏
 * Created by fhp on 2017/2/1 19:21.
 * description: You share rose get fun. *_* *_*
 */

public class SimpleTreeRecycleViewAdapter extends TreeRecycleViewAdapter {
    /**
     * @param context
     * @param dataList      数据集合
     * @param defaultExpand 默认展开层级
     * @throws IllegalAccessException
     */
    public SimpleTreeRecycleViewAdapter(Context context, List dataList, int defaultExpand) throws IllegalAccessException {
        super(context, dataList, defaultExpand);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindNodeViewHolder(NodeViewHolder holder, Node node, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.mText.setText(node.label);

        if (node.icon == -1) {
            itemViewHolder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            itemViewHolder.mIcon.setVisibility(View.VISIBLE);
            itemViewHolder.mIcon.setImageResource(node.icon);
        }
    }

    public class ItemViewHolder extends NodeViewHolder {

        private ImageView mIcon;
        private TextView mText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.id_item_icon);
            mText = (TextView) itemView.findViewById(R.id.id_item_text);
        }
    }

}
