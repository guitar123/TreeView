package app.sh.com.treeview.bean;


import join.zz.com.treeviewlab.annotator.TreeNodeId;
import join.zz.com.treeviewlab.annotator.TreeNodeLabel;
import join.zz.com.treeviewlab.annotator.TreeNodePId;

/**
 * Created by 樊浩鹏 on 2016/8/19.
 */
public class FileBean {
    @TreeNodeId
    public int id;
    @TreeNodePId
    public int pId;//指向父节点
    @TreeNodeLabel
    public String label;

    public FileBean(int id, int pId, String label) {
        this.id = id;
        this.pId = pId;
        this.label = label;
    }

    public String desc;

}
