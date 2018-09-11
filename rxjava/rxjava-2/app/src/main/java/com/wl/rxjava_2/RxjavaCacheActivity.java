package com.wl.rxjava_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * @describe: rxjava-2
 * @author: 武梁
 * @date: 2018/9/10 13:23
 * @mailbox: 1034905058@qq.com
 */
public class RxjavaCacheActivity extends Activity {

    String memoryCache=null;
    String diskCache="从磁盘缓存获取数据";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Observable memory=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (memoryCache!=null) {
                    e.onNext(memoryCache);
                }else {
                    e.onComplete();
                }
            }
        });

        Observable discache=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (diskCache!=null) {
                    e.onNext(diskCache);
                }else {
                    e.onComplete();
                }
            }
        });
       Observable wangluo=Observable.just("网络请求");

        // 即本例的逻辑为：
        // a. firstElement()取出第1个事件 = memory，即先判断内存缓存中有无数据缓存；由于memoryCache = null，即内存缓存中无数据，所以发送结束事件（视为无效事件）
        // b. firstElement()继续取出第2个事件 = disk，即判断磁盘缓存中有无数据缓存：由于diskCache ≠ null，即磁盘缓存中有数据，所以发送Next事件（有效事件）
        // c. 即firstElement()已发出第1个有效事件（disk事件），所以停止判断。

       Observable.concat(memory,discache,wangluo).firstElement().subscribe(new Consumer<String>() {
           @Override
           public void accept(String o) throws Exception {
               Log.d(TAG,"最终获取的数据来源 =  "+ 0);
           }
       });
    }
}
