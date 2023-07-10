package DTO;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WifiAccessMessage {
    @SerializedName("CODE")
    private String code;

    @SerializedName("MESSAGE")
    private String message;
}
