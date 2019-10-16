package Myapp;

import java.io.*;

/**
 * ��֤�û��𰸵ĶԴ�����Grade.txt �ɼ��ļ�
 * ��ȡ�û������ļ��ʹ��ļ����бȽ�����
 *
 */
public class Grade {

    /**
     *  ��֤�𰸵ĶԴ�����Grade.txt �ļ�
     * @param writeFilePath  �û������ļ��ļ�·��
     * @param answerFilePath    ��ʽ���ļ�·��
     */
    public static void isAnswer(String writeFilePath, String answerFilePath) {
        //��ȡ�û��������ϰ�𰸵��ļ�
        File writeFile = new File(writeFilePath);
        File answerFile = new File(answerFilePath);
        //�ҵ����Writer.txt��Answer.txt�ļ����ļ���
        String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
        File gradeFile = new File(fileDirectory, "Grade.txt");//�ڵ�ǰ�ļ���������Grade.txt�ļ�
        if(writeFile.isFile() && answerFile.isFile()) {
            try {
                BufferedReader writeReader = new BufferedReader(new InputStreamReader(new FileInputStream(writeFile)));
                BufferedReader answerReader = new BufferedReader(new InputStreamReader(new FileInputStream(answerFile)));
                //�������Ͷ���
                String correct = "";
                String wrong = "";
                int correctNum = 0;
                int wrongNum = 0;
                //��¼�����������
                int index = 1;
                String write = null;
                String answer = null;
                System.out.println("��ʼ��֤�𰸡�����");
                //ͨ��һ���ж�ȡ�ļ��Ƚϴ������
                while((( write= writeReader.readLine()) != null) && ((answer = answerReader.readLine()) != null)){
                    if(write.equals(answer)){
                        //����Ե������ַ���ƴ��
                        correct += "," + index;
                        index ++;
                        correctNum ++;
                    }else{
                        wrong += "," + index;
                        index ++;
                        wrongNum ++;
                    }
                }
                //��������Grade.txt�ļ�����
                if(correctNum > 0){
                    correct = "Correct: " + correctNum + "(" + correct.substring(1) +")" + "\r\n" ;
                }else{
                    correct = "Correct: 0" +"\r\n";
                }
                if(wrongNum > 0){
                    wrong = "Wrong: " + wrongNum +  "(" + wrong.substring(1) +")";
                }else{
                    wrong = "Wrong: 0";
                }
                //д���ļ�
                BufferedWriter gradeFileBuf = new BufferedWriter(new FileWriter(gradeFile));
                gradeFileBuf.write(correct);//��correct ��wrongд���ļ�
                gradeFileBuf.write(wrong);
                System.out.print(correct);//����̨Ҳ��ӡ�������
                System.out.println(wrong);
                if(writeReader != null){
                    writeReader.close();
                }
                if(answerReader != null){
                    answerReader.close();
                }
                if(gradeFileBuf != null){
                    gradeFileBuf.close();
                }
                System.out.println("�ɼ������ʳ�¯����ȥ�鿴��(#^.^#)");
            } catch (FileNotFoundException e) {
                System.out.println("�ļ������ڣ�����������Ŷ(#^.^#)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("�ļ������ڣ�����������Ŷ(#^.^#)");
        }
    }
}
