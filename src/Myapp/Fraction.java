package Myapp;

/**
 *  ������
 *  �ܹ���ȡ�����յķ�����ʽ
 */
public class Fraction {

    private int inter;           //��������
    private int numerator;       //����
    private int denominator;    //��ĸ

    /**
     *  �����ɵķ�����Ϊ�������ʽ
     * @param fraction  ���ɵķ���
     * @return  ���ķ�����ʽ
     */
    public static String getFinalFraction(Fraction fraction) {
        int denominator = fraction.getDenominator();        //��÷��ӷ�ĸ
        int numerator = fraction.getNumerator();
        if(numerator == 0){ //������Ϊ0�������0
            return "0";
        }
        if(denominator == 0){
            return "";
        }
        int a = gcd(numerator, denominator);        //c�Ƿ��ӷ�ĸ����������������ӷ�ĸ����
        if (denominator == 1 ) {   //��ĸΪ1
            return numerator + "";
        }
        if(numerator == denominator){
            return 1+"";
        }else {
            if(numerator > denominator){           //���Ӵ��ڷ�ĸ��Ϊ�����
                fraction = getRealFraction(fraction);	//�ٷ����ָ�Ϊ�̺�������������Ϊ�µķ���
                if(fraction.getNumerator() == 0){//������Ϊ0�����������
                    return fraction.getInter()+"";
                }else {
                    return fraction.getInter() + "'" + fraction.getNumerator()/a + "/" + fraction.getDenominator()/a;
                }
            }else{              //��������������
                return numerator/a + "/" + denominator/a;
            }
        }
    }
    //�������������
    public Fraction(int numerator,int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * ����ӷ�ĸ���������������ʱ�õ���
     * @param numerator     ����
     * @param denominator   ��ĸ
     * @return      ���ӷ�ĸ����������
     */
    private static int gcd(int numerator, int denominator){
        if(numerator%denominator == 0){
            return denominator;         //��ȡ�������
        }else{
            return gcd(denominator,numerator%denominator);    //շת������ݹ��ȡ�������
        }
    }

    /**
     * �ٷ�����Ϊ�����
     * @param f   ����ķ���
     * @return     �������ʽ
     */
    private static Fraction getRealFraction(Fraction f){

        int numerator = f.getNumerator();
        int denominator = f.getDenominator();
        int newNumerator = numerator % denominator;  //������Ϊ�µķ���
        int inter = numerator/denominator;      //��������Ϊ��������
        Fraction fraction = new Fraction(newNumerator, denominator);   //������������ʽ
        //��ȡ��������
        fraction.setInter(inter);
        return fraction;            //����������ʽ
    }

    @Override
    public String toString(){

        return "Fraction{" + "inter=" + inter + ", numerator=" + numerator + ", denominator=" + denominator + '}';
    }

    public int getInter(){

        return inter;
    }

    public void setInter(int inter){

        this.inter = inter;
    }

    public void setNumerator(int numerator){

        this.numerator = numerator;
    }

    public int getNumerator(){

        return numerator;
    }

    public void setDenominator(int denominator){

        this.denominator = denominator;
    }

    public int getDenominator(){

        return denominator;
    }
}
