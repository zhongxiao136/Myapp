package Myapp;

/**
 *  分数类
 *  能够获取到最终的分数形式
 */
public class Fraction {

    private int inter;           //整数部分
    private int numerator;       //分子
    private int denominator;    //分母

    /**
     *  将生成的分数化为真分数形式
     * @param fraction  生成的分数
     * @return  最后的分数形式
     */
    public static String getFinalFraction(Fraction fraction) {
        int denominator = fraction.getDenominator();        //获得分子分母
        int numerator = fraction.getNumerator();
        if(numerator == 0){ //若分子为0，则输出0
            return "0";
        }
        if(denominator == 0){
            return "";
        }
        int a = gcd(numerator, denominator);        //c是分子分母的最大公因数，将分子分母化简
        if (denominator == 1 ) {   //分母为1
            return numerator + "";
        }
        if(numerator == denominator){
            return 1+"";
        }else {
            if(numerator > denominator){           //分子大于分母化为真分数
                fraction = getRealFraction(fraction);	//假分数分割为商和余数，余数作为新的分子
                if(fraction.getNumerator() == 0){//若余数为0，则代表整除
                    return fraction.getInter()+"";
                }else {
                    return fraction.getInter() + "'" + fraction.getNumerator()/a + "/" + fraction.getDenominator()/a;
                }
            }else{              //其他情况化简分数
                return numerator/a + "/" + denominator/a;
            }
        }
    }
    //带参数构造分数
    public Fraction(int numerator,int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * 求分子分母的最大公因数（化简时用到）
     * @param numerator     分子
     * @param denominator   分母
     * @return      分子分母的做大公因数
     */
    private static int gcd(int numerator, int denominator){
        if(numerator%denominator == 0){
            return denominator;         //获取最大公因数
        }else{
            return gcd(denominator,numerator%denominator);    //辗转相除法递归获取最大公因数
        }
    }

    /**
     * 假分数化为真分数
     * @param f   输入的分数
     * @return     真分数形式
     */
    private static Fraction getRealFraction(Fraction f){

        int numerator = f.getNumerator();
        int denominator = f.getDenominator();
        int newNumerator = numerator % denominator;  //余数作为新的分子
        int inter = numerator/denominator;      //计算商作为整数部分
        Fraction fraction = new Fraction(newNumerator, denominator);   //化简后的数据形式
        //获取整数部分
        fraction.setInter(inter);
        return fraction;            //返回最终形式
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
