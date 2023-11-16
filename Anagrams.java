import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	/**
	 * This method builds a has table that associates letters with each prime number
	 * the index are calculated by subtracting i from a, which is 97 in ASCII code
	 * it continues until it reaches z
	 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();

		    for(char i = 'a'; i <= 'z'; i++){
				int j = i - 'a';
				letterTable.put(i, primes[j]);

			}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	/**
	 * computes the hash code of the string s passed as argument
	 * adds the word to the has table 
	 * will check if the key is already in the anagramtable 
	 * @param s
	 */
	public void addWord(String s) {
		long hash = myHashCode(s);
		ArrayList<String> words = anagramTable.get(hash);
		if(!anagramTable.containsKey(hash)) {
			ArrayList<String> words1 = new ArrayList<String>();
			words1.add(s);
			anagramTable.put(hash, words1);
		} else {
			words.add(s);
			anagramTable.put(hash, words);
		}
		
	}

	/**
	 * Computes the hash code given a string s 
	 * checks if the string is empty 
	 * it will iterate through the string and keep multiplying by the character
	 * @param s
	 * @return hash code, type long 
	 */
public long myHashCode(String s) {
		if (s == null || s.isEmpty()){
			throw new IllegalArgumentException();
		}

			long hash = 1;
			for(int i = 0; i < s.length(); i++ ){
				hash *= letterTable.get(s.charAt(i));
			}
	    return hash;

	}

	/**
	 * receives the name of a text file containing words and builds the hash table
	 * it uses the addWord method
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}

	/**
	 * returns the entries in the anagramTable that have the largest number of anagrams
	 * returns a list of them 
	 * @return
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> listOfWords = new ArrayList<>();
		int maxValue = 0;
	
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			int size = entry.getValue().size();
			if (size > maxValue) {
				maxValue = size;
				listOfWords.clear();
				listOfWords.add(entry);
			} else if (size == maxValue) {
				listOfWords.add(entry);
			}
		}
	
		return listOfWords;
	}
	
	public static void main(String[] args) {
		 
		int[] findtarget = {4,3,100,1,5,10,90,9,101};
		Scanner scan = new Scanner(System.in); 

		System.out.println("Enter value: ");
		int value = scan.nextInt();
		int counter = 0;

		for(int i = o;  i < findtarget.length; i++){
			if(findtarget[i] == value);{
				counter++;
				System.out.println("Target found");
				System.out.println("Numer of occurences: " + counter);
			}


		}
	
	}
}
