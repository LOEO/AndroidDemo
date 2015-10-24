package com.leo.app;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.leo.app.bean.MenuItem;

/**
 * Created by LT on 2015-08-01.
 */
public class MainActivity extends AppCompatActivity implements LeftMenuFragment.OnMenuSelectedListener{
    private LeftMenuFragment leftMenuFragment;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button testBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = (Button) findViewById(R.id.btn_test);
        leftMenuFragment = new LeftMenuFragment();
        leftMenuFragment.setOnMenuSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.left_menu, leftMenuFragment).commit();
        initToolBar();
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        Button button = new Button(this);
        button.setText("btn");
        toolbar.addView(button);
        MenuBuilder menuBuilder = new MenuBuilder(this);
        menuBuilder.add("菜单1");
        menuBuilder.add("菜单2");
        ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this);
        toolbar.setMenu(menuBuilder, actionMenuPresenter);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void init(){
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestHandlerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMenuSelected(MenuItem menuItem) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent;
        switch (menuItem.getText()) {
            case "下载":
                DownLoadAsyncTask downLoadAsyncTask = new DownLoadAsyncTask();
                //串行执行
                //downLoadAsyncTask.execute();
                //并行执行
                downLoadAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case "列表":
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case "卡片":
                intent = new Intent(this, CardActivity.class);
                startActivity(intent);
                break;
            case "NavigationView":
                intent = new Intent(this,NewMainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class DownLoadAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setIcon(R.mipmap.ic_launcher);
            pd.setMessage("正在下载...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int i=0;
            while (true) {
                if (i >= 100) {
                    break;
                }
                publishProgress(i);
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pd.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (pd != null&& pd.isShowing()) {
                pd.hide();
            }
        }

    }

}
