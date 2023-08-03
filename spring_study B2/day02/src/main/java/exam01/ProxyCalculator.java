package exam01;

public class ProxyCalculator implements Calculator{
    private  Calculator cal;

    public ProxyCalculator(Calculator cal){
        this.cal = cal;
    }

    @Override
    public long factorial(long num) {
        long stime = System.nanoTime();
        try{
            long result = cal.factorial(num);
            return result;
        }finally {
            long etime = System.nanoTime();
            System.out.println("걸린시간 :" + (etime - stime));
        }
    }
}
