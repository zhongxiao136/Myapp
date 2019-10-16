package Myapp;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.Map;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static JFrame jf = new JFrame();
    private JToolBar toolState;			//边框的工具栏
    private JButton button_1 = new JButton("生成题目");
    private JButton button_2 = new JButton("查看答案");
    private JButton button_3 = new JButton("查看成绩");
    private JPanel panel1 = new JPanel();

    //文本域  加入滚动条
    private static JTextArea textArea1 = new JTextArea();
    private static JTextArea textArea2 = new JTextArea();
    private static JTextArea textArea3 = new JTextArea();

    private JScrollPane textScrollPane1 = new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//水平和竖直滚动条
    private JScrollPane textScrollPane2 = new JScrollPane(textArea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//水平和竖直滚动条
    private JScrollPane textScrollPane3 = new JScrollPane(textArea3,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//水平和竖直滚动条

    //主页面构造函数
    public Frame() {

        this.setContentPane(panel1);
        panel1.setLayout(null);
        panel1.add(button_1);
        panel1.add(button_2);
        panel1.add(button_3);
        button_1.setBounds(100, 10, 90, 30);
        button_2.setBounds(270, 10, 90, 30);
        button_3.setBounds(450, 10, 90, 30);
        panel1.add(textScrollPane1);
        panel1.add(textScrollPane2);
        panel1.add(textScrollPane3);
        textScrollPane1.setBounds(10, 50, 200, 450);
        textScrollPane2.setBounds(220, 50, 200, 450);
        textScrollPane3.setBounds(430, 50, 200, 450);
        this.setTitle("四则运算");
        this.setSize(650, 550);     //设置窗口的位置大小
        this.setLocation(330, 150);
        JTextField number = new JTextField(15);
        JTextField round = new JTextField(15);
        JButton button = new JButton("确认");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog();
                jd.setSize(300, 150);
                jd.setLocationRelativeTo(null);
                jd.setTitle("生成题目");
                jd.getContentPane().setLayout(new FlowLayout());
                jd.add(new JLabel("生成的题目数："));
                jd.add(number);
                jd.add(new JLabel("数字的范围："));
                jd.add(round);
                jd.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        jd.dispose();
                    }
                });
                jd.setModal(true);
                jd.setVisible(true);
                Map<String, String> exerciseAndAnswer = CreateExpression.getExerciseAndAnswer(Integer.parseInt(number.getText()), Integer.parseInt(round.getText()));
                number.setText("");
                round.setText("");
                //将生成的题目和算出的答案打印输出到文件
                CreateFile.createExerciseAndAnswerFile(exerciseAndAnswer);
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                File writeFile = new File(fileDirectory + File.separator + "Write.txt");
                File exerciseFile = new File(fileDirectory + File.separator + "Exercises.txt");
                BufferedReader br = null;
                BufferedReader br1 = null;
                try {
                    //建立文件流[字符流]
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(exerciseFile), "GBK");
                    InputStreamReader isr2 = new InputStreamReader(new FileInputStream(writeFile), "GBK");
                    br = new BufferedReader(isr);//动态绑定
                    br1 = new BufferedReader(isr2);//动态绑定
                    //读取内容
                    StringBuffer sb = new StringBuffer();
                    StringBuffer sb1 = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    while ((line = br1.readLine()) != null) {
                        sb1.append(line).append("\n");
                    }
                    //显示在文本框[多框]
                    textArea1.setText(sb.toString());
                    textArea1.setEditable(false);
                    textArea2.setText(sb1.toString());

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (br != null) br.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                File answerFile = new File(fileDirectory + File.separator + "Answers.txt");
                BufferedReader br = null;
                try {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(answerFile), "GBK");
                    br = new BufferedReader(isr);//动态绑定
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    textArea3.setText(sb.toString());
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog();
                jd.setSize(300, 200);
                jd.setLocationRelativeTo(null);
                jd.setTitle("成绩");
                jd.setVisible(true);
                jd.setModal(true);
                JTextArea grade = new JTextArea();
                JScrollPane textScrollPane = new JScrollPane(grade,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//水平和竖直滚动条
                jd.add(textScrollPane);
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                File answerFile = new File(fileDirectory + File.separator + "Answers.txt");
                File writeFile = new File(fileDirectory + File.separator + "Write.txt");
                BufferedWriter bw = null;
                BufferedReader br = null;
                try {
                    bw = new BufferedWriter(new FileWriter(new File(String.valueOf(writeFile))));
                    bw.write(textArea2.getText());//向文件写出数据
                    bw.flush();
                    bw.close();//关闭输出流
                    Grade.isAnswer(writeFile.toString(), answerFile.toString());
                    File gradeFile = new File(fileDirectory + File.separator + "Grade.txt");
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(gradeFile), "GBK");
                    br = new BufferedReader(isr);//动态绑定
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    grade.setText(sb.toString());
                    grade.setLineWrap(true);
                    grade.setEditable(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
    //测试类
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setVisible(true);
    }

}
