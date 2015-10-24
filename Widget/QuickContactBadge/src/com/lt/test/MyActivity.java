package com.lt.test;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.QuickContactBadge;

public class MyActivity extends Activity {

    Test<?> test;

    private String [] CONTACT_SUMMARY = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.STARRED,
            ContactsContract.Contacts.TIMES_CONTACTED,
            ContactsContract.Contacts.CONTACT_PRESENCE,
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,

    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String select = "(( " + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL ) AND ("+
                ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1) AND ("+
                ContactsContract.Contacts.DISPLAY_NAME + " != ''))";

        //查询所有联系人
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                CONTACT_SUMMARY,select,null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
                );
        cursor.moveToFirst();
        QuickContactBadge quickContactBadge = (QuickContactBadge) findViewById(R.id.quickContactBadge);
        QuickContactBadge quickContactBadge1 = (QuickContactBadge) findViewById(R.id.quickContactBadge1);

        long contact_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

        //将quickContactBadge1 与 联系人关联
        quickContactBadge.assignContactUri(ContactsContract.Contacts.getLookupUri(contact_id,lookupKey));

        cursor.moveToNext();

        contact_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
        quickContactBadge1.assignContactUri(ContactsContract.Contacts.getLookupUri(contact_id,lookupKey));
    }
}
