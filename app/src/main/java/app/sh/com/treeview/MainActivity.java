package app.sh.com.treeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.sh.com.treeview.adapter.SimpleTreeRecycleViewAdapter;
import app.sh.com.treeview.bean.FileBean;

public class MainActivity extends AppCompatActivity {

    private ListView mTree;
    private RecyclerView rv;
    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1, 0, "根目录1");
        mDatas.add(bean);
        bean = new FileBean(2, 0, "根目录2");
        mDatas.add(bean);
        bean = new FileBean(3, 0, "根目录3");
        mDatas.add(bean);
        bean = new FileBean(4, 1, "根目录1-1");
        mDatas.add(bean);
        bean = new FileBean(5, 1, "根目录1-2");
        mDatas.add(bean);
        bean = new FileBean(6, 5, "根目录1-2-1");
        mDatas.add(bean);
        bean = new FileBean(7, 3, "根目录3-1");
        mDatas.add(bean);
        bean = new FileBean(8, 3, "根目录3-2");
        mDatas.add(bean);
        bean = new FileBean(9, 3, "根目录3-3");
        mDatas.add(bean);
        bean = new FileBean(10, 3, "根目录3-4");
        mDatas.add(bean);
        bean = new FileBean(11, 0, "根目录4");
        mDatas.add(bean);
        bean = new FileBean(12, 11, "根目录4-1");
        mDatas.add(bean);
        bean = new FileBean(13, 11, "根目录4-2");
        mDatas.add(bean);
        bean = new FileBean(14, 13, "根目录4-2-1");
        mDatas.add(bean);
        bean = new FileBean(15, 14, "根目录4-2-1-1");
        mDatas.add(bean);
        bean = new FileBean(16, 14, "根目录4-2-1-2");
        mDatas.add(bean);
        bean = new FileBean(17, 15, "根目录4-2-1-1-1");
        mDatas.add(bean);
        bean = new FileBean(18, 10, "根目录3-3-1");
        mDatas.add(bean);

//        // initDatas
//        mDatas2 = new ArrayList<OrgBean>();
//        OrgBean bean2 = new OrgBean(1, 0, "根目录1");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(2, 0, "根目录2");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(3, 0, "根目录3");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(4, 1, "根目录1-1");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(5, 1, "根目录1-2");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(6, 5, "根目录1-2-1");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(7, 3, "根目录3-1");
//        mDatas2.add(bean2);
//        bean2 = new OrgBean(8, 3, "根目录3-2");
//        mDatas2.add(bean2);
    }


    private void initView() {
//        mTree = (ListView) findViewById(R.id.id_listview);
        rv = (RecyclerView) findViewById(R.id.rv);

    }

    private void initListener() {
//        try {
//            SimpleTreeListViewAdapter mAdapter = new SimpleTreeListViewAdapter<FileBean>(this, mTree,
//                    mDatas, 1);
//            mTree.setAdapter(mAdapter);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

//        try {
//            BaseTreeViewRecycleAdapter adapter = new BaseTreeViewRecycleAdapter(this, mDatas, 1);
//            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//            rv.setAdapter(adapter);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        try {
            SimpleTreeRecycleViewAdapter adapter = new SimpleTreeRecycleViewAdapter(this, mDatas, 1);
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rv.setAdapter(adapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
