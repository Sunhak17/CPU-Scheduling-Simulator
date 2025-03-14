import java.util.*;

class Process {
    String id;
    int arrivalTime, burstTime, remainingTime, completionTime, waitingTime, turnaroundTime;

    public Process(String id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    public void reset() {
        this.remainingTime = this.burstTime;
        this.completionTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class CPUScheduler {

    private static void fcfs(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int time = 0;
        StringBuilder ganttChart = new StringBuilder("Gantt Chart: ");

        for (Process p : processes) {
            if (time < p.arrivalTime) time = p.arrivalTime;
            ganttChart.append(p.id).append(" (" + time + "-");
            time += p.burstTime;
            p.completionTime = time;
            ganttChart.append(time + ") -> ");
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }

        ganttChart.setLength(ganttChart.length() - 3);
        System.out.println(ganttChart);
        printResults(processes);
    }

    private static void sjf(List<Process> processes) {
        int time = 0, completed = 0, n = processes.size();
        List<Process> readyQueue = new ArrayList<>();
        StringBuilder ganttChart = new StringBuilder("Gantt Chart: ");

        while (completed < n) {
            for (Process p : processes) {
                if (p.arrivalTime <= time && !readyQueue.contains(p) && p.remainingTime > 0) {
                    readyQueue.add(p);
                }
            }
            readyQueue.sort(Comparator.comparingInt(p -> p.burstTime));

            if (!readyQueue.isEmpty()) {
                Process p = readyQueue.remove(0);
                ganttChart.append(p.id).append(" (" + time + "-");
                time += p.burstTime;
                p.completionTime = time;
                ganttChart.append(time + ") -> ");
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                completed++;
            } else {
                time++;
            }
        }

        ganttChart.setLength(ganttChart.length() - 3);
        System.out.println(ganttChart);
        printResults(processes);
    }

    private static void srt(List<Process> processes) {
        int time = 0, completed = 0, n = processes.size();
        StringBuilder ganttChart = new StringBuilder("Gantt Chart: ");

        while (completed < n) {
            Process shortest = null;
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0 && (shortest == null || p.remainingTime < shortest.remainingTime)) {
                    shortest = p;
                }
            }

            if (shortest == null) {
                time++;
                continue;
            }

            ganttChart.append(shortest.id).append(" (" + time + "-");
            shortest.remainingTime--;
            time++;

            if (shortest.remainingTime == 0) {
                shortest.completionTime = time;
                shortest.turnaroundTime = shortest.completionTime - shortest.arrivalTime;
                shortest.waitingTime = shortest.turnaroundTime - shortest.burstTime;
                completed++;
            }
            ganttChart.append(time + ") -> ");
        }

        ganttChart.setLength(ganttChart.length() - 3);
        System.out.println(ganttChart);
        printResults(processes);
    }

    private static void rr(List<Process> processes, int quantum) {
        Queue<Process> queue = new LinkedList<>();
        int time = 0, completed = 0;
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int index = 0;
        StringBuilder ganttChart = new StringBuilder("Gantt Chart: ");

        while (completed < processes.size()) {
            while (index < processes.size() && processes.get(index).arrivalTime <= time) {
                queue.add(processes.get(index));
                index++;
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            Process p = queue.poll();
            int startTime = time;
            if (p.remainingTime > quantum) {
                time += quantum;
                p.remainingTime -= quantum;
                queue.add(p);
            } else {
                time += p.remainingTime;
                p.remainingTime = 0;
                p.completionTime = time;
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                completed++;
            }
            ganttChart.append(p.id).append(" (" + startTime + "-" + time + ") -> ");
        }

        ganttChart.setLength(ganttChart.length() - 3);
        System.out.println(ganttChart);
        printResults(processes);
    }

    private static void printResults(List<Process> processes) {
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        System.out.println("\nWaiting Times:");
        for (Process p : processes) {
            System.out.print(p.id + " = " + p.waitingTime + " ");
            totalWaitingTime += p.waitingTime;
        }

        System.out.println("\nTurnaround Times:");
        for (Process p : processes) {
            System.out.print(p.id + " = " + p.turnaroundTime + " ");
            totalTurnaroundTime += p.turnaroundTime;
        }

        System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / processes.size());
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processes.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCPU Scheduling Algorithms:");
            System.out.println("1. First-Come, First-Served (FCFS)");
            System.out.println("2. Shortest-Job-First (SJF)");
            System.out.println("3. Shortest-Remaining-Time (SRT)");
            System.out.println("4. Round Robin (RR)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Exiting program.");
                break;
            }

            System.out.print("Enter number of processes: ");
            int n = scanner.nextInt();
            List<Process> processes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Process ID, Arrival Time, and Burst Time: ");
                String id = scanner.next();
                int arrivalTime = scanner.nextInt();
                int burstTime = scanner.nextInt();
                processes.add(new Process(id, arrivalTime, burstTime));
            }

            if (choice == 1) fcfs(processes);
            else if (choice == 2) sjf(processes);
            else if (choice == 3) srt(processes);
            else if (choice == 4) {
                System.out.print("Enter time quantum: ");
                int quantum = scanner.nextInt();
                rr(processes, quantum);
            }

            for (Process p : processes) p.reset();
        }
        scanner.close();
    }
}
