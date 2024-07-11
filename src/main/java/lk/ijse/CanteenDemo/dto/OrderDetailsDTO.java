package lk.ijse.CanteenDemo.dto;

public class OrderDetailsDTO {
    private String orderID;
    private String mealId;
    private int qty;

    public OrderDetailsDTO(String orderID, String mealId, int qty) {
        this.orderID = orderID;
        this.mealId = mealId;
        this.qty = qty;
    }

    public OrderDetailsDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
