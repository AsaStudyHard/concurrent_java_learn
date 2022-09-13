/**
 * description:  引用逃逸的测试
 * date:         2022/9/13 9:49
 * author        ZhuJunfei
 */

package com.zjf.chapter03synchronized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class Bar {
    public void bar() {
        // 是否安全
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        foo(sdf);
    }
    public abstract void foo(SimpleDateFormat sdf);
}

class ImplementFoo extends Bar {
    @Override
    public void foo(SimpleDateFormat sdf) {
        String date = "1999-11-12 12:21:11";
        try {
            Date parse = sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("override foo method have exception");
            e.printStackTrace();
        }
    }
}


public class Demo02ReferenceEscape {
    public static void main(String[] args) {
        new ImplementFoo().bar();
    }
}
