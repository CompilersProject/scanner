import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeGenerator
{
  private static int currentLineNumber = 0;
  private static int registerCounter   = 3; // First argument will go here for main
  private static int offsetCounter     = 1;
  
  private static int returnAddress  = 1;
  private static int returnValue    = 2;
  private static int programCounter = 7;
  
  private static String output = "";
  
  public static void generateTMCode( String inputFileName, SemanticAction programNode )
  {
    for( SemanticAction def: programNode.getBranches() ){
      if( def.getName().equals("main") ){
        for( SemanticAction child: def.getBranches() ){
          if( child.getType() == SemanticAction.TYPE.FORMAL ){
            appendRegisterMemory( "LD", registerCounter, offsetCounter, 0 );          
          }
        }
        
        appendRegisterMemory( "LDA", returnAddress, 1, programCounter ); // LDA 1, 1(7)
        appendRegisterMemory( "LDA", programCounter, (currentLineNumber + 3), 0 );
        appendRegisterOnly(   "OUT", returnValue, 0, 0 );
        appendRegisterOnly(   "HALT", 0, 0, 0 );
      }
    }
    
    //recurseTree( programNode );
    writeOutputFile( inputFileName );
  }
  
  private static void recurseTree( SemanticAction node )
  {
    for( SemanticAction child: node.getBranches() ){
      
    }
  }
  
  private static void appendRegisterMemory( String instruction, int r1, int offset, int r2 )
  {
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + offset + "(" + r2 + ")\n";
    currentLineNumber++;
  }
  
  private static void appendRegisterOnly( String instruction, int r1, int r2, int r3 )
  {
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + r2 + ", " + r3 + "\n";
    currentLineNumber++;
  }
  
  private static void writeOutputFile( String outFileName )
  {
    try {
    File file = new File( "abcdefg.tm" );
    BufferedWriter outBuffer = new BufferedWriter( new FileWriter( file ) );
    outBuffer.write( output );
    outBuffer.close();
    }
    catch(Exception e){
      System.out.println( "exception" );
    }
  }
  
  private static String outputFileName( String fileName )
  {
    return fileName + ".tm"; // TODO: create output .tm name
  }
  
  private static void typeCheckCodeGenerator( SemanticAction node, String leftOrRight )
  {
    switch ( node.getType() )
    {
      case DEFINITION:
        if( !node.getName().equals("main") ){
        
      }
        break;
        
      case PRINT:
        break;
        
      case INTEGER:
        break;
    }
  }
}