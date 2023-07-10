package API;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DB.HistoryInfo;
import DTO.HistoryDTO;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import DTO.PublicWifiAccessObject;
import DTO.WifiDTO;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import DB.WifiInfo;
import util.MathUtil;

public class ApiExplorer {
    private static String authKey = "4756686d416a6f7339395566585159";
    private static String urlHomePart = "http://openapi.seoul.go.kr:8088/";
    private static String urlRequestPart = "/json/TbPublicWifiInfo/";

    @Getter
    @Setter
    private class PublicWifiAccessObjectWrapper {
        @SerializedName("TbPublicWifiInfo")
        private PublicWifiAccessObject publicWifiAccessObject;
    }

    private String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public int getWifiCountFromPublicData() {
        String startToEnd = "1/1";
        String url = urlHomePart + authKey + urlRequestPart + startToEnd;
        String json = null;
        PublicWifiAccessObjectWrapper publicWifiAccessObjectWrapper = null;
        int count = -1;

        try {
            json = run(url);
            Gson gson = new Gson();
            publicWifiAccessObjectWrapper = gson.fromJson(json, PublicWifiAccessObjectWrapper.class);
            count = publicWifiAccessObjectWrapper.getPublicWifiAccessObject().getTotalCount();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<WifiDTO> getWifiListFromPublicData(int start, int end) {
        String url = urlHomePart + authKey + urlRequestPart + start + "/" + end;
        String json = null;
        PublicWifiAccessObjectWrapper publicWifiAccessObjectWrapper = null;
        List<WifiDTO> wifis = null;

        try {
            json = run(url);
            Gson gson = new Gson();
            publicWifiAccessObjectWrapper = gson.fromJson(json, PublicWifiAccessObjectWrapper.class);
            wifis = publicWifiAccessObjectWrapper.getPublicWifiAccessObject().getWifis();

            for (WifiDTO wifi : wifis) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    wifi.setWorkDate(simpleDateFormat.parse(wifi.getWorkDateStr()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wifis;
    }

    public int saveAllWifiListFromPublicData() {
        WifiInfo wifiInfo = new WifiInfo();
        int totalCount = getWifiCountFromPublicData();

        for (int i = 0; i < totalCount; i+=1000) {
            List<WifiDTO> wifiList = getWifiListFromPublicData(i + 1, i + 1000);
            for (WifiDTO wifi : wifiList) {
                wifiInfo.insert(wifi);
            }
        }

        return totalCount;
    }

    public List<WifiDTO> getNearbyWifiList(double latitude, double longitude) {
        WifiInfo wifiInfo = new WifiInfo();
        List<WifiDTO> wifiList = wifiInfo.readAll();
        List<WifiDTO> result = new ArrayList<>();

        Collections.sort(wifiList, new Comparator<WifiDTO>() {
            @Override
            public int compare(WifiDTO w1, WifiDTO w2) {
                double dist1 = MathUtil.calculateDistance(latitude, longitude, w1.getLatitude(), w1.getLongitude());
                double dist2 = MathUtil.calculateDistance(latitude, longitude, w2.getLatitude(), w2.getLongitude());

                if (dist1 < dist2) return -1;
                else if (dist1 > dist2) return 1;
                else return 0;
            }
        });

        for (int i = 0; i < 20; i++) {
            result.add(wifiList.get(i));
        }

        HistoryInfo historyInfo = new HistoryInfo();
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setLatitude(latitude);
        historyDTO.setLongitude(longitude);
        historyInfo.insert(historyDTO);

        return result;
    }

    public List<HistoryDTO> getAllHistoryDTO() {
        HistoryInfo historyInfo = new HistoryInfo();
        return HistoryInfo.getAll();
    }
}