import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;


public class Compiler {
  // No good compile-time code stripping for Java
  // Any if statements with static final variables should be evaluated at compile time
  // So this should work without unnecessary overhead
  public static boolean extendedDebug = false;
  
  public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
  {
	  BufferedReader dataIn = new BufferedReader( new InputStreamReader(System.in) );
	  System.out.print("Enter a file name:  ");
	  String userFile = dataIn.readLine();
	
    try{
      String testFile;
      if( args.length != 0 ){
        testFile = args[0];
      }
      else{
    	  testFile = "src/klein-programs/"+userFile;
      }
      SemanticAnalyzer analyzer;
      Scanner test = new Scanner( testFile );
      TableDrivenParser tdp = new TableDrivenParser( test );
      
      tdp.parseProgram();
      
      SemanticAction programNode = tdp.getProgramNode();
      
      analyzer = new SemanticAnalyzer( programNode );
      
      if(extendedDebug == true) {
        for( String defName: analyzer.getSymbolTable().getTable().keySet() )
      	{
    	  System.out.println( defName + ": " + analyzer.getSymbolTable().getTable().get( defName ).toString() );
      	}
      }
      
      analyzer.analyzeTree( );
      
      CodeGenerator.generateTMCode( testFile, programNode );
    }
    catch(Exception e){
      System.out.println( e );
    }
  }
}
