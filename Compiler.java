import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;


public class Compiler {
   public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
   {
    
     try{

     Scanner test = new Scanner( "testKlein!.txt" );
     Token tmp = test.getNextToken();
    
     System.out.println("<PROGRAM>");
     while( tmp.getValue() != "" ){
       
       if((tmp.getType() == Token.TYPE.IDENTIFIER))
         System.out.println("<"+tmp.getType()+">" + " " +tmp);
       
       else{System.out.println("<"+tmp.getType()+">");
       }
       
       tmp = test.getNextToken();
     }
     
     }
     catch(Exception e){
       System.out.println( e );
     }
 }
}