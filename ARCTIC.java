import java.util.Arrays;
import java.util.Scanner;
public class ARCTIC {
	
	static int[] ck;
	static int C;
	static int N;
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		C = sc.nextInt();
		double[][] xy;
		
		for(int i = 0; i < C; i++)
		{
			N = sc.nextInt();
			
			xy = new double[N][2];
			
			ck = new int[N];
			
			for(int j = 0; j < N; j++)
			{
				xy[j][0] = sc.nextDouble();
				xy[j][1] = sc.nextDouble();
			}
		
			System.out.printf("%.2f\n", opt(xy, 0, 1000*Math.sqrt(2) + 10));
		}
	}
	
	public static void decision(double[][] xy, int i, double gap)
	{	
		double a;
		double b;
		
		for(int j = 1; j < xy.length; j++)
		{
			if(i == j) continue;
			
			a = xy[i][0] - xy[j][0];
			b = xy[i][1] - xy[j][1];
			
			if( Math.sqrt( (a * a) + (b * b) ) <= gap )	
			{
				if(ck[j] == 0)
				{
					ck[j] = 1;
					decision(xy, j, gap);
				}
			}
		}
		

	}

	public static double opt(double[][] xy, double low, double high)
	{
		double mid;
		int k;
		
		for(int i = 0; i < 100; i++)
		{
			Arrays.fill(ck, 0);
		
			mid = ( low + high) / 2;
			
			decision(xy, 0, mid);
			
			for(k = 1; k < N; k++)
			{
				if(ck[k] != 1) break;
			}
			
			if (k == N) high = mid;
			else low = mid;
			
		}
		
		return high;
	}

}
