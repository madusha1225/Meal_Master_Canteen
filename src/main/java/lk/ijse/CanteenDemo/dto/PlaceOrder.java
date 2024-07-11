package lk.ijse.CanteenDemo.dto;

import javafx.collections.ObservableList;

public class PlaceOrder {
    private OrderDTO orderDTO;
    private ObservableList<OrderDetailsDTO> details;

    public PlaceOrder(OrderDTO orderDTO, ObservableList<OrderDetailsDTO> details) {
        this.orderDTO = orderDTO;
        this.details = details;
    }

    public OrderDTO getOrder() {
        return orderDTO;
    }

    public void setOrder(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public ObservableList<OrderDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(ObservableList<OrderDetailsDTO> details) {
        this.details = details;
    }
}
