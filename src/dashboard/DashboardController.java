package dashboard;

import Models.Permissions;
import Models.User;
import ScreenManager.ControlledScreen;
import ScreenManager.ScreenController;
import ScreenManager.ScreenNavigator;
import Util.Util;
import database.Database;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class DashboardController implements Initializable, ControlledScreen{


    
    private String currentUser;
    private String currentUserRole;
    private User user;
    private ArrayList<String> availableUsers = new ArrayList<String>();
    
    private Database database = new Database();
    private Permissions permission = new Permissions();
    
    ScreenController myController;
    
    private String adminRole = "Admin";
    
    @FXML
    public Label lblWelcome;
    
    @FXML
    public TreeView<String> treeview;
    
    @FXML TextField txtUsername;
    @FXML TextField txtRole;
    @FXML TextField txtKey;
    
    
    @FXML Label lblUserError;
    @FXML Label lblAccess;
    
    
    @FXML Button btnAddUser;
    @FXML Button btnInsert;
    
    TreeItem<String> root;
    
    Image icon = new Image(getClass().getResourceAsStream("/Images/File.png"), 20, 20, false, false);
    
    @FXML
    @Override
     public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void setScreenParent(ScreenController screenParent) {
         myController = screenParent;
    }

    @Override
    public void setInitData(User user) {
        this.currentUser = user.getUser();
        this.currentUserRole = user.getRole();
        this.user = user;
        lblWelcome.setText("Welcome " + this.currentUser);
    }
    
    @FXML
    @Override
    public void setTreeView(User user) {
        String role = user.getRole();
        if(role != null && !role.equals(adminRole))
            {
                availableUsers.remove(this.currentUser);
                root.getChildren().remove("TestUser2");
                treeview.setRoot(root);
            }
    }
    
    @FXML
    public void newPoduct(ActionEvent event)
    {
        String refScreen = "Product";
        gotoNew(refScreen);
    }
    
    @FXML
    public void editProduct(ActionEvent event)
    {
        String refScreen = "Product";
        gotoEdit(refScreen);
    }
    
    @FXML
    public void viewProduct(ActionEvent event)
    {
        String refScreen = "Product";
        gotoView(refScreen);
    }
    
    @FXML
    public void newOverview(ActionEvent event)
    {
        String refScreen = "Overview";
        gotoNew(refScreen);
    }
    
    @FXML
    public void editOverview(ActionEvent event)
    {
        String refScreen = "Overview";
        gotoEdit(refScreen);
    }
    
    @FXML
    public void viewOverview(ActionEvent event)
    {
        String refScreen = "Overview";
        gotoView(refScreen);
    }
    
    @FXML
    public void newStock(ActionEvent event)
    {
        
        String refScreen = "Stock";
        gotoNew(refScreen);
    }
    
    @FXML
    public void editStock(ActionEvent event)
    {
        
        String refScreen = "Stock";
        gotoEdit(refScreen);
    }
    
    @FXML
    public void viewStock(ActionEvent event)
    {
        
        String refScreen = "Stock";
        gotoView(refScreen);
    }
    
    @FXML
    public void Traceability (ActionEvent event)
    {
        
        String refScreen = "Traceability";
        gotoView(refScreen);
    }
    
    private void gotoNew(String screen)
    {
        lblAccess.setVisible(false);
        if(this.permission.getNewAccess())
        {
            myController.loadScreen(ScreenNavigator.newPaneScreenId, ScreenNavigator.newPaneScreeFile, this.user, screen, treeview);
            myController.setScreen(ScreenNavigator.newPaneScreenId);
        }
        else
        {
            lblAccess.setVisible(true);
            lblAccess.setText("Access Denied");
        }
    }
    
    private void gotoView(String screen)
    {
        lblAccess.setVisible(false);
        if(this.permission.getViewAccess())
        {
            myController.loadScreen(ScreenNavigator.viewPaneScreenId, ScreenNavigator.viewPaneScreeFile, this.user, screen, treeview);
            myController.setScreen(ScreenNavigator.viewPaneScreenId);
        }
        else
        {
            lblAccess.setVisible(true);
            lblAccess.setText("Access Denied");
        }
       
    }
    
    private void gotoEdit(String screen)
    {
        lblAccess.setVisible(false);
        if(this.permission.getEditAccess())
        {
            myController.loadScreen(ScreenNavigator.editPaneScreenId, ScreenNavigator.editPaneScreeFile, this.user, screen, treeview);
            myController.setScreen(ScreenNavigator.editPaneScreenId);
        }
        else
        {
            lblAccess.setVisible(true);
            lblAccess.setText("Access Denied");
        }
    }

    @Override
    public void setInitialData(User user, String screen, TreeView<String> treeview1) {
        this.currentUser = user.getUser();
        this.currentUserRole = user.getRole();
        this.user = user;
        lblWelcome.setText("Welcome " + this.currentUser);
        
          Util util = new Util();
          TreeItem<String> root = util.setTreeView(user);
          treeview.setRoot(root);
          
          this.permission = util.IsUserPermitted(user);
    }
    
    public void addUser(ActionEvent event)
    {
        lblUserError.setVisible(false);
        btnAddUser.setVisible(false);
        btnInsert.setVisible(true);
        txtUsername.setVisible(true);
        txtRole.setVisible(true);
        txtKey.setVisible(true);
        txtUsername.setText("");
        txtRole.setText("");
        txtKey.setText("");
    }
    
    public void insertUser(ActionEvent event)
    {
        String username = "";
        String role = "";
        String key = "";
        username = txtUsername.getText();
        role = txtRole.getText();
        key = txtKey.getText();
        if(username != null && role != null && key != null)
        {
            if(!username.equals("") && !role.equals("") && !key.equals(""))  
            {
                database.insertUser(username, role, key);
                txtUsername.setVisible(false);
                txtRole.setVisible(false);
                txtKey.setVisible(false);
                btnInsert.setVisible(false);
                btnAddUser.setVisible(true);
                
            }
            else
            {
                System.out.println("User Insert Error");
                lblUserError.setVisible(true);
                txtUsername.setVisible(false);
                txtRole.setVisible(false);
                txtKey.setVisible(false);
                btnInsert.setVisible(false);
                btnAddUser.setVisible(true);
            }
        }
        else
        {
            System.out.println("User Insert Error");
            lblUserError.setVisible(true);
            txtUsername.setVisible(false);
            txtRole.setVisible(false);
            txtKey.setVisible(false);
            btnInsert.setVisible(false);
            btnAddUser.setVisible(true);
        }
    }
    
    public void logout(ActionEvent event)
    {
        myController.loadScreen(ScreenNavigator.loginScreenId, ScreenNavigator.loginScreenFile, null, null, null);
        myController.setScreen(ScreenNavigator.loginScreenId);
    }
}
