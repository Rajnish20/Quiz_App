package com.example.android.coffeeorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int Quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.Zero_text_view);
        quantityTextView.setText("" + number);

    }

    public void increment(View v) {
        Quantity = Quantity + 1;
        displayQuantity(Quantity);


    }

    public void decrement(View v) {
        if (Quantity > 0) {
            Quantity = Quantity - 1;
            displayQuantity(Quantity);

        }

    }

    public void submitOrder(View v) {
        EditText nameValue = (EditText) findViewById(R.id.Name_Edit_Text);
        String name = nameValue.getText().toString();
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.WhippedCream_check_box);
        boolean hasWhippedCream = WhippedCream.isChecked();
        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_chech_box);
        boolean hasChocolate = Chocolate.isChecked();
        int Price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, Price, hasWhippedCream, hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


     private int calculatePrice(boolean addWhippedCream,boolean addChocolate){
        int Price = 10;
        if(addWhippedCream) {
            Price = Price + 5;
        }
        if(addChocolate){
            Price = Price + 10;
        }
        return (Quantity * Price);
     }

     public String createOrderSummary(String Name,int Price,boolean addWhippedCream,boolean addChocolate){
         String priceMessage= "Order Summary\n";
         priceMessage=priceMessage + "Name " + Name + "\n";
         priceMessage= priceMessage + "Quantity " + Quantity +"\n";
         priceMessage= priceMessage + "Whipped Cream?" + addWhippedCream + "\n";
         priceMessage = priceMessage + "Chocolate?" + addChocolate + "\n";
         priceMessage = priceMessage + "Total " + Price + "\n";
         priceMessage=priceMessage + getString(R.string.thank_you);
         return priceMessage;

     }


}