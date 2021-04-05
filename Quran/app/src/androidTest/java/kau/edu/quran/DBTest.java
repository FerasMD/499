package kau.edu.quran;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import junit.framework.TestCase;

public class DBTest extends TestCase {

    public void testInsertInto1() {

        DB db=new DB(  InstrumentationRegistry.getContext());

        db.insertInto1(1,10,"04/04/2021","04/06/2021","الفَاتِحة ",1,1);
        assertEquals(true,true);

    }
}