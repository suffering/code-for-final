package unit13.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task implements Comparable<Task> {
    private final String name;
    private final int time;

    public Task(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Task o) {
        return this.time - o.time;
    }

    @Override
    public String toString() {
        return name + "(" + time + " hours)";
    }

    /**
     * 
     * @param allTasks
     * @param time
     * @return
     */
    public static List<Task> findMaximumSubset(List<Task> allTasks, int time) {
        List<Task> tasks = new ArrayList<>(allTasks.size());
        
        Collections.sort(allTasks);
        int totalTime = 0;

        for(int i=0; i<allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if(task.time + totalTime < time) {
                tasks.add(task);
                totalTime += task.time;
            } else {
                break;
            }
        }

        return tasks;
    }
    
}
