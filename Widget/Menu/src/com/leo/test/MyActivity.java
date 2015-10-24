package com.leo.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MyActivity extends Activity implements MenuItem.OnMenuItemClickListener,View.OnClickListener{

    private Menu menu;

    @InjectView(R.id.open_context_menu)
    Button button;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);
        //registerForContextMenu(button);
        button.setOnClickListener(this);
    }

    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "菜单1");
        menu.add(1, 2, 2, "菜单2");
        menu.add(1, 3, 3, "菜单3");
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle("上下文菜单");

        SubMenu subMenu = menu.addSubMenu(2,2,2,"含子菜单");
        subMenu.add(3,3,3,"子菜单1").setCheckable(true);
        subMenu.add(3,4,4,"子菜单2").setCheckable(true);
    }

    //点击手机上的菜单按钮后，会调用此方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        Intent intent = new Intent(this,Test1.class);
        menu.add(1,1,1,"主菜单").setIcon(R.drawable.ic_launcher).setIntent(intent).setOnMenuItemClickListener(this);
        menu.add(1, 2, 2, "菜单1").setIcon(R.drawable.ic_launcher).setOnMenuItemClickListener(this);
        menu.add(1,3,3,"动态添加按钮").setIcon(R.drawable.ic_launcher).setOnMenuItemClickListener(this);
        menu.addSubMenu(1,20,20,"子菜单").setIcon(R.drawable.ic_launcher)
                .add(1, 21, 21, "新建")
                .setCheckable(true)
                .setChecked(true);

        return true;
    }


    //在MenuItemClick之后调用
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Toast.makeText(this,item.getTitle()+"onMenuItemSelected",Toast.LENGTH_SHORT).show();
        return super.onMenuItemSelected(featureId, item);
    }

    //在MenuItemSelected之后调用
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getTitle()+"onOptionsItemSelected",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //此方法返回true  onMenuItemSelected和onOptionsItemSelected 都不会触发
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        if(item.getItemId() == 3){
            for(int i=0;i<5;i++){
                this.menu.add(1,i+5,i+5,"菜单"+i);
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_menu,null);
        final PopupWindow popupWindow = new PopupWindow(layout,getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight());
        popupWindow.showAtLocation(layout,Gravity.CENTER,0,0);
        popupWindow.setFocusable(true);
        layout.setFocusable(true);
        layout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == event.KEYCODE_BACK){
                    if(popupWindow.isShowing()){
                        popupWindow.dismiss();
                    }
                }
                return false;
            }
        });
    }

}
