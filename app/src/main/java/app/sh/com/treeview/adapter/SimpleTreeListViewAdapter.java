package app.sh.com.treeview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.sh.com.treeview.R;
import join.zz.com.treeviewlab.Node;
import join.zz.com.treeviewlab.adapter.TreeListViewAdapter;

public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

    public SimpleTreeListViewAdapter(Context context, ListView listView, List<T> dataList, int defaultExpand) throws IllegalAccessException {
        super(context, listView, dataList, defaultExpand);
    }

    @Override
    public View getNodeView(Node node, int position, View convertView,
                            ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.id_item_icon);
            holder.mText = (TextView) convertView.findViewById(R.id.id_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.icon == -1) {
            holder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(node.icon);
        }

        holder.mText.setText(node.label);

        return convertView;
    }

    private class ViewHolder {
        ImageView mIcon;
        TextView mText;
    }

//	/**
//	 * 动态插入节点
//	 *
//	 * @param position
//	 * @param string
//	 */
//	public void addExtraNode(int position, String string)
//	{
//		Node node = mVisibleNodes.get(position);
//		int indexOf = mAllNodes.indexOf(node);
//		// Node
//		Node extraNode = new Node(-1, node.getId(), string);
//		extraNode.setParent(node);
//		node.getChildren().add(extraNode);
//		mAllNodes.add(indexOf + 1, extraNode);
//
//		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
//		notifyDataSetChanged();
//
//	}

}
