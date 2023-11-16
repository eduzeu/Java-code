import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Dictionary {

    /*
     * Eduardo Hernandez
     * CS 284 Section B
     * I pledge my honor that I have abided by the Stevens Honor System.
     */
	
   //fields declaration
	private ArrayList <String> wordList;
	private ArrayList <DictionaryItem> dictArrayList;
	
	/* This constructor takes a string that represents the name of the file to be read.
	 * initializes the constructor with the values 1300 for the fields
	 * readFile is called and the string is passed.
	 * throws exception if file is not found
	 */
    public Dictionary() throws FileNotFoundException {
        this.wordList = new ArrayList<String>(1300);
        this.dictArrayList = new ArrayList<DictionaryItem>(1300);
        readFile("ionDictionary.txt");
    }
    
    /* This constructor takes a String and passes it as a parameter to Readfile
     * Throws and exception if file is not found 
     * initializes the fields with the values 1300
     * */
    public Dictionary(String filename) throws FileNotFoundException {
    	this.wordList = new ArrayList<String>(1300);
        this.dictArrayList = new ArrayList<DictionaryItem>(1300);
        readFile(filename);
    }
    
    /* The readFile method loops through the lines of the file until no line is found. 
     * For each line, it calls the helper splitStoreLine to apply an operation to every line
     * throws exception if file is not found
     * */
    public void readFile(String filename){
           try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            splitStoreLine(scanner); 
            scanner.close();

    }catch(FileNotFoundException e){
        System.out.println("File not found");
    }
          
    }

    /* The splitStoreLine method applies an operation to each line of the file.
     * It will divide the word and the count into an array using the split method and using | as delimiter
     * The trim() method is to get rid of the spaces that counts or words have after and before to make it easier
     * the count will be converted to a string 
     * wordList will take the words, and dictArrayList will take the word-count pair.
     * ex: apple   |  4 will become {apple, 4} 
     *if it can't find a line with the format above, it will throw an exception and go to next line
     * */
    private void splitStoreLine(Scanner scan) {
        while(scan.hasNextLine()){
            try{
                String line = scan.nextLine();
                String[] splitString = line.split("\\|");
                String words = splitString[0].trim(); //trim method called to remove white spaces
                int count = Integer.parseInt(splitString[1].trim()); 
                DictionaryItem word_and_count = new DictionaryItem(words, count);
                dictArrayList.add(word_and_count); 
                wordList.add(words);
            // handle any exceptions that may occur
            } catch (Exception e) {
               
                /*left empty because the program worked like that and I also went to Dr. Ackam office hours and
                she came up with the idea*/
            }
        }
    }
   
   /* processMenuItem method will return true if the userinput keeps the menu going.
    * It will return false only when the user wants to quit the program
    * choose 1 will call a method that prints all the words in the dictionary 
    * choose 2 will search a word in the dictionary 
    * choose 3 will quit the program 
    * any other input will throw an error and give the menu again.
    * */
   private boolean processMenuItem(int menuItem, Scanner scan) {
       if (menuItem == 1) {
          printDictionary();
       } else if (menuItem == 2) {
           System.out.println("Please enter the word you would like to search: ");
         String word = scan.nextLine();
           searchDictionary(word);
       } else if (menuItem == 3) {
           System.out.println("Thanks for using Ion Dictionary! Bye!");
           return false; //the only condition that makes the program false 
       } else {
           System.out.println("ERROR! Please enter a number between 1 and 3.");
       }
       return true;
   }
   
   /* printMenu() method will loop the menu until a false value is returned from processMenuItem.
    * while the loop is true, the menu will be display
    * if the loop is false, the loop will end. 
    * if the input is not a number between 1-3 but it's not an integer either, it will catch an exception
    * */
   public void printMenu(){
       Scanner scanner = new Scanner(System.in);

       boolean True = true;
       System.out.println("Welcome to the Ion Dictionary! This dictionary is "
       + "created from the book Ion by Plato!");
       
       while(True){
       System.out.println("Please choose one of the folllowing "
       + "menu items indicated with 1-3\n1. To print all the words in the dictionary, choose 1\n"
       + "2. To search for a word in the dictionary, choose 2\n3. To quit the program, choose 3 ");
       try{
       int number = scanner.nextInt();
           scanner.nextLine();
           True = processMenuItem(number, scanner);
       }catch (InputMismatchException e){
        System.out.println("ERROR! Please enter a number between 1 and 3.");
        scanner.nextLine();
       }
    }
        scanner.close();

     }
      
   /* this method will be called if the user wants to see all the words in the dictionary
    * it will iterate through each of the items of the wordlist, and print each element until the size is reached
    * */
   public void printDictionary(){
	 System.out.println("All the words mentioned in the Ion book!\nWords\n-----");
	 for(int i = 0; i < wordList.size(); i++) {
		 String allWords = wordList.get(i);
		 System.out.println(allWords);
	 }

   }

   /**
    * will print the word and the count in the dictionary 
    * @param word
    * @return integer that is either -1 if the word does not exist, or the count for the word if word is found
    */
   public int searchDictionary(String word) {
    int index = binarySearch(word, 0, wordList.size() - 1);
    if (index == -1) {
        System.out.println("The word \"" + word + " \" does not exist in the Ion dictionary!");
       return -1;
    } else {
        int count = dictArrayList.get(index).getCount();
        System.out.println("The word \"" + word + "\" ocurred " + count + " times in the dictionary.");
      return count;
    }
}
/**
 * 
 * @param word: a string to find in the dictionary 
 * @param low: lowest number in the dictionary
 * @param high: highest number in the dictionary
 * @return: an integer representing the position of the word
 */
    private int binarySearch(String word, int low, int high) {

    while (low <= high) {
        int mid = low + (high - low) / 2; //get the middle element 
        String midWord = wordList.get(mid); 
        
        if (midWord.equals(word)) {
            return mid; //the word is found in the middle of the array  
        } else if (midWord.compareTo(word) > 0) {
            high = mid - 1; //the word is in the lower half of the dictionary
        } else {
            low = mid + 1; //the word is in the upper half of the dictionary 
        }
    }
    return -1; //it will return -1 if the word is not found in dictionary 
}


}







