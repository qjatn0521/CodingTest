    import java.io.*;

    public class G1_11401 {
        static int[] dp;
        static long quotient = 1000000007;
        public static void main(String[] args) {
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

                String[] inputs = bf.readLine().split(" ");
                dp = new int[30];

                int a = Integer.parseInt(inputs[0]);
                int b = Integer.parseInt(inputs[1]);
                if(b==0) {
                    bw.write("1");
                    bw.flush();
                    bw.close();
                    return;
                }
                if(a==0) {
                    if(b==0) bw.write("1");
                    else bw.write("0");
                    bw.flush();
                    bw.close();
                    return;
                }

                int mod1 = module1(a,b);


                int mod2 = module2(b);

                long ans = ((long)mod1*(long)mod2)%quotient;

                bw.write(""+ans);

                //bw.write("");
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        static int module1(int n, int k) {
            long facto = n;
            long remainder = facto--;
            for(int i=1;i<k;i++) {
                remainder = (remainder * (facto--))%quotient;
            }
            return (int)remainder;
        }
        static int module2(int k) {
            int remainder = (int) module1(k,k);
            module(remainder,(int) quotient-2,0);
            return dp[0];
        }
        static void module(int remainder, int b,int depth) {
            if(b==1) {
                dp[depth] = (int) (remainder%quotient);
            }
            else {
                module(remainder,b/2,depth+1);

                long tmp = (long) dp[depth + 1] * (long) dp[depth+1];

                dp[depth] = (int) (tmp%quotient);

                if(b%2==1) {
                    long tmp2 = (long) dp[depth] * (((long)remainder)%quotient);
                    dp[depth] = (int) (tmp2%quotient);
                }
            }
            //System.out.println("["+b+"] : "+dp[depth]);
        }

    }