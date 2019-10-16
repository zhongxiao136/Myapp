package Myapp;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("ʹ��-n ����������Ŀ������Ŀ -r ����������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ ����-n 10 -r 10");
        System.out.println("ʹ�� -e <exercisefile>.txt -a <answerfile>.txt ���𰸵���ȷ��  ����-e Write.txt -a Answers.txt");

        while(true){

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command.equals("exit")){
                break;
            }
            String[] s = command.split(" ");
            if(s[0].equals("-n")){
                int number = Integer.parseInt(s[1]);
                if(number <= 0){
                    System.out.println("-n ������Ŀ�����Ĳ������벻��ȷ������������");
                }else if(s[2].equals("-r")){
                    int round = Integer.parseInt(s[3]);
                    if(round <= 0){
                        System.out.println("-r ֵ��Χ�Ĳ������벻��ȷ������������");
                    }else{
                        //������Ŀ
                        System.out.println("����������Ŀ������");
                        Map<String, String> exerciseAndAnswer = CreateExpression.getExerciseAndAnswer(number,round);
                        //�����ɵ���Ŀ������Ĵ𰸴�ӡ������ļ�
                        CreateFile.createExerciseAndAnswerFile(exerciseAndAnswer);
                    }
                }
            }else if(s[0].equals("-e") && s[2].equals("-a")){
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                String exerciseFilePath = fileDirectory + File.separator + s[1];
                String answerFilePath = fileDirectory + File.separator + s[3];
                Grade.isAnswer(exerciseFilePath, answerFilePath);

            }else{
                System.out.println("����Ĳ�������ȷ");
            }
        }
    }
}
