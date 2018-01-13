package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    loadJsp task;
    Handler handler = new Handler();
    dd t;
    int k=0;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = new dd();
        try {
            //GiveupThread();
            t.start();
            Thread.sleep(5000);
            kim();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    private void kim()
    {
        new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        task = new loadJsp(); // 전송 소켓 스레드
                        task.execute();
                    }
                });
            }
        }.start();
    }
    /* private synchronized void GiveupThread()
     {
         t = new Thread(new Runnable() {
             @Override
             public void run() {
                     while(true) {
                         try {
                             Thread.sleep(2000);
                             Log.i("Tag", "wait");
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
             }
         });
         t.start();
     }*/
    class  loadJsp extends AsyncTask<Void, String, Void> {  //어싱크테스트 스레드 함수 사용
        @Override
        protected  Void doInBackground(Void... param) {            // TODO Auto-generated method stub
            k=1;
            while(true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                Log.i("Tag", String.valueOf(count));
                if(count==10) {
                    k=0;
                    t.run();
                    break;
                }

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) { //Background 작업이 끝난 후 에//Background 작업 시작전에 ui 작업 진행 ui 작업 진행
            // TODO Auto-generated method stub
            super.onPostExecute(result);


        }
    }
    class dd extends Thread{
        @Override
        public void run() {
            while(true) {
                try {
                    if(k==1){break;}
                    Thread.sleep(1000);
                    Log.i("Tag", "wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
