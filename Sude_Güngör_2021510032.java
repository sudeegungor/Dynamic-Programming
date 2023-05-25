
import java.io.*;
import java.util.*;
public class Sude_Güngör_2021510032 {
	
	public static int DP(int n,int p,int c,int[] demand,int[] salary)
	{
	int[][] cost=new int[n][n * p]; // amount of footballers in reserve is atmost n * p, optimal solution never reaches that value
	
	// initialize the dp matrix filling indexes with infinity
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n * p; j++) {
			cost[i][j] = Integer.MAX_VALUE/ 2;
		}
	}
    //initialize the base cases according to first years probability
	if (demand[0] > p) {
		cost[0][0] = (demand[0] - p) * c; // base case for demand>promotion
	} else {
		for (int j = 0; j <=p-demand[0] ; j++) {
			cost[0][j] = salary[j]; // base case for demand<promotion
		}
	}

	//after first year calculate according to two cases
	//case 1: demand>promotion aka having deficit
	for (int i = 1; i < n; i++) {
		if (demand[i] > p) {
			int deficit = demand[i] - p;

			for (int j = 0; j < n * p; j++)
			{
				// k is the number of players hired from the previous year to reach the optimal solution.
				for (int k = 0; k <= Math.min(deficit, j); k++) 
				{
					// adding the cost of hiring j players from the previous year
					//to the salary needed and cost of hiring the remaining deficit players from the current position
					int transTotCost = cost[i - 1][j] + salary[j - k] + (deficit - k) * c;
					//store the minimum value
					cost[i][j - k] = Math.min(cost[i][j - k], transTotCost);
				}
			}
		} 
		//case 2: promotion>demand aka having surplus
		else {
			int surplus = p - demand[i];

			//j is the number of players hired from the current year to reach the optimal solution.
			for (int j = 0; j < n * p; j++)
			{
				for (int k = 0; k <= Math.min(surplus, j); k++)
				{
					//adding the cost of hiring j players from the previous year
					// to the cost of the cheapest demand[i] players with salaries from the salary array
					int transTotCost = cost[i - 1][j - k] + salary[j];
					//store the minimum value
					cost[i][j] = Math.min(cost[i][j], transTotCost);
				}
			}
		}
	}
	return cost[n - 1][0]; // finishing with 0 surplus is always optimal
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		    //assignment of n,p,c values
		    //n is number of years
		    //p is the number of players promoted to the main squad from the youth team
		    //c is the cost per player for that year
		    int n=3, p=5, c=5;
	        int[]salary = new int[311];
	        int i=0; //i is for line while reading file(first line wont be included to array)
	        //reading of salary file
	        BufferedReader br = new BufferedReader(new FileReader("players_salary.txt"));
	        String line;
	        while ((line = br.readLine()) != null) {
	        	if(i==0)
	        	{
	        		salary[i]=0; //first element of salary array must be 0
	        		//because basically 0 extra player will be paid 0
	        	}
	        	else if(i>0) {
	            String[] splitted = line.split("\\s");
	            salary[i]=Integer.parseInt(splitted[1]); //creating salary array
	        	}
	            i++;
	        }
	        br.close();
	        // reading of demand file
	        int[]demand = new int[51];
	        int j=0; //j is pointer for lines (first line wont be included to array)
	        int m=0; //m is pointer for array index
	        BufferedReader br2 = new BufferedReader(new FileReader("yearly_player_demand.txt"));
	        String line2;
	        while ((line2 = br2.readLine()) != null) {
	        	if(j>0) {
	            String[]splitted2 = line2.split("\\s");
	            demand[m]=Integer.parseInt(splitted2[1]); //creating demand array
	            m++;
	        	}
	            j++;
	        }
	        br2.close();	 
	        
            int cost= DP(n,p,c,demand,salary); //dynamic programming calculation
            System.out.println("DP Results: " + cost); //print minimum cost
	 
	    }

	}


