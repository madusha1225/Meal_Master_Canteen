package lk.ijse.CanteenDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDTO {
    private int EmpId;
    private Time inTime;
    private Time outTime;
    private Date date;
}
