import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SemanticAnalyzer 
{
  private SymbolTable symbolTable = new SymbolTable();
  private SemanticAction startNode = new SemanticAction();
  private int mainCounter;

  public SemanticAnalyzer( SemanticAction node )
  {
    mainCounter = 0;
    
    startNode = node;
    makeSymbolTable();
  }
  
  private void makeSymbolTable( )
  {
    SemanticAction[] branches = startNode.getBranches();
    
    for (int i = 0; i < branches.length; i++) // Search through defs
    {
      String defName = branches[i].toString();
      if( defName.equals("print") ){
        System.out.println( "User-defined print function not allowed." );
      } else if( defName.equals("main") ){
        mainCounter++;
      }
      
      SemanticAction[] functionArgs = branches[i].getBranches();
      for( int j = 0; j < functionArgs.length; j++ )
      {
        if( functionArgs[j].type == SemanticAction.TYPE.TYPE ||
            functionArgs[j].type == SemanticAction.TYPE.FORMAL )
          symbolTable.put( defName, functionArgs[j] );
      }
    }
    
    
    if( mainCounter < 1 )
      System.out.println( "No main function defined." );
    else if( mainCounter > 1 )
      System.out.println( "Too many main functions defined." );
  }
  
  public SemanticAction analyzeTree( )
  {
    //if( !mainCheck( startNode ) )
    //  System.out.println( "ERROR" );
    
    SemanticAction[] defBranches = startNode.getBranches();
    
    for (int i = 0; i < defBranches.length; i++) // Search through defs
    {
      SemanticAction tmp = defBranches[i].getBranches()[0];
      helper( defBranches[i].getName(), tmp );
    }
    
    return new SemanticAction();
  }
  
  public void helper (String defName, SemanticAction node)
  {
    if (node.getBranches().length==0)
    {
      if(node.getType() == SemanticAction.TYPE.INTEGER ||
         node.getType() == SemanticAction.TYPE.BOOLEAN ){
        return;
      } else {
        if( !symbolTable.compare( defName, node ) ){
          System.out.println( "No formal parameter: " + node + " inside defintion of " + defName );
        }
      }
    } else {
      for (int i = 0; i < node.getBranches().length; i++)
      {
        helper( defName, node.getBranches()[i] );
      }
    }
    
    /*
    for (int i = 0; i < node.getBranches().length; i++)
    {
      //allElements.add(node.getBranches()[i]);
      //allElements.add(helper(node.getBranches()[i], allElements));
    }
    */
    //return allElements;
  }
  
  public void adder(ArrayList allElements, int col)
  {
    for (int i=0; i<allElements.size(); i++)
    {
      //symbolTable.add( allElements.get(i), i, col );
    }
  }

  
    
  public boolean mainCheck (SemanticAction node)
  {
    Set<String> MS = new TreeSet<String>();
    SemanticAction[] branches = node.getBranches();
    
    for (int i = 0; i < branches.length; i++)
    {
      String tmp = branches[i].toString();
      if( !MS.add(tmp) ){
        if( tmp.equals("main") ){
          if( Compiler.extendedDebug)
            System.out.println( "More than one 'main' function found." );
          
          return false;
        }
      }
    }
    if( Compiler.extendedDebug ){
      if( !MS.contains("main") ){
        System.out.println( "No 'main' function found." );
        return false;
      }
      else{
        return true;
      }
    }
    else
      return MS.contains("main");
  }
  
  public SymbolTable getSymbolTable() { return symbolTable; }
}