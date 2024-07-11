package lk.ijse.CanteenDemo.view.tdm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MealTm {
    private String mealId;
    private String description;
    private double price;
    private String status;
}
