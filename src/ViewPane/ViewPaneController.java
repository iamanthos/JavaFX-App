package ViewPane;

import Models.Overview;
import Models.Product;
import Models.Stock;
import Models.Traceability;
import Models.User;
import ScreenManager.ControlledScreen;
import ScreenManager.ScreenController;
import ScreenManager.ScreenNavigator;
import Util.Util;
import database.Database;
import javafx.scene.control.Label;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
//import javafx.Scene.Control.TableView;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
 
public class ViewPaneController implements Initializable, ControlledScreen {

    ScreenController myController;
    Database database = new Database();
    
    String prodId = "ProductId";
    String prodName = "Product_Name";
    String overviewId = "Overview_Id";
    String prodDesc = "Product_Desc";
    String status = "Status";
    String actions = "Actions";
    String prodPriority = "Product_Priority";
    String traceability = "Traceability";
    String enrollDate = "Enrollment_Date";
    String enrolledUser = "Enrollment_User";
    
    private String productScene = "Product";
    private String overviewScene = "Overview";
    private String stockScene = "Stock";
    private String filter = "";
    private String viewPane = "";
    
    private User user;
    
    @FXML TableColumn c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
    
    @FXML TableView table;
    
    @FXML Button edit;
    @FXML Button remove;
    
    public Label lblWelcome;
    @FXML Label lblFilter;    
    
    @FXML TextField txtFilter;
    
    @FXML TreeView treeview1;
          
    @FXML
    public void MouseClick(ActionEvent event)
    {
    }
    
    public void setGraphic()
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    private void setProductColumnName()
    {
        c1.setText(prodId);
        c2.setText(prodName);
        c3.setText(overviewId);
        c4.setText(prodDesc);
        c5.setText(status);
        c6.setText(actions);
        c7.setText(prodPriority);
        c8.setText(traceability);
        c9.setText(enrollDate);
        c10.setText(enrolledUser);
    }
    
    private void setProductRowValues ()
    {
        c1.setCellValueFactory(new PropertyValueFactory("productId"));
        c2.setCellValueFactory(new PropertyValueFactory("productName"));
        c3.setCellValueFactory(new PropertyValueFactory("overviewId"));
        c4.setCellValueFactory(new PropertyValueFactory("productDesc"));
        c5.setCellValueFactory(new PropertyValueFactory("status"));
        c6.setCellValueFactory(new PropertyValueFactory("actions"));
        c7.setCellValueFactory(new PropertyValueFactory("productPriority"));
        c8.setCellValueFactory(new PropertyValueFactory("traceability"));
        c9.setCellValueFactory(new PropertyValueFactory("enrollDate"));
        c10.setCellValueFactory(new PropertyValueFactory("enrolledUser"));
        
    }
    
    @FXML
    private void setOverviewColumnName()
    {
        c1.setText("OverviewId");
        c2.setText("Overview_Desc");
        c3.setText("StockId");
        c4.setText("Action");
        c5.setText("Status");
        c6.setText("Traceability");
        c7.setText("enrollDate");
        c8.setText("enrolledUser");
    }
    
    @FXML
    private void setOverviewRowValues ()
    {
        c1.setCellValueFactory(new PropertyValueFactory("overviewId"));
        c2.setCellValueFactory(new PropertyValueFactory("overview_Desc"));
        c3.setCellValueFactory(new PropertyValueFactory("stockId"));
        c4.setCellValueFactory(new PropertyValueFactory("action"));
        c5.setCellValueFactory(new PropertyValueFactory("status"));
        c6.setCellValueFactory(new PropertyValueFactory("traceability"));
        c7.setCellValueFactory(new PropertyValueFactory("enrollDate"));
        c8.setCellValueFactory(new PropertyValueFactory("enrolledUser"));
    }
    
    @FXML
    private void setStockColumnName()
    {
        c1.setText("StockId");
        c2.setText("Stock_Desc");
        c3.setText("Product_Id");
        c4.setText("Steps");
        c5.setText("status");
        c6.setText("actions");
        c7.setText("traceability");
        c8.setText("enrollDate");
        c9.setText("enrolledUser");
    }
    
