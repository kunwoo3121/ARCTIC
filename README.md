# ARCTIC

https://algospot.com/judge/problem/read/ARCTIC

# 구현 방법
Bisection Method를 이용하여 답을 구한다.
```
 i)   좌표가 (0, 0)부터 (1000, 1000)까지이므로 최대 길이는 1000 * sqrt(2) 이다. 
      Bisection Method를 이용하여 값을 찾기 시작한다.
 
 ii)  시작 low 값은 0, high 값은 1000 * sqrt(2)로 한다. 
      이 둘의 중간값이 조건을 충족하면 이보다 더 작은 값으로도 조건을 충족할 수 있는지 확인한다. ( low = low, high = 중간값 )
      아닐 경우 이보다 더 큰 값중에 조건을 충족할 수 있는 값이 있는지 찾는다. ( low = middle, high = high )
     
 iii) 이 과정을 100번 반복하게 되면 정답과의 오차가 굉장히 작아지게 되어 소수점 셋째자리에서 반올림을 하여 출력하게 되면 같은 값이 되게 된다.
 
 ```
 
 # 구현 코드
 ```java
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
```
