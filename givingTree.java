import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public Solution parentNode;
    public String [] childList;
    public String dataValue;
    
    public void String(String dataValue){
        this.dataValue = dataValue;
    }
        
    public Solution(String dataValue){
        this.dataValue = dataValue;
        String childList[][];
    }
    
    public static void checkError(String currentInput, int parentInt, List<String> inputs, String parent, String child, int count, String[][] children){
        int c = 0;
            if(currentInput.length() > 6){
                errors(1);
                return;
            }
            if(parentInt < 10 || parentInt > 35) {
                errors(1);
                return;
            }
            if(!currentInput.substring(0,1).equals("(")){
                errors(1);
                return;
            }
            if(!currentInput.substring(2,3).equals(",")){
                errors(1);
                return;
            }
            if(!currentInput.substring(4,5).equals(")")){
                errors(1);
                return;
            }
            for(String str : inputs){
                if(inputs.contains("(" + parent + "," + child + ")")){
                    c++;
                }
            }
            
            if(c > 1) { errors(2); return; }
            
            for(int n = 0; n < 26; n++){
                for(int m = 0; m < 26; m++){
                    if(children[n][m] != null){
                        count++;
                        if (count > 2) {errors(3); return;}
                    }
                }
                count = 0;
            }
    }
    
    public static void errors (int err){
        String errorMessage = ""; //assign the errorMessage
            switch (err) {
                case 1: errorMessage = "E1";
                    break;
                case 2: errorMessage = "E2";
                    break;
                case 3: errorMessage = "E3";
                    break;
                case 4: errorMessage = "E4";
                    break;
                case 5: errorMessage = "E5";
                    break;
            }
         System.out.println(errorMessage); //print the error message
         System.exit(0); //quit
    }
    
    public static void printSexp(int numParents, Solution[] myParents, String[][] children, List<String> parentList, List<String> childrenList){
        
        String x = "";
        int count = 0;
        String s = "";
        String strToRmv = "";
        String secondChild = "";
        String p = "";
        
        List<String> n1 = new ArrayList<String>();
        List<String> n2 = new ArrayList<String>();
        List<String> parentChild = new ArrayList<String>();
        
         for(int r = 0; r<parentList.size(); r++) {  
            parentChild.add(r, childrenList.get(r));
        }
         for(int r = 0; r<parentList.size(); r++) {
             parentChild.add(r, parentList.get(r));
        }
        //adds to second node second child at parent index
        for(int r = 0; r<parentList.size(); r++) {           
            x = parentList.get(r);
            n1.add(childrenList.get(r));
            if ((Collections.frequency(parentList, x)) > 1 && count < 1){
                n2.add(r, childrenList.get(r+1));
                String hold = childrenList.get(r);
                char new_char = hold.charAt(0);
                int new_int = (int) new_char;
                new_int++; // sets to 'C' ASCII
                char c = (char)new_int;
                s = String.valueOf(c);  
                count++;
            }
            else { n2.add(""); count = 0;}
        }
        n1.remove(s);
        n1.add("");
        count = 0;
        
        System.out.print("(" + parentList.get(0));
        for(int i = 0; i<n2.size(); i++){
            if(i<4){
            if(n2.get(i) != ""){
                secondChild = n2.get(i);
            }
                for(int r = 0; r<parentChild.size()-1;r++){
                    if(r<6){
                    if(parentChild.get(r).equals(secondChild)){
                        p = parentChild.get(r);
                        strToRmv = parentChild.get(r+6);
                        }
                    }
                    else { break; }
                    //r=0;
                }
            } else{ break; }
                n1.remove(strToRmv);
                n1.add("");
                System.out.print("(" + n1.get(i)); 
        }
        
        if(secondChild == ""){
            System.out.print("()");
        }
        for(int i=0; i<parentList.size()-2; i++){
            System.out.print(")"); 
        }
        if(secondChild != ""){
            System.out.print("("+secondChild);
        }
        if(strToRmv != ""){
            System.out.print("("+strToRmv+")");
        }
        for (int i = 0; i<numParents; i++){
            if (i > numParents-3){ 
                System.out.print(")");
            }
        }
    }
                
    
    public static void getSexp (){
        String currentInput = "";
        String parent = "";
        String child = "";
        char parentChar = 'a';
        char childChar = 'a';
        int count = 0;
        int index = 0;
        int j = 0;
        int numParents = 0;
        int placeHolder = 0;
        int parentInt = 0;
        int childInt = 0;
        
        Scanner scan = new Scanner(System.in);
        List<String> inputs = new ArrayList<String>();
        List<String> parentList = new ArrayList<String>();
        List<String> childrenList = new ArrayList<String>();
        
        //init Parents array
        Solution[] myParents = new Solution[26];
            for(int i = 0; i < 26; i++){
                myParents[i] = new Solution("null.."); //init the datavalue to null for all the parents
            }
        
        //init Children of parents
        String[][] children = new String[26][26];
            for(int i = 0; i < 26; i++){
                for(int k = 0; k<26; k++){
                children[i][j] = null;
            }
        }
        
        while(scan.hasNext()){
            currentInput=scan.next();
            parent = currentInput.substring(1,2);
            child = currentInput.substring(3,4);
            parentChar = parent.charAt(0);
            childChar = child.charAt(0);
            parentInt = Character.getNumericValue(parentChar);
            childInt = Character.getNumericValue(childChar);
            children[parentInt-10][childInt-10] = child;
            myParents[parentInt-10].dataValue = parent;
            checkError(currentInput, parentInt, inputs, parent, child, count, children);
            inputs.add(currentInput);

            for(int i = 0; i < 26; i++){
                if(parentInt == (i+10)){
                    myParents[i].dataValue = parent;
                    parentList.add(myParents[i].dataValue);
                    childrenList.add(child);

                }
            }
            j++;
        }
        
        
        for(int i = 0; i < 26; i++){
            if(myParents[i].dataValue.equals("null..")){
                placeHolder = i;
                break; 
            }
        }
        for(int i = placeHolder+1; i < 26; i++){
            if(!myParents[i].dataValue.equals("null..")){
                errors(4);
                return;
            }
        }

        for(int x = 0; x<26; x++){
            if(myParents[j].dataValue.equals("null..")){
                numParents = j-1;
            }
        }

        if(parent.equals(child)){
            errors(5);
            return;
        }
        printSexp(numParents, myParents, children, parentList, childrenList);
        scan.close(); //close up shop.
    }

    public static void main(String args[] ) throws Exception {
        getSexp();
    } 
}