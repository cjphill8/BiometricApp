package asu.capstone.biometricapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class RegisterActivity extends AppCompatActivity {

    public PersonalInfoDO infoDO;
    public DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Instantiate a AmazonDynamoDBMapperClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        readInfo();

       if (infoDO != null){
            Intent next = new Intent(RegisterActivity.this, BaseActivity.class);
            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(next);
        }

        final Button button = findViewById(R.id.buttonSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonPress();
            }
        });
    }

    public void readInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                infoDO = dynamoDBMapper.load(
                        PersonalInfoDO.class,
                        IdentityManager.getDefaultIdentityManager().getCachedUserID());

                // Item read
                // Log.d("News Item:", newsItem.toString());
            }
        }).start();
    }

    public void buttonPress() {
        View focusView = null;
        boolean cancel = false;
        Double feet = 0.0;

        PersonalInfoDO infoDO = new PersonalInfoDO();

        EditText edit = findViewById(R.id.editFirst);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else
            infoDO.setFirstName(edit.getText().toString());

        edit = findViewById(R.id.editLast);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else
            infoDO.setLastName(edit.getText().toString());

        edit = findViewById(R.id.editAge);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else
            infoDO.setAge(edit.getText().toString());

        edit = findViewById(R.id.editFeet);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else {
            feet = Double.parseDouble(edit.getText().toString());
        }

        edit = findViewById(R.id.editInches);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else
            infoDO.setHeight(12*feet + Double.parseDouble(edit.getText().toString()));

        edit = findViewById(R.id.editWeight);
        if (TextUtils.isEmpty(edit.getText().toString())) {
            edit.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else
            infoDO.setWeight(Double.parseDouble(edit.getText().toString()));

        RadioGroup radio = findViewById(R.id.radioGender);
        if (radio.getCheckedRadioButtonId() == -1) {
            RadioButton radioFem = findViewById(R.id.radioFemale);
            radioFem.setError(getString(R.string.error_field_required));
            focusView = edit;
            cancel = true;
        }
        else if (radio.getCheckedRadioButtonId() == R.id.radioMale)
            infoDO.setGender(false);
        else
            infoDO.setGender(true);


        if (cancel)
            focusView.requestFocus();
        else {
            setInfo(infoDO);

            Intent next = new Intent(RegisterActivity.this, BaseActivity.class);
            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(next);
        }
    }

    public void setInfo(final PersonalInfoDO infoDO) {
        infoDO.setUserId(IdentityManager.getDefaultIdentityManager().getCachedUserID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(infoDO);
                // Item saved
            }
        }).start();
    }
}