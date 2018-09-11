package com.wl.rxjava_2;

import android.util.Log;

/**
 * @describe: rxjava-2
 * @author: 武梁
 * @date: 2018/9/4 09:54
 * @mailbox: 1034905058@qq.com
 */
public class Translation {
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public String show() {
        Log.d("RxJava", content.out );
        return ("第1次翻译=" + content.out);
    }
    public static class Translation2 {
        private int status;
        private content content;
        private static class content {
            private String from;
            private String to;
            private String vendor;
            private String out;
            private int errNo;
        }

        //定义 输出返回数据 的方法
        public String show() {

            return ("第2次翻译=" + content.out);

        }
    }
}


