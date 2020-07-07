package ScreenManager;

import Models.Product;
import Models.User;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeView;

public interface ControlledScreen {
    public void setScreenParent(ScreenController screenPage);
    public void setInitData(User user);
    public void setTreeView(User user);
    
    public void setInitialData(User user, String scene, TreeView<String> treeview);
    
//    public TableCell<Product, Void> call(final TableColumn<Product, Void> param);
}
