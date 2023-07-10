package DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryDTO {

    private Integer id; // ID
    private double latitude; // X좌표
    private double longitude; // Y좌표
    private Date lookupDate;    // 조회일자

}
