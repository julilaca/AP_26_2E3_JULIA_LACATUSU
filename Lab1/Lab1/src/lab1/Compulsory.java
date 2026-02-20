package lab1;

public class Compulsory {
    public void solve(String[] args){
        System.out.println("Hello World!");

        String[] languages = {
                "C", "C++", "C#", "Python", "Go",
                "Rust", "JavaScript", "PHP", "Swift", "Java"
        };

        int n = (int) (Math.random() * 1_000_000);

        int result = n * 3;
        result += 0b10101;
        result += 0xFF;
        result *= 6;

        System.out.println(result);

        while (result > 9) {
            int sum = 0;
            int temp = result;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            result = sum;
        }
        System.out.println(result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
