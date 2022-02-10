package com.example.a220210_otp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

//Notification을 이용하려면 넣어야 되는 3가지
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    private static String CHANNEL_ID = "channel1"; //각각의 변수에 채널로 봄봄
    private static String CHANNEL_NAME = "Channel1";

    private static String CHANNEL_ID2 = "channel2"; //각각의 변수에 채널로 봄봄
    private static String CHANNEL_NAME2 = "Channel2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showNoti1();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showNoti2();
            }
        });
    }

    public void showNoti1() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 이후 버전에서는 알림 채널이 지정되어야 함
            manager.createNotificationChannel(new NotificationChannel( // createNotificationChannel() 채널 생성
                    CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ));
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
                //Builder 객체가 만들어지면 알림 정보를 설정할 수 있다.
            } else {
            builder = new NotificationCompat.Builder(this);
        }

        builder.setContentTitle("알림"); // 알림 제목
        builder.setContentText("메시지입니다."); // 알림 메시지
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification noti = builder.build(); // Builder 객체의 build()를 호출하면 Notfication객체 생성 -> 이후 파라미터 전달하면 알림 띄움

        manager.notify(1, noti); // 상단 알림 띄우기

    }

    public void showNoti2() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 이후 버전에서는 알림 채널이 지정되어야 함
            manager.createNotificationChannel(new NotificationChannel( // createNotificationChannel() 채널 생성
                    CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_DEFAULT
            ));
            builder = new NotificationCompat.Builder(this, CHANNEL_ID2);
            //Builder 객체가 만들어지면 알림 정보를 설정할 수 있다.
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("알림"); // 알림 제목
        builder.setContentText("메시지입니다."); // 알림 메시지
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        builder.setAutoCancel(true); // 실질적으로 보여주는 것이니까.
        builder.setContentIntent(pendingIntent);

        Notification noti = builder.build(); // Builder 객체의 build()를 호출하면 Notfication객체 생성 -> 이후 파라미터 전달하면 알림 띄움

        manager.notify(2, noti); // 상단 알림 띄우기

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText("글자들자 글자 글자");
        style.setBigContentTitle("제목");
        style.setSummaryText("요약 글");

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, "channel3")
                .setContentTitle("알림 제목")
                .setContentText("알림 내용")
                .setSmallIcon(android.R.drawable.ic_menu_send)
                .setStyle(style);
    }
}