package Myapp;

import java.util.Stack;

/**
 *  ���㹤����
 */

public class Calculate {

    /**
     * ������ʽ�ó���
     * @param expression      ��ʽ
     * @return  ��
     */
    public static String getAnswer(String expression) {

        Stack<Character> operators = new Stack<Character>();  //�����ջ�����ڴ�������
        Stack<Fraction> fractions = new Stack<Fraction>();  //������ջ,���ڴ�Ų�����
        if(expression == null){
            return "null";
        }
        char[] chars = expression.toCharArray();   //�����ʽ�ַ���ת���ַ�����
        //������ȡ����
        for (int i = 0 ; i<chars.length ; i++) {
            char c = chars[i];
            if(c == '('){             //�ȴ��������ŵ����������������ţ���ջ
                operators.push(c);
            }   else if(c == ')'){           //��ǰ�ַ�Ϊ������
                while(operators.peek() != '('){   //�������ջ����Ԫ�ز�Ϊ��(��,�����

                    Fraction fraction1 = fractions.pop();   //��ȡ����ջ�е���������
                    Fraction  fraction2 = fractions.pop();
                    //��ȡ������ֵ
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    if(result.getNumerator()<0){            //�ж��Ƿ�Ϊ����
                        return  "-";
                    }
                    //�����ѹ��ջ��
                    fractions.push(result);
                }
                //�������ų�ջ
                operators.pop();
            }else if(isOperator(c)){//�������
                //�������ջ��Ϊ�գ��ҵ�ǰ��������ȼ�С��ջ����������ȼ�
                while(!operators.empty() && !(priority(c)>= priority(operators.peek()))){
                    //��ȡ����ջ�е���������
                    Fraction fraction1 = fractions.pop();
                    Fraction  fraction2 = fractions.pop();
                    //��ȡ������ֵ
                    Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    if(result.getNumerator()<0){
                        return  "-";
                    }
                    //�����ѹ��ջ��
                    fractions.push(result);
                }
                //���������ջ
                operators.push(c);
            }else{          //�ǲ�����
                if(c >= '0' && c <= '9'){
                    StringBuilder fra = new StringBuilder();
                    //�Է�ʽ���д���
                    while(i<chars.length && (chars[i]=='/' || ((chars[i]>='0') && chars[i]<='9'))){
                        fra.append(chars[i]);
                        i++;
                    }
                    i--;
                    //���� fra������һ��������
                    String val = fra.toString();
                    //��ǡ�/����λ��
                    int flag = val.length();
                    for(int j = 0 ; j<val.length() ; j++){
                        if(val.charAt(j)=='/'){//����ȡ����ֵ����/����/��λ�ã����ڽ��������ַ��Ӻͷ�ĸ���ɷ�������
                            flag = j;
                        }
                    }
                    //�Ѳ�������ɷ�ʽ����
                    //����
                    StringBuilder numeratorBuf = new StringBuilder();
                    //��ĸ
                    StringBuilder denominatorBuf = new StringBuilder();
                    for(int k = 0 ; k<flag; k++){
                        numeratorBuf.append(val.charAt(k));
                    }
                    //�ж��Ƿ�Ϊ����
                    if(flag != val.length()){
                        for(int m = flag+1 ; m<val.length() ; m++){
                            denominatorBuf.append(val.charAt(m));
                        }
                    }else{//������Ƿ������ĸ��Ϊ1
                        denominatorBuf.append('1');
                    }
                    //�����ӷ�ĸ�ֱ���ջ
                    fractions.push(new Fraction(Integer.parseInt(numeratorBuf.toString()),
                            Integer.parseInt(denominatorBuf.toString())));
                }
            }
        }
        while(!operators.empty() && fractions.size()>1){

            Fraction fraction1 = fractions.pop();
            Fraction  fraction2 = fractions.pop();
            //��ȡ������ֵ
            Fraction result = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                    fraction2.getNumerator(), fraction2.getDenominator());
            if(result.getNumerator()<0){
                return "-";
            }
            //�����ѹ��ջ��
            fractions.push(result);
        }
        //������
        Fraction result = fractions.pop();
        //��ȡ���յĽ��(����������Լ��)
        return Fraction.getFinalFraction(result);
    }

    /**
     *  ��������������ȼ������ڼ���ʱ�Ƚ����ȼ�
     *
     */

    public static int priority(Character character) {
        switch(character) {

            case '��':
            case '��':return 1;
            case '+':
            case '-':return 0;
        }
        return -1;
    }

    //�����ж��Ƿ�Ϊ�����
    public static boolean isOperator(Character c) {
        if(c.equals('+')||c.equals('-')||c.equals('��')||c.equals('��')) {
            return true;
        }
        return false;
    }

    /**
     * �ӡ������ˡ���
     * ����������������Ӧ�����㣬��ȡ����ķ��ӷ�ĸ   (������ܴ��ڼٷ�����֮��Լ�־ͺ�)
     */
    private static Fraction calculate(Character operator, int numerator1, int denominator1, int numerator2, int denominator2){
        //�������,��Ž���ķ������ĸ        ���ڼӼ��˳���Ҫ�õ��������ķ��ӷ�ĸ����һ��д
        int[] result = new int[2];
        switch (operator){
            case'+':
                result[0] = numerator1*denominator2 + numerator2*denominator1; result[1]= denominator1*denominator2;
                break;
            case '-':
                result[0] = numerator2*denominator1 - numerator1*denominator2; result[1]= denominator1*denominator2;
                break;
            case '��':
                result[0] = numerator1*numerator2; result[1] = denominator1*denominator2;
                break;
            case '��':
                result[0] = numerator2*denominator1; result[1] = numerator1*denominator2;
                break;
        }
        return new Fraction(result[0],result[1]);
    }
}
