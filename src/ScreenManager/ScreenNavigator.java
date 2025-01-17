package ScreenManager;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenNavigator extends Application {
    
    public static String loginScreenId = "loginScreen";
    public static String loginScreenFile = "/login/Login.fxml";
    public static String dashboardScreenId = "dashboardScreen";
    public static String dashboardScreeFile = "/dashboard/Dashboard.fxml";
    public static String viewPaneScreenId = "viewPaneScreen";
    public static String viewPaneScreeFile = "/ViewPane/ViewPane.fxml";
    public static String newPaneScreenId = "newPaneScreen";
    public static String newPaneScreeFile = "/NewPane/newPane.fxml";
    public static String editPaneScreenId = "editPaneScreen";
    public static String editPaneScreeFile = "/EditPane/EditPane.fxml";
    public static String forgotPasswordId = "forgotPasswordScreen";
    public static String forgotPasswordFile = "/ForgotPassword/forgotPassword.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(ScreenNavigator.loginScreenId, ScreenNavigator.loginScreenFile);
        mainContainer.setScreen(ScreenNavigator.loginScreenId);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 800, 500);
        
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
