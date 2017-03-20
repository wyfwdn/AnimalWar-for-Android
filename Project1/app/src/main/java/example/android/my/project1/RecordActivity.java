package example.android.my.project1;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
/**
 * Created by Administrator on 2017/3/18.
 */

public class RecordActivity extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        final HelperActivity helper = new HelperActivity(this);
        Cursor c = helper.query();
        String[] from = {"name", "wins", "loses", "time"};
        int[] to = {R.id.text0, R.id.text1, R.id.text2, R.id.text3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_record, c, from, to);
        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                final long temp = arg3;
                builder.setMessage("Are you sure to delete this record?").setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                helper.delete((int)temp);
                                Intent intent = new Intent(RecordActivity.this,
                                        RecordActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
                AlertDialog ad = builder.create();
                ad.show();
            }
        });
        helper.close();
    }
}
