private boolean isReservedPlusOp(String candidate)
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
        	return false;
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
                        case '+': state++; break;
                        default : state = -1;
                    }
                    break; 
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

}
