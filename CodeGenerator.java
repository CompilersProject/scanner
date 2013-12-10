import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeGenerator
{
  private static int currentLineNumber = 0;
  private static int registerCounter   = 3; // First argument will go here for main
  private static int memoryCounter     = 1; // Memory offset (consider renaming)
  
  private static int returnAddress        = 1;
  private static int returnValue          = 2;
  private static int leftReturnRegister   = 2;
  private static int rightReturnRegister  = 3;
  private static int leftWorkingRegister  = 4;
  private static int rightWorkingRegister = 5;
  private static int jumpAddressRegister  = 6;
  private static int programCounter       = 7;
  
  private static String output = ""; // Avoid passing around the reference everywhere. All appends should be serial anyway
  
  public static void generateTMCode( String inputFileName, SemanticAction programNode )
  {
    for( SemanticAction def: programNode.getBranches() ){
      if( def.getName().equals("main") ){
        appendCommentFunctName( "RUN-TIME SETUP" ); // ***
        
        for( SemanticAction child: def.getBranches() ){
          if( child.getType() == SemanticAction.TYPE.FORMAL ){
            appendRegisterMemory( "LD", registerCounter, memoryCounter, 0, "Load command-line args" );          
          }
        }
        
        appendRegisterMemory( "LDA", returnAddress, 1, programCounter, "save return address in r1" ); // LDA 1, 1(7) store return address
        appendRegisterMemory( "LDA", programCounter, (currentLineNumber + 3), 0, "jump to main loop" );
        appendRegisterOnly(   "OUT", returnValue, 0, 0 );
        appendRegisterOnly(   "HALT", 0, 0, 0 );
        
        //TODO: move to recursive loop
        
        appendCommentFunctName( "MAIN" ); // ***
        appendRegisterMemory( "ST", returnAddress, memoryCounter, 0 ); // Store return address into offset[0]
        memoryCounter++;
        typeCheckCodeGenerator( def.getBranches().get(0), returnValue );
        memoryCounter--;
        appendRegisterMemory( "LD", programCounter, memoryCounter, 0 ); // Load return address into PC
        
        
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
  // Overloaded to accept comments
  private static void appendRegisterMemory( String instruction, int r1, int offset, int r2, String comment )
  {
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + offset + "(" + r2 + ")\t\t* " + comment + "\n";
    currentLineNumber++;
  }
  
  private static void appendRegisterOnly( String instruction, int r1, int r2, int r3 )
  {
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + r2 + ", " + r3 + "\n";
    currentLineNumber++;
  }
  // Overloaded to accept comments
  private static void appendRegisterOnly( String instruction, int r1, int r2, int r3, String comment )
  {
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + r2 + ", " + r3 + "\t\t* " + comment + "\n";
    currentLineNumber++;
  }
  
  private static void appendComment( String comment )
  {
    output += "* " + comment + "\n";
  }
  
  private static void appendCommentFunctName( String comment)
  {
    output += "*\n* " + comment + "\n*\n";
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
      case ADDITION:
        branchHelperGuy(node);
        appendRegisterOnly( "ADD", branchReturnRegister, leftWorkingRegister, rightReturnRegister );
        break;
        
      case BOOLEAN:
        if( node.getName().equals("true") )
          appendRegisterMemory( "LDC", branchReturnRegister, 1, 0, "Load 'true' ( 1 )" );
        else
          appendRegisterOnly( "MUL", branchReturnRegister, 0, 0, "Load 'false' ( 0 )" );
        break;
        
      case DIVISION:
        branchHelperGuy(node);
        appendRegisterOnly( "DIV", branchReturnRegister, leftWorkingRegister, rightReturnRegister );
        break;
        
      case INTEGER:
        appendRegisterMemory( "LDC", branchReturnRegister, Integer.parseInt( node.getName() ), 0 );
        break;
        
      /*case FUNCTION:
        //appendCommentFunctName( "MAIN" ); // ***
        appendRegisterMemory( "ST", returnAddress, memoryCounter, 0 ); // Store return address into offset[0]
        typeCheckCodeGenerator( node.getBranches().get(0), returnValue );
        appendRegisterMemory( "LD", programCounter, memoryCounter, 0 ); // Load return address into PC
        break;
        */
        
      case MULTIPLICATION:
        branchHelperGuy(node);
        appendRegisterOnly( "MUL", branchReturnRegister, leftWorkingRegister, rightReturnRegister );
        break;
        
      case OR:
        branchHelperGuy(node);
        
        appendRegisterMemory( "LDA", jumpAddressRegister, 4, programCounter, "Store address of true return value" );
        // Because we want true to take precedent over false, check based on truth (greater than 0)
        appendRegisterMemory( "JGT", leftWorkingRegister, 0, jumpAddressRegister );
        appendRegisterMemory( "JGT", rightReturnRegister, 0, jumpAddressRegister );
        appendRegisterOnly( "MUL", branchReturnRegister, 0, 0, "Return false" ); // *Is arithmetic faster than LDC?*
        //appendRegisterOnly( "ADD", programCounter, programCounter, 1, "Skip over false return instruction" ); // Not working?
        appendRegisterMemory( "LDA", programCounter, 1, programCounter, "Skip over return true instruction" );
        appendRegisterMemory( "LDC", branchReturnRegister, 1, 0, "Return true" );
        break;
        
      case PRINT:
        typeCheckCodeGenerator( node.getBranches().get(0), leftReturnRegister ); // Call left branch
        appendRegisterOnly( "OUT", leftReturnRegister, 0, 0 );
        typeCheckCodeGenerator( node.getBranches().get(1), rightReturnRegister ); // Call right branch
        appendRegisterOnly( "ADD", branchReturnRegister, rightReturnRegister, 0 );
        break;
        
      case SUBTRACTION:
        branchHelperGuy(node);
        appendRegisterOnly( "SUB", branchReturnRegister, leftWorkingRegister, rightReturnRegister );
        break;
    }
  }
  
  private static void branchHelperGuy( SemanticAction node ){
    typeCheckCodeGenerator( node.getBranches().get(0), leftReturnRegister ); // Call left branch
    appendRegisterMemory( "ST", leftReturnRegister, memoryCounter, 0, "Store left branch value so it is not overwritten." );
    memoryCounter++;
    typeCheckCodeGenerator( node.getBranches().get(1), rightReturnRegister ); // Call right branch
    memoryCounter--;
    appendRegisterMemory( "LD", leftWorkingRegister, memoryCounter, 0, "Load left branch value back into left working register" );
  }
}