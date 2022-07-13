package sg.edu.rp.c346.id20026955.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvData;
    EditText etTasks, etDate;
    ListView lvTasks;
    ArrayAdapter<Task> aa;
    ArrayList<Task> alTask;

    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        lvTasks = findViewById(R.id.lv);
        etTasks = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTasks.getText().toString(), etDate.getText().toString());
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvData.setText(txt);

                alTask = db.getTasks(asc);
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTask);
                lvTasks.setAdapter(aa);
            }
        });
    }
}