package Myapp;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("使用-n 参数控制题目生成数目 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围 例：-n 10 -r 10");
        System.out.println("使用 -e <exercisefile>.txt -a <answerfile>.txt 检查答案的正确率  例：-e Write.txt -a Answers.txt");

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
                    System.out.println("-n 生成题目数量的参数输入不正确，请重新输入");
                }else if(s[2].equals("-r")){
                    int round = Integer.parseInt(s[3]);
                    if(round <= 0){
                        System.out.println("-r 值范围的参数输入不正确，请重新输入");
                    }else{
                        //生成题目
                        System.out.println("正在生成题目···");
                        Map<String, String> exerciseAndAnswer = CreateExpression.getExerciseAndAnswer(number,round);
                        //将生成的题目和算出的答案打印输出到文件
                        CreateFile.createExerciseAndAnswerFile(exerciseAndAnswer);
                    }
                }
            }else if(s[0].equals("-e") && s[2].equals("-a")){
                String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
                String exerciseFilePath = fileDirectory + File.separator + s[1];
                String answerFilePath = fileDirectory + File.separator + s[3];
                Grade.isAnswer(exerciseFilePath, answerFilePath);

            }else{
                System.out.println("输入的参数不正确");
            }
        }
    }
}
