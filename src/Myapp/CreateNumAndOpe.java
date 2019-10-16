package Myapp;


import java.util.Random;

/**
 *  ������ɲ�����������num[n]�ַ�������
 *  ʹ��flag���������[0,1],��Ϊ0��������������Ϊ1�����ɷ���
 */
public class CreateNumAndOpe {
    /**
     * @param number    ���ɸ���
     * @param round     ���ַ�Χ
     * @return  ����
     */
    public static String[] createNumber(int number, int round){

        Random random = new Random();
        String[] num = new String[number];

        for (int i = 0; i < number; i++) {
            //�����ж������������Ƿ���
            int flag = (int)(Math.random()*10) % 2;
            if(flag == 0){          //��������
                int n = random.nextInt(round);
                if(n == 0){
                    num[i] = 1 + "";
                }else{
                    num[i] = n +"";
                }
            }else{          //���ɷ���
                //������ɷ��Ӻͷ�ĸ
                int numerator = random.nextInt(round);
                int denominator = random.nextInt(round);;
                while(numerator>=denominator || numerator==0 || denominator==0){    //�ж��Ƿ�Ϊ��������Ҳ������ɴ�0�ķ���
                    numerator = random.nextInt(round);
                    denominator = random.nextInt(round);
                }
                //ƴװ�ɷ�����ʽ
                num[i] = numerator + "/" + denominator;
            }
        }
        return num;
    }

    /**
     * ������������
     * ���������������һ����̬���ɱ��operatorTypes[]�ַ�������
     * �������index��������ȡ������
     */
    private static final Character[] operatorTypes = {'+' , '-' , '��' , '��'};

    public static Character[] createOperators(int number) {
        Character[] operators = new Character[number];
        for (int i = 0; i < number; i++) {
            //�����ȡ�����������(0~3 ����4�������(+��-��������)������)
            int index = (int)(Math.random()*4);
            Character operatorType = operatorTypes[index];
            operators[i] = operatorType;
        }
        return operators;
    }
}

