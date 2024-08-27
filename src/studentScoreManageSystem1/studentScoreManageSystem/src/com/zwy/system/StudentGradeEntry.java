package com.zwy.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeEntry{

    public StudentGradeEntry(){
        initialize();
    }

    private void initialize(){//设置窗口

        JFrame frame = new JFrame("学生成绩管理系统");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口  关闭子窗口，父窗口也跟着关闭
        frame.setLayout(new GridLayout(5,1));//设置布局

        JButton inputButton = new JButton("录入");
        inputButton.setPreferredSize(new Dimension(100,50));
        frame.add(inputButton);
        inputButton.addActionListener(new ActionListener() {//设置监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                new Insert();
            }
        });

        JButton updateButton = new JButton("修改");
        updateButton.setPreferredSize(new Dimension(100,50));
        frame.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Modify();
            }
        });

        JButton queryButton = new JButton("查询");
        queryButton.setPreferredSize(new Dimension(100,50));
        frame.add(queryButton);
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search();
            }
        });

        JButton deleteButton = new JButton("删除");
        deleteButton.setPreferredSize(new Dimension(100,50));
        frame.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Delete();
            }
        });

        JButton exitButton = new JButton("退出");
        exitButton.setPreferredSize(new Dimension(100,50));
        frame.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();//释放对象资源
            }
        });

        frame.setVisible(true);//设置窗口可见
    }
}
