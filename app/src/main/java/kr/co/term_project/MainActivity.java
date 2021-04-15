package kr.co.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callback(View view) {
        int buttonID = view.getId();
        switch (buttonID) {
            case R.id.homepage_button:
                Intent homepageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hanbat.ac.kr/infocomm/"));
                startActivity(homepageIntent);
                break;
            case R.id.dept_button:
                Intent introductionIntent = new Intent(this, IntroductionActivity.class);
                startActivity(introductionIntent);
                break;
            case R.id.diagram_button:
                Intent diagramIntent = new Intent(this, DiagramActivity.class);
                startActivity(diagramIntent);
                break;
            case R.id.subject_button:
                Intent subjectIntent = new Intent(this, SubjectActivity.class);
                startActivity(subjectIntent);
                break;
            case R.id.location_button:
                Intent locationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0, 0?q=한밭대학교 산업정보관"));
                startActivity(locationIntent);
                sendNotification(view);
                break;
            case R.id.call_button:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0428211199"));
                startActivity(callIntent);
                break;
        }
    }


    /*
    에뮬레이터 실행시 의도한 알림(헤드업 알림)이 뜨지 않습니다.
    실기기에선 잘 동작합니다.
     */
    public void sendNotification(View view) {
        Notification.Builder notificationBuilder = new Notification.Builder(this, LoadingActivity.NOTIFICATION_CHANNEL_ID);

        Intent locationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0, 0?q=한밭대학교 산업정보관"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, locationIntent, 0);

        notificationBuilder.setSmallIcon(R.drawable.hanbat_logo)
                .setContentTitle("한밭대학교 정보통신공학과 건물위치")
                .setContentText("정보통신공학과는 산업정보관(N4동) 3층입니다.")
                .setFullScreenIntent(pendingIntent, true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

}