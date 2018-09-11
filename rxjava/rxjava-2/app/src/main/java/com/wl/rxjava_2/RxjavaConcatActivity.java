package com.wl.rxjava_2;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * @describe: rxjava-2
 * @author: 武梁
 * @date: 2018/9/10 11:31
 * @mailbox: 1034905058@qq.com
 */
public class RxjavaConcatActivity extends Activity {


    // 定义Observable接口类型的网络请求对象
    Observable<Translation> observable1;
    Observable<Translation.Translation2> observable2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GetRequest_Interface retrofita=retrofit.create(GetRequest_Interface.class);

        Observable a1=retrofita.getCall().subscribeOn(Schedulers.io());
        Observable a2=retrofita.getCall_2().subscribeOn(Schedulers.io());
        Observable.zip(a1, a2, new BiFunction<Translation,Translation.Translation2,String>() {
            @Override
            public String apply(Translation o, Translation.Translation2 o2) throws Exception {
                return o.show()+"&"+o2.show();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.d(TAG, "最终接收到的数据是：" + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("登录失败");
            }
        });

    }
}
