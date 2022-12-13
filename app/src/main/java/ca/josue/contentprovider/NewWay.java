package ca.josue.contentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewWay extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_way);

        txt1 = (TextView) findViewById(R.id.txtv1);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this::reload);
        btn2.setOnClickListener(this::returnToMain);

        // init LoaderManager
        LoaderManager.getInstance(this).initLoader(1, null, this);
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

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        return new CursorLoader(
                this,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
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

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        txt1.setText("");
    }
}