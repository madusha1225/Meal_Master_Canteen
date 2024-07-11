package lk.ijse.CanteenDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {
    private String id;
    private int userId;
    private String name;
    private String address;
    private String email;
    private int phone;

    public WorkerDTO(String id, String name, String address, String email, int phone) {
        this.id = id;

        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
