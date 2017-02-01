# TreeView
使用单一条目来展示树形数据，对ListView和RecycleView进行的扩展：
	使用时候需要引入treeviewlab模块儿。
  
> 需要说明的是，本库没有对RecyclerView做大的修改，只是树形数据结构的封装，暂时不支持添加其他的条目类型。
----

## 注解的使用：
以下三个注解需要给条目数据进行添加
```java
// Node节点的id
public @interface TreeNodeId {
}

// Node中需要展示的数据
public @interface TreeNodeLabel {
}

//Node节点的父节点id
public @interface TreeNodePId {
}
```
## 第一步
如果是RecycleView则需要继承TreeRecycleViewAdapter，其中的节点ViewHolder需要继承NodeViewHolder。
如果是ListView则需要继承TreeListViewAdapter。
## 第二步
如果是RecycleView的话，重写方法。
可以使用Node对象进行数据绑定
```java
@Override
public void onBindNodeViewHolder(NodeViewHolder holder, Node node, int position) {

}
```
