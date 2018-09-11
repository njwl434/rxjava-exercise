package com.wl.rxjava_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * rxjava 2.0 实践
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3_just)
    Button btn3Just;
    @Bind(R.id.btn4_fromArray)
    Button btn4FromArray;
    @Bind(R.id.btn5_fromIterable)
    Button btn5FromIterable;
    @Bind(R.id.btn6_defer)
    Button btn6Defer;
    @Bind(R.id.btn7_timer)
    Button btn7Timer;
    @Bind(R.id.btn8_interval)
    Button btn8Interval;
    @Bind(R.id.btn9_intervalRange)
    Button btn9IntervalRange;
    @Bind(R.id.btn10_range)
    Button btn10Range;
    @Bind(R.id.btn11_rangeLong)
    Button btn11RangeLong;
    Integer i;
    @Bind(R.id.btn_zuhe)
    Button btnZuhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_zuhe,R.id.btn1, R.id.btn2, R.id.btn3_just, R.id.btn4_fromArray, R.id.btn5_fromIterable, R.id.btn6_defer, R.id.btn7_timer, R.id.btn8_interval, R.id.btn9_intervalRange, R.id.btn10_range, R.id.btn11_rangeLong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_zuhe:
                startActivity(new Intent(MainActivity.this, Rxjava3Activity.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, Rxjava1Activity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, Rxjava2Activity.class));
                break;
            case R.id.btn3_just:
                Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            case R.id.btn4_fromArray:
                String[] s = {"测试", "1", "2", "适应10个以上"};
                Observable.fromArray(s).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "集合遍历");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d(TAG, "集合中的数据元素 = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "遍历结束");
                    }
                });
                break;
            case R.id.btn5_fromIterable:
                List<String> list = new ArrayList<>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                list.add("5");
                Observable.fromIterable(list).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "集合遍历");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d(TAG, "集合中的数据元素 = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "遍历结束");
                    }
                });
                break;
            case R.id.btn6_defer:
                i = 10;
                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(i);
                    }
                });
                i = 15;
                observable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "集合遍历");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "集合中的数据元素 = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "遍历结束");
                    }
                });


                break;
            case R.id.btn7_timer:
                Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            case R.id.btn8_interval:
                Observable.interval(3, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            case R.id.btn9_intervalRange:
                Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            case R.id.btn10_range:
                Observable.range(3, 10).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            case R.id.btn11_rangeLong:
                Observable.rangeLong(1, 10).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
                break;
            default:
        }
    }
}
