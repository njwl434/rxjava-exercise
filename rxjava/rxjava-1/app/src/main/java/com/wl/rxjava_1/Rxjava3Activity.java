package com.wl.rxjava_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @describe: rxjava-1
 * @author: 武梁
 * @date: 2018/9/10 09:58
 * @mailbox: 1034905058@qq.com
 */
public class Rxjava3Activity extends Activity {

    @Bind(R.id.btn12_concat)
    Button btn12Concat;
    @Bind(R.id.btn13_concatArray)
    Button btn13ConcatArray;
    @Bind(R.id.btn14_merge)
    Button btn14Merge;
    @Bind(R.id.btn15_mergeArray)
    Button btn15MergeArray;
    @Bind(R.id.btn16_concatDelayError)
    Button btn16ConcatDelayError;
    @Bind(R.id.btn17_mergeDelayError)
    Button btn17MergeDelayError;
    @Bind(R.id.btn18_zip)
    Button btn18Zip;
    @Bind(R.id.btn19_combineLatest)
    Button btn19CombineLatest;
    @Bind(R.id.btn20_combineLatestDelayError)
    Button btn20CombineLatestDelayError;
    @Bind(R.id.btn21_reduce)
    Button btn21Reduce;
    @Bind(R.id.btn22_collect)
    Button btn22Collect;
    @Bind(R.id.btn23_startwith)
    Button btn23Startwith;
    @Bind(R.id.btn24_startWithArray)
    Button btn24StartWithArray;
    @Bind(R.id.btn25_count)
    Button btn25Count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn12_concat, R.id.btn13_concatArray, R.id.btn14_merge, R.id.btn15_mergeArray, R.id.btn16_concatDelayError, R.id.btn17_mergeDelayError, R.id.btn18_zip, R.id.btn19_combineLatest, R.id.btn20_combineLatestDelayError, R.id.btn21_reduce, R.id.btn22_collect, R.id.btn23_startwith, R.id.btn24_startWithArray, R.id.btn25_count})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn12_concat:
                Observable.concat(Observable.just(1,2,3),Observable.just(2,3,4),Observable.just(7,8,9)).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件"+ integer  );
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
            case R.id.btn13_concatArray:
                Observable.concatArray(Observable.intervalRange(0,3,1,1, TimeUnit.SECONDS),
                       Observable.intervalRange(2,5,1,1,TimeUnit.SECONDS) ).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "接收到了事件"+ aLong  );
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
            case R.id.btn14_merge:
                Observable.merge(Observable.just(1,2,3),Observable.just(2,3,4),Observable.just(7,8,9)).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件"+ integer  );
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
            case R.id.btn15_mergeArray:
                Observable.mergeArray(Observable.intervalRange(0,3,1,1, TimeUnit.SECONDS),
                        Observable.intervalRange(2,5,1,1,TimeUnit.SECONDS) ).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "接收到了事件"+ aLong  );
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
            case R.id.btn16_concatDelayError:
                    Observable.concatArrayDelayError(
                            Observable.create(new ObservableOnSubscribe<Integer>() {
                                @Override
                                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                                    emitter.onNext(1);
                                    emitter.onNext(2);
                                    emitter.onNext(3);
                                    emitter.onError(new NullPointerException()); // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                                    emitter.onComplete();
                                }
                            }),
                            Observable.just(4, 5, 6))
                            .subscribe(new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }
                                @Override
                                public void onNext(Integer value) {
                                    Log.d(TAG, "接收到了事件"+ value  );
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
            case R.id.btn17_mergeDelayError:
                Observable.mergeDelayError(
                        Observable.create(new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                                emitter.onNext(1);
                                emitter.onNext(2);
                                emitter.onNext(3);
                                emitter.onError(new NullPointerException()); // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                                emitter.onComplete();
                            }
                        }),
                        Observable.just(4, 5, 6))
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
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
            case R.id.btn18_zip:
                Observable ab1=Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        Log.d(TAG, "被观察者1发送了事件1");
                        emitter.onNext(1);
                        // 为了方便展示效果，所以在发送事件后加入2s的延迟
                        Thread.sleep(1000);

                        Log.d(TAG, "被观察者1发送了事件2");
                        emitter.onNext(2);
                        Thread.sleep(1000);

                        Log.d(TAG, "被观察者1发送了事件3");
                        emitter.onNext(3);
                        Thread.sleep(1000);

                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io());

                Observable ob2=Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Log.d(TAG, "被观察者2发送了事件A");
                        emitter.onNext("A");
                        Thread.sleep(1000);

                        Log.d(TAG, "被观察者2发送了事件B");
                        emitter.onNext("B");
                        Thread.sleep(1000);

                        Log.d(TAG, "被观察者2发送了事件C");
                        emitter.onNext("C");
                        Thread.sleep(1000);

                        Log.d(TAG, "被观察者2发送了事件D");
                        emitter.onNext("D");
                        Thread.sleep(1000);

                        emitter.onComplete();

                    }
                }).subscribeOn(Schedulers.newThread());


// 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
                    Observable.zip(ab1, ob2, new BiFunction<Integer, String, String>() {
                        @Override
                        public String apply(Integer integer, String string) throws Exception {
                            return  integer + string;
                        }
                    }).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onSubscribe");
                        }

                        @Override
                        public void onNext(String value) {
                            Log.d(TAG, "最终接收到的事件 =  " + value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError");
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete");
                        }
                    });
                break;
            case R.id.btn19_combineLatest:
                Observable.combineLatest(
                        Observable.just(1L, 2L, 3L), // 第1个发送数据事件的Observable
                        Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                        new BiFunction<Long, Long, Long>() {
                            @Override
                            public Long apply(Long o1, Long o2) throws Exception {
                                // o1 = 第1个Observable发送的最新（最后）1个数据
                                // o2 = 第2个Observable发送的每1个数据
                                Log.e(TAG, "合并的数据是： "+ o1 + " "+ o2);
                                return o1 + o2;
                                // 合并的逻辑 = 相加
                                // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                            }
                        }).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Exception {
                        Log.e(TAG, "合并的结果是： "+s);
                    }
                });

                break;
            case R.id.btn20_combineLatestDelayError:
                break;
            case R.id.btn21_reduce:
                Observable.just(2,3,4,5).reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s1, Integer s2) throws Exception {
                        Log.e(TAG, "本次计算的数据是： "+s1 +" 乘 "+ s2);
                        return s1 * s2;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
                break;
            case R.id.btn22_collect:
                Observable.just(1,2,3,4,5,6,7).collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> strings, Integer integer) throws Exception {
                        strings.add(integer);
                    }
                }).subscribe(new BiConsumer<ArrayList<Integer>, Throwable>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Throwable throwable) throws Exception {
                        Log.e(TAG, "本次发送的数据是： "+integers);
                    }
                });
                break;
            case R.id.btn23_startwith:
                // 注：追加数据顺序 = 后调用先追加
                Observable.just(4, 5, 6)
                        .startWith(0)  // 追加单个数据 = startWith()
                        .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
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
            case R.id.btn24_startWithArray:
                // 注：追加数据顺序 = 后调用先追加
                Observable.just(4, 5, 6)
                        .startWith(0)  // 追加单个数据 = startWith()
                        .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
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
            case R.id.btn25_count:
                Observable.just(1, 2, 3, 4)
                        .count()
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Log.e(TAG, "发送的事件数量 =  "+aLong);

                            }
                        });
                break;
                default:
        }
    }
}
