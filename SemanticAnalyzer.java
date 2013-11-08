import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SemanticAnalyzer 
{
  SymbolTable symbolTable = new SymbolTable();
  SemanticAction node = new SemanticAction();

  public void makeSymbolTable (SemanticAction node)
  {
    mainCheck( node );
    
    for (int i = 0; i < node.getBranches().length; i++)
    {
      adder(helper(node.getBranches()[i], new ArrayList()), i);
    }
  }
  
  public ArrayList helper (SemanticAction node, ArrayList allElements)
  {
    if (node.getBranches().length==0)
    {
      return allElements;
    }
    
    for (int i = 0; i < node.getBranches().length; i++)
    {
      allElements.add(node.getBranches()[i]);
      allElements.add(helper(node.getBranches()[i], allElements));
    }
    
    return allElements;
  }
  
  public void adder(ArrayList allElements, int col)
  {
    for (int i=0; i<allElements.size(); i++)
    {
      symbolTable.add( allElements.get(i), i, col );
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
}