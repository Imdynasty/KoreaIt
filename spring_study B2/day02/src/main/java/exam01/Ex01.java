package exam01;

public class Ex01 {

    public static void main(String[] args) {
        long stime = System.nanoTime();
        ImplCalculator impeCal = new ImplCalculator();
        System.out.println("연산 :" + impeCal.factorial(4));
        long etime = System.nanoTime();
        System.out.println("걸린시간 :" + (etime-stime));


        stime=System.nanoTime();
        RecCalculator recCal = new RecCalculator();
        System.out.println("연산 :" + recCal.factorial(4));
        etime=System.nanoTime();
        System.out.println("걸린시간 :" + (etime-stime));
    }
}
