public class BinaryNumber {
    /*
     * Name: Eduardo Hernandez
     * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
     * Section: CS284-B
     */
    private int length;
    private int[] data;

    // Constructor BinaryNumber for length, creates a binay number of length lenght and returns only zeros
    public BinaryNumber(int length){
        if (length <= 0) {
			throw new IllegalArgumentException("Length  must be greater than 0.");
		}
        this.length = length;
        int[] ArrayOfZeros = new int[length];
        for(int zeros = 0; zeros < ArrayOfZeros.length; zeros++){
            ArrayOfZeros[zeros] = 0;
        }
        this.data = ArrayOfZeros;
    }

    /* constructor BinaryNumber for String str, for creating a binary number given a
    string. For example, given the string "1011", the corresponding binary number should be created and 
    stored in an array  */
    public BinaryNumber(String str) {
        int lengthOfString = str.length();
        char characterIndex;
        int[] binaryArray = new int[lengthOfString]; // the length of the string will be the length of the array

        for (int countIndex = 0; countIndex < lengthOfString; countIndex++) {
            characterIndex = str.charAt(countIndex);
            int characterValue = Character.getNumericValue(characterIndex);
            if(characterValue == 0 || characterValue == 1){
                binaryArray[countIndex] = characterValue;
            }
            else{
                throw new IllegalArgumentException("number must be 0 or 1");
            }
            binaryArray[countIndex] = characterValue;
    }
        this.length = lengthOfString;
        this.data = binaryArray;
    }

    // determines the length of a binary number
    public int getLength() {
        this.length = this.data.length;
        int finalLength = this.length;
        return finalLength;
    }
 
    //obtains a digit of a binary number given an index. If the index is out of bounds it will identifiy it
    public int getDigit(int index){
        if(index > this.data.length){
            throw new ArrayIndexOutOfBoundsException("The index is out of bounds");
        }
        return this.data[index];
    }  
       // returns the integer array representing the binary number
       public int[] getInnerArray() {
        return this.data;
    }
    
    /*converts a binary number string to its decimal notation. It iterates through each digit of the string and convert
    it to decimal.*/
    public int toDecimal(){
        int convertToDecimal = 0;
        //implement for loop
        for(int stringCounter = 0; stringCounter < this.getLength() ; stringCounter++){
            convertToDecimal += Math.pow(2, stringCounter) * this.data[this.length - stringCounter - 1];
        }
        return convertToDecimal;
    }
     /*computes the bitwise OR of the two numbers.  It adds one if one of the two strings of equal length have a one
    if both  have a zero, it will add a zero. For example, 100 + 010 will give  110 because all the sums have a one except 
    for the first one/*/
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
        int[] OR_Array = new int[bn1.length];

        if(bn1.length!= bn2.length){
           throw new IllegalArgumentException("Error: length must be equal!");
        }
        else{ 
            for(int countOr = 0; countOr < bn2.length; countOr++){
                if(bn1.getDigit(countOr) == 1 || bn2.getDigit(countOr) == 1){
                    OR_Array[countOr] = 1;
                }else{
                    OR_Array[countOr] = 0;
                }
            }
        }
        return OR_Array;
    }
  
     /*computes the bitwise AND of the two numbers.  It adds one if one BOTH strings of equal length have a one when being added
    For examplpe, if we have 10 + 11, the first number will be a 1 because there is a zero, and the
    second one was a one because both are one.*/
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2){
        int[] AND_Array = new int[bn2.length];
        if(bn1.length != bn2.length){
            throw new IllegalArgumentException("Error: length must be equal!");
        }
        else{
            for(int countAnd = 0; countAnd < bn2.length; countAnd++){
                if(bn1.getDigit(countAnd) == 1 && bn2.getDigit(countAnd) == 1){
                    AND_Array[countAnd] = 1;
                }else{
                    AND_Array[countAnd] = 0;
                }
            }
        }
        return AND_Array;
    }

