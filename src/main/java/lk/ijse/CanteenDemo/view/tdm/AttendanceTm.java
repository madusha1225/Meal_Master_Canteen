package lk.ijse.CanteenDemo.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceTm {
    private String name;
    private Date date;
    private Time inTime;
    private Time outTime;
}
