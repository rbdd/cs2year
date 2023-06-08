package q1singleton;
class PrimeGenerator{
    private int current;
    private static PrimeGenerator generator;
    private PrimeGenerator(int number){current = number; if (!(isPrime(number))) current = getNextPrime();}
    private static boolean isPrime(int number){
        for (int i = 2; i < number; i++){
            if (number%i == 0) return false;
        }
        return true;
    }
    public static PrimeGenerator getInstance(int value){
        if (generator == null) generator = new PrimeGenerator(value);
        return generator;
    }
    public int getCurrentPrime(){return current;}
    public int getNextPrime(){
        int i = 1;
        while (!(isPrime(current + i))){
            i++;
        }
        current += i;
        return current;
    }
    
}