    @FXML
    private void setStockRowValues ()
    {
        c1.setCellValueFactory(new PropertyValueFactory("stockId"));
        c2.setCellValueFactory(new PropertyValueFactory("stock_Desc"));
        c3.setCellValueFactory(new PropertyValueFactory("product_Id"));
        c4.setCellValueFactory(new PropertyValueFactory("Steps"));
        c5.setCellValueFactory(new PropertyValueFactory("status"));
        c6.setCellValueFactory(new PropertyValueFactory("actions"));
        c7.setCellValueFactory(new PropertyValueFactory("traceability"));
        c8.setCellValueFactory(new PropertyValueFactory("enrollDate"));
        c9.setCellValueFactory(new PropertyValueFactory("enrolledUser"));
    }
    
    @FXML
    private void setTraceabilityColumnName()
    {
        c1.setText("Overview_Id");
        c2.setText("Product_Id");
        c3.setText("Stock_Id");
    }
    
    @FXML
    private void setTraceabilityRowValues ()
    {
        c1.setCellValueFactory(new PropertyValueFactory("overviewId"));
        c2.setCellValueFactory(new PropertyValueFactory("productId"));
        c3.setCellValueFactory(new PropertyValueFactory("stockId"));
    }
    
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent;
    }

    @Override
    public void setInitData(User user) {
        
    }

    @Override
    public void setTreeView(User user) {
       
    }
    
    @FXML
    public void viewProducts()
    {
        ObservableList<Product> products = database.GetAllProducts(this.filter);
        setProductColumnName();
        table.setItems(products);
        setProductRowValues();
        table.getColumns().setAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
    }
    
    @FXML
    public void viewOverviews()
    {
        ObservableList<Overview> overviews = database.GetAllOverviews(this.filter);
        setOverviewColumnName();
        table.setItems(overviews);
        setOverviewRowValues();
        table.getColumns().setAll(c1, c2, c3, c4, c5, c6, c7, c8);
    }
    
    @FXML
    public void viewStocks()
    {
        ObservableList<Stock> stock = database.GetAllStocks(this.filter);
        setStockColumnName();
        table.setItems(stock);
        setStockRowValues();
        table.getColumns().setAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
    }
    
    @FXML
    public void viewTraceability()
    {
        ObservableList<Traceability> traceability = database.GetAllTraceability(this.filter);
        setTraceabilityColumnName();
        table.setItems(traceability);
        setTraceabilityRowValues();
        table.getColumns().setAll(c1, c2, c3);
    }

    
    @Override
    public void setInitialData(User user, String screen, TreeView<String> treeview) {
        
        this.user = user;
        lblWelcome.setText("Welcome " + user.getUser());
        this.viewPane = screen;
        
        if(screen.equals(productScene))
        {
            viewProducts();
        }
        
        if(screen.equals(overviewScene))
        {
            viewOverviews();
        }
        
        if(screen.equals(stockScene))
        {
            viewStocks();
        }
        
        if(screen.equals("Traceability"))
        {
            viewTraceability();
        }
         
          Util util = new Util();
          TreeItem<String> root = util.setTreeView(user);
          treeview1.setRoot(root);
    }

    public void goToDashboard(ActionEvent action) {
        myController.loadScreen(ScreenNavigator.dashboardScreenId, ScreenNavigator.dashboardScreeFile, this.user, null, null);
        myController.setScreen(ScreenNavigator.dashboardScreenId);
    }
        
    public void filter (ActionEvent event)
    {
        this.filter = txtFilter.getText();
        table.getItems().clear();
        if(this.viewPane.equals(productScene))
        {
            viewProducts();
        }
        if(this.viewPane.equals(overviewScene))
        {
            viewOverviews();
        }
        if(this.viewPane.equals(stockScene))
        {
            viewStocks();
        }
    }
}
