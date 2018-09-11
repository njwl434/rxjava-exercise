package com.wl.rxjava_2;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @describe: rxjava-2
 * @author: 武梁
 * @date: 2018/9/4 09:55
 * @mailbox: 1034905058@qq.com
 */
public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();

    // 网络请求2
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20china")
    Observable<Translation.Translation2> getCall_2();
}
