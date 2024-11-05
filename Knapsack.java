import java.util.*;
public class Knapsack {
    public static int knapSack(int W, int[] weights, int[] values, int items) {
        int[][] table = new int[items + 1][W + 1];
        for (int i = 0; i <= items; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    table[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    table[i][w] = Math.max(values[i - 1] + table[i - 1][w - weights[i - 1]], table[i - 1][w]);
                } else {
                    table[i][w] = table[i - 1][w];
                }
            }
        }
        return table[items][W];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter values of items (space-separated): ");
        String valuesInput = sc.nextLine();
        String[] valuesArray = valuesInput.split(" ");
        int[] values = new int[valuesArray.length];
        for (int i = 0; i < valuesArray.length; i++) {
            values[i] = Integer.parseInt(valuesArray[i].trim());
        }
        System.out.print("Enter weights of items (space-separated): ");
        String weightsInput = sc.nextLine();
        String[] weightsArray = weightsInput.split(" ");
        int[] weights = new int[weightsArray.length];
        for (int i = 0; i < weightsArray.length; i++) {
            weights[i] = Integer.parseInt(weightsArray[i].trim());
        }
        System.out.print("Enter Knapsack Capacity: ");
        int W = sc.nextInt();
        int n = values.length;
        int maxProfit = knapSack(W, weights, values, n);
        System.out.println("Maximum profit that can be achieved: " + maxProfit);
        sc.close();
    }
}

/* Input
Enter values of items (space-separated): 2 3 1 4
Enter weights of items (space-separated): 3 4 6 5
Enter Knapsack Capacity: 8
Maximum profit that can be achieved: 6


Item	Value	Weight	Capacity 0	Capacity 1	Capacity 2	Capacity 3	Capacity 4	Capacity 5	Capacity 6	Capacity 7	Capacity 8
1	2	3	0	0	0	2	2	2	2	2	2
2	3	4	0	0	0	2	3	3	3	5	5
3	1	6	0	0	0	2	3	3	3	5	5
4	4	5	0	0	0	2	3	4	5	5	6


The 0/1 Knapsack Problem is a fundamental problem in combinatorial optimization and computer science, often used to illustrate concepts in dynamic programming and greedy algorithms. It is widely applicable in fields like resource allocation, financial investment, and logistics.

In the 0/1 Knapsack Problem, we are given:

A set of items, each with a specific weight and value:
Let the weight of item 
𝑖
i be 
𝑤
𝑖
w 
i
​
 .
Let the value (or profit) of item 
𝑖
i be 
𝑣
𝑖
v 
i
​
 .
A knapsack with a weight capacity, denoted 
𝑊
W.
The goal is to maximize the total value of items included in the knapsack without exceeding its weight capacity. Each item can either be included entirely or excluded — hence the name "0/1," indicating two choices per item.

Constraints
Capacity Constraint: The sum of the weights of selected items should not exceed the knapsack's capacity 
𝑊
W.
Binary Decision: For each item, decide to either include it (1) or exclude it (0).

 */