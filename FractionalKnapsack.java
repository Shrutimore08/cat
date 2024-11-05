import java.util.*;
public class FractionalKnapsack {
    public static double fractionalKnapsack(double[] weights, double[] values, double capacity) {
        if (weights.length != values.length) {
            throw new IllegalArgumentException("Weights and values must be of the same length.");
        }
        double res = 0;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            items.add(new Item(weights[i], values[i]));
        }
        items.sort((a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));
        for (Item item : items) {
            if (capacity <= 0) {
                break;
            }
            if (item.weight > capacity) {
                res += capacity * (item.value / item.weight);
                capacity = 0;
            } else {
                res += item.value;
                capacity -= item.weight;
            }
        }
        return res;
    }
    private static class Item {
        double weight;
        double value;
        Item(double weight, double value) {
            this.weight = weight;
            this.value = value;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();
        double[] weights = new double[numItems];
        System.out.print("Enter the weights (space-separated): ");
        for (int i = 0; i < numItems; i++) {
            weights[i] = scanner.nextDouble();
        }
        double[] values = new double[numItems];
        System.out.print("Enter the values (space-separated): ");
        for (int i = 0; i < numItems; i++) {
            values[i] = scanner.nextDouble();
        }
        if (weights.length != numItems || values.length != numItems) {
            throw new IllegalArgumentException("The number of weights and values must match the number of items.");
        }
        System.out.print("Enter the capacity of the knapsack: ");
        double capacity = scanner.nextDouble();
        double maxValue = fractionalKnapsack(weights, values, capacity);
        System.out.printf("Maximum value in the knapsack: %.2f%n", maxValue);
        scanner.close();
    }
}

/* INPUT

Enter the number of items: 3
Enter the weights (space-separated): 10 20 30
Enter the values (space-separated): 60 100 120
Enter the capacity of the knapsack: 50
Maximum value in the knapsack: 240.00


The knapsack problem is a classic optimization problem in computer science and mathematics that involves selecting items with the goal of maximizing a total value while staying within a given weight limit. It gets its name from the analogy of a person trying to pack a knapsack (backpack) with valuable items without exceeding the knapsack's weight capacity.

Problem Definition
In the most basic form, the knapsack problem can be defined as follows:

Given:
A set of items, each with a weight and a value.
A knapsack with a fixed weight capacity.
Objective:
Select a subset of the items such that:
The total weight of the selected items does not exceed the knapsack's capacity.
The total value of the selected items is maximized.
Types of Knapsack Problems
There are different variations of the knapsack problem:

0/1 Knapsack Problem:

Each item can either be included in the knapsack or left out (hence the "0/1").
You cannot take fractional parts of an item.
This problem is typically solved using dynamic programming.
Fractional Knapsack Problem:

You can take fractions of items rather than needing to include or exclude an item entirely.
This is often solved using a greedy approach by selecting items with the highest value-to-weight ratio until the knapsack is full.
Unbounded Knapsack Problem:

Any number of each item can be taken, as long as the weight constraint is not exceeded.
Why It's Important
The knapsack problem is a foundational topic in optimization, combinatorial optimization, and algorithm design. It has practical applications in various fields:

Resource allocation: Allocating limited resources (such as budget or time) to maximize outcomes.
Logistics: Maximizing the value of cargo within weight constraints.
Finance and portfolio selection: Selecting a combination of investments that maximizes return within a risk or budget constraint.
*/