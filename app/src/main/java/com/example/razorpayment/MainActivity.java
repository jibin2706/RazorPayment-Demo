package com.example.razorpayment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    Button checkoutButton;
    EditText priceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Preload payment resources
        Checkout.preload(getApplicationContext());

        priceInput = findViewById(R.id.price);
        checkoutButton = findViewById(R.id.checkout_button);


//        click listener on the button
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price = Double.parseDouble((priceInput.getText().toString()));
                startPayment(price);
            }
        });
    }

    private void startPayment(double price) {
        Checkout checkout = new Checkout();
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Merchant Name");
            options.put("description", "Testing payment");
            options.put("currency", "INR");
//            converting rupees to paise
            options.put("amount", price*100);
            checkout.open(activity, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment Successful :)", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DisplayPaymentDetails.class);
        i.putExtra("payment_id", paymentData.getPaymentId());
        i.putExtra("order_id", paymentData.getOrderId());
        i.putExtra("email", paymentData.getUserEmail());
        i.putExtra("contact_no", paymentData.getUserContact());
        startActivity(i);
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment unsuccessful :(", Toast.LENGTH_LONG).show();
    }
}
