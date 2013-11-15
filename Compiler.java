import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;


public class Compiler {
  // No good compile-time code stripping for Java
  // Any if statements with static final variables should be evaluated at compile time
  // So this should work without unnecessary overhead
  public static final boolean extendedDebug = false;
  
  public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
  {
    try{
      String testFile;
      if( args.length != 0 ){
        testFile = args[0];
      }
      else{
        testFile = "klein-programs\\tests02-parser\\05-if-expression.kln";
      }
      SemanticAnalyzer analyzer;
      Scanner test = new Scanner( testFile );
      TableDrivenParser tdp = new TableDrivenParser( test );
      
      tdp.parseProgram();
      
      analyzer = new SemanticAnalyzer( tdp.getSemanticNode() );
      for( String defName: analyzer.getSymbolTable().getTable().keySet() )
      {
        System.out.println( defName + ": " + analyzer.getSymbolTable().getTable().get( defName ).toString() );
      }
      analyzer.analyzeTree( );
    }
    catch(Exception e){
      System.out.println( e );
    }
  }
}
