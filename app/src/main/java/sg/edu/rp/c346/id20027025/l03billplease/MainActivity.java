package sg.edu.rp.c346.id20027025.l03billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText amount;
        EditText num_pax;
        ToggleButton toggle_svs;
        ToggleButton toggle_gst;
        EditText discount;
        Button split;
        Button reset;
        TextView total_bill;
        TextView each_pay;
        RadioGroup method_pay;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount_input);
        num_pax = findViewById(R.id.num_of_pax_input);
        toggle_svs = findViewById(R.id.toggle_svs);
        toggle_gst = findViewById(R.id.toggle_gst);
        discount = findViewById(R.id.discount_input);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        total_bill = findViewById(R.id.total_bill_view);
        each_pay = findViewById(R.id.each_pay);
        method_pay = findViewById(R.id.method_pay);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringAmount = amount.getText().toString();
                String stringNumPax = num_pax.getText().toString();
                if (stringAmount.isEmpty() || stringNumPax.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please input the amount/ number of people",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (amount.getText().toString().trim().length() != 0 && num_pax.getText().toString().trim().length() != 0) {
                        double newAmt = 0.0;
                        if (!toggle_svs.isChecked() && !toggle_gst.isChecked()) {
                            newAmt = Double.parseDouble(amount.getText().toString());
                        } else if (toggle_svs.isChecked() && !toggle_gst.isChecked()) {
                            newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                        } else if (!toggle_svs.isChecked() && toggle_gst.isChecked()) {
                            newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                        } else {
                            newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                        }


                        if (discount.getText().toString().trim().length() != 0) {
                            newAmt += 1 - Double.parseDouble(discount.getText().toString()) / 100;
                        }

                        total_bill.setText("Total Bill; $" + String.format("%.2f", newAmt));


                        int numPerson = Integer.parseInt(num_pax.getText().toString());
                        int checkPayMethod = method_pay.getCheckedRadioButtonId();
                        if (numPerson != 0) {
                            if (checkPayMethod == R.id.method_pay_cash) {
                                String textCash = " in cash.";
                                each_pay.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + textCash);
                            } else {
                                String textPayNow = " via PayNow to 912345678";
                                each_pay.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + textPayNow);
                            }
                        } else {
                            each_pay.setText("Negative number entered. Please try again.");
                        }

                    }
                }

            }

        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText(" ");
                num_pax.setText(" ");
                toggle_svs.setChecked(false);
                toggle_gst.setChecked(false);
                discount.setText(" ");
            }
        });

    }
}