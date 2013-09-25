public class WallingfordsOutputClass{
  public static boolean isInt(String num)
 {
  boolean why;
  for(int x = 0; x < num.length(); x = x+1) {
   if (Character.isDigit(num.charAt(x))) {
    why = true;
   } else {
    why = false;
    break;
   }
   if (why) {
    return true;
   }
  }
  return false;
 }
 /*
 public static String printType(String tokenString)
 {
  String type = "";
  String value = "";
  
  if (isReservedPlusOp(tokenString)) {
   type = "PlusOp";
  } else if (isReservedMinusOp(tokenString)) {
   type = "MinusOp";
  } else if(isReservedMultiplyOp(tokenString)) {
   type = "MultiplyOp";
  } else if(isReservedAssignmentOp(tokenString)) {
   type = "AssignmentOp";
  } else if (isReservedLessThanOp(tokenString)) {
   type = "LessThanOp";
  } else if (isReservedOpenParen(tokenString)) {
   type = "OpenParen";
  } else if (isReservedClosedParen(tokenString)) {
   type = "ClosedParen";
  } else if (isReservedIf(tokenString)) {
   type = "If";
  } else if (isReservedThen(tokenString)) {
   type = "Then";
  } else if (isReservedElse(tokenString)) {
   type = "Else";
  } else if (isReservedEndIf(tokenString)) {
   type = "EndIf";
  } else if (isReservedMain(tokenString)) {
   type = "Main";
  } else if (isReservedNot(tokenString)) {
   type = "Not";
  } else if (isReservedOr(tokenString)) {
   type = "Or";
  } else if (isReservedAnd(tokenString)) {
   type = "And";
  } else if (isReservedComment(tokenString)) {
   type = "Comment";
  } else if (isReservedForwardSlash(tokenString)) {
   type = "ForwardSlash";
  } else if (isReservedComma(tokenString)) {
   type = "Comma";
  } else if (isReservedBoolean(tokenString)) {
   type = "Boolean";
  } else if (isReservedTrue(tokenString)) {
   type = "True";
  } else if (isReservedFalse(tokenString)) {
   type = "False";
  } else if (isReservedColon(tokenString)) {
   type = "Colon";
  } else if (isInt(tokenString)) {
   type = "Integer";
   value = tokenString;
  } else if (isReservedPrint(tokenString)) {
   type = "Print";
  } else {
   type = "Identifier";
   value = tokenString;
  }
  

  return type + "      " + tokenString;

 }
 */
}