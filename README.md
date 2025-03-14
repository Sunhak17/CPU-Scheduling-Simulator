# CPU-Scheduling-Simulator

This program implements different CPU scheduling algorithms:
- First-Come, First-Served (FCFS)
- Shortest-Job-First (SJF)
- Shortest Remaining Time (SRT)
- Round Robin (RR)
  
It allows users to input process details and select a scheduling algorithm to analyze the performance in terms of waiting time and turnaround time.

## How to Run the Program

### Compilation

Make sure you have Java installed. 
Then you open in VS code you will see the word run above main method.

Then Click on the word Run! The program will be execute for you.

## Here is sample menu that you might see
```
CPU Scheduling Algorithms:
1. First-Come, First-Served (FCFS)
2. Shortest-Job-First (SJF)
3. Shortest-Remaining-Time (SRT)
4. Round Robin (RR)
5. Exit
Choose an option:
```

## Example Input & Output
### Sample Input:
```
Choose an option: 1
Enter number of processes: 3
Enter Process ID, Arrival Time, and Burst Time: P1 0 5
Enter Process ID, Arrival Time, and Burst Time: P2 2 3
Enter Process ID, Arrival Time, and Burst Time: P3 4 2
```

### Sample Output:
```
Gantt Chart: P1 (0-5) → P2 (5-8) → P3 (8-10)
Waiting Times:
P1 = 0 P2 = 3 P3 = 4
Turnaround Times:
P1 = 5 P2 = 6 P3 = 6
Average Waiting Time: 2.33
Average Turnaround Time: 5.67
```

