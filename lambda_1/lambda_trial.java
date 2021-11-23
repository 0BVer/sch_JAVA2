package lambda_1;

public class lambda_trial {
    public static void main(String []args) {
        MyFunctionalInterface fi = (x, y) -> x + y;
        int result = fi.method(2, 5);
        System.out.println(result);
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    int method(int x, int y);
}
