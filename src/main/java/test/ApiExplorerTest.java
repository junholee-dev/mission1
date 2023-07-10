package test;

import API.ApiExplorer;
import DTO.WifiDTO;
import util.MathUtil;

import java.util.List;

public class ApiExplorerTest {
    public static void main(String[] args) {
        ApiExplorer apiExplorer = new ApiExplorer();
        int count = apiExplorer.getWifiCountFromPublicData();

        System.out.println("total count : " + count + "\n");

        List<WifiDTO> wifis = apiExplorer.getWifiListFromPublicData(1, 1);

        for (WifiDTO wifi : wifis) {
            System.out.println(wifi);
        }

        int totalCount = apiExplorer.saveAllWifiListFromPublicData();

        System.out.println(totalCount);

        double curLat = 0.0;
        double curLon = 0.0;
        wifis = apiExplorer.getNearbyWifiList(curLat, curLon);

        System.out.println(wifis.size() + "\n");
        for (WifiDTO wifi : wifis) {
            double dist = MathUtil.calculateDistance(curLat, curLon, wifi.getLatitude(), wifi.getLongitude());
            System.out.println(dist);
            System.out.println(wifi);
        }
    }
}
