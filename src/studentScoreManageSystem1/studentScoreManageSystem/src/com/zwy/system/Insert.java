package com.zwy.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert {

    public Insert() {
        initialize();
    }

    private void initialize(){
        JFrame frame = new JFrame("录入学生成绩管理系统");
        frame.setSize(450, 250);
        frame.setLayout(new GridLayout(5,1));

        JLabel nameLabel = new JLabel("姓名");
        JTextField nameField = new JTextField();//文本框
        nameLabel.setBounds(10, 20, 80, 25);
        nameField.setBounds(100, 20, 165, 25);
        frame.add(nameLabel);
        frame.add(nameField);
        nameField.setColumns(10);

        JLabel sexLabel = new JLabel("性别（女/男）");
        JTextField sexField = new JTextField();
        sexLabel.setBounds(10, 50, 80, 25);
        sexField.setBounds(100, 50, 165, 25);
        frame.add(sexLabel);
        frame.add(sexField);
        sexField.setColumns(10);//设置文本字段中的列数为10

        JLabel classLabel = new JLabel("班级（计算机科学与技术2301班）");
        JTextField classField = new JTextField();
        classLabel.setBounds(10, 80, 80, 25);
        classField.setBounds(100, 80, 165, 25);
        frame.add(classLabel);
        frame.add(classField);
        classField.setColumns(10);

        JLabel idLabel = new JLabel("学号");
        JTextField idField = new JTextField();
        idLabel.setBounds(10, 110, 80, 25);
        idField.setBounds(100, 110, 165, 25);
        frame.add(idLabel);
        frame.add(idField);
        idField.setColumns(10);

        JButton saveButton = new JButton("保存");
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {//增加监听器
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String sex = sexField.getText();
                String classInfo = classField.getText();
                String id = idField.getText();

                boolean checkSex = checkSex(sex);
                boolean checkClass = checkClass(classInfo);

                if(checkSex && checkClass){
                    //性别和班级信息均正确的话就存入文件中
                    try{
                        //写入文件
                        FileWriter fileWriter = new FileWriter("student.txt",true);//追加数据（学生信息可追加），保证再次输入时不覆盖之前的内容

                        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                        bufferWriter.write("姓名: " + name + "\t");
                        bufferWriter.write("性别: " + sex + "\t");
                        bufferWriter.write("班级: " + classInfo + "\t");
                        bufferWriter.write("学号: " + id + "\n");
                        bufferWriter.write("***********************************************************"+"\n");
                        bufferWriter.write("***********************************************************"+"\n");

                        bufferWriter.flush();
                        bufferWriter.close();
                        fileWriter.close();

                        //创建窗口，显示“保存成功”的信息
                        JFrame frameNew = new JFrame("录入学生成绩管理系统");
                        frameNew.setSize(450, 250);
                        frameNew.setLayout(new FlowLayout());
                        JLabel successlabel = new JLabel("保存成功！");
                        successlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(successlabel);
                        frameNew.setVisible(true);

                    } catch (Exception exception){
                        //显示“保存失败”的信息
                        JFrame frameNew = new JFrame("录入学生成绩管理系统");
                        frameNew.setSize(450, 250);
                        frameNew.setLayout(new FlowLayout());
                        JLabel errorlabel = new JLabel("保存失败，请重试！");
                        errorlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(errorlabel);
                        frameNew.setVisible(true);

                    }
                } else if(checkSex){
                    //班级格式不对
                    JFrame frameNew = new JFrame("录入学生成绩管理系统");
                    frameNew.setSize(450, 250);
                    frameNew.setLayout(new FlowLayout());
                    frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭子窗口
                    JLabel errorlabel = new JLabel("插入失败：班级格式不对！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                } else {
                    //性别格式不对
                    JFrame frameNew = new JFrame("录入学生成绩管理系统");
                    frameNew.setSize(450, 250);
                    frameNew.setLayout(new FlowLayout());
                    frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭子窗口
                    JLabel errorlabel = new JLabel("插入失败：性别格式不对！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                }
            }
        });

        JButton exitButton = new JButton("退出");
        frame.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private Boolean checkSex(String sex){
        return sex.equals("女") || sex.equals("男");
    }

    private Boolean checkClass(String classInfo){
        String pattern = ".*?\\d{4}班";//正则表达式
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(classInfo);
        return m.matches();
    }

}