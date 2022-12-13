package ca.josue.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.josue.contentprovider.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button oldBtn, newBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        oldBtn = binding.oldBtn;
        newBtn = binding.newBtn;

        oldBtn.setOnClickListener(this::getAllContactsOldWay);
        newBtn.setOnClickListener(this::getAllContactsNewWay);
    }

    private void getAllContactsNewWay(View view) {
        Intent intent = new Intent(this, NewWay.class);
        startActivity(intent);
    }

    private void getAllContactsOldWay(View view) {
        Intent intent = new Intent(this, OldWay.class);
        startActivity(intent);
        finish();
    }
}