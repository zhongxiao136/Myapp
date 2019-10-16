package Myapp;


import java.util.Random;

/**
 *  随机生成操作数，存入num[n]字符串数组
 *  使用flag生成随机数[0,1],若为0则生成整数，若为1则生成分数
 */
public class CreateNumAndOpe {
    /**
     * @param number    生成个数
     * @param round     数字范围
     * @return  数组
     */
    public static String[] createNumber(int number, int round){

        Random random = new Random();
        String[] num = new String[number];

        for (int i = 0; i < number; i++) {
            //用于判断生成整数还是分数
            int flag = (int)(Math.random()*10) % 2;
            if(flag == 0){          //生成整数
                int n = random.nextInt(round);
                if(n == 0){
                    num[i] = 1 + "";
                }else{
                    num[i] = n +"";
                }
            }else{          //生成分数
                //随机生成分子和分母
                int numerator = random.nextInt(round);
                int denominator = random.nextInt(round);;
                while(numerator>=denominator || numerator==0 || denominator==0){    //判断是否为真分数，且不能生成带0的分数
                    numerator = random.nextInt(round);
                    denominator = random.nextInt(round);
                }
                //拼装成分数形式
                num[i] = numerator + "/" + denominator;
            }
        }
        return num;
    }

    /**
     * 随机生成运算符
     * 将四则运算符放入一个静态不可变的operatorTypes[]字符数组中
     * 随机产生index到数组中取操作符
     */
    private static final Character[] operatorTypes = {'+' , '-' , '×' , '÷'};

    public static Character[] createOperators(int number) {
        Character[] operators = new Character[number];
        for (int i = 0; i < number; i++) {
            //随机获取运算符的类型(0~3 代表4个运算符(+、-、×、÷)的类型)
            int index = (int)(Math.random()*4);
            Character operatorType = operatorTypes[index];
            operators[i] = operatorType;
        }
        return operators;
    }
}

