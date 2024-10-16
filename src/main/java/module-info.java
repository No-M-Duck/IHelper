module com.example.ihelperbetalol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ihelperbetalol to javafx.fxml;
    exports com.example.ihelperbetalol;
}