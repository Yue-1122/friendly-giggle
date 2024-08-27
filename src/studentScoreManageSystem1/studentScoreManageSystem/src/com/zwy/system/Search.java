package com.zwy.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class Search {
    public Search() {
        initialize();
    }

    private void initialize(){
        JFrame frame = new JFrame("查询学生成绩管理系统");
        frame.setSize(450, 250);
        frame.setLayout(new GridLayout(4,1));

        JButton queryStudentButton = new JButton("查询学生信息");
        frame.add(queryStudentButton);
        queryStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudentByNameAndId();
            }
        });

        JButton queryClassButton = new JButton("查询班级学生信息");
        frame.add(queryClassButton);
        queryClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        JButton queryUnPassedButton = new JButton("查询单科成绩未及格学生信息");
        frame.add(queryUnPassedButton);
        queryUnPassedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameNew = new JFrame("查询学生成绩管理系统===单科成绩未及格学生信息");
                frameNew.setSize(300, 200);
                frameNew.setLayout(new GridLayout(5,1));

                JLabel semLabel = new JLabel("学期（2023-2024学年第一学期）");
                JTextField semField = new JTextField();
                semLabel.setBounds(10, 20, 80, 25);
                semField.setBounds(100, 20, 165, 25);
                frameNew.add(semLabel);
                frameNew.add(semField);
                semField.setColumns(10);

                JButton queryButton1 = new JButton("查询高等数学");
                frameNew.add(queryButton1);
                queryButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchSubjectScores(semField.getText(), 1);
                    }
                });

                JButton queryButton2 = new JButton("查询大学英语");
                frameNew.add(queryButton2);
                queryButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchSubjectScores(semField.getText(), 2);
                    }
                });

                JButton queryButton3 = new JButton("查询计算机导论");
                frameNew.add(queryButton3);
                queryButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchSubjectScores(semField.getText(), 3);
                    }
                });

                JButton queryButton4 = new JButton("查询体育");
                frameNew.add(queryButton4);
                queryButton4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchSubjectScores(semField.getText(), 4);
                    }
                });

                JButton exitButton = new JButton("退出");
                frameNew.add(exitButton);
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameNew.dispose();
                    }
                });

                frameNew.setVisible(true);
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

    private void searchStudentByNameAndId() {

        JFrame frame = new JFrame("查询学生成绩管理系统===学生信息");
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("请输入要查找的学生姓名");
        JTextField nameField = new JTextField();
        nameLabel.setBounds(10, 20, 80, 25);
        nameField.setBounds(100, 20, 165, 25);
        frame.add(nameLabel);
        frame.add(nameField);
        nameField.setColumns(10);

        JLabel idLabel = new JLabel("请输入要查找的学生学号");
        JTextField idField = new JTextField();
        idLabel.setBounds(10, 50, 80, 25);
        idField.setBounds(100, 50, 165, 25);
        frame.add(idLabel);
        frame.add(idField);
        idField.setColumns(10);

        JButton queryStudentButton = new JButton("查询");
        frame.add(queryStudentButton);
        queryStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();

                File file = new File("student.txt");
                if (file.exists()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        boolean found = false;

                        Outer:
                        while ((line = reader.readLine()) != null) {
                            String[] info = line.split("\t");
                            if (info[0].contains(name) && info[3].contains(id)) {
                                found = true;

                                //显示查找学生信息
                                JFrame frameNew = new JFrame("查询学生成绩管理系统===学生信息");
                                frameNew.setSize(300, 200);
                                frameNew.setLayout(new FlowLayout());

                                JLabel label1 = new JLabel(info[0]);
                                label1.setBounds(10, 20, 80, 25);
                                frameNew.add(label1);

                                JLabel label2 = new JLabel(info[1]);
                                label2.setBounds(10, 50, 80, 25);
                                frameNew.add(label2);

                                JLabel label3 = new JLabel(info[2]);
                                label3.setBounds(10, 80, 80, 25);
                                frameNew.add(label3);

                                JLabel label4 = new JLabel(info[3]);
                                label4.setBounds(10, 110, 80, 25);
                                frameNew.add(label4);

                                frameNew.setVisible(true);

                                JPanel panel = new JPanel();
                                panel.setVisible(true);
                                panel.setSize(600,1000);
                                panel.add(label1);
                                panel.add(label2);
                                panel.add(label3);
                                panel.add(label4);

                                for (int i = 0; i < 4; i++) {
                                    try {
                                        line = reader.readLine();
                                        if(line != null){
                                            String[] info1 = line.split("\t");
                                            if (line.contains("学期：")) {
                                                JLabel label5 = new JLabel(info1[0]);
                                                label5.setBounds(10, 140, 80, 25);
                                                frameNew.add(label5);
                                                panel.add(label5);

                                                JLabel label6 = new JLabel(info1[1]);
                                                label6.setBounds(40, 140, 80, 25);
                                                frameNew.add(label6);
                                                panel.add(label6);

                                                JLabel label7 = new JLabel(info1[2]);
                                                label7.setBounds(70, 140, 80, 25);
                                                frameNew.add(label7);
                                                panel.add(label7);

                                                JLabel label8 = new JLabel(info1[3]);
                                                label8.setBounds(100, 140, 80, 25);
                                                frameNew.add(label8);
                                                panel.add(label8);

                                                JLabel label9 = new JLabel(info1[4]);
                                                label9.setBounds(130, 140, 80, 25);
                                                frameNew.add(label9);
                                                panel.add(label9);

                                            } else if (line.contains("姓名：")) {
                                                reader.close();
                                                break Outer;
                                            }
                                        }
                                    } catch (IOException e1) {
                                        //noinspection CallToPrintStackTrace
                                        e1.printStackTrace();
                                    }
                                }
                                JScrollPane scrollPane = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                                frameNew.add(scrollPane);
                            }
                        }

                        if(!found){
                            JFrame frameNew = new JFrame("查询学生成绩管理系统===学生信息");
                            frameNew.setSize(300, 200);
                            frameNew.setLayout(new FlowLayout());
                            JLabel errorlabel = new JLabel("未找到该名学生信息！");
                            errorlabel.setBounds(10, 130, 80, 25);
                            frameNew.add(errorlabel);
                            frameNew.setVisible(true);
                        }
                    } catch (IOException exception) {
                        JFrame frameNew = new JFrame("查询学生成绩管理系统===学生信息");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        JLabel errorlabel = new JLabel("查询失败，请重试！");
                        errorlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(errorlabel);
                        frameNew.setVisible(true);
                    }
                }else{
                    JFrame frameNew = new JFrame("查询学生成绩管理系统===学生信息");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    JLabel errorlabel = new JLabel("文件不存在！");
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

    private void displayAllStudents() {
        JFrame frame = new JFrame("查询学生成绩管理系统===班级学生信息");
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel classLabel = new JLabel("请输入要查找的班级");
        JTextField classField = new JTextField();
        classLabel.setBounds(10, 20, 80, 25);
        classField.setBounds(100, 20, 165, 25);
        frame.add(classLabel);
        frame.add(classField);
        classField.setColumns(10);

        JLabel semesterLabel = new JLabel("请输入要查找的学期(2023-2024学年第一学期）");
        JTextField semesterField = new JTextField();
        classLabel.setBounds(10, 20, 80, 25);
        classField.setBounds(100, 20, 165, 25);
        frame.add(semesterLabel);
        frame.add(semesterField);
        semesterField.setColumns(10);

        JButton queryStudentButton = new JButton("查询");
        frame.add(queryStudentButton);
        queryStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classInfo = classField.getText();
                String semesterInfo = semesterField.getText();

                File file = new File("student.txt");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    String scoreLine;
                    StringBuilder info = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        if(line.contains(classInfo)){
                            info.append(line).append("\n");
                        }
                        for (int i = 0; i < 4; i++) {
                            scoreLine = reader.readLine();
                            if(scoreLine.contains(semesterInfo)){
                                info.append(scoreLine).append("\n");
                                break;
                            }
                        }
                    }
                    if(info.length() > 0){
                        JFrame frameNew = new JFrame("查询学生成绩管理系统===班级学生信息");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        JLabel successlabel = new JLabel("<html>" + info.toString().replaceAll("\n", "<br>") + "</html>");
                        frameNew.add(successlabel);
                        frameNew.setVisible(true);
                    }else{
                        JFrame frameNew = new JFrame("查询学生成绩管理系统===班级学生信息");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        JLabel errorlabel = new JLabel("查询失败，请重试！");
                        errorlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(errorlabel);
                        frameNew.setVisible(true);
                    }
                } catch (IOException exception) {
                    JFrame frameNew = new JFrame("查询学生成绩管理系统===班级学生信息");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    JLabel errorlabel = new JLabel("程序异常！");
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

    private void searchSubjectScores(String semesterInfo,int subject) {

        StringBuilder info = new StringBuilder();

        switch(subject){
            case 1:
                try {
                    File file = new File("mathUnderAverageScore.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader read = new BufferedReader(fileReader);
                    String line;

                    while((line = read.readLine()) != null){
                        if(line.contains(semesterInfo)){
                            info.append(line);
                            info.append("\n");
                        }
                    }

                    read.close();

                    JFrame frame = new JFrame("单科成绩管理系统");
                    frame.setSize(450, 250);
                    frame.setLayout(new FlowLayout());
                    JTextArea textArea = new JTextArea(info.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);//非编辑状态
                    frame.add(scrollPane);
                    //JLabel successlabel = new JLabel(info);
                    //successlabel.setBounds(10, 130, 80, 25);
                    //frame.add(successlabel);
                    frame.setVisible(true);

                } catch (IOException e) {
                    JFrame frameNew = new JFrame("单科成绩管理系统");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    JLabel errorlabel = new JLabel("查询失败，请重试！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                    throw new RuntimeException(e);
                }
                break;

            case 2:
                try {
                    File file = new File("englishUnderAverageScore.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader read = new BufferedReader(fileReader);
                    String line;

                    while((line = read.readLine()) != null){
                        if(line.contains(semesterInfo)){
                            info.append(line);
                            info.append("\n");
                        }
                    }

                    read.close();

                    JFrame frame = new JFrame("单科成绩管理系统");
                    frame.setSize(450, 250);
                    frame.setLayout(new FlowLayout());
                    JTextArea textArea = new JTextArea(info.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);//非编辑状态
                    frame.add(scrollPane);
                    //JLabel successlabel = new JLabel(info);
                    //successlabel.setBounds(10, 130, 80, 25);
                    //frame.add(successlabel);
                    frame.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3:
                try {
                    File file = new File("introductionUnderAverageScore.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader read = new BufferedReader(fileReader);
                    String line;

                    while((line = read.readLine()) != null){
                        if(line.contains(semesterInfo)){
                            info.append(line);
                            info.append("\n");
                        }
                    }

                    read.close();

                    JFrame frame = new JFrame("单科成绩管理系统");
                    frame.setSize(450, 250);
                    frame.setLayout(new FlowLayout());
                    JTextArea textArea = new JTextArea(info.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);//非编辑状态
                    frame.add(scrollPane);
                    //JLabel successlabel = new JLabel(info);
                    //successlabel.setBounds(10, 130, 80, 25);
                    //frame.add(successlabel);
                    frame.setVisible(true);
                } catch (IOException e) {
                    JFrame frameNew = new JFrame("单科成绩管理系统");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    JLabel errorlabel = new JLabel("查询失败，请重试！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                    throw new RuntimeException(e);
                }
                break;

            case 4:
                try {
                    File file = new File("peUnderAverageScore.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader read = new BufferedReader(fileReader);
                    String line;

                    while((line = read.readLine()) != null){
                        if(line.contains(semesterInfo)){
                            info.append(line);
                            info.append("\n");
                        }
                    }

                    read.close();

                    JFrame frame = new JFrame("单科成绩管理系统");
                    frame.setSize(450, 250);
                    frame.setLayout(new FlowLayout());
                    JTextArea textArea = new JTextArea(info.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);//非编辑状态
                    frame.add(scrollPane);
                    //JLabel successlabel = new JLabel(info);
                    //successlabel.setBounds(10, 130, 80, 25);
                    //frame.add(successlabel);
                    frame.setVisible(true);
                } catch (IOException e) {
                    JFrame frameNew = new JFrame("单科成绩管理系统");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    JLabel errorlabel = new JLabel("查询失败，请重试！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                    throw new RuntimeException(e);
                }
                break;

            default:
                break;
        }

        if(info.length() > 0){
            JFrame frameNew = new JFrame("查询学生成绩管理系统===单科成绩未及格学生信息");
            frameNew.setSize(300, 200);
            frameNew.setLayout(new FlowLayout());
            JLabel successlabel = new JLabel("info");
            successlabel.setBounds(10, 130, 80, 25);
            frameNew.add(successlabel);
            frameNew.setVisible(true);
            JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
            panel2.setVisible(true);
            panel2.add(successlabel);
            JScrollPane scrollPane = new JScrollPane(panel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            frameNew.add(scrollPane);
        }else{
            JFrame frameNew = new JFrame("查询学生成绩管理系统===单科成绩未及格学生信息");
            frameNew.setSize(300, 200);
            frameNew.setLayout(new FlowLayout());
            JLabel errorlabel = new JLabel("查询失败，请重新检查输入内容是否有错");
            errorlabel.setBounds(10, 130, 80, 25);
            frameNew.add(errorlabel);
            frameNew.setVisible(true);
        }
    }
}