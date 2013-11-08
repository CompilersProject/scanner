import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;


public class Compiler {
  // No good compile-time code stripping for Java
  // Any if statements with static final variables should be evaluated at compile time
  // So this should work without unnecessary overhead
  public static final boolean extendedDebug = true;
  
   public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
   {
     
     try{
       String testFile;
       if( args.length != 0 ){
         testFile = args[0];
       }
       else{
         testFile = "04-boolean.kln";
       }
       Scanner test = new Scanner( testFile );
       TableDrivenParser tdp = new TableDrivenParser( test );
       SemanticAnalyzer analyzer = new SemanticAnalyzer();
       
       tdp.parseProgram();
       
       analyzer.makeSymbolTable( tdp.getSemanticNode() );
     }
     catch(Exception e){
       System.out.println( e );
     }
   }
}
