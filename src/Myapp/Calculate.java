package Myapp;

import java.util.Stack;

/**
 *  运算工具类
 */

public class Calculate {

    /**
     * 计算算式得出答案
     * @param expression      算式
     * @return  答案
     */
    public static String getAnswer(String expression) {

        Stack<Character> operators = new Stack<Character>();  //运算符栈，用于存放运算符
        Stack<Fraction> fractions = new Stack<Fraction>();  //操作数栈,用于存放操作数
        if(expression == null){
            return "null";
        }
        char[] chars = expression.toCharArray();   //将表达式字符串转成字符数组
        //遍历获取处理
        for (int i = 0 ; i<chars.length ; i++) {
            char c = chars[i];
            if(c == '('){             //先处理有括号的情况，如果是左括号，入栈
                operators.push(c);
            }   else if(c == ')'){           //当前字符为右括号
                while(operators.peek() != '('){   //当运算符栈顶的元素不为‘(’,则继续

                    Fraction fraction1 = fractions.pop();   //拿取操作栈中的两个分数
                    Fraction  fraction2 = fractions.pop();
                    //获取计算后的值
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    if(result.getNumerator()<0){            //判断是否为负数
                        return  "-";
                    }
                    //将结果压入栈中
                    fractions.push(result);
                }
                //将左括号出栈
                operators.pop();
            }else if(isOperator(c)){//是运算符
                //当运算符栈不为空，且当前运算符优先级小于栈顶运算符优先级
                while(!operators.empty() && !(priority(c)>= priority(operators.peek()))){
                    //拿取操作栈中的两个分数
                    Fraction fraction1 = fractions.pop();
                    Fraction  fraction2 = fractions.pop();
                    //获取计算后的值
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    if(result.getNumerator()<0){
                        return  "-";
                    }
                    //将结果压入栈中
                    fractions.push(result);
                }
                //将运算符入栈
                operators.push(c);
            }else{          //是操作数
                if(c >= '0' && c <= '9'){
                    StringBuilder fra = new StringBuilder();
                    //对分式进行处理
                    while(i<chars.length && (chars[i]=='/' || ((chars[i]>='0') && chars[i]<='9'))){
                        fra.append(chars[i]);
                        i++;
                    }
                    i--;
                    //到此 fra里面是一个操作数
                    String val = fra.toString();
                    //标记‘/’的位置
                    int flag = val.length();
                    for(int j = 0 ; j<val.length() ; j++){
                        if(val.charAt(j)=='/'){//当获取的数值存在/则标记/的位置，便于接下来划分分子和分母生成分数对象
                            flag = j;
                        }
                    }
                    //把操作数拆成分式计算
                    //分子
                    StringBuilder numeratorBuf = new StringBuilder();
                    //分母
                    StringBuilder denominatorBuf = new StringBuilder();
                    for(int k = 0 ; k<flag; k++){
                        numeratorBuf.append(val.charAt(k));
                    }
                    //判断是否为分数
                    if(flag != val.length()){
                        for(int m = flag+1 ; m<val.length() ; m++){
                            denominatorBuf.append(val.charAt(m));
                        }
                    }else{//如果不是分数则分母计为1
                        denominatorBuf.append('1');
                    }
                    //将分子分母分别入栈
                    fractions.push(new Fraction(Integer.parseInt(numeratorBuf.toString()),
                            Integer.parseInt(denominatorBuf.toString())));
                }
            }
        }
        while(!operators.empty() && fractions.size()>1){

            Fraction fraction1 = fractions.pop();
            Fraction  fraction2 = fractions.pop();
            //获取计算后的值
            Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                    fraction2.getNumerator(), fraction2.getDenominator());
            if(result.getNumerator()<0){
                return "-";
            }
            //将结果压入栈中
            fractions.push(result);
        }
        //计算结果
        Fraction result = fractions.pop();
        //获取最终的结果(将分数进行约分)
        return Fraction.getFinalFraction(result);
    }

    /**
     *  定义运算符的优先级，用于计算时比较优先级
     *
     */

    public static int priority(Character character) {
        switch(character) {

            case '×':
            case '÷':return 1;
            case '+':
            case '-':return 0;
        }
        return -1;
    }

    //用于判断是否为运算符
    public static boolean isOperator(Character c) {
        if(c.equals('+')||c.equals('-')||c.equals('×')||c.equals('÷')) {
            return true;
        }
        return false;
    }

    /**
     * 加、减、乘、除
     * 对两个分数进行相应的运算，获取结果的分子分母   (结果可能存在假分数，之后约分就好)
     */
    private static Fraction calculate(Character operator, int numerator1, int denominator1, int numerator2, int denominator2){
        //结果数组,存放结果的分子与分母        由于加减乘除都要用到两个数的分子分母，故一起写
        int[] result = new int[2];
        switch (operator){
            case'+':
                result[0] = numerator1*denominator2 + numerator2*denominator1; result[1]= denominator1*denominator2;
                break;
            case '-':
                result[0] = numerator2*denominator1 - numerator1*denominator2; result[1]= denominator1*denominator2;
                break;
            case '×':
                result[0] = numerator1*numerator2; result[1] = denominator1*denominator2;
                break;
            case '÷':
                result[0] = numerator2*denominator1; result[1] = numerator1*denominator2;
                break;
        }
        return new Fraction(result[0],result[1]);
    }
}
