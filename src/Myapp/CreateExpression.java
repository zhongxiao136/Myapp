package Myapp;

import java.util.*;

/**
 * ������ʽ
 * ����isAddBracket��������������Ƿ�����ţ����������ż��ϱ��
 */
public class CreateExpression {
    /**
     * ���ݲ�������������������������ʽ
     * @param numbers       ����������
     * @param operators     ���������
     * @return  ���飨��ʽ�ͽ����
     */
    private static String[] exerciseAndAnswer(String[] numbers, Character[] operators){

        Random random = new Random();
        //��ò�����������
        int num = numbers.length;
        //������ɴ����ŵ���ʽ
        int isAddBracket = (int)(Math.random()*10) %2;
        if(isAddBracket ==  1){    //��isAddBracket==1ʱ�����ɴ����ŵı��ʽ
            //�����Ϊ1ʱ����ò������Ѿ������������
            int[] leftBracket = new int[num];
            //�����Ϊ1ʱ����ò������Ѿ������������
            int[] rightBracket = new int[num];
            //�������������飬����������
            for (int index = 0 ; index<num-1 ; index++) {
                int n = (int)(Math.random()*10)%2;
                if(n == 0 && rightBracket[index] != 1) {//�жϵ�ǰ�������Ƿ�����������
                    leftBracket[index] = 1;     //���������������
                    numbers[index] = "(" + numbers[index];  //������֮ǰ����������
                    int k = num - 1;
                    //���������ŵ�λ�ã������ŵ�λ��~���
                    int rightBracketIndex = random.nextInt(k)%(k-index) + (index+1);
                    //�����ǰ�������������ţ���������������λ��
                    while (leftBracket[rightBracketIndex] == 1){
                        rightBracketIndex = random.nextInt(k)%(k-index) + (index+1);
                    }
                    rightBracket[rightBracketIndex] = 1;        //���������������
                    numbers[rightBracketIndex] = numbers[rightBracketIndex] +")";
                }
            }
        }
        //�����������Ͳ��������齻��ƴ��һ����ʽ�ַ���
        StringBuilder str = new StringBuilder(numbers[0]);
        for (int k = 0; k < operators.length; k++) {     //�����ʽ
            str.append(operators[k]).append(numbers[k + 1]);
        }
        //����ʽת��ΪString
        String exercise = str.toString();
        //��ȡ����ʽ���
        String answer = Calculate.getAnswer(exercise);
        if(answer.equals("-")){      //������̳��ָ����򷵻�null
            return null;
        }
        return new String[]{exercise,answer};
    }

    /**
     *  ͨ���û�������ʽ���������ַ�Χ��������Ӧ����ʽ�ʹ�
     * @param number    ��ʽ�ĸ���
     * @param round     ���ֵķ�Χ
     * @return     ��ʽ�ʹ�
     */
    public static Map<String,String> getExerciseAndAnswer(int number, int round){
        //����ʽ�ͽ���ļ���
        Map<String,String> exerciseAndAnswer = new HashMap<String,String>();
        //������ϣ������ж��Ƿ��ظ�
        Set<String> answer = new HashSet<String>();
        for (int i = 0; i < number; i++) {
            //�����ȡ������ĸ���(1~3��)
            int num = (int)(Math.random()*3)+1;
            Character[] operators = CreateNumAndOpe.createOperators(num);
            //�����ȡnum+1�������������ƱȲ�������1
            String[] numbers = CreateNumAndOpe.createNumber(num+1,round);
            //��ȡ����ʽ���ʽ�ʹ�
            String[] questionAndAnswer = exerciseAndAnswer(numbers, operators);
            if(questionAndAnswer==null || questionAndAnswer[1].contains("-")){    //�жϴ��Ƿ�Ϊ����
                i--;
            }else if (answer.contains(questionAndAnswer[1])) {//�ж��Ƿ��ظ�
                i--;
            }else {
                answer.add(questionAndAnswer[1]);       //û���ظ������answer������
                exerciseAndAnswer.put(questionAndAnswer[0],questionAndAnswer[1]);//����ʽ�ʹ𰸴���map��
            }
        }
        return exerciseAndAnswer;
    }
}
