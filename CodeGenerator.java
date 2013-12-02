import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeGenerator
{
  private static int currentLineNumber = 0;
  private static int registerCounter   = 3; // First argument will go here for main
  private static int offsetCounter     = 1; // Memory offset (consider renaming)
  
  private static int returnAddress             = 1;
  private static int returnValue               = 2;
  private static int leftReturnRegister  = 2;
  private static int rightReturnRegister = 3;
  private static int leftWorkingRegister       = 4;
  private static int rightWorkingRegister      = 5;
  // 6?
  private static int programCounter            = 7;
  
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
        
        // *** RUN-TIME SETUP ***
        appendRegisterMemory( "LDA", returnAddress, 1, programCounter ); // LDA 1, 1(7) store return address
        appendRegisterMemory( "LDA", programCounter, (currentLineNumber + 3), 0 );
        appendRegisterOnly(   "OUT", returnValue, 0, 0 );
        appendRegisterOnly(   "HALT", 0, 0, 0 );
        
        // *** MAIN ***    TODO: move to recursive loop
        appendRegisterMemory( "ST", returnAddress, offsetCounter, 0 ); // Store return address into offset[0]
        typeCheckCodeGenerator( def.getBranches().get(0), returnValue );
        appendRegisterMemory( "LD", programCounter, offsetCounter, 0 ); // Load return address into PC
        
        break;
      }
    }

    writeOutputFile( outputFileName(inputFileName) );
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
    File file = new File( outFileName );
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
    return fileName.substring(0, fileName.indexOf('.')) + ".tm";
  }
  
  private static void typeCheckCodeGenerator( SemanticAction node, int branchReturnRegister )
  {
    switch ( node.getType() )
    {
      case PRINT:
        typeCheckCodeGenerator( node.getBranches().get(0), leftReturnRegister ); // Call left branch
        appendRegisterOnly( "OUT", leftReturnRegister, 0, 0 );
        typeCheckCodeGenerator( node.getBranches().get(1), rightReturnRegister ); // Call right branch
        appendRegisterOnly( "ADD", branchReturnRegister, rightReturnRegister, 0 );
        break;
        
      case INTEGER:
        appendRegisterMemory( "LDC", branchReturnRegister, Integer.parseInt( node.getName() ), 0 );
        break;
        
      case ADDITION:
        typeCheckCodeGenerator( node.getBranches().get(0), leftReturnRegister ); // Call left branch
        appendRegisterOnly( "ADD", leftWorkingRegister, leftReturnRegister, 0 );
        typeCheckCodeGenerator( node.getBranches().get(1), rightReturnRegister ); // Call right branch
        // OPTIMIZED
        appendRegisterOnly( "ADD", branchReturnRegister, leftWorkingRegister, rightReturnRegister );
        break;
    }
  }
}