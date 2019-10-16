package Myapp;

import java.io.*;
import java.util.Map;

/**
 *  生成算式和答案文件
 */
public class CreateFile {

    /**
     *将生成的算式和算出的答案分别写入Exercise.txt和Answer.txt文件
     *用户输入的答案放入Writer文件
     * @param exerciseAndAnswer     算式练习和算式答案
     */
    public static void createExerciseAndAnswerFile(Map<String,String> exerciseAndAnswer)  {

        //获取当前用户的目录，用一个文件夹（PrintFile）存放Exercise.txt和Answer.txt文件
        String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
        File dir = new File(fileDirectory);
        if (!dir.exists()) {            //文件夹不存在时新建
            dir.mkdir();
        }
        File exerciseFile = new File(fileDirectory, "Exercises.txt");   //生成的算式存放处
        File answerFile = new File(fileDirectory, "Answers.txt");      //算式对应的答案存放处
        File writeFile = new File(fileDirectory,"Write.txt");			//用户输入的答案存放处
        try {
            BufferedWriter exerciseFileWriter = new BufferedWriter(new FileWriter(exerciseFile));
            BufferedWriter  answerFileWriter = new BufferedWriter(new FileWriter(answerFile));
            BufferedWriter  writeFileWriter = new BufferedWriter(new FileWriter(writeFile));
            StringBuilder exercise = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            StringBuilder write = new StringBuilder();
            System.out.println("正在写入文件···");
            int index = 1;          //设置题号
            for(Map.Entry<String,String>entry:exerciseAndAnswer.entrySet()){      //遍历处理Map表中的算式和答案
                exercise.append(index).append(".").append(entry.getKey()).append("\r\n");     //取出map中的key作为算式练习
                answer.append(index).append(".").append(entry.getValue()).append("\r\n");      //map中的value为算式答案
                write.append(index).append(".").append("\r\n");
                index ++;
            }
            //写入文件
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
            System.out.println("生成的文件已存放在当前目录的'PrintFile'文件夹里，请注意查收哦！(#^.^#)");
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件呢！再看看？ヾ(?°?°?)??");
        } catch (IOException e) {
            System.out.println("文件操作异常，看看哪里出错啦(*?▽?*)");
        }
    }
}
