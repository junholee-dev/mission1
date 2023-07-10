package test;

import DB.WifiInfo;
import DTO.WifiDTO;

import java.util.List;

public class WifiInfoTest {
    public static void main(String[] args) {
        WifiInfo wifiInfo = new WifiInfo();
        WifiDTO wifiDTO = new WifiDTO();
        wifiDTO.setManagement_number("test1");
        wifiDTO.setLatitude(1.0);
        wifiDTO.setLongitude(1.0);

        boolean result = wifiInfo.insert(wifiDTO);

        System.out.println(result);

        result = wifiInfo.delete(wifiDTO);

        System.out.println(result);

        List<WifiDTO> wifiDTOList = wifiInfo.readAll();

        System.out.println(wifiDTOList.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(wifiDTOList.get(i));
        }
    }
}
