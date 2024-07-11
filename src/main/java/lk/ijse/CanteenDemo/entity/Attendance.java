package lk.ijse.CanteenDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {
    private int EmpId;
    private Time inTime;
    private Time outTime;
    private Date date;
    private int attendanceId;
}
