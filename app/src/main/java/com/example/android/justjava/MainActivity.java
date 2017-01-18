package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checkbox = (CheckBox)findViewById(R.id.checkbox_meat);
        boolean hasCheaked = checkbox.isChecked();
        CheckBox checkbox1 = (CheckBox)findViewById(R.id.checkbox_meat1);
        boolean hasCheaked1 = checkbox1.isChecked();
        EditText name = (EditText)findViewById(R.id.album_description_view);
        String Name = name.getText().toString();
        display(quantity);

        String A= createOrderSummary(Name,quantity,hasCheaked,hasCheaked1);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);//not required 'coz we don't Want add feild already written
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java Order For "+ Name );
        intent.putExtra(Intent.EXTRA_TEXT,A);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(A);
    }

    public int calculatePrice(boolean hasChecked,boolean hasChecked1){
        int baseCase = 5;
        if(hasChecked)
            baseCase+=1;
        if (hasChecked1)
            baseCase+=2;

      return baseCase*quantity;

    }

    public String createOrderSummary(String Name,int x,boolean haschecked,boolean haschecked1){
        return getString(R.string.order_summary_name,Name)+
                "\nAdd Whipped Cream ?  "+
                haschecked + "\nAdd Chocolate? "+
                haschecked1 +"\nQuantity :"+ x +"\n"+"Total : $"+
                calculatePrice(haschecked,haschecked1) +"\n"+ getString(R.string.thank_you);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
    public void increment(View view){

        if (quantity==100){
            Toast.makeText(this,"Maxi no. of Coffees is 100!!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view){

        if (quantity==1){
            Toast.makeText(this,"Mini ni. of Coffees is 1!!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
}