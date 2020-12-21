package com.example.firebasenotificationpushnotification.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.firebasenotificationpushnotification.R;
import com.example.firebasenotificationpushnotification.model.NotificationReq;
import com.example.firebasenotificationpushnotification.model.NotificationResponce;
import com.example.firebasenotificationpushnotification.network.NotificationRequest;
import com.example.firebasenotificationpushnotification.network.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.firebasenotificationpushnotification.network.Constants.BASE_URL;

public class SendNotificationActivity extends AppCompatActivity {
    private EditText title,description;
    private Button send;
    private DialogPlus dialogPlus;
     DatabaseReference reference;
    private String id;
    private ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        id = getIntent().getStringExtra("id");

       String key1 =getIntent().getStringExtra("key1");
        String key2= getIntent().getStringExtra("key2");

        Toast.makeText(this, "key1"+key1+" "+"key2:"+key2, Toast.LENGTH_SHORT).show();


        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        send = findViewById(R.id.send);
        progress_bar = findViewById(R.id.progress_bar);


        reference = FirebaseDatabase.getInstance().getReference().child("Notification");

        //dialouge create
        dialogPlus = DialogPlus.newDialog(this)
                .setMargin(50,0,50,0)
                .setContentHolder(new ViewHolder(R.layout.dialog_notification_type))
                .setGravity(Gravity.CENTER)
                .setExpanded(false)
                .create();
        //dialouge button get
        LinearLayout layout = (LinearLayout)dialogPlus.getHolderView();
        Button restApi = layout.findViewById(R.id.rest_api);
        Button cloudFunction = layout.findViewById(R.id.cloud_funcation);

        //notification call by rest api
        restApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
                progress_bar.setVisibility(View.VISIBLE);

                Map<String ,Object>map=new HashMap<>();
                map.put("title",title.getText().toString());
                map.put("description",description.getText().toString());
                map.put("id",id);

                reference.child(FirebaseAuth.getInstance().getUid())
                        .child("RestApi")
                        .push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                sentByRest();

                            }
                        });
            }
        });

        //notification call by couldMassage api
        cloudFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
                progress_bar.setVisibility(View.VISIBLE);

                Map<String ,Object>map=new HashMap<>();
                map.put("title",title.getText().toString());
                map.put("description",description.getText().toString());
                map.put("id",id);

                reference.child(FirebaseAuth.getInstance().getUid())
                        .child("CloudFuncation")
                        .push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progress_bar.setVisibility(View.GONE);
                            }
                        });

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() == 0) {
                    Toast.makeText(SendNotificationActivity.this, "Enter Title ...", Toast.LENGTH_SHORT).show();
                } else if (description.getText().toString().length() == 0) {
                    Toast.makeText(SendNotificationActivity.this, "Enter Description ...", Toast.LENGTH_SHORT).show();
                } else {
                    dialogPlus.show();
                }
            }
        });

    }

    private void sentByRest() {

        FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //to
                        //Notification
                        //data
//                        NotificationReq req = new NotificationReq(
//                                dataSnapshot.child("token").getValue().toString(),
//                                new NotificationReq.Notification(title.getText().toString(), description.getText().toString(), "https://embedsocial.com/wp-content/uploads/2020/02/latest-instagram-api-changes.jpg", "my_clcik"),
//                                new NotificationReq.Data_("sifat", "hassan","notification sifat")
//                        );


                        //to
                        //data
                        //for background
                        NotificationReq req = new NotificationReq(
                                dataSnapshot.child("token").getValue().toString(),
                                new NotificationReq.Data_("value 1","value 2",
                                        title.getText().toString(),
                                        description.getText().toString(),
                                        "https://image.shutterstock.com/image-photo/kiev-ukraine-may-14-2016-260nw-420838831.jpg",
                                        "my_click",
                                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in")
                        );

                        RetrofitClient.getRetrofit(BASE_URL)
                                .create(NotificationRequest.class)
                                .sent(req)
                                .enqueue(new Callback<NotificationResponce>() {
                                    @Override
                                    public void onResponse(Call<NotificationResponce> call, Response<NotificationResponce> response) {
                                        if(response.code()==200){
                                            Log.i("hfbvjes", "ok: ");
                                            Toast.makeText(SendNotificationActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(SendNotificationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                            Log.i("hfbvjes", response.toString());
                                        }
                                        progress_bar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFailure(Call<NotificationResponce> call, Throwable t) {
                                        progress_bar.setVisibility(View.GONE);
                                        Log.i("hfbvjes", "onFailure: "+t.toString());
                                        Toast.makeText(SendNotificationActivity.this, "Filed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        progress_bar.setVisibility(View.GONE);
                    }
                });


    }

}