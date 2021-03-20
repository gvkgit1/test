package com.example.osos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osos.OnBindCallback;
import com.example.osos.RecyclerviewItemAdapter;
import com.example.osos.RecyclerviewItemAdapternew;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Button ok,cancel,b;
    EditText Title;

    RecyclerView recyclerView;
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    List<getdata> itemsList;

    public OnBindCallback onBind;

    int PICK_IMAGE_MULTIPLE = 1;
    ArrayList<getdatanew> mArrayUri;
    RecyclerView recyclerView1;
    RecyclerviewItemAdapternew recyclerviewItemAdapternew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=findViewById(R.id.floatingActionButton2);

        itemsList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        101);


            }
        }



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder= new AlertDialog.Builder(MainActivity.this);
                View view =getLayoutInflater().inflate(R.layout.title,null);
                builder.setView(view);
                Title=view.findViewById(R.id.editTextTextPersonName);
                ok=view.findViewById(R.id.button);
                cancel=view.findViewById(R.id.button2);

                alertDialog=builder.create();
                alertDialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        getdata items = new getdata(Title.getText().toString());
                        itemsList.add(items);

                        alertDialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


            }
        });

        recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerviewItemAdapter);


        RecyclerviewItemAdapter.onBind = (viewHolder, position) -> {
            viewHolder.itemView.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mArrayUri = new ArrayList<>();

                    recyclerView1=viewHolder.itemView.findViewById(R.id.recyclerview1);
                    Intent intent = new Intent();

                    intent.setType("image/*");

                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pick Picture"), PICK_IMAGE_MULTIPLE);

                }
            });
        };
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            if (data.getClipData() != null) {
                // ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    getdatanew items = new getdatanew(imageurl);
                    mArrayUri.add(items);
                    recyclerviewItemAdapternew = new RecyclerviewItemAdapternew(mArrayUri);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
                    recyclerView1.setLayoutManager(layoutManager);
                    recyclerView1.setAdapter(recyclerviewItemAdapternew);

                }

            } else {
                Uri imageurl = data.getData();

                getdatanew items = new getdatanew(imageurl);
                mArrayUri.add(items);
                recyclerviewItemAdapternew = new RecyclerviewItemAdapternew(mArrayUri);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(recyclerviewItemAdapternew);

            }
        } else {
            Toast.makeText(this, "No Image Picked", Toast.LENGTH_LONG).show();
        }
    }



}