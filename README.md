# CPU-Scheduling-Simulator
# CPU Scheduling Simulator

## Description
This program implements different CPU scheduling algorithms:
- First-Come, First-Served (FCFS)
- Shortest-Job-First (SJF)
- Shortest Remaining Time (SRT)

It allows users to input process details and select a scheduling algorithm to analyze the performance in terms of waiting time and turnaround time.

## How to Run the Program

### Compilation
Make sure you have Java installed. Then, compile the program using:
```
javac CPUScheduler.java
```

### Execution
Run the compiled program:
```
java CPUScheduler
```

Follow the on-screen instructions to input process details and select a scheduling algorithm.

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

