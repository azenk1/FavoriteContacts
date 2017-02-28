package com.example.owner.favoritecontacts;
/**
 * Author: Al Zenk
 * Date: 02/27/2017
 * Class: CIS 3334
 * Project: CIS 3334 assignment 6 Intents
 * Program: Favorite Contacts
 * Description: Program introducing the student to the concept of intents. Allows user to call or text
 * three of their favorite contacts.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Arrays to store and access string-array resources from strings.xml
    String[] contacts;
    String[] numbers;
    //TextView declarations
    TextView firstTV;
    TextView secondTV;
    TextView thirdTV;

    //Button Declarations
    Button daveCallButton;
    Button alCallButton;
    Button alyssaCallButton;
    Button daveHiButton;
    Button alHiButton;
    Button alyssaHiButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Array to access and store string-array of contacts.
        contacts = getResources().getStringArray(R.array.contacts);

        //Array to access and store string-array of contacts
        numbers = getResources().getStringArray(R.array.numbers);

        //String to provide clear formatting
        String numberPrefix = getString(R.string.number);

        //TextViews to display each contact and their associatied phone number
        firstTV = (TextView)findViewById(R.id.firstContactTextView);
        secondTV = (TextView)findViewById(R.id.secondContactTextView);
        thirdTV = (TextView)findViewById(R.id.thirdContactTextView);

        //Setting Text with toString method.
        firstTV.setText(toString(0));
        secondTV.setText(toString(1));
        thirdTV.setText(toString(2));

        //Attributing views to button variables.
        daveCallButton = (Button)findViewById(R.id.daveCallButton);
        alCallButton = (Button)findViewById(R.id.alCallButton);
        alyssaCallButton = (Button)findViewById(R.id.alyssaCallButton);
        daveHiButton = (Button)findViewById(R.id.daveHiButton);
        alHiButton = (Button)findViewById(R.id.alHiButton);
        alyssaHiButton = (Button)findViewById(R.id.alyssaHiButton);
    }

    //onClick method for call intent Passes number string from string-array
    public void callButton(View view)
    {

        //Determine which number to pass by checking view id.
        switch(view.getId())
        {
            case R.id.daveCallButton:
                dialNumber(numbers[0]);
                break;

            case R.id.alCallButton:
                dialNumber(numbers[1]);
                break;

            case R.id.alyssaCallButton:
                dialNumber(numbers[2]);
                break;
        }

    }

    //Determine which number to text by checking view id.
    public void hiClick(View view)
    {
        switch(view.getId())
        {
            case R.id.daveHiButton:
                textNumber(numbers[0]);
                break;

            case R.id.alHiButton:
                textNumber(numbers[1]);
                break;

            case R.id.alyssaHiButton:
                textNumber(numbers[2]);
                break;
        }
    }

    //Method utilizing ACTION_DIAL Intent. Open dialer with number typed in, but do not auto call.
    public void dialNumber(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }

    //Text specified contact by number passed as parameter.
    public void textNumber(String phoneNumber)
    {

        //Create intent
        Intent textIntent = new Intent(Intent.ACTION_VIEW);

        //Set data based on phone number passed.
        textIntent.setData(Uri.parse("sms:" + phoneNumber));
        textIntent.putExtra("sms_body", "Hi.");

        //Determine if the intent can be utilized.
        if (textIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(textIntent);
        }
    }


    /**
     *
     * @param contact - Index to determine which name and number to use
     * @return - returns string with appropriate information and format.
     */
    public String toString(int contact)
    {
        String contactInfo = contacts[contact] + "'s " + getString(R.string.number) + " " + numbers[contact];

        return contactInfo;
    }
}
