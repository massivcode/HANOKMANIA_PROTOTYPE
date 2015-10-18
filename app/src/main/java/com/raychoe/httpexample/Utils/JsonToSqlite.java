package com.raychoe.httpexample.Utils;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raychoe.httpexample.Hanok;
import com.raychoe.httpexample.HanokRepairAdvice.HanokRepairAdvice;
import com.raychoe.httpexample.HanokRepairAdvice.HanokRepairAdviceFacade;
import com.raychoe.httpexample.HanokStatus.HanokStatus;
import com.raychoe.httpexample.HanokStatus.HanokStatusFacade;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by massivCode on 2015-10-17.
 */
public class JsonToSqlite<T extends Hanok> {
    private static final String TAG = JsonToSqlite.class.getSimpleName();

    public static final String URL_HANOK_STATUS = "http://openapi.seoul.go.kr:8088/6171556f526d617339307a50504549/json/SeoulHanokStatus/1/600";
    public static final String URL_HANOK_REPAIR_ADVICE_PART1 = "http://openapi.seoul.go.kr:8088/58455859736d61733131334d5975734e/json/SeoulHanokRepairAdvice/1/1000";
    public static final String URL_HANOK_REPAIR_ADVICE_PART2 = "http://openapi.seoul.go.kr:8088/58455859736d61733131334d5975734e/json/SeoulHanokRepairAdvice/1001/1928";

    // 서울 한옥 등록 현황
    public static final String SERVICE_NAME_HANOK_STATUS = "SeoulHanokStatus";
    // 서울 한옥 수선 지원 현황
    public static final String SERVICE_NAME_HANOK_REPAIR_ADVICE = "SeoulHanokRepairAdvice";
    // 서울 북촌 한옥마을 분류별 한옥정보
    public static final String SERVICE_NAME_BUKCHON_INFO = "BukchonHanokVillageInfo";

    private HanokDBHelper mHelper;
    private Context mContext;

    private T t;

    public void add(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }

    public JsonToSqlite(Context context, T type) {
        t = type;
        mContext = context;
        mHelper = HanokDBHelper.getInstance(context);
    }

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


    public void receiveData(String url, String serviceName) {
        List<T> list = null;
        try {
            // HTTP 에서 내용을 String 으로 받아 온다
            String jsonString = getResponse(url);
            JSONObject jsonObject = new JSONObject(jsonString).getJSONObject(serviceName);
            JSONArray jsonArray = jsonObject.getJSONArray("row");


            ObjectMapper objectMapper = new ObjectMapper();

            list = objectMapper.readValue(jsonArray.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, t.getClass()
                    ));

            switch (serviceName) {
                case SERVICE_NAME_HANOK_STATUS:
                    new HanokStatusFacade(mContext).bulkInsert((List<HanokStatus>) list);
                    break;
                case SERVICE_NAME_HANOK_REPAIR_ADVICE:
                    new HanokRepairAdviceFacade(mContext).bulkInsert((List<HanokRepairAdvice>) list);
                    break;
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }




}
