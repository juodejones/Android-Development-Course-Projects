package com.jones.contactmanager;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jones.contactmanager.data.Contact;
import com.jones.contactmanager.model.DatabaseHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        db.addContact(new Contact("Jeremy", "9578631489"));
//        db.addContact(new Contact("David", "8647531616"));
//        db.addContact(new Contact("Jason", "7356654135"));

        Contact c = db.getContact(4);
        db.deleteContatct(c);

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.d("Contacts", "onCreate: " + contact.getName());
        }

//        c.setName("NewJeremy");
//
//        db.updateContact(c);

//        Log.d("Contacts", "onCreate: " + c.getName());
//
    }








}