import java.util.*;
class Job {
    char id;
    int deadline;
    int profit;
    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
public class JobSequencing {
    public static void printJobSequence(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                return Integer.compare(j2.profit, j1.profit);
            }
        });
        int n = jobs.length;
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];
        Arrays.fill(result, 'X');
        Arrays.fill(slot, false);
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(maxDeadline - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }
        System.out.print("Job Sequence: ");
        for (char jobId : result) {
            if (jobId != 'X') {
                System.out.print(jobId);
            }
        }
        System.out.println();
        int totalProfit = 0;
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                for (Job job : jobs) {
                    if (job.id == result[i]) {
                        totalProfit += job.profit;
                        break;
                    }
                }
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of jobs:");
        int n = scanner.nextInt();
        Job[] jobs = new Job[n];
        System.out.println("Enter jobs in the format (id deadline profit):");
        for (int i = 0; i < n; i++) {
            char id = scanner.next().charAt(0);
            int deadline = scanner.nextInt();
            int profit = scanner.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }
        printJobSequence(jobs);
    }
}

/*
### Explanation of the Program:

1. **Job Class**: Represents a job with an identifier, a deadline, and a profit.

2. **printJobSequence Method**: 
   - Sorts the jobs in descending order based on profit.
   - Determines the maximum deadline from the jobs to set up the scheduling slots.
   - Initializes an array for the result and a boolean array to track filled slots.
   - Iterates through the sorted jobs and assigns each job to the latest available slot before its deadline.
   - Calculates the total profit from the scheduled jobs.

3. **Main Method**:
   - Accepts user input for the number of jobs and their details (id, deadline, and profit).
   - Calls the `printJobSequence` method to process the jobs and display the results.

### Sample Input and Output:

**Input:**
```
5         // total no of jobs
a 2 100   // job class having - a-identifier, 2-deadline, 100-profit
b 1 19
c 2 27
d 1 25
e 3 15
```

**Output:**
```
Job Sequence: a c e 
Total Profit: 142
```

### Breakdown of Output:

- **Job Sequence**: 
  - **a**: Scheduled in slot 1 (deadline 2).
  - **c**: Scheduled in slot 2 (also has a deadline of 2, but slot 1 was filled by 'a').
  - **e**: Scheduled in slot 3 (fits within its deadline).

- **Total Profit**: 
  - From the scheduled jobs:
    - Job 'a' (profit 100)
    - Job 'c' (profit 27)
    - Job 'e' (profit 15)
  - Total Profit = 100 + 27 + 15 = 142.

#########Step-by-Step Execution
Sorting Jobs by Profit: The jobs are sorted in descending order by profit, resulting in the following order:

a (100), c (27), d (25), e (15), b (19)
Determine Maximum Deadline:

The program scans all jobs to find the maximum deadline, which is 3 (from job e).
This means we need a maximum of 3 slots (one for each deadline up to 3).
Initialize Arrays:

result = ['X', 'X', 'X']: This array will store the job IDs in the sequence they are scheduled. It starts with 'X' to denote empty slots.
slot = [false, false, false]: This array indicates if a slot has been filled.
Scheduling Jobs:

The program then tries to place each job in the latest available slot on or before its deadline.
Job Scheduling Details:
Job a (profit 100, deadline 2):

The latest slot it can fit is slot 1 (index 1, since array indices start at 0).
Slot 1 is free, so job a is placed in slot 1.
Updated result: ['X', 'a', 'X']
Updated slot: [false, true, false]
Job c (profit 27, deadline 2):

The latest slot it can fit is slot 1.
Slot 1 is already occupied by job a, so we try slot 0.
Slot 0 is free, so job c is placed in slot 0.
Updated result: ['c', 'a', 'X']
Updated slot: [true, true, false]
Job d (profit 25, deadline 1):

The latest slot it can fit is slot 0.
Slot 0 is already occupied by job c, so job d cannot be scheduled.
Job e (profit 15, deadline 3):

The latest slot it can fit is slot 2.
Slot 2 is free, so job e is placed in slot 2.
Updated result: ['c', 'a', 'e']
Updated slot: [true, true, true]
Job b (profit 19, deadline 1):

The latest slot it can fit is slot 0.
Slot 0 is already occupied by job c, so job b cannot be scheduled.
After scheduling, the jobs in result are ['c', 'a', 'e'], which represents the scheduled job sequence.

Calculating Total Profit:

The program adds up the profits of the scheduled jobs:
Job c (profit 27)
Job a (profit 100)
Job e (profit 15)
Total Profit = 100 + 27 + 15 = 142
Final Output
The program displays:

Job Sequence: a c e
Total Profit: 142
Explanation of Output
Job Sequence a c e: The jobs a, c, and e are selected because they maximize profit while respecting each job’s deadline constraints.
Total Profit 142: The total profit is calculated by summing the profits of jobs a, c, and e.
*/