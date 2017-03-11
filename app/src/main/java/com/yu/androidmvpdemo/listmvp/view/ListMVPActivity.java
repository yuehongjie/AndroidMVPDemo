package com.yu.androidmvpdemo.listmvp.view;

import android.databinding.adapters.AbsListViewBindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.yu.androidmvpdemo.R;
import com.yu.androidmvpdemo.listmvp.presenter.GirlPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMVPActivity extends AppCompatActivity implements IMVPView{

    @BindView(R.id.rl_content)
    RecyclerView rlContent;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private GirlPresenter mPresenter;
    private static String TAG = "ListMVPActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mvp);
        ButterKnife.bind(this);

        //presenter 分离 view 和 model
        mPresenter = new GirlPresenter(this);

        initData();
        initListener();
    }


    private void initListener() {

        //加载更多
        rlContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.getChildCount() > 0) {
                    int count = layoutManager.getItemCount();
                    int last = layoutManager.findLastVisibleItemPosition();
                    if (count - last <= 1) {
                        Log.e(TAG , "load more");
                        mPresenter.getDataWithRetrofit(true);
                    }
                }
            }
        });
    }

    private void initData() {
        //mPresenter.initData();
        mPresenter.getDataWithRetrofit(false);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rlContent;
    }
}
