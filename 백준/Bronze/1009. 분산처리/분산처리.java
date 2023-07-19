import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
  

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(reader.readLine());
        
        for (int i = 0; i < T; i++)
        {
            String[] temp = reader.readLine().split(" ");
            
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            
            int result = 1;
            
            for (int j = 1; j <= b; j++)
            {
                result = result * a % 10;
            }
            
            result = result == 0 ? 10 : result;
            
            System.out.println(result);
        }
        
        reader.close();
    }
}