package enoch.app.ui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        // SpringApplication.run(JavaStockClientUiApplication.class, args);

        Application.launch(MainJavaFXApplication.class, args);
    }

}
