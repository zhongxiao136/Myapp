package Myapp;

import java.io.*;

/**
 * 验证用户答案的对错并生成Grade.txt 成绩文件
 * 读取用户答题文件和答案文件进行比较评分
 *
 */
public class Grade {

    /**
     *  验证答案的对错并生成Grade.txt 文件
     * @param writeFilePath  用户答题文件文件路径
     * @param answerFilePath    算式答案文件路径
     */
    public static void isAnswer(String writeFilePath, String answerFilePath) {
        //获取用户答题和练习答案的文件
        File writeFile = new File(writeFilePath);
        File answerFile = new File(answerFilePath);
        //找到存放Writer.txt和Answer.txt文件的文件夹
        String fileDirectory = System.getProperty("user.dir") + File.separator + "PrintFile";
        File gradeFile = new File(fileDirectory, "Grade.txt");//在当前文件夹下生成Grade.txt文件
        if(writeFile.isFile() && answerFile.isFile()) {
            try {
                BufferedReader writeReader = new BufferedReader(new InputStreamReader(new FileInputStream(writeFile)));
                BufferedReader answerReader = new BufferedReader(new InputStreamReader(new FileInputStream(answerFile)));
                //储存错题和对题
                String correct = "";
                String wrong = "";
                int correctNum = 0;
                int wrongNum = 0;
                //记录错题对题的题号
                int index = 1;
                String write = null;
                String answer = null;
                System.out.println("开始验证答案···");
                //通过一行行读取文件比较答题情况
                while((( write= writeReader.readLine()) != null) && ((answer = answerReader.readLine()) != null)){
                    if(write.equals(answer)){
                        //将答对的题用字符串拼接
                        correct += "," + index;
                        index ++;
                        correctNum ++;
                    }else{
                        wrong += "," + index;
                        index ++;
                        wrongNum ++;
                    }
                }
                //用于生成Grade.txt文件内容
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
                //写入文件
                BufferedWriter gradeFileBuf = new BufferedWriter(new FileWriter(gradeFile));
                gradeFileBuf.write(correct);//将correct 和wrong写入文件
                gradeFileBuf.write(wrong);
                System.out.print(correct);//控制台也打印答题情况
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
                System.out.println("成绩已新鲜出炉，快去查看吧(#^.^#)");
            } catch (FileNotFoundException e) {
                System.out.println("文件不存在！请重新输入哦(#^.^#)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件不存在！请重新输入哦(#^.^#)");
        }
    }
}
