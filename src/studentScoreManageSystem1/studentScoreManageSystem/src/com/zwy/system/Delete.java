package com.zwy.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Delete {
    public Delete() {
        initialize();
    }

    private void initialize(){
        JFrame frame = new JFrame("删除学生成绩管理系统");
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("请输入要删除的学生姓名");
        JTextField nameField = new JTextField();
        nameLabel.setBounds(10, 20, 80, 25);
        nameField.setBounds(100, 20, 165, 25);
        frame.add(nameLabel);
        frame.add(nameField);
        nameField.setColumns(10);

        JLabel idLabel = new JLabel("请输入要删除的学生学号");
        JTextField idField = new JTextField();
        idLabel.setBounds(10, 50, 80, 25);
        idField.setBounds(100, 50, 165, 25);
        frame.add(idLabel);
        frame.add(idField);
        idField.setColumns(10);

        JButton deleteButton = new JButton("删除");
        frame.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String id = idField.getText();

                File file = new File("student.txt");
                if (file.exists()) {
                    File tempFile = new File("temp.txt");

                    try (FileReader fr = new FileReader(file);
                         BufferedReader reader = new BufferedReader(fr);
                         FileWriter fw = new FileWriter(tempFile,true);
                         BufferedWriter writer = new BufferedWriter(fw)) {

                        String line;
                        boolean found = false;

                        while ((line = reader.readLine()) != null) {
                            String[] info = line.split("\t");
                            if (info[0].contains(name) && info[3].contains(id)) {
                                found = true;

                                for (int j = 0; j < 8; j++) {
                                    line = reader.readLine();
                                    if (line != null && line.contains("学期：")) {
                                        System.out.println(line);
                                    } else if(line != null && line.contains("姓名：")){
                                        writer.write(line + "\n");
                                        break;
                                    }
                                }

                                while((line = reader.readLine())!=null){
                                    writer.write(line + "\n");
                                }
                                break;
                            } else {
                                writer.write(line + "\n");
                            }
                        }

//                        file.delete();
//                        tempFile.renameTo(file);

                        if (!found) {
                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel errorLabel = new JLabel("删除失败：未找到该学生信息！");
                            errorLabel.setBounds(10, 130, 80, 25);
                            frameNew.add(errorLabel);
                            frameNew.setVisible(true);
                       }
//                        else {
//                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
//                            frameNew.setSize(300, 200);
//                            frameNew.setLayout(new FlowLayout());
//                            JLabel successLable = new JLabel("删除成功！");
//                            successLable.setBounds(10, 130, 80, 25);
//                            frameNew.add(successLable);
//                            frameNew.setVisible(true);
//                        }

                        reader.close();
                        writer.close();

                        //noinspection ResultOfMethodCallIgnored
                        file.delete();
                        if (file.delete()) {
                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel errorLabel = new JLabel("删除失败：无法删除原文件！");
                            errorLabel.setBounds(10, 130, 80, 25);
                            frameNew.add(errorLabel);
                            frameNew.setVisible(true);
                        }else{
                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel successLabel = new JLabel("删除成功！");
                            successLabel.setBounds(10, 130, 80, 25);
                            frameNew.add(successLabel);
                            frameNew.setVisible(true);
                        }

                        //noinspection ResultOfMethodCallIgnored
                        tempFile.renameTo(file);

                        if (tempFile.renameTo(file)) {
                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel errorLabel = new JLabel("删除失败：无法重命名临时文件！");
                            errorLabel.setBounds(10, 130, 80, 25);
                            frameNew.add(errorLabel);
                            frameNew.setVisible(true);
                        }else{
                            JFrame frameNew = new JFrame("删除学生成绩管理系统");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel successLabel = new JLabel("文件重命名成功！");
                            successLabel.setBounds(10, 130, 80, 25);
                            frameNew.add(successLabel);
                            frameNew.setVisible(true);
                        }

                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }

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
}