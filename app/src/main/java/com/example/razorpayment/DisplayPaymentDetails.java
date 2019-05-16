package com.example.razorpayment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayPaymentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_details);
        Intent i = getIntent();

        String payment_id = i.getStringExtra("payment_id");
        String contact = i.getStringExtra("contact_no");
        String email = i.getStringExtra("email");


        TextView mPaymentText = findViewById(R.id.paymentId);
        TextView mEmailText = findViewById(R.id.email);
        TextView mContactText = findViewById(R.id.contact);

        mPaymentText.setText(payment_id);
        mContactText.setText(contact);
        mEmailText.setText(email);


    }
}
