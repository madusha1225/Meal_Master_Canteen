package lk.ijse.CanteenDemo.bo;

import lk.ijse.CanteenDemo.bo.custom.impl.*;

public class BOFactory {
    public enum BOType{
        ATTENDANCE,CUSTOMER,LOGIN,MAIN,MEAL,ORDER,PAYMENT,SETTING,SUPPLIER,WORKER
    }
    public static SuperBO getBO(BOType type){
        switch (type){
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MAIN:
                return new MainBOImpl();
            case MEAL:
                return new MealBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SETTING:
                return new SettingBOImpl();
             case SUPPLIER:
                 return new SupplierBOImpl();
             case WORKER:
                 return new WorkerBOImpl();
            default:
                return null;
        }
    }
}
