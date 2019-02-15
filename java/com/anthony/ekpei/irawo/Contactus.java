package com.anthony.ekpei.irawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contactus extends AppCompatActivity {
    private Toolbar mtoolbar;
    private ProgressDialog mpro;
    private Button mbut;
    private EditText meditext1;
    private EditText medit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

       meditext1=(EditText) findViewById(R.id.editText);
        medit2=(EditText) findViewById(R.id.editText2);
        mbut=(Button) findViewById(R.id.button);
        mtoolbar=(Toolbar) findViewById(R.id.main_pageappbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Contact Us");
        mpro= new ProgressDialog(Contactus.this);
        
        
mbut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        passigmethod();
    }
});        


    }

    private void passigmethod() {

        String email = meditext1.getText().toString();
        String message = medit2.getText().toString();

        if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(message)) {
            mpro.setCanceledOnTouchOutside(false);
            mpro.setMessage("please wait.....");
            mpro.show();

            String[] TO= {"anthony4arsenal@gmail.com"};
            Intent emailintent=new Intent(Intent.ACTION_SEND);
            emailintent.setData(Uri.parse("mailto:"));
            emailintent.setType("text/plain");
            emailintent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailintent.putExtra(Intent.EXTRA_SUBJECT, "Message");
            emailintent.putExtra(Intent.EXTRA_TEXT, email +"\n" + message);

            try {
                startActivity(Intent.createChooser(emailintent,"Sending message....."));
                finish();
            }catch (Exception e){
                Toast.makeText(this,"you don't have any email client",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,"Empty boxes",Toast.LENGTH_LONG).show();
        }
        }
    }