package lk.ijse.CanteenDemo.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkerTm {
    private String workerId;
    private String name;
    private String address;
    private String email;
    private int phone;
}
