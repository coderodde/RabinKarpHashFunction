import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import net.coderodde.string.RabinKarpHashFunction;

public class Main {

    
    
    public static void main(String[] args) {
        int r = 37;
        int q = 11;
        int[] str = new int[]{ 2, 1, 1, 4, 3 };
        
        if (args.length > 2) {
            r = Integer.parseInt(args[0]);
            q = Integer.parseInt(args[1]);
            List<Integer> integerString = new ArrayList<>(args.length - 2);
            
            for (int i = 2; i < args.length; ++i) {
                integerString.add(Integer.parseInt(args[i]));
            }
            
            str = new int[integerString.size()];
            
            for (int i = 0; i < str.length; ++i) {
                str[i] = integerString.get(i);
            }
        }
        
        RabinKarpHashFunction f = new RabinKarpHashFunction(str, r, q);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            int index1 = scanner.nextInt();
            int index2 = scanner.nextInt();
            System.out.println("H(" + getSubstring(str, index1, index2) + 
                                ") = " + f.getHashValue(index1, index2));
        }
    }
    
    private static String getSubstring(int[] arr, int i, int j) {
        StringBuilder sb = new StringBuilder();
        
        for (int k = i; k < j; ++k) {
            sb.append(arr[k]);
            
            if (k < j - 1) {
                sb.append(", ");
            }
        }
        
        return sb.toString();
    }
}
