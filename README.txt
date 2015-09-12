Token Class-

Our Token class constructors hold the values of the type and the character value of that type.
These constructors take string or integer values.
The method getTokenNum retrieves the type of the token. The following methods use a simple
state machine design to check if a certain string is a reserved word. The isInt method 
checks for valid integers between -2^32 through 2^32-1.

Scanner Class-

Contained in the Scanner class is a main function to scan a given file and pick out tokens.
The scanner takes in a file with a scanner and uses a push back input stream to read the data.
When retrieving and creating tokens the getNextToken function checks each byte and decides if it
is part of the grammar's symbol list (defined in isSymbol). If this is the case, a token is created 
with that symbol. If this is NOT the case, each byte is read in until whitespace or a symbol is found
and stored into a variable to return as a token.

Compiling-

in compiler/src/ directory, run
	$ javac Scanner.java

Running- 

The main exists in the Scanner.java and will be executed when running the file.
The main also runs the test file automatically.

in compiler/src/ directory, run
	$ java Scanner.java

