/**
 * JAINAM SHETH
 * FINAL PROJECT
 * 991549128
 */
package finalproject;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Laptop
 */
public class DisplayStage extends Stage {
    
    public DisplayStage(String search)
    {
        setScene(addScene(search));
    }
    
    private Scene addScene(String search)
    {
        TextArea txtDisplay=new TextArea(search);
        Pane pane=new Pane(txtDisplay);      
        Scene scene=new Scene(pane,400,400);
        return scene;
    }
    
    
}
