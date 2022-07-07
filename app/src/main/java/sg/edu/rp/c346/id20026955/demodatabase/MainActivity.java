package sg.edu.rp.c346.id20026955.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvData;
    EditText etTasks, etDate;
    ListView lv;
    ArrayAdapter<Task> aa;
    ArrayList<Task> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        lv = findViewById(R.id.lv);
        etTasks = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTasks.getText().toString(), etDate.getText().toString());
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                //populate TextView
                ArrayList<String> al = db.getTaskContent();
                db.close();

                String data = "";
                for (int i = 0; i < al.size(); i++){
                    Log.d("Database Content", i + ". "+ data.get(i));
                    data += al.get(i) + "\n";
                }
                tvData.setText(data);

                //populate ListView
                alTask = db.getTasks(asc);
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTask);
                lv.setAdapter(aa);
            }
        });


    }
}