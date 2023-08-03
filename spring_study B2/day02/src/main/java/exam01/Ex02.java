package exam01;

public class Ex02 {
    public static void main(String[] args) {
        Calculator cal1 = new ProxyCalculator(new ImplCalculator());
        System.out.println(cal1.factorial(2));
        Calculator cal2 = new ProxyCalculator(new RecCalculator());
        System.out.println(cal2.factorial(2));
    }
}
