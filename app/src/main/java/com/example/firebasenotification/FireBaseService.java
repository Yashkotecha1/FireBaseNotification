package com.example.firebasenotification;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseService extends FirebaseMessagingService
{


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        System.out.println("Message "+remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            System.out.println("Message Data "+ remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            System.out.println("Message Body "+ remoteMessage.getNotification().getBody());
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            System.out.println("Fetching FCM registration token failed"+task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("Token is "+token);
                        //----token is  fF5kJZFMTROV78hLRpZhxo:APA91bH4csyaghxkaaZ5_x9kN0usXoVGx28PGJqaLne4mHxQsyK7_WKTSvZrhvIq7Je19fC0TGvnxy70iBndqbvAxH5GFNOKxam3tJPIL7RKbjziO4zGPDtrhbqZZEHYOGIaGBtswqRu
                        // Log and toast

                        Toast.makeText(FireBaseService.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onNewToken(@NonNull String token) {

        System.out.println("Refreshed token: "+token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.

    }

}
