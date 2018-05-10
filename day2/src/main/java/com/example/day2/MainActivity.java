package com.example.day2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wzq.wzlibrary.view.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{

    String url = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
    private XListView xListView;
    List<News.ResultBean.DataBean> list = new ArrayList<>(); //大集合
    private MAdapter mAdapter;
    private ImageLoader imageLoaderInstances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.xlv);
        imageLoaderInstances = ImageLoader.getInstance();
        mAdapter = new MAdapter();
        xListView.setAdapter(mAdapter);
        xListView.setPullLoadEnable(true);  //允许上拉加载更多;
        xListView.setXListViewListener(this);  //设置(上下拉的)监听 设置(上下拉的)监听   设置(上下拉的)监听
        getNetData();


    }

    /**
     * 请求网络
     */
    private void getNetData() {
        new MAsyncTask().execute(url);
    }


    /**
     * 下拉刷新新数据;
     */
    @Override
    public void onRefresh() {
        // 清空集合
        //请求新数据
        //更新适配器
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        //大集合中添加新数据
        //更新适配器
    }

    private class MAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewItem = View.inflate(MainActivity.this, R.layout.item, null);
            TextView textView = (TextView) viewItem.findViewById(R.id.textView);
            ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
            textView.setText(list.get(i).getAuthor_name());
            imageLoaderInstances.displayImage(list.get(i).getThumbnail_pic_s(), imageView);


            return viewItem;
        }
    }

    private class MAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return NetWordUtils.getNetJson(url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            News goods = gson.fromJson(s, News.class);
            List<News.ResultBean.DataBean> dataTemp = goods.getResult().getData();
            list.addAll(dataTemp);
            mAdapter.notifyDataSetChanged();

        }
    }
}
