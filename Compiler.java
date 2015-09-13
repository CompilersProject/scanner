import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;


public class Compiler {
  // No good compile-time code stripping for Java
  // Any if statements with static final variables should be evaluated at compile time
  // So this should work without unnecessary overhead
  // NOTE: I'm not sure if this is true of non-final vars
  public static boolean extendedDebug;
  
  public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
  {
    try{
      String testFile;
      if( args.length != 0 ){
        testFile = args[0];
        if( args.length > 1 && args[1].equals("-d") ){
          extendedDebug = true;
        } else {
          extendedDebug = false;
        }
      }
      else{
        System.out.println("Please provide a .kln file to compile.");
        return;
      }
      
      SemanticAnalyzer analyzer;
      Scanner test = new Scanner( testFile );
      TableDrivenParser tdp = new TableDrivenParser( test );
      
      tdp.parseProgram();
      
      SemanticAction programNode = tdp.getProgramNode();
      
      analyzer = new SemanticAnalyzer( programNode );
      
//      analyzer.showErrors();
      
      if(extendedDebug == true) {
        for( String defName: analyzer.getSymbolTable().getTable().keySet() )
       {
       System.out.println( defName + ": " + analyzer.getSymbolTable().getTable().get( defName ).toString() );
       }
      }
      
      analyzer.analyzeTree( );
      
      if (analyzer.showErrors().isEmpty()){
       CodeGenerator.generateTMCode( testFile, programNode, analyzer.getSymbolTable() );
      }

    }
    catch(Exception e){
      System.out.println( e );
    }
  }
}
