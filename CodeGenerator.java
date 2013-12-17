import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

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
  
  private static Map<String, Integer> memoryMap = new HashMap<String, Integer>();
  private static String currentDefName = "";
  private static ArrayList<String> functionCallList = new ArrayList<String>();
  private static ArrayList<String> functionReturnList = new ArrayList<String>();
  
  private static SymbolTable symbolTable;
  
  public static void generateTMCode( String inputFileName, SemanticAction programNode, SymbolTable st )
  {
    symbolTable = new SymbolTable(st);
    
    for( SemanticAction def: programNode.getBranches() ){
      for( SemanticAction child: def.getBranches() ){
        if( child.getType() == SemanticAction.TYPE.FORMAL ){
          hashHelp( def.getName(), child.getName() );
        }
      }
    }
    
    for( SemanticAction def: programNode.getBranches() ){
      if( def.getName().equals("main") ){
        appendCommentFunctName( "RUN-TIME SETUP" ); // ***
        /*
        for( SemanticAction child: def.getBranches() ){
          if( child.getType() == SemanticAction.TYPE.FORMAL ){
            hashHelp( def.getName(), child.getName() );
          }
        }*/
        
        appendRegisterMemory( "LDA", returnAddress, 1, programCounter, "save return address in r1" ); // LDA 1, 1(7) store return address
        appendRegisterMemory( "LDA", programCounter, (currentLineNumber + 3), 0, "jump to main loop" );
        appendRegisterOnly(   "OUT", returnValue, 0, 0 );
        appendRegisterOnly(   "HALT", 0, 0, 0 );

        //TODO: move to recursive loop
        
        currentDefName = def.getName();
        appendCommentFunctName( currentDefName );
        memoryMap.put( new String(currentDefName), new Integer(currentLineNumber) );
        memoryCounter++;
        memoryMap.put( currentDefName + "/return", memoryCounter );
        appendRegisterMemory( "ST", returnAddress, memoryCounter, 0 ); // Store return address into offset[0]
        memoryCounter++;
        typeCheckCodeGenerator( def.getBranches().get(0), returnValue );
        memoryCounter--;
        int returnLocation = memoryMap.get( currentDefName + "/return" );
        appendRegisterMemory( "LD", programCounter, returnLocation, 0 ); // Load return address into PC
//appendRegisterMemory( "LD", programCounter, memoryCounter, 0 ); // Load return address into PC
        memoryCounter--;
        break;
      }
    }

    for( SemanticAction def: programNode.getBranches() ){
      if( !def.getName().equals("main") ){
        /*
        for( SemanticAction child: def.getBranches() ){
          if( child.getType() == SemanticAction.TYPE.FORMAL ){
            hashHelp( def.getName(), child.getName() );
          }
        }*/
        
        currentDefName = def.getName();
        appendCommentFunctName( currentDefName ); 
        memoryMap.put( new String(currentDefName), new Integer(currentLineNumber)  );
        memoryCounter++;
        typeCheckCodeGenerator( def.getBranches().get(0), returnValue );
        //memoryCounter--;
        int returnLocation = memoryMap.get( def.getName() + "/return" );
        functionReturnList.add( currentLineNumber + ": LD " + programCounter + ", " + returnLocation + "(0) \t\t\t* Magic\n" );
        currentLineNumber++;
//appendRegisterMemory( "LD", programCounter, memoryCounter, 0, "Load return address" );
        memoryCounter++;
      }
    }
    
    // Write jumps after we have all the information necessary
    for( String x: functionCallList ){
      String[] y = x.split("/");
      String lineNumber = y[0];
      String functionName = y[1];
      String comment = y[2];
      
      int functionAddress = memoryMap.get( functionName );
      appendComment( "Start of function call addresses" );
      output += lineNumber + ": LDA " + jumpAddressRegister + ", " + functionAddress + "(0) \t\t\t*" + comment + "\n";
    }
    
    for( String y: functionReturnList ){
      appendComment( "Start return address magic" );
      output += y;
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
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + offset + "(" + r2 + ")\t\t\t* " + comment + "\n";
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
    output += currentLineNumber + ": " + instruction + " " + r1 + ", " + r2 + ", " + r3 + "\t\t\t* " + comment + "\n";
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
        
      case AND:
        branchHelperGuy(node);
        
        appendRegisterMemory( "LDA", jumpAddressRegister, 4, programCounter, "Store address of false return value" );
        appendRegisterMemory( "JEQ", leftWorkingRegister, 0, jumpAddressRegister );
        appendRegisterMemory( "JEQ", rightReturnRegister, 0, jumpAddressRegister );
        appendRegisterMemory( "LDC", branchReturnRegister, 1, 0, "Return true" ); // *Is arithmetic faster than LDC?*
        appendRegisterMemory( "LDA", programCounter, 1, programCounter, "Skip over return false instruction" );
        appendRegisterOnly( "MUL", branchReturnRegister, 0, 0, "Return false" );
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
        
      case EQUALS:
        branchHelperGuy(node);
        
        appendRegisterMemory( "LDA", jumpAddressRegister, 4, programCounter, "Store address of false return value" );
        
        appendRegisterOnly( "SUB", leftWorkingRegister, leftWorkingRegister, rightReturnRegister, "Difference of operands (a = b -> a - b)" );
        appendRegisterMemory( "JEQ", leftWorkingRegister, 0, jumpAddressRegister );
        
        appendRegisterOnly( "MUL", branchReturnRegister, 0, 0, "Return false" );
        appendRegisterMemory( "LDA", programCounter, 1, programCounter, "Skip over return true instruction" );
        appendRegisterMemory( "LDC", branchReturnRegister, 1, 0, "Return true" ); // *Is arithmetic faster than LDC?*
        break;
        
      case IDENTIFIER:
        int identifierMemoryLocation = memoryMap.get( currentDefName + "/" + node.getName() );
        appendRegisterMemory( "LD", branchReturnRegister, identifierMemoryLocation, 0, ("Load variable: " + node.getName()) );
        break;
        
      case IF:
        appendComment( "Start If" );
        typeCheckCodeGenerator( node.getBranches().get(0), leftReturnRegister );
        appendComment( "End If: value of conditional stored in register " + leftReturnRegister );
        
        branchHelperGirl( node.getBranches().get(1), node.getBranches().get(2), branchReturnRegister );
        
        break;
        
      case INTEGER:
        appendRegisterMemory( "LDC", branchReturnRegister, Integer.parseInt( node.getName() ), 0 );
        break;
        
      case LESSTHAN:
        branchHelperGuy(node);
        
        appendRegisterMemory( "LDA", jumpAddressRegister, 4, programCounter, "Store address of false return value" );
        
        appendRegisterOnly( "SUB", leftWorkingRegister, leftReturnRegister, rightReturnRegister, "Difference of operands (a < b -> a - b)" );
        appendRegisterMemory( "JGE", leftWorkingRegister, 0, jumpAddressRegister );
        
        appendRegisterMemory( "LDC", branchReturnRegister, 1, 0, "Return true" ); // *Is arithmetic faster than LDC?*
        appendRegisterMemory( "LDA", programCounter, 1, programCounter, "Skip over return false instruction" );
        appendRegisterOnly( "MUL", branchReturnRegister, 0, 0, "Return false" );
        break;
        
      case FUNCTION:
        // Save variables
        appendComment( "Calculate arguments for " + node.getName() );
        int argCounter = 1;
        for( SemanticAction arg: node.getBranches() ){
          typeCheckCodeGenerator( arg, branchReturnRegister );
          String argName = symbolTable.getFormalNamez( node.getName(), argCounter );
          int formalMemoryLocation = memoryMap.get( node.getName() + "/" + argName );
          appendRegisterMemory( "ST", branchReturnRegister, formalMemoryLocation, 0, "Save argument number " + argCounter );
          argCounter++;
        }
        memoryCounter--;
        appendRegisterMemory( "LDA", returnAddress, 3, programCounter, "Save branch return address" );
        //memoryCounter++;
        memoryMap.put( node.getName() + "/return", memoryCounter );
        appendRegisterMemory( "ST", returnAddress, memoryCounter, 0, "Save branch return address" ); // Store return address into offset[0]
        memoryCounter++;
        
        addFunctionJump( currentLineNumber, node.getName(), "Store address of function " + node.getName() );
        currentLineNumber++;
        appendRegisterMemory( "LDA", programCounter, 0, jumpAddressRegister, "Jump to " + node.getName() );
        
        //typeCheckCodeGenerator( node.getBranches().get(0), branchReturnRegister );
        
        //memoryCounter--;
        //appendRegisterMemory( "LD", programCounter, memoryCounter, 0 ); // Load return address into PC*/
        break;
        
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
        //appendRegisterOnly( "ADD", programCounter, programCounter, 1, "Skip over false return instruction" ); // TODO: Not working?
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
    memoryCounter++;
    appendRegisterMemory( "ST", leftReturnRegister, memoryCounter, 0, "Store left branch value so it is not overwritten." );
    memoryCounter++;
    typeCheckCodeGenerator( node.getBranches().get(1), rightReturnRegister ); // Call right branch
    memoryCounter--;
    appendRegisterMemory( "LD", leftWorkingRegister, memoryCounter, 0, "Load left branch value back into left working register" );
    memoryCounter--;
  }
  
  // This is for if
  private static void branchHelperGirl( SemanticAction leftNode, SemanticAction rightNode, int branchReturnRegister ){
    int tmpLineNumber = currentLineNumber;
    currentLineNumber += 2;
    
    typeCheckCodeGenerator( leftNode, branchReturnRegister );
    
    int elseLineNumber = currentLineNumber + 1;
    currentLineNumber = tmpLineNumber;
    appendRegisterMemory( "LDC", jumpAddressRegister, elseLineNumber, 0, "Load else line number" );
    appendRegisterMemory( "JEQ", leftReturnRegister, 0, jumpAddressRegister, "Jump to else" );
    
    tmpLineNumber = elseLineNumber - 1;
    currentLineNumber = elseLineNumber;
    typeCheckCodeGenerator( rightNode, branchReturnRegister );
    
    int wallingfordsTmpNumber = currentLineNumber;
    currentLineNumber = tmpLineNumber;
    appendRegisterMemory( "LDC", programCounter, wallingfordsTmpNumber, 0, "End of 'then' statement, so skip 'else' and return value in register 2" );
    currentLineNumber = wallingfordsTmpNumber;
  }
  
  private static void hashHelp( String defName, String idName ){
    String key = defName + "/" + idName;
    //memoryCounter++; ?
    memoryMap.put( key, new Integer(memoryCounter) );
    memoryCounter++;
  }
  
  private static void addFunctionJump( int lineNumber, String functionName, String comment ){
    functionCallList.add( lineNumber + "/" + functionName + "/" + comment );
  }
}