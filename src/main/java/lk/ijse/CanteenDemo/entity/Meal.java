package lk.ijse.CanteenDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    private String mealId;
    private String description;
    private double price;
    private String status;

}
