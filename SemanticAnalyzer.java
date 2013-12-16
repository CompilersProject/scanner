import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SemanticAnalyzer 
{
  private SymbolTable symbolTable = new SymbolTable();
  private SemanticAction startNode = new SemanticAction();
  private int mainCounter;
  private ArrayList<String> errorList = new ArrayList<String>();
    
  public SemanticAnalyzer( SemanticAction node )
  {
    mainCounter = 0;
    
    startNode = node;
    makeSymbolTable();
  }
  
  private void makeSymbolTable( )
  {
    for( SemanticAction def: startNode.getBranches() )
    {
      String defName = def.getName();
      if( defName.equals("print") ){
        errorList.add( "User-defined print function not allowed.\n" );
      } else if( defName.equals("main") ){
        mainCounter++;
      }
      for( SemanticAction defNode: def.getBranches() )
      {
        if( defNode.type == SemanticAction.TYPE.TYPE ||
           defNode.type == SemanticAction.TYPE.FORMAL )
          symbolTable.put( defName, defNode );
      }
    }
    
    
    if( mainCounter < 1 )
      errorList.add( "No main function defined.\n" );
    else if( mainCounter > 1 )
      errorList.add( "Too many main functions defined.\n" );
  }
  
  public void analyzeTree( )
  {
    for( SemanticAction defNode: startNode.getBranches() )
    {
      helper( defNode.getName(), defNode.getBranches().get(0) );
      checkDefReturn( defNode );
    }
  }
  
  public void helper (String defName, SemanticAction node)
  {
    if( Compiler.extendedDebug ){
      System.out.println( node.getName() );
    }
    
    if( node.getBranches().isEmpty() ){
      if(node.getType() == SemanticAction.TYPE.INTEGER ||
         node.getType() == SemanticAction.TYPE.BOOLEAN ){
        return;
      } else {
        if( !symbolTable.compare( defName, node ) ){
          errorList.add( "No formal parameter: " + node + " inside defintion of " + defName + "\n" );
        }
      }
    }
    
    if( node.getType() == SemanticAction.TYPE.FUNCTION &&
       !symbolTable.checkFunctionCall( node.getName() ) ){
      errorList.add( "No function definition for call to: " + node.getName() + "\n" );
    }
    
    for( SemanticAction branch: node.getBranches() )
    {
      helper( defName, branch );
    }
    
    checkTypes( defName, node );
  }

  private void checkTypes( String defName, SemanticAction node )
  {
    switch( node.type ){
      case IF:
        String funcReturnType = symbolTable.getFunctionType( defName );
        String nodeReturnType = node.getBranches().get(0).getReturnType();
        if( !nodeReturnType.equals( "boolean" ) ){
          errorList.add( "If conditional statements expect type boolean. " + node.getBranches().get(0).getName() + " is type " + nodeReturnType ); // ***
        }
        nodeReturnType = node.getBranches().get(1).getReturnType();
        if( !nodeReturnType.equals( funcReturnType ) ){
          errorList.add( "Argument " + node.getBranches().get(1).getName() + " does not match return type of " + node.getName() + ". Expected " + funcReturnType );
        }
        nodeReturnType = node.getBranches().get(2).getReturnType();
        if( !nodeReturnType.equals( funcReturnType ) ){
          errorList.add( "Argument " + node.getBranches().get(1).getName() + " does not match return type of " + node.getName() + ". Expected " + funcReturnType );
        }
        break;
        
      case FUNCTION:
        
        break;

      case IDENTIFIER:
        
        break;
        
      case PRINT:
        // Skip print as it has branches but there is no requirement for their types
        break;
        
      default: // Not a special case, so we should have a return type
        if( node.hasBranches() ){ // TODO: Try without this check, the only nodes with out branches should have already been dealt with.
          String childType = node.getChildType();
          for( SemanticAction child: node.getBranches() ){
            if( child.getType() == SemanticAction.TYPE.IDENTIFIER || child.getType() == SemanticAction.TYPE.FUNCTION ){ // ****** REMOVE AFTER IMPLEMENTING IDENTIFIER TYPE CHECKING *******
              break;
            } // **************************
            if( !child.getReturnType().equals( childType ) ){
              errorList.add( "Argument " + child.getName() + " does not match type of " + node.getName() + ". Expected " + childType );
            }
          }
        }
    }
  }
  
  private void checkDefReturn( SemanticAction defNode ){
    // Skip if statements because they handle matching return values in checkTypes
    if( defNode.getBranches().get(0).getType() == SemanticAction.TYPE.IF ||
        defNode.getType() == SemanticAction.TYPE.DEFINITION ){ // *** REMOVE ***get(0).getType() == SemanticAction.TYPE.IF ){
      return;
    }
    
    String defReturn = defNode.getBranches().get(1).getReturnType();
    String bodyType = defNode.getBranches().get(0).getReturnType();
    if( !defReturn.equals( bodyType ) ){
      errorList.add( "Type mismatch. Function " + defNode.getName() + " does not return type " + bodyType + " expected " + defReturn );
    }
  }

  public SymbolTable getSymbolTable() { return symbolTable; }
  
  public ArrayList<String> showErrors(){
    if (!errorList.isEmpty()) {
      for (int i = 0; i < errorList.size(); i++){
        System.out.println(errorList.get(i));
      }
    }
    return errorList;
  }
}