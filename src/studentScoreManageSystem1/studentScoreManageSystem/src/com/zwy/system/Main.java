package com.zwy.system;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        //运行指定代码的方法
        //EVentQueue可以把来自底层同位体类和受信任的应用程序类的事件列入队列，按顺序指派
        EventQueue.invokeLater(() -> {//实现Runnable（）接口->lambda表达式
            try {
                new StudentGradeEntry();//创建StudentGradeEntry实例
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
