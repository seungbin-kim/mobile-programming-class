package kr.co.term_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DiagramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DiagramView(this));
    }

}