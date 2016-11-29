package com.example.zcj.surfaceviewtest;

import android.app.Service;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zcj on 2016/11/12.
 */
public class FloatService extends Service {
    private static final int UPDATA_PIC=0x100;
    private int statusBarHeight;//状态栏高度
    private View view;//透明窗体
    private TextView text;
    private Button hideBtn = null;
    private Button updataBth = null;
    private HandlerUI handler = null;
    private Thread updateThread = null;
    private boolean viewadded = false;//透明窗体是否已经显示
    private boolean viewHide = false;//窗口隐藏
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 关闭悬浮窗
     */
    public void removeView(){
        if (viewadded){
            windowManager.removeView(view);
            viewadded = false;
        }
    }

    private void createFloatView(){
        handler = new HandlerUI();

    }

    public void refreshView(int x,int y){
        //状态栏高度不能立即取，不然得到的是0
        if (statusBarHeight==0){
           View rootView = view.getRootView();
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);
            statusBarHeight = r.top;
        }

        layoutParams.x =x;
        //y轴减去状态栏的高度，因为状态栏不是用户可以绘制的区域，不然拖动的时候会有跳动
        layoutParams.y = y-statusBarHeight;
        refresh();
    }

    /**
     * 添加悬浮窗或者更新悬浮窗，如果悬浮窗还没添加则添加，如果已经添加则更新其位置
     */
    private void refresh() {
        //如果已经添加了就之跟新viwe
        if (viewadded){
            windowManager.updateViewLayout(view,layoutParams);
            viewadded = true;
        }
    }

    /**
     * 接受消息和处理消息
     */
    private class HandlerUI extends Handler{
        public HandlerUI(){

        }
        public HandlerUI(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==UPDATA_PIC){
            }
        }
    }
}
