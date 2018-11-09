package com.example.admin.handlertest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn =  findViewById(R.id.btn);
        HandlerThread t1 = new  HandlerThread();
        HandlerThread t2 = new HandlerThread();

        t1.start();
        t2.start();
        try {
            /**
             *   等待  使子线程内的handler能够初始化完毕，避免 时序错误带来的 mHandler = null
             */
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        t1.mHandler.sendMessage(new Message());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
        t2.mHandler.sendMessage(new Message());
//        t1.stop();
//        t2.stop();
    }

    static  public class HandlerThread extends  Thread{
       public Handler  mHandler;
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.d(TAG, "handleMessage:---------------------------- "+Thread.currentThread().getName());
                    Log.d(TAG, "Thread ID-----------------------"+Thread.currentThread().getId());
                }} ;

            Looper.loop();
        };
        }
    }



