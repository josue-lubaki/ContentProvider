package ca.josue.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OldWay extends AppCompatActivity {

    private TextView txt1;
    private final String[] projection = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_way);


        txt1 = (TextView) findViewById(R.id.txtv1);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this::reload);
        btn2.setOnClickListener(this::returnToMain);

        getAllContacts();
    }

    private void returnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void reload(View view) {
        finish();
        startActivity(getIntent());
    }

    private void getAllContacts() {

        // ask for permission to read contacts
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            return;
        }

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, null, null, null);

        if(cursor == null || cursor.getCount() == 0){
            txt1.setText("No Contacts in device");
            return;
        }

        if (cursor.getCount() > 0) {

            StringBuilder stringBuilder = new StringBuilder("");

            while (cursor.moveToNext()) {
                stringBuilder
                        .append(cursor.getString(0))
                        .append(" -> ")
                        .append(cursor.getString(1))
                        .append(" -> ")
                        .append(cursor.getString(2))
                        .append("\n");
            }
            txt1.setText(stringBuilder.toString());
        }
    }
}