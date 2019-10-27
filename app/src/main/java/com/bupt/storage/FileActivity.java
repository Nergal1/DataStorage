package com.bupt.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editname;
    private EditText editdetail;
    private Button btnsave;
    private Button btnclean;
    private Button btnread;
    private Button btnsave_app;
    private Button btndelete_app;
    private Button btnread_app;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mContext = getApplicationContext();
        bindViews();
    }

    private void bindViews() {
        editname = (EditText) findViewById(R.id.edittitle);
        editdetail = (EditText) findViewById(R.id.editdetail);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnclean = (Button) findViewById(R.id.btnclean);
        btnread = (Button) findViewById(R.id.btnread);
        btnsave_app = (Button) findViewById(R.id.btnsave_app);
        btndelete_app = (Button) findViewById(R.id.btndelete_app);
        btnread_app = (Button) findViewById(R.id.btnread_app);

        btnsave.setOnClickListener(this);
        btnclean.setOnClickListener(this);
        btnread.setOnClickListener(this);
        btnsave_app.setOnClickListener(this);
        btndelete_app.setOnClickListener(this);
        btnread_app.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String filename = editname.getText().toString();
        String filedetail = editdetail.getText().toString();
        FileUtils fileUtils = FileUtils.getInstance();
        switch (v.getId()){
            case R.id.btnclean:
                try {
                    fileUtils.deleteFileFormSD(getApplicationContext(),filename);
                    Toast.makeText(v.getContext(),"删除文件成功:/storage/emulated/0/"+filename,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnsave:
                try
                {
                    fileUtils.savaFileToSD(v.getContext(),filename, filedetail);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnread:
                String detail = "";
                try
                {
                    String filename2 = editname.getText().toString();
                    detail = fileUtils.readFromSD(filename2);
                }
                catch(IOException e){e.printStackTrace();}
                Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btndelete_app:

                try {
                    fileUtils.deleteFromApp(getApplicationContext(),filename);
                    //    /data/user/0  = /data/data/
                    Toast.makeText(v.getContext(),"删除文件成功:/data/data/con.bupt.storage/files/"+filename,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnsave_app:

                FileUtils sdHelper = FileUtils.getInstance();
                try
                {
                    sdHelper.saveToApp(v.getContext(),filename, filedetail);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnread_app:
                String detail1 = "";
                try
                {
                    String filename2 = editname.getText().toString();
                    detail1 = fileUtils.readFromApp(v.getContext(),filename2);
                }
                catch(IOException e){e.printStackTrace();}
                Toast.makeText(getApplicationContext(), detail1, Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
