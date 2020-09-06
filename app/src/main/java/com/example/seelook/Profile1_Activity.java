package com.example.seelook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile1_Activity extends AppCompatActivity {

    private static final String TAG="Profile_Activity";

    private FirebaseDatabase firebaseDatabase;

    private String getUserEmail;
    private String getUserPassword;
    private SharedPreferences appData;
    private DatabaseReference mDatabase;

    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageRef=storage.getReference();
    //참조가 이렇게 많다고???
    StorageReference pathReference;
    StorageReference gsReference;
    StorageReference httpsReference;

    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1_);

        videoView1 = (VideoView)findViewById(R.id.videoView1);
        //MediaController mc = new MediaController(this);
        //videoView1.setMediaController(mc); // Video View 에 사용할 컨트롤러 지정

        appData = getSharedPreferences("appData",MODE_PRIVATE);
        load();//자동 로그인 정보 로드
        getStorage();

        Button homeBt = (Button)findViewById(R.id.home_btn);//홈버튼
        homeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home_Activity1.class);
                startActivity(intent);
            }
        });

        Button uploadBt = (Button)findViewById(R.id.upload_btn);//업로드 버튼
        uploadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Post_Activity.class);
                startActivity(intent);
            }
        });

        Button profileBt = (Button)findViewById(R.id.profile_btn);//프로필 버튼
        profileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Profile1_Activity.class);
                startActivity(intent);
            }
        });
    }

   public void getStorage(){
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://seelook-d3a93.appspot.com/"+getUserEmail);
        StorageReference storageRef = storage.getReference();
        
    }

   public void load(){
       //SharedPreference 객체.get타입(저장된 이름, 기본 값)
       //저장된 이름이 존재하지 않을 시 기본 값
       getUserEmail=appData.getString("email","");
       getUserPassword=appData.getString("pw","");
   }
}