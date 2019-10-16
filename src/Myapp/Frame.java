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
    private JToolBar toolState;			//�߿�Ĺ�����
    private JButton button_1 = new JButton("������Ŀ");
    private JButton button_2 = new JButton("�鿴��");
    private JButton button_3 = new JButton("�鿴�ɼ�");
    private JPanel panel1 = new JPanel();

    //�ı���  ���������
    private static JTextArea textArea1 = new JTextArea();
    private static JTextArea textArea2 = new JTextArea();
    private static JTextArea textArea3 = new JTextArea();

    private JScrollPane textScrollPane1 = new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//ˮƽ����ֱ������
    private JScrollPane textScrollPane2 = new JScrollPane(textArea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//ˮƽ����ֱ������
    private JScrollPane textScrollPane3 = new JScrollPane(textArea3,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//ˮƽ����ֱ������

    //��ҳ�湹�캯��
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
        this.setTitle("��������");
        this.setSize(650, 550);     //���ô��ڵ�λ�ô�С
        this.setLocation(330, 150);
        JTextField number = new JTextField(15);
        JTextField round = new JTextField(15);
        JButton button = new JButton("ȷ��");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jd = new JDialog();
                jd.setSize(300, 150);
                jd.setLocationRelativeTo(null);
                jd.setTitle("������Ŀ");
                jd.getContentPane().setLayout(new FlowLayout());
                jd.add(new JLabel("���ɵ���Ŀ����"));
                jd.add(number);
                jd.add(new JLabel("���ֵķ�Χ��"));
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
                //�����ɵ���Ŀ������Ĵ𰸴�ӡ������ļ�
                CreateFile.createExerciseAndAnswerFile(exerciseAndAnswer);
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                File writeFile = new File(fileDirectory + File.separator + "Write.txt");
                File exerciseFile = new File(fileDirectory + File.separator + "Exercises.txt");
                BufferedReader br = null;
                BufferedReader br1 = null;
                try {
                    //�����ļ���[�ַ���]
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(exerciseFile), "GBK");
                    InputStreamReader isr2 = new InputStreamReader(new FileInputStream(writeFile), "GBK");
                    br = new BufferedReader(isr);//��̬��
                    br1 = new BufferedReader(isr2);//��̬��
                    //��ȡ����
                    StringBuffer sb = new StringBuffer();
                    StringBuffer sb1 = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    while ((line = br1.readLine()) != null) {
                        sb1.append(line).append("\n");
                    }
                    //��ʾ���ı���[���]
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
                    br = new BufferedReader(isr);//��̬��
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
                jd.setTitle("�ɼ�");
                jd.setVisible(true);
                jd.setModal(true);
                JTextArea grade = new JTextArea();
                JScrollPane textScrollPane = new JScrollPane(grade,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	//ˮƽ����ֱ������
                jd.add(textScrollPane);
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                File answerFile = new File(fileDirectory + File.separator + "Answers.txt");
                File writeFile = new File(fileDirectory + File.separator + "Write.txt");
                BufferedWriter bw = null;
                BufferedReader br = null;
                try {
                    bw = new BufferedWriter(new FileWriter(new File(String.valueOf(writeFile))));
                    bw.write(textArea2.getText());//���ļ�д������
                    bw.flush();
                    bw.close();//�ر������
                    Grade.isAnswer(writeFile.toString(), answerFile.toString());
                    File gradeFile = new File(fileDirectory + File.separator + "Grade.txt");
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(gradeFile), "GBK");
                    br = new BufferedReader(isr);//��̬��
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
    //������
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setVisible(true);
    }

}
