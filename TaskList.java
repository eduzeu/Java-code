import java.util.*;
public class TaskList<E>{

    /*
     * Eduardo Hernandez
     * CS 284 Section B
     * I pledge my Honor that I have abided by the Stevens Honor System.
     */

     //fields declaration
    private ListQueue<E> all; 
    private ListQueue<E> completed; 
    private ListQueue<E> active;
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    private int HIGH_PRIORITY = 1;
    
    // initializes all the ListQueues in the attributes
    public TaskList(){
        all = new ListQueue<>();
        completed = new ListQueue<>();
        active = new ListQueue<>();
        
    }

    //will add the item into active and all queues with default priority as LOW_PRIORITY.
    public boolean createTask(E item) {
        if (item == null) {
            return false;
        }
        all.addRear(item);
        active.addRear(item);
        System.out.println("Successfully entered the task to the to-do list!");
        return true;
    }
    //will add the item into active and all queues. If item is null, it will return false. Otherwise returns true.
    public boolean createTask(E item, int priority){
        if(item == null){
            return false;
        }
        all.offer(item, priority);
        active.offer(item, priority);
        System.out.println("Successfully entered the task to the to-do list!");
        return true;
    }
    
    //getter
    public ListQueue<E> getAll(){
        return all; 
    }
    
    //getter
    public ListQueue<E> getCompleted(){
        return completed; 
    }

    //getter
    public ListQueue<E> getActive(){
        return active; 
    }

   //will print the top three tasks in the queue. If there are less than 3, it will print them all.
    public void printTopThreeTasks(){
        System.out.println("Top 3 highest priority tasks:\n" +
        "------------------------------\n" +
        "Printing Top Three Tasks...");

        ListQueue.Node<E> front1 = active.getFront();
        int taskNum = 1;

        while(front1 != null && taskNum <= 3){
            System.out.println(taskNum + ". " + front1.getData());
            front1 = front1.getNext();
            taskNum++;
        }
    }

    // helper method will use the iterator() to iterate through the queue elements and print them with numbers
    private void printTasks(ListQueue<E> queue){
        Iterator<E> iterate = queue.iterator();

        int taskNum = 1;
        
        while(iterate.hasNext()){
            System.out.println(taskNum + ". " + iterate.next());
            taskNum++;
        }
    }

    //shows only active tasks
    public void showActiveTasks(){

        System.out.println("Current TO-DO List:\n"+
        "-------------------");
        printTasks(active);
    }

    //shows all tasks in the queue 
    public void showAllTasks(){
        System.out.println("All of the tasks - Both completed and active:\n" +
        "---------------------------------------------");
        printTasks(all);
    }
    
    //shows completed tasks in the queue 
    public void showCompletedTasks(){
        System.out.println("Completed tasks:\n" +
        "----------------");
        printTasks(completed);
    }
    //removes the highest priority queue. Meaning the first element in the queue
    public boolean crossOffMostUrgent(){ 
        ListQueue.Node<E> front1 = active.getFront();
        if(front1 == null){
            System.out.println("Unsuccessful operation! Please try again!");
            return false; 
        }
        System.out.println("Task is completed and removed from the list: " + front1.getData());
        completed.addRear(front1.getData());
        active.remove(front1);
        System.out.println("Successfully removed the most urgent task/top of the list task!");
        return true;
    }

    /*
     * removes a task depending on the userinput
     * if the number is not found in the queue, it returns false and does not remove anything
     */
    public boolean crossOffTask(int taskNumber){

        ListQueue.Node<E> front1 = active.getFront();
        int num = 1;
        if(taskNumber > active.getSize() || taskNumber <= 0){
            System.out.println("Unsuccessful operation! Please try again!");
            return false; 
        }
        while(front1 != null){
           
            if(num == taskNumber){
                System.out.println("Task number is removed: "  + front1.getData());
                completed.addRear(front1.getData());
                active.remove(front1);
            }
            front1 = front1.getNext();
            num++;
        }  
        System.out.println("Succesfully removed the task number: " + taskNumber);
        return true; 
    }

    /*
     * Helper for the printMenu method in TestTaskLit class. 
     * it will print the menu with active tasks if there are tasks in the queue 
     * if there are no tasks, it will print the menu with a statement saying that there are no elemtns 
     */
    public void secondMenu(){
        if(active.getSize() == 0){
            System.out.println("==> Currently there are NO items in the To-Do List");
            System.out.println("To add a new task without priority information, press 1.");
            System.out.println("To add a new task with a priority information, press 2.");
            System.out.println("To cross off the task at the top of the list, press 3.");
            System.out.println("To cross off a certain task in the list, press 4.");
            System.out.println("To see the top 3 highest priority tasks, press 5.");
            System.out.println("To see the completed tasks, press 6.");
            System.out.println("To see the all tasks that has been completed or still active, press 7.");
            System.out.println("To quit the program, press 8.");
        }
        else{
            showActiveTasks();
            System.out.println("To add a new task without priority information, press 1.");
            System.out.println("To add a new task with a priority information, press 2.");
            System.out.println("To cross off the task at the top of the list, press 3.");
            System.out.println("To cross off a certain task in the list, press 4.");
            System.out.println("To see the top 3 highest priority tasks, press 5.");
            System.out.println("To see the completed tasks, press 6.");
            System.out.println("To see the all tasks that has been completed or still active, press 7.");
            System.out.println("To quit the program, press 8.");
        }
    }
    
}
