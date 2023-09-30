import java.util.InputMismatchException;
import java.util.Scanner;
public class TestTaskList<E>{
    
    /*
     * Eduardo Hernandez
     * CS 284 Section B
     * I pledge my Honor that I have abided by the Stevens Honor System.
     */

    //fields declaration 
    private TaskList<E> toDoList; 
    private Scanner scan;

    //initializes todolist and scan
    public TestTaskList() {
        toDoList = new TaskList<>();
        scan = new Scanner(System.in);
    }

    /*
     * This method will print the menu. There are two possible menus. If the active task is empty, it will print that there are no items
     * if the queue has tasks, it will print the options and the current task list
     * if the input is 8, it will terminate the program
     * if it's a number out of bounds, it will give error and loop back to the menu
     * if a non integer is given, it will catch an exception and go back to the menu
     */
    public void printMenu() {
        boolean quit = false;
        System.out.println("~~~ TO-DO List Program, created by truly yours ~~~");
    
        int userInput = 0;
        
        while (!quit) {
        
            toDoList.secondMenu();
    
            try {
                userInput = scan.nextInt();
    
                if (userInput == 8) {
                    break; //break if the input is 8
                } else if (userInput < 1 || userInput > 8) {
                    System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
                    userInput = 0; //reset input to zero so that we can assume the queue is empty and go back to the first menu
                } else {
                    processMenuItem(userInput); //input is between 1-8
                }
            } catch (InputMismatchException e) { //throw error if the user enters a non-integer
             
                System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
                scan.nextLine();  
                userInput = 0;  //reset input to zero so we can assume the queue is empty and return to the first menu
            }
        }
    }

    /*
     * takes the input representing the operation that the user choses 
     * calls the appropiate functions according to what the user has requested 
     */
    private boolean processMenuItem(int menuItem){
        
            if(menuItem == 1){
                System.out.println("Please enter the task description: ");
                scan.nextLine();
                String task = scan.nextLine();
                toDoList.createTask((E) task);  
               
            }
            if(menuItem == 2){  
                System.out.println("Please enter the task description:");
                scan.nextLine();
                try{
                String task1 = scan.nextLine();
                System.out.println("Please enter a priority number: (1 indicates highest priority, increasing numbers show lower priority) : ");
                int num = scan.nextInt();
                toDoList.createTask((E) task1, num);
                }catch(InputMismatchException e){
                    System.out.println("Unsuccessful operation! Please try again!");
                    scan.nextLine();
                }
            }
            if(menuItem == 3){ 
                toDoList.crossOffMostUrgent();
            
            }
            if(menuItem == 4){ 
                System.out.println("Please enter the task number you would like to cross off the list :");
                scan.nextLine();  
                try{
                    int nums = scan.nextInt();
                    toDoList.crossOffTask(nums);
                }catch (InputMismatchException e){
                    System.out.println("Unsuccessful operation! Please try again!");
                    scan.nextLine();
                }
            }
            if(menuItem == 5){
                toDoList.printTopThreeTasks();

            }
            if(menuItem == 6){ 
               toDoList.getCompleted();
               toDoList.showCompletedTasks();

            }
            if(menuItem == 7){
               toDoList.showAllTasks();
            }
        return true; 
    }
   
    //main function, calls the menu 
    public static void main(String[] args) {

        TestTaskList<String> tester = new TestTaskList<>();
        tester.printMenu();
    }
}

