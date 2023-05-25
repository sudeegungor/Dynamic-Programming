# Dynamic-Programming
Dynamic programming is an optimization approach that transforms a complex problem into a sequence of simpler problems. This approach allows the algorithm to avoid redundant calculations and to reuse the solution to subproblems in order to solve the larger problem more efficiently. Dynamic programming algorithms often involve using a table or an array to store solutions to smaller subproblems, which can be solved using either a recursive approach (memorization) or an iterative approach (tabulation). In memorization, solutions are stored in a cache or memo to avoid redundant calculations when solving each subproblem recursively. In tabulation, solutions to smaller subproblems are computed first and used to solve larger subproblems iteratively until the final solution is obtained. In order to introduce the dynamic-programming approach , we analyze a simple example: A Football Club’s Staffing problem. The algorithm is expected to compute the minimum total costs to promote players for the planned n years with given yearly demands and salaries for surplus footballers.

# EXPLANATION OF THE ALGORITHM

This algorithm implements a dynamic programming solution to a football club's staffing problem. The goal is to hire the minimum number of footballers to meet the required demand for each position, while minimizing the total cost of their salaries and the cost of having surplus or deficit players in reserve.

The problem is defined by the following variables:
•	n: the number of years
•	p: the number of players promoted to the main squad from the youth team
•	c: the cost per player for that year
•	salary[]: an array of the salary cost of each player, where salary[i] is the salary cost of i players kept unrented
•	demand[]: an array of size n that contains the number of players demanded for each year, where demand[i] is the number of players demanded for the i-th year.

The solution is implemented by first creating a two-dimensional cost array of size n by n*p. Initially, cost array is filled with infinity, in this implementation it is represented by maximum integer/2 in java.

The first step is to initialize the base cases of the cost array, for the first year i=0, if the required number of players demand[0] is greater than p, then the minimum cost is calculated by adding the cost of having demand[0]-p deficit players which is “ (demand[0]-p)*c “, else the cost for j players kept unrented with salary is simply the salary of the surplus players can be promoted which is cost[0][j]=salary[j].

For the remaining years i>0, the minimum cost of staffing each year is calculated by considering two cases: a surplus or a deficit of players in reserve.
•	If demand[i] > p, then there is a deficit of demand[i]-p players in reserve, and the cost of staffing the year with j players in reserve is calculated by adding the cost of hiring j players from the previous year, cost[i-1][j], to the cost of hiring the remaining demand[i]-p players from the current position, which is (demand[i]-p)*c, plus the cost of the cheapest j players with salaries from the salary array. This cost is calculated for all possible values of j, and the minimum value is stored in cost[i][j-k], where k is the number of players hired from the previous year to reach the optimal solution.
•	If demand[i] <= p, then there is a surplus of p-demand[i] players in reserve, and the cost of staffing the year with j players in reserve is calculated by adding the cost of hiring j players from the previous year, cost[i-1][j-k], to the cost of the cheapest demand[i] players with salaries from the salary array. This cost is calculated for all possible values of j, and the minimum value is stored in cost[i][j], where j is the number of players hired from the current year to reach the optimal solution.
The optimal solution is then found by taking the value of cost[n-1][0], which represents the finishing with 0 surplus which is always optimal.
