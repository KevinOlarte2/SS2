package Lib;

public class Conversion {
    
    static final String NUMBERSHEX = "0123456789ABCDEF";  // List with the number in Hex, her index is number in Decimal

    /*
     * ------ INDEX ------
     * 1.- Decimal to Bin
     * 2.- Decimal to Hex
     * 3.- Bin to Decimal
     * 4.- Bin to Hex
     * 5.- Hex to Bin
     * 6.-Hex to Decimal
    */


    /**
     * 1.- Decimal to Bin
     * --------------------
     * @param numDecimal Decimal number to convert.
     * @return binary number in a variable of type long.
    */
    public static long decimalBin(int numDecimal){
        String numBin = numDecimal == 0 ? "0" : "";
        // int space = 0;
        while (numDecimal > 0) {

            // Extraction of the remainder from the division and appending it to the left side of the string.
            numBin = String.valueOf(numDecimal % 2) + numBin;
            
            /* 
                It would be used for a proper visualization, with a space every 4 digits. 
            space++;
            if (space == 4) {                   
                numBin = " " + numBin;                  Condition for use, the return need be String
                space = 0;
            }
            */

            numDecimal /= 2;
        }
        return Long.parseLong(numBin);
    }


     /**
     * 2.- Decimal to Hex
     * --------------------
     * @param numDecimal Decimal number to convert.
     * @return hex number in a variable of type string.
    */
    public static String decimalHex(int numDecimal){
        String numHex = "";
        if (numDecimal == 0)
            return "0";
         while (numDecimal >= 1) {

            // Extraction of the remainder from the division and appending it to the left side of the string.
            numHex = NUMBERSHEX.charAt(numDecimal % 16) + numHex;
            
          
            numDecimal /= 16;
        }
        
        return numHex;
    }


    /**
     * 3.- Bin to Decimal
     * --------------------
     * @param numBin binary number to convert.
     * @return decimal number in a variable of type int.
    */
    public static int binDecimal(long numBin){
    
        //We don't start with 0 because if we want to raise something to a power, it always be 0.
        int numDecimalPosition = 1;
        int numDecimal = 0;
        String numBinTxt = "" + numBin;

        for (int i = numBinTxt.length() -1; i >= 0; i--) {
            if (numBinTxt.charAt(i) == '1') {
                // System.out.println(numBinTxt.charAt(i) + " - " + i);

                // Raising to the power of the position of the 1. 
                //Since the string is read from right to left, this calculation gives it in reverse: //! ((i - (numBinTxt.length() - 1))."
                
                for (int j = 1; j <= ((i-(numBinTxt.length()-1)) * -1); j++) {
                    
                    numDecimalPosition *= 2;
                }
            }
             else{
                numDecimalPosition = 0;
            }

            numDecimal += numDecimalPosition;
            numDecimalPosition = 1;
               
        }
        numDecimal = numBin == 0 ? 0 : numDecimal;

        return numDecimal;
    }


     /**
     * 4.- Bin to Hex
     * --------------------
     * @param numBin binary number to convert.
     * @return hex number in a variable of type string.
    */
    public static String binHex(long numBin){
        String numHex = "";
        
        // Reusing already created methods to obtain the hex number.
        numHex = decimalHex(binDecimal(numBin));
        return numHex; 

    }

    /**
     * 5.- Hex to Bin
     * --------------------
     * @param numHex  Hexadecimal number to convert.
     * @return binary number in a variable of type long. 
    */
    public static long hexBin(String numHex){ 
        long numBin = 0;
        
        // Reusing already created methods to obtain the bin number.
        numBin = decimalBin(hexDecimal(numHex));
        return numBin;
    }


    /**
     * 6.- Hex to Decimal
     * --------------------
     * @param numHex  Hexadecimal number to convert.
     * @return decimal number in a variable of type int. 
    */
    public static int hexDecimal(String numHex){ 
        
        int numDecimal = 0;
        char charHex;
        for (int x = 0; x < numHex.length() ; x++) {

            // Extracting the character with index x.
            charHex = numHex.charAt(x); 

            int base = 1;

            // Extracting the value of the power with base 16, and the exponent varies according to its position.
            for (int i = (numHex.length() -1) - x; i > 0  ; i--) {
                if (numHex.length() == 1) 
                    break;
                base *=  16;      
            }

            // Performing the calculation to cast the hexadecimal number to decimal.
            numDecimal += NUMBERSHEX.indexOf(charHex) * base; 
        }
        return numDecimal;
    }
    
}
