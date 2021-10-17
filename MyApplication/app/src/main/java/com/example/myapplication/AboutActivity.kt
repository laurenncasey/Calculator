package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val info = findViewById<TextView>(R.id.information)
        info.text = "Thanks for using this calculator! \n We have provided some information on this specific calculators input, as well as input that will cause Error messages. \n\nCalculator Functions:\n•Stats\n\t\t\tGiven user dataset input, provides user with mean, median, trimmed mean, maxiumum, minimum, and summation of dataset.\n•{ }\n\t\t\tAllows user to input a number followed by Enter. Selecting { } after entering dataset values closes the dataset. It can be selected again to view or edit. \n •Xtr(%)\n\t\t\tAllows user to input percentage to use calculating trimmed mean.\n •√\n\t\t\tAllows user to take square root of number inputted after √ .\n •^\n\t\t\tAllows user to raise number inputted to an exponent.\n Any number is used for inputting numbers. Decimals are allowed with the decimal button. You may use the Clear and Enter. All of which follow regular calculator functions. \n\n Errors:\n •√\n\t\t\tInputting an exponent, or negative value will cause error.\n •^\n\t\t\tInputting a square root, negative value, nested exponent or decimal will cause error.\n •{ }\n\t\t\tInputting anything other than a basic number may cause errors.\n •Xtr(%)\n\t\t\tInputting anything other than a whole percentage may cause errors. \n"
        val backBut = findViewById<Button>(R.id.back)

        backBut?.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}