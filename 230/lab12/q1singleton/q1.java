package q1singleton;
public class q1 {
    public static void main(String[] args){
	
        PrimeGenerator s1 = PrimeGenerator.getInstance(4);
        PrimeGenerator s2 = PrimeGenerator.getInstance(5);
        PrimeGenerator s3 = PrimeGenerator.getInstance(6);
        System.out.println( s1 == s2 );
        System.out.println( s1 == s3 );
        System.out.println( s2 == s3 );
    }
}
