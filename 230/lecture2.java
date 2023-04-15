import java.util.Scanner;
public class lecture2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer:");
        String s = input.next();
        int max = -1;
        int min = 10;
        for (int i = 0; i < s.length(); i++){
            int a = Integer.parseInt(s.substring(i, i + 1));
            if (a > max){
                max = a;
            }
            if (a < min){
                min = a;
            }
        }
        System.out.printf("The least significant digit of %s is %d.\nThe most significant digit of %s is %d.", s, min, s, max);
    }
}