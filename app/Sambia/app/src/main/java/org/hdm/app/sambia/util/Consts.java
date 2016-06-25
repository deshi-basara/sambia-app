package org.hdm.app.sambia.util;

/**
 * Created by Hannes on 25.06.2016.
 */
public final class Consts {

    public final static boolean DEBUGMODE = true;


    public final static int CALENDARITEMROW = 1;





    /**
     The caller references the constants using <tt>Consts.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private Consts(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }
}
