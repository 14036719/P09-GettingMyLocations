package sg.edu.rp.soi.c347.p09_gettingmylocations;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by 14036719 on 21/7/2017.
 */

public class ChkRecord extends AppCompatActivity {
    TextView tvNo;
    Button btnRefresh;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> al;
    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chkrecord);

        tvNo = (TextView) findViewById(R.id.tvNo);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList<String>();

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/P09";

        File targetFile = new File(folderLocation, "data.txt");

        if (targetFile.exists() == true) {
            String data = "";
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                int i = 0;
                while (line != null) {
                    al.add(line);
                    data += line + "\n";
                    line = br.readLine();
                    i++;
                }
                br.close();
                reader.close();
                tvNo.setText(i);
                aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);
            } catch (Exception e) {
                Toast.makeText(ChkRecord.this, "Failed to read", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            Toast.makeText(ChkRecord.this, data, Toast.LENGTH_SHORT).show();
        }
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File targetFile = new File(folderLocation, "data.txt");
                al.clear();

                if (targetFile.exists() == true) {
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        int i = 0;
                        while (line != null) {
                            al.add(line);
                            data += line + "\n";
                            line = br.readLine();
                            i++;
                        }
                        br.close();
                        reader.close();
                        tvNo.setText(i);
                        aa.notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(ChkRecord.this, "Failed to read", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Toast.makeText(ChkRecord.this, data, Toast.LENGTH_SHORT).show();
                }
                al.clear();
            }
        });
    }
}

