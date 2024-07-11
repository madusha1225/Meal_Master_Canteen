package lk.ijse.CanteenDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {
    private String mealId;
    private String description;
    private double price;
    private String status;

}
