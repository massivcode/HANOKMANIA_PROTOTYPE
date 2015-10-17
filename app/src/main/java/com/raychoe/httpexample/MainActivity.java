package com.raychoe.httpexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raychoe.httpexample.Utils.DBHelper;
import com.raychoe.httpexample.Utils.HanokStatusFacade;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "http://openapi.seoul.go.kr:8088/6171556f526d617339307a50504549/json/SeoulHanokStatus/1/600";
    private static final String TAG = MainActivity.class.getSimpleName();

    private DBHelper mHelper;
    private HanokStatusFacade mFacade;

    // 클라이언트 오브젝트
    private OkHttpClient client = new OkHttpClient();

    /**
     * url 로 부터 스트림을 읽어 String 으로 반환한다
     * @param url
     * @return
     * @throws IOException
     */
    private String getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = DBHelper.getInstance(MainActivity.this);
        mFacade = new HanokStatusFacade(MainActivity.this);

        new InfoLoadTask().execute();
    }


    class InfoLoadTask extends AsyncTask<Void, Void, List<Hanok>> {

        @Override
        protected List<Hanok> doInBackground(Void... params) {
            List<Hanok> hanokList = null;


            try {
                // HTTP 에서 내용을 String 으로 받아 온다
                String jsonString = getResponse(API_KEY);
                Log.d(TAG, jsonString);
                JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("SeoulHanokStatus");
                JSONArray jsonArray = jsonObject.getJSONArray("row");


                ObjectMapper objectMapper = new ObjectMapper();

                hanokList = objectMapper.readValue(jsonArray.toString(),
                        objectMapper.getTypeFactory().constructCollectionType(
                                List.class, Hanok.class
                        ));

                mFacade.bulkInsert(hanokList);
                mFacade.select();




//                // 받아온 JSON String 을 JSON Object로 변환한다
//                JSONObject jsonObject = new JSONObject(jsonString);
//                JSONArray jsonArray = jsonObject.getJSONArray("list");
//
//                // 날씨 정보 저장할 리스트
//                weatherList = new ArrayList<>();
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject object = jsonArray.getJSONObject(i);
//
//                    String time = object.getString("dt_txt");
//                    time = time.split(" ")[1].substring(0, 5);
//                    String temp = object.getJSONObject("main").getString("temp");
//                    String description = object.getJSONArray("weather")
//                            .getJSONObject(0).getString("description");
//
//                    weatherList.add(new Weather(time, temp, description));
//                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            return hanokList;
        }
    }
}
