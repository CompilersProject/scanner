
public class ReservedAnd {
	
	public static void main( String[] args )
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 3; 
        

        String candidate = "and";
        char   next;
        
        if (candidate.length()!=3){
        	System.out.println("Reject");
        	System.exit(0);
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'd': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            System.out.println( "accept" );
        else
            System.out.println( "reject" );
    }

}
