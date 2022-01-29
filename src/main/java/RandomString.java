import java.security.SecureRandom;

public class RandomString {
    private static final String BASE = "abcdefghijklmnopqrstuvwxyz";
    private static SecureRandom random = new SecureRandom();
    private int length;
    private String result;

    public RandomString(int length){
        this.length = length;
        this.result = generate(length);
    }

    public String get(){
        return this.result;
    }

    private String generate(int length){
        StringBuilder str = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            str.append(BASE.charAt(random.nextInt(BASE.length())));
        }

        return str.toString();
    }
}
