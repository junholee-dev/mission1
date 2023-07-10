package DTO;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicWifiAccessObject {
    @SerializedName("list_total_count")
    private Integer totalCount;

    @SerializedName("RESULT")
    private WifiAccessMessage message;

    @SerializedName("row")
    private List<WifiDTO> wifis;
}
