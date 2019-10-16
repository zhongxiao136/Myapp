package Myapp;

import java.util.*;

/**
 * 生成算式
 * 根据isAddBracket随机生成数决定是否加括号，给左右括号加上标记
 */
public class CreateExpression {
    /**
     * 根据操作数数组和运算符数组生成算式
     * @param numbers       操作数数组
     * @param operators     运算符数组
     * @return  数组（算式和结果）
     */
    private static String[] exerciseAndAnswer(String[] numbers, Character[] operators){

        Random random = new Random();
        //获得操作数的数量
        int num = numbers.length;
        //随机生成带括号的算式
        int isAddBracket = (int)(Math.random()*10) %2;
        if(isAddBracket ==  1){    //当isAddBracket==1时，生成带括号的表达式
            //当标记为1时代表该操作数已经添加了左括号
            int[] leftBracket = new int[num];
            //当标记为1时代表该操作数已经添加了右括号
            int[] rightBracket = new int[num];
            //遍历操作数数组，随机添加括号
            for (int index = 0 ; index<num-1 ; index++) {
                int n = (int)(Math.random()*10)%2;
                if(n == 0 && rightBracket[index] != 1) {//判断当前操作数是否标记了左括号
                    leftBracket[index] = 1;     //标记已生成左括号
                    numbers[index] = "(" + numbers[index];  //操作数之前加上左括号
                    int k = num - 1;
                    //生成右括号的位置（左括号的位置~最后）
                    int rightBracketIndex = random.nextInt(k)%(k-index) + (index+1);
                    //如果当前操作数有左括号，则重新生成括号位置
                    while (leftBracket[rightBracketIndex] == 1){
                        rightBracketIndex = random.nextInt(k)%(k-index) + (index+1);
                    }
                    rightBracket[rightBracketIndex] = 1;        //标记已生成右括号
                    numbers[rightBracketIndex] = numbers[rightBracketIndex] +")";
                }
            }
        }
        //将运算符数组和操作数数组交替拼成一个算式字符串
        StringBuilder str = new StringBuilder(numbers[0]);
        for (int k = 0; k < operators.length; k++) {     //组成算式
            str.append(operators[k]).append(numbers[k + 1]);
        }
        //将算式转换为String
        String exercise = str.toString();
        //获取运算式结果
        String answer = Calculate.getAnswer(exercise);
        if(answer.equals("-")){      //运算过程出现负数则返回null
            return null;
        }
        return new String[]{exercise,answer};
    }

    /**
     *  通过用户输入算式个数个数字范围来生成相应的算式和答案
     * @param number    算式的个数
     * @param round     数字的范围
     * @return     算式和答案
     */
    public static Map<String,String> getExerciseAndAnswer(int number, int round){
        //运算式和结果的集合
        Map<String,String> exerciseAndAnswer = new HashMap<String,String>();
        //结果集合，用于判断是否重复
        Set<String> answer = new HashSet<String>();
        for (int i = 0; i < number; i++) {
            //随机获取运算符的个数(1~3个)
            int num = (int)(Math.random()*3)+1;
            Character[] operators = CreateNumAndOpe.createOperators(num);
            //随机获取num+1个操作数，控制比操作符多1
            String[] numbers = CreateNumAndOpe.createNumber(num+1,round);
            //获取运算式表达式和答案
            String[] questionAndAnswer = exerciseAndAnswer(numbers, operators);
            if(questionAndAnswer==null || questionAndAnswer[1].contains("-")){    //判断答案是否为负数
                i--;
            }else if (answer.contains(questionAndAnswer[1])) {//判断是否重复
                i--;
            }else {
                answer.add(questionAndAnswer[1]);       //没有重复则加入answer集合中
                exerciseAndAnswer.put(questionAndAnswer[0],questionAndAnswer[1]);//将算式和答案存入map中
            }
        }
        return exerciseAndAnswer;
    }
}
