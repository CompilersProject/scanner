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
  }

  public void adder(ArrayList allElements, int col)
  {
    for (int i=0; i<allElements.size(); i++)
    {
      //symbolTable.add( allElements.get(i), i, col );
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