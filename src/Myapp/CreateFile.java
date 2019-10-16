package Myapp;

import java.io.*;
import java.util.Map;

/**
 *  ������ʽ�ʹ��ļ�
 */
public class CreateFile {

    /**
     *�����ɵ���ʽ������Ĵ𰸷ֱ�д��Exercise.txt��Answer.txt�ļ�
     *�û�����Ĵ𰸷���Writer�ļ�
     * @param exerciseAndAnswer     ��ʽ��ϰ����ʽ��
     */
    public static void createExerciseAndAnswerFile(Map<String,String> exerciseAndAnswer)  {

        //��ȡ��ǰ�û���Ŀ¼����һ���ļ��У�PrintFile�����Exercise.txt��Answer.txt�ļ�
        String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
        File dir = new File(fileDirectory);
        if (!dir.exists()) {            //�ļ��в�����ʱ�½�
            dir.mkdir();
        }
        File exerciseFile = new File(fileDirectory, "Exercises.txt");   //���ɵ���ʽ��Ŵ�
        File answerFile = new File(fileDirectory, "Answers.txt");      //��ʽ��Ӧ�Ĵ𰸴�Ŵ�
        File writeFile = new File(fileDirectory,"Write.txt");			//�û�����Ĵ𰸴�Ŵ�
        try {
            BufferedWriter exerciseFileWriter = new BufferedWriter(new FileWriter(exerciseFile));
            BufferedWriter  answerFileWriter = new BufferedWriter(new FileWriter(answerFile));
            BufferedWriter  writeFileWriter = new BufferedWriter(new FileWriter(writeFile));
            StringBuilder exercise = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            StringBuilder write = new StringBuilder();
            System.out.println("����д���ļ�������");
            int index = 1;          //�������
            for(Map.Entry<String,String>entry:exerciseAndAnswer.entrySet()){      //��������Map���е���ʽ�ʹ�
                exercise.append(index).append(".").append(entry.getKey()).append("\r\n");     //ȡ��map�е�key��Ϊ��ʽ��ϰ
                answer.append(index).append(".").append(entry.getValue()).append("\r\n");      //map�е�valueΪ��ʽ��
                write.append(index).append(".").append("\r\n");
                index ++;
            }
            //д���ļ�
            exerciseFileWriter.write(exercise.toString());
            exerciseFileWriter.flush();
            answerFileWriter.write(answer.toString());
            answerFileWriter.flush();
            writeFileWriter.write(write.toString());
            writeFileWriter.flush();
            if(exerciseFileWriter != null){
                exerciseFileWriter.close();
            }
            if(answerFileWriter != null){
                exerciseFileWriter.close();
            }
            if(writeFileWriter != null){
                exerciseFileWriter.close();
            }
            System.out.println("���ɵ��ļ��Ѵ���ڵ�ǰĿ¼��'PrintFile'�ļ������ע�����Ŷ��(#^.^#)");
        } catch (FileNotFoundException e) {
            System.out.println("�Ҳ����ļ��أ��ٿ������d(?��?��?)??");
        } catch (IOException e) {
            System.out.println("�ļ������쳣���������������(*?��?*)");
        }
    }
}
