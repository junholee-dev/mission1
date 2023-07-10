package DTO;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiDTO {
    @SerializedName("X_SWIFI_MGR_NO")
    private String management_number;

    @SerializedName("X_SWIFI_WRDOFC")
    private String district;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String name;

    @SerializedName("X_SWIFI_ADRES1")
    private String address1;

    @SerializedName("X_SWIFI_ADRES2")
    private String address2;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String installed_floor;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String install_type;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String install_agency;

    @SerializedName("X_SWIFI_SVC_SE")
    private String service_classification;

    @SerializedName("X_SWIFI_CMCWR")
    private String mesh_type;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String install_year;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String in_out_door;

    @SerializedName("X_SWIFI_REMARS3")
    private String connection_environment;

    @SerializedName("LAT")
    private Double latitude;

    @SerializedName("LNT")
    private Double longitude;

    private Date workDate;

    @SerializedName("WORK_DTTM")
    private String workDateStr;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object[] varArr = new Object[] {
                management_number, district, name, address1, address2,
                installed_floor, install_type, install_agency, service_classification,
                mesh_type, install_year, in_out_door, connection_environment, latitude, longitude,
                workDateStr
        };
        String[] strArr = new String[] {
                "management_number", "district", "name", "address1", "address2",
                "installed_floor", "install_type", "install_agency", "service_classification",
                "mesh_type", "install_year", "in_out_door", "connection_environment", "latitude", "longitude",
                "workDateStr"
        };
        for (int i = 0; i < varArr.length; i++) {
            appendMsg(sb, strArr[i], varArr[i]);
        }
        return sb.toString();
    }

    private void appendMsg(StringBuilder sb, String msg, Object obj) {
        if (obj == null) return;
        if (obj instanceof String && "".equals(obj)) return;
        sb.append(msg).append(" : ").append(obj).append("\n");
    }
}