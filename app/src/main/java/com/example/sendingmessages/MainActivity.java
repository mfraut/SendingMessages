package com.example.sendingmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCLick(View v){
        try{
        URL url = new URL("https://194.224.79.42:44080/wstutorial/User/signup.php");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        byte[] in = new byte[1024];
//        Map<String,String> arguments = new HashMap<>();
//        arguments.put("username", "root");
//        arguments.put("password", "sjh76HSn!"); // This is a fake password obviously
//        StringJoiner sj = new StringJoiner("&");
//        for(Map.Entry<String,String> entry : arguments.entrySet())
//            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
//                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
//        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);


            EditText etu = findViewById(R.id.usernameET);
            EditText etp = findViewById(R.id.passwordET);

            byte[] out = (etu.getText().toString() + "=" + etp.getText().toString()).getBytes();
            int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();

        OutputStream os = http.getOutputStream();
        os.write(out);
        InputStream is = http.getInputStream();
        is.read(in);
        String result = new String(in);
            TextView tw = findViewById(R.id.resultTW);
            tw.setText(result);
        } catch (Exception e) {
            TextView tw = findViewById(R.id.resultTW);
            tw.setText(e.getLocalizedMessage());
        }
    }
}
