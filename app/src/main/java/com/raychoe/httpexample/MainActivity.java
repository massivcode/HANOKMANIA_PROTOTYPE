package com.raychoe.httpexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.raychoe.httpexample.HanokRepairAdvice.HanokRepairAdvice;
import com.raychoe.httpexample.HanokRepairAdvice.HanokRepairAdviceFacade;
import com.raychoe.httpexample.HanokStatus.HanokStatus;
import com.raychoe.httpexample.HanokStatus.HanokStatusFacade;
import com.raychoe.httpexample.Utils.HanokDBHelper;
import com.raychoe.httpexample.Utils.JsonToSqlite;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private HanokDBHelper mHelper;
    private HanokStatusFacade mHanokStatusFacade;
    private HanokRepairAdviceFacade mHanokRepairAdviceFacade;

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
        mHelper = HanokDBHelper.getInstance(MainActivity.this);
        mHanokStatusFacade = new HanokStatusFacade(MainActivity.this);
        mHanokRepairAdviceFacade = new HanokRepairAdviceFacade(MainActivity.this);

        new InfoLoadTask().execute();


    }


    class InfoLoadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            new JsonToSqlite<HanokStatus>(MainActivity.this, new HanokStatus()).receiveData(JsonToSqlite.URL_HANOK_STATUS, JsonToSqlite.SERVICE_NAME_HANOK_STATUS);
            new JsonToSqlite<HanokRepairAdvice>(MainActivity.this, new HanokRepairAdvice()).receiveData(JsonToSqlite.URL_HANOK_REPAIR_ADVICE_PART1, JsonToSqlite.SERVICE_NAME_HANOK_REPAIR_ADVICE);
            new JsonToSqlite<HanokRepairAdvice>(MainActivity.this, new HanokRepairAdvice()).receiveData(JsonToSqlite.URL_HANOK_REPAIR_ADVICE_PART2, JsonToSqlite.SERVICE_NAME_HANOK_REPAIR_ADVICE);

            mHanokRepairAdviceFacade.select();

            return null;
        }
    }

}