/* shifts all digits in a binary number to the left or right. If the direction is not 1 or -1, it will throw an error.
if the amount is not greater than zero, it will display another error. For right shifts it will subtract the amount
provided, and for left shifts it will add zeros. For example, if a 2 left is given and the number is 1000, the result is 100000
 but if the 1000 is shifted two units right, the outcome will be 10 
*/
public void bitShift(int direction, int amount) {

    int subtract_digits = this.getLength() - amount; //subtracting for right shift 
    int add_digits  = this.getLength() + amount; // add zeros for left shift 
  
    if(amount > 0){
            if(direction == 1){ //right shift 
                if(amount > this.getLength()){
                    throw new IllegalArgumentException("length to be shifted" 
                    + "right has to be greather than length of the number");
                }else{
                int[] rightShift = new int[subtract_digits];
                
                for(int i = 0; i < subtract_digits; i++){
                    
                    rightShift[i] = this.getDigit(i);
                }
                this.data = rightShift;
            }
            }else if(direction == -1){ //left shift 
                int[] leftShift = new int[add_digits];
                for(int k = 0; k < data.length; k++){
                    leftShift[k] = data[k];
                    
                }
                for(int j = data.length; j < add_digits; j++){
                    leftShift[j] = 0;
                }
                this.data = leftShift;
            }
           else{
                throw new IllegalArgumentException("Error: directions must have value of 1 or -1");
            }
        }else{
            throw new IllegalArgumentException("Amount should be greater than zero");
        }
    }

/*adds to binary numbers. If the lengths of the numbers is not equal, the prepend method will add zeros to the
one of less length or subtract from a longer one to make them equal and perform the operations. 
the loop will check for all posible outcomes when adding binary and add a carry to the end if there's is need to
with a final if statement. */
public void add(BinaryNumber aBinaryNumber){
  
        if(this.length < aBinaryNumber.length){
            int totalPrepended = aBinaryNumber.length - this.length;
            this.prepend(totalPrepended);
        
        }else if(aBinaryNumber.length < this.length){
            int totalPrepended = this.length  - aBinaryNumber.length;
            aBinaryNumber.prepend(totalPrepended);
        }
        int carryDigit = 0;
        int[] binary = new int[this.length];

        for(int j = this.length - 1; j >= 0; j--){
            int addition = this.getDigit(j) + aBinaryNumber.getDigit(j) + carryDigit;
            if(addition == 3){
                binary[j] = 1;
                carryDigit = 1;
            }
            else if(addition == 2){
                binary[j] = 0;
                carryDigit = 1;
            }
            else if(addition == 1){
                binary[j] = 1;
                carryDigit = 0;
            }
            else if(addition == 0){
                binary[j] = 0;
                carryDigit = 0;
            }
        }
      this.data = binary;
      this.length = binary.length;

    if (carryDigit == 1) {
        //add a zero if we have a carry 
        this.prepend(1);
        //the first digit will always be one 
        this.data[0] = 1;
        //update the length by one since a carry is being added 
        this.length = this.length + 1;
    }
}
 /*converts a binarynumber to a string. It will iterate through the number and for each index of the number,
 it will add the digit until the length is reached.  
*/
 public String toString(){
        String convertToString = new String();
        for (int binaryCounter = 0; binaryCounter < this.getLength(); binaryCounter++) {
            convertToString = convertToString + this.getDigit(binaryCounter);
        }
        return convertToString;
    }
//prependes zeros to the binary number. The amount represents the number of zeros to be added to the binary digit
public void prepend(int amount){
        int zeros_to_prepend = this.length + amount;
        int[] newArrayLength = new int[zeros_to_prepend];
        //for loop for assigning zeros to the number 
        for(int counter = 0; counter < amount; counter++ ){
            newArrayLength[counter] = 0;
         }
        //for loop for updating the length of the number
        for (int counter1 = 0; counter1 < this.length; counter1++){
            newArrayLength[counter1 + amount] = data[counter1];
        }
        this.data = newArrayLength;
        this.length = this.length + amount;
    }

   public static void main(String[] args) {
    
    }


}

