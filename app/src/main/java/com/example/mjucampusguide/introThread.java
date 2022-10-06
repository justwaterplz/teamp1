package com.example.mjucampusguide;

import android.os.Handler;
import android.os.Message;

public class introThread extends Thread{

    private Handler handler;
    
//  생성자를 통해 handler 전달받기
    public introThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(){

        Message msg = new Message();

        try {
            Thread.sleep(3000);
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
