package com.zwy.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Modify {

    public Modify() {
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame("修改学生成绩管理系统");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
        frame.setLayout(new GridLayout(5, 1));

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

        JButton insertButton = new JButton("录入成绩");
        frame.add(insertButton);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addscore(nameField.getText(), idField.getText());
            }
        });

        JButton updateButton = new JButton("修改成绩");
        frame.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revise(nameField.getText(), idField.getText());
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

    private void addscore(String searchName, String searchStudentId) {//录入成绩

        JFrame frame = new JFrame("录入学生成绩");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(6, 1));

        JLabel semLabel = new JLabel("学期（2023-2024学年第一学期）");
        JTextField semField = new JTextField();
        semLabel.setBounds(10, 20, 80, 25);
        semField.setBounds(100, 20, 165, 25);
        frame.add(semLabel);
        frame.add(semField);
        semField.setColumns(10);

        JLabel mathLabel = new JLabel("高等数学成绩");
        JTextField mathField = new JTextField();
        mathLabel.setBounds(10, 20, 80, 25);
        mathField.setBounds(100, 20, 165, 25);
        frame.add(mathLabel);
        frame.add(mathField);
        mathField.setColumns(10);

        JLabel englishLabel = new JLabel("大学英语成绩");
        JTextField englishField = new JTextField();
        englishLabel.setBounds(10, 20, 80, 25);
        englishField.setBounds(100, 20, 165, 25);
        frame.add(englishLabel);
        frame.add(englishField);
        englishField.setColumns(10);

        JLabel introductionLabel = new JLabel("计算机导论成绩");
        JTextField introductionField = new JTextField();
        introductionLabel.setBounds(10, 20, 80, 25);
        introductionField.setBounds(100, 20, 165, 25);
        frame.add(introductionLabel);
        frame.add(introductionField);
        introductionField.setColumns(10);

        JLabel peLabel = new JLabel("体育成绩");
        JTextField peField = new JTextField();
        peLabel.setBounds(10, 20, 80, 25);
        peField.setBounds(100, 20, 165, 25);
        frame.add(peLabel);
        frame.add(peField);
        peField.setColumns(10);

        JButton saveButton = new JButton("保存");
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {//信息保存的逻辑
            @Override
            public void actionPerformed(ActionEvent e) {

                String sem = semField.getText();
                String math = mathField.getText();
                String english = englishField.getText();
                String introduction = introductionField.getText();
                String pe = peField.getText();

                if (checkSem(sem)) {//学期格式不对
                    JFrame frameNew = new JFrame("录入学生成绩");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭子窗口
                    JLabel errorlabel = new JLabel("录入失败：学期格式不对！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                }else if(!checkSem(sem) && !checkwhetherexist(searchName, searchStudentId)){//格式正确，学生信息不存在
                    JFrame frameNew = new JFrame("录入学生成绩");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    frameNew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭子窗口
                    JLabel errorlabel = new JLabel("未找到相应学生信息，请重试");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                } else if(!checkSem(sem) && checkwhetherexist(searchName, searchStudentId)){//格式正确，学生信息存在
                    //格式正确，把成绩录入文件
                    try {
                        File file = new File("student.txt");
                        File mathUnderAverageScore = new File("mathUnderAverageScore.txt");
                        File englishUnderAverageScore = new File("englishUnderAverageScore.txt");
                        File introductionUnderAverageScore = new File("introductionUnderAverageScore.txt");
                        File peUnderAverageScore = new File("peUnderAverageScore.txt");

                        FileReader fileReader = new FileReader(file);
                        BufferedReader read = new BufferedReader(fileReader);//读文件
                        StringBuilder content = new StringBuilder();//处理字符序列
                        String line;

                        while ((line = read.readLine()) != null) {
                            String[] info = line.split("\t");
                            if (info[0].contains(searchName) && info[3].contains(searchStudentId)) {
                                content.append(line).append("\n").append("学期：").append(sem).append("\t")
                                        .append("高等数学成绩：").append(math).append("\t")
                                        .append("大学英语成绩：").append(english).append("\t")
                                        .append("计算机导论成绩：").append(introduction).append("\t")
                                        .append("体育成绩：").append(pe).append("\n");

                                if (Integer.parseInt(math) < 60) {//化为整数
                                    FileWriter writermathScore = new FileWriter("mathUnderAverageScore.txt", true);//写进文件
                                    writermathScore.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 高等数学成绩: " + math + "\n");
                                    writermathScore.close();
                                }

                                if (Integer.parseInt(english) < 60) {
                                    FileWriter writerenglishScore = new FileWriter("englishUnderAverageScore.txt", true);
                                    writerenglishScore.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 大学英语成绩: " + english + "\n");
                                    writerenglishScore.close();
                                }

                                if (Integer.parseInt(introduction) < 60) {
                                    FileWriter writerintroductionScore = new FileWriter("introductionUnderAverageScore.txt", true);
                                    writerintroductionScore.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 计算机导论成绩: " + introduction + "\n");
                                    writerintroductionScore.close();
                                }

                                if (Integer.parseInt(pe) < 60) {
                                    FileWriter writerpeScore = new FileWriter("peUnderAverageScore.txt", true);
                                    writerpeScore.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 体育成绩: " + pe + "\n");
                                    writerpeScore.close();
                                }

                            } else {
                                content.append(line).append("\n");
                            }
                        }

                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        writer.write(content.toString());
                        writer.flush();
                        writer.close();
                        fileWriter.close();
                        read.close();
                        fileReader.close();

                        JFrame frameNew = new JFrame("录入学生成绩");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        frameNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel successlabel = new JLabel("录入成功！");
                        successlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(successlabel);
                        frameNew.setVisible(true);

                    } catch (Exception exception) {
                        //保存失败
                        JFrame frameNew = new JFrame("录入学生成绩");
                        frameNew.setSize(450, 250);
                        frameNew.setLayout(new FlowLayout());
                        frameNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel errorlabel = new JLabel("保存失败，请重试！");
                        errorlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(errorlabel);
                        frameNew.setVisible(true);

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

    private void revise(String searchName, String searchStudentId) {//修改学生信息
        JFrame frame = new JFrame("修改学生成绩");
        frame.setSize(450, 450);
        frame.setLayout(new GridLayout(7, 1));

        JLabel infoLabel = new JLabel("请输入待修改的学生成绩，留空则不修改");
        infoLabel.setBounds(10, 130, 80, 25);
        frame.add(infoLabel);

        JLabel semLabel = new JLabel("学期（2023-2024学年第一学期）");
        JTextField semField = new JTextField();
        semLabel.setBounds(10, 20, 80, 25);
        semField.setBounds(100, 20, 165, 25);
        frame.add(semLabel);
        frame.add(semField);
        semField.setColumns(10);

        JLabel mathLabel = new JLabel("高等数学成绩");
        JTextField mathField = new JTextField();
        mathLabel.setBounds(10, 20, 80, 25);
        mathField.setBounds(100, 20, 165, 25);
        frame.add(mathLabel);
        frame.add(mathField);
        mathField.setColumns(10);

        JLabel englishLabel = new JLabel("大学英语成绩");
        JTextField englishField = new JTextField();
        englishLabel.setBounds(10, 20, 80, 25);
        englishField.setBounds(100, 20, 165, 25);
        frame.add(englishLabel);
        frame.add(englishField);
        englishField.setColumns(10);

        JLabel introductionLabel = new JLabel("计算机导论成绩");
        JTextField introductionField = new JTextField();
        introductionLabel.setBounds(10, 20, 80, 25);
        introductionField.setBounds(100, 20, 165, 25);
        frame.add(introductionLabel);
        frame.add(introductionField);
        introductionField.setColumns(10);

        JLabel peLabel = new JLabel("体育成绩");
        JTextField peField = new JTextField();
        peLabel.setBounds(10, 20, 80, 25);
        peField.setBounds(100, 20, 165, 25);
        frame.add(peLabel);
        frame.add(peField);
        peField.setColumns(10);

        JButton saveButton = new JButton("保存");
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sem = semField.getText();
                String math = mathField.getText();
                String english = englishField.getText();
                String introduction = introductionField.getText();
                String pe = peField.getText();

                if (checkSem(sem)) {//输入学期格式不对
                    JFrame frameNew = new JFrame("修改学生成绩");
                    frameNew.setSize(300, 200);
                    frameNew.setLayout(new FlowLayout());
                    frameNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    JLabel errorlabel = new JLabel("修改失败：学期格式不对！");
                    errorlabel.setBounds(10, 130, 80, 25);
                    frameNew.add(errorlabel);
                    frameNew.setVisible(true);
                } else {
                    try {
                        String line;
                        /*
                        content.append(line).append("\n").append("学期：").append(sem).append("\t")
                                        .append("高等数学成绩：").append(math).append("\t")
                                        .append("大学英语成绩：").append(english).append("\t")
                                        .append("计算机导论成绩：").append(introduction).append("\t")
                                        .append("体育成绩：").append(pe).append("\n");
                                        */

                        if (math != null && !math.isEmpty()) {//不空且存在字符，进行修改
                            //更新主要名单
                            reviseSingleSubjectScore(searchName, searchStudentId, sem, 1, "高等数学成绩：" + math);

                            //看文件中是否有该学生该学期的信息
                            FileReader fileread1 = new FileReader("mathUnderAverageScore.txt");
                            BufferedReader bufferedread1 = new BufferedReader(fileread1);

                            boolean exist = false;
                            while ((line = bufferedread1.readLine()) != null) {
                                if (line.contains(searchStudentId) && line.contains(sem)) {
                                     exist = true;
                                     break;
                                }
                            }

                            fileread1.close();
                            bufferedread1.close();

                            if(exist){
                                //更新成绩不及格名单
                                if (Integer.parseInt(math) < 60) {
                                    //重写
                                    FileReader fr1 = new FileReader("mathUnderAverageScore.txt");
                                    BufferedReader readsubject1 = new BufferedReader(fr1);
                                    FileWriter fw1 = new FileWriter("mathUnderAverageScore.txt");
                                    BufferedWriter writersubject1 = new BufferedWriter(fw1);
                                    while ((line = readsubject1.readLine()) != null) {
                                        if (line.contains(searchStudentId) && line.contains(sem)) {
                                            writersubject1.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 高等数学成绩: " + math + "\n");
                                        } else {
                                            writersubject1.write(line + "\n");
                                        }
                                    }

                                    readsubject1.close();
                                    writersubject1.flush();
                                    writersubject1.close();
                                }else{
                                    checkifneedupdateSubjectScore(searchName, searchStudentId, sem, 1);
                                }
                            }else{
                                if (Integer.parseInt(math) < 60) {
                                    //追加
                                    FileWriter fwriter1 = new FileWriter("mathUnderAverageScore.txt",true);
                                    BufferedWriter writer1 = new BufferedWriter(fwriter1);
                                    writer1.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 高等数学成绩: " + math + "\n");

                                    writer1.flush();
                                    writer1.close();
                                    fwriter1.close();
                                    }
                                }
                            }

                        if (english != null && !english.isEmpty()) {
                            //更新主要名单
                            reviseSingleSubjectScore(searchName, searchStudentId, sem, 2, "大学英语成绩：" + english);

                            //看文件中是否有该学生该学期的信息
                            FileReader fileread2 = new FileReader("englishUnderAverageScore.txt");
                            BufferedReader bufferedread2 = new BufferedReader(fileread2);

                            boolean exist = false;
                            while ((line = bufferedread2.readLine()) != null) {
                                if (line.contains(searchStudentId) && line.contains(sem)) {
                                    exist = true;
                                    break;
                                }
                            }

                            fileread2.close();
                            bufferedread2.close();

                            if(exist){
                                //更新成绩不及格名单
                                if(Integer.parseInt(english)<60){
                                    FileReader fr2 = new FileReader("englishUnderAverageScore.txt");
                                    BufferedReader readsubject2 = new BufferedReader(fr2);
                                    FileWriter fw2 = new FileWriter("englishUnderAverageScore.txt");
                                    BufferedWriter writersubject2 = new BufferedWriter(fw2);
                                    while ((line = readsubject2.readLine()) != null) {
                                        if (line.contains(searchStudentId) && line.contains(sem)) {
                                            writersubject2.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 大学英语成绩: " + english + "\n");
                                        } else {
                                            writersubject2.write(line + "\n");
                                        }
                                    }

                                    readsubject2.close();
                                    writersubject2.flush();
                                    writersubject2.close();
                                }else{
                                    checkifneedupdateSubjectScore(searchName, searchStudentId, sem, 2);
                                }
                            }else{
                                if (Integer.parseInt(english) < 60) {
                                    //追加
                                    FileWriter fwriter2 = new FileWriter("englishUnderAverageScore.txt",true);
                                    BufferedWriter writer2 = new BufferedWriter(fwriter2);
                                    writer2.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 大学英语成绩: " + english + "\n");

                                    writer2.flush();
                                    writer2.close();
                                    fwriter2.close();
                                }
                            }
                        }

                        if (introduction != null && !introduction.isEmpty()) {
                            //更新主要名单
                            reviseSingleSubjectScore(searchName, searchStudentId, sem, 3, "计算机导论成绩：" + introduction);

                            //看文件中是否有该学生该学期的信息
                            FileReader fileread3 = new FileReader("introductionUnderAverageScore.txt");
                            BufferedReader bufferedread3 = new BufferedReader(fileread3);

                            boolean exist = false;
                            while ((line = bufferedread3.readLine()) != null) {
                                if (line.contains(searchStudentId) && line.contains(sem)) {
                                    exist = true;
                                    break;
                                }
                            }

                            fileread3.close();
                            bufferedread3.close();

                            if(exist){
                                //更新成绩不及格名单
                                if(Integer.parseInt(introduction)<60){
                                    FileReader fr3 = new FileReader("introductionUnderAverageScore.txt");
                                    BufferedReader readsubject3 = new BufferedReader(fr3);
                                    FileWriter fw3 = new FileWriter("introductionUnderAverageScore.txt");
                                    BufferedWriter writersubject3 = new BufferedWriter(fw3);
                                    while ((line = readsubject3.readLine()) != null) {
                                        if (line.contains(searchStudentId) && line.contains(sem)) {
                                            writersubject3.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 大学英语成绩: " + introduction + "\n");
                                        } else {
                                            writersubject3.write(line + "\n");
                                        }
                                    }

                                    readsubject3.close();
                                    writersubject3.flush();
                                    writersubject3.close();
                                }else{
                                    checkifneedupdateSubjectScore(searchName, searchStudentId, sem, 3);
                                }
                            }else{
                                if (Integer.parseInt(introduction) < 60) {
                                    //追加
                                    FileWriter fwriter3 = new FileWriter("introductionUnderAverageScore.txt",true);
                                    BufferedWriter writer3 = new BufferedWriter(fwriter3);
                                    writer3.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 计算机导论成绩: " + introduction + "\n");

                                    writer3.flush();
                                    writer3.close();
                                    fwriter3.close();
                                }
                            }
                        }

                        if (pe != null && !pe.isEmpty()) {
                            //更新主要名单
                            reviseSingleSubjectScore(searchName, searchStudentId, sem, 4, "体育成绩：" + pe);

                            //看文件中是否有该学生该学期的信息
                            FileReader fileread4 = new FileReader("peUnderAverageScore.txt");
                            BufferedReader bufferedread4 = new BufferedReader(fileread4);

                            boolean exist = false;
                            while ((line = bufferedread4.readLine()) != null) {
                                if (line.contains(searchStudentId) && line.contains(sem)) {
                                    exist = true;
                                    break;
                                }
                            }

                            fileread4.close();
                            bufferedread4.close();

                            if(exist){
                                //更新不及格名单
                                if(Integer.parseInt(pe)<60){
                                    FileReader fr4 = new FileReader("peUnderAverageScore.txt");
                                    BufferedReader readsubject4 = new BufferedReader(fr4);
                                    FileWriter fw4 = new FileWriter("peUnderAverageScore.txt");
                                    BufferedWriter writersubject4 = new BufferedWriter(fw4);
                                    while ((line = readsubject4.readLine()) != null) {
                                        if (line.contains(searchStudentId) && line.contains(sem)) {
                                            writersubject4.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 体育成绩: " + pe +"\n");
                                        } else {
                                            writersubject4.write(line + "\n");
                                        }
                                    }

                                    readsubject4.close();
                                    writersubject4.flush();
                                    writersubject4.close();
                                }
                                }else{
                                    checkifneedupdateSubjectScore(searchName, searchStudentId, sem, 4);
                                }
                            }else{
                            if (pe != null && Integer.parseInt(pe) < 60) {
                                //追加
                                FileWriter fwriter4 = new FileWriter("peUnderAverageScore.txt", true);
                                BufferedWriter writer4 = new BufferedWriter(fwriter4);
                                writer4.write("学生: " + searchName + " " + "学号：" + searchStudentId + " " + sem + " " + " 体育成绩: " + pe + "\n");

                                writer4.flush();
                                writer4.close();
                                fwriter4.close();
                            }
                        }

                        JFrame frameNew = new JFrame("修改学生成绩");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        frameNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel successlabel = new JLabel("修改成功");
                        successlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(successlabel);
                        frameNew.setVisible(true);

                    } catch (Exception exception) {
                        //保存失败
                        JFrame frameNew = new JFrame("修改学生成绩");
                        frameNew.setSize(300, 200);
                        frameNew.setLayout(new FlowLayout());
                        frameNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel errorlabel = new JLabel("保存失败，请重试！");
                        errorlabel.setBounds(10, 130, 80, 25);
                        frameNew.add(errorlabel);
                        frameNew.setVisible(true);
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


    private void checkifneedupdateSubjectScore(String searchName, String searchStudentId, String sem, int num){
        String line;
        switch(num){
            case 1:
                try{
                    FileReader fr1 = new FileReader("mathUnderAverageScore.txt");
                    BufferedReader readsubject1 = new BufferedReader(fr1);
                    FileWriter fw1 = new FileWriter("mathUnderAverageScore.txt");
                    BufferedWriter writersubject1 = new BufferedWriter(fw1);
                    while ((line = readsubject1.readLine()) != null) {
                        if (!(line.contains(searchStudentId) && line.contains(sem))) {
                            writersubject1.write(line + "\n");
                        }
                    }

                    readsubject1.close();
                    writersubject1.flush();
                    writersubject1.close();
                }catch(Exception e){
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
                break;
            case 2:
                try{
                    FileReader fr2 = new FileReader("englishUnderAverageScore.txt");
                    BufferedReader readsubject2 = new BufferedReader(fr2);
                    FileWriter fw2 = new FileWriter("englishUnderAverageScore.txt");
                    BufferedWriter writersubject2 = new BufferedWriter(fw2);
                    while ((line = readsubject2.readLine()) != null) {
                        if (!(line.contains(searchStudentId) && line.contains(sem))) {
                            writersubject2.write(line + "\n");
                        }
                    }

                    readsubject2.close();
                    writersubject2.flush();
                    writersubject2.close();
                }catch(Exception e){
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
                break;
            case 3:
                try{
                    FileReader fr3 = new FileReader("introductionUnderAverageScore.txt");
                    BufferedReader readsubject3 = new BufferedReader(fr3);
                    FileWriter fw3 = new FileWriter("introductionUnderAverageScore.txt");
                    BufferedWriter writersubject3 = new BufferedWriter(fw3);
                    while ((line = readsubject3.readLine()) != null) {
                        if (!(line.contains(searchStudentId) && line.contains(sem))) {
                            writersubject3.write(line + "\n");
                        }
                    }

                    readsubject3.close();
                    writersubject3.flush();
                    writersubject3.close();
                }catch(Exception e){
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
                break;
            case 4:
                try{
                    FileReader fr4 = new FileReader("peUnderAverageScore.txt");
                    BufferedReader readsubject4 = new BufferedReader(fr4);
                    FileWriter fw4 = new FileWriter("peUnderAverageScore.txt");
                    BufferedWriter writersubject4 = new BufferedWriter(fw4);
                    while ((line = readsubject4.readLine()) != null) {
                        if (!(line.contains(searchStudentId) && line.contains(sem))) {
                            writersubject4.write(line + "\n");
                        }
                    }

                    readsubject4.close();
                    writersubject4.flush();
                    writersubject4.close();
                }catch(Exception e){
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
                break;
            default:
                break;

        }
    }
    private void reviseSingleSubjectScore(String searchName, String searchStudentId, String sem, int num, String score){
        try{
            File file = new File("student.txt");
            FileReader read = new FileReader(file);
            BufferedReader reader = new BufferedReader(read);
            StringBuilder content = new StringBuilder();

            String line;
            String scoreLine;
/*
content.append(line).append("\n").append("学期：").append(sem).append("\t")
                                        .append("高等数学成绩：").append(math).append("\t")
                                        .append("大学英语成绩：").append(english).append("\t")
                                        .append("计算机导论成绩：").append(introduction).append("\t")
                                        .append("体育成绩：").append(pe).append("\n");
*/
            while((line = reader.readLine())!=null){
                String[] Info = line.split("\t");
                if(Info[0].contains(searchName)&&Info[3].contains(searchStudentId)){
                    content.append(line).append("\n");
                    for (int i = 0; i < 8; i++) {
                        scoreLine = reader.readLine();
                        if(scoreLine.contains(sem)){
                            String[] reviseInfo = scoreLine.split("\t");
                            reviseInfo[num] = score;
                            for (String s : reviseInfo) {
                                content.append(s).append("\t");
                            }
                            content.append("\n");
                            break;
                        }else{
                            content.append(scoreLine).append("\n");
                        }
                    }

                    while((line = reader.readLine())!=null){
                        content.append(line).append("\n");
                    }

                    break;
                }else{
                    content.append(line).append("\n");
                }
            }


            FileWriter write = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content.toString());
            writer.flush();
            writer.close();
            write.close();
            reader.close();
            read.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private boolean checkSem(String semesterInfo) {
        String pattern = "[1-9]\\d{3}-[1-9]\\d{3}学年第[一二]学期";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(semesterInfo);

        return !m.matches();
    }

    private boolean checkwhetherexist(String searchName, String searchStudentId) {
        File file = new File("student.txt");
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader read = new BufferedReader(fileReader);//读文件
        String line;
        while (true) {
            try {
                if ((line = read.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] info = line.split("\t");
            if (info[0].contains(searchName) && info[3].contains(searchStudentId)) {
                return true;
            }
        }
        return false;
    }

}
