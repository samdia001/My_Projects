package cookbook.controller;

import java.sql.SQLException;

import cookbook.model.DataQuery;
import cookbook.model.QueryMaker;
import cookbook.model.Recipe;
import cookbook.model.Session;
import cookbook.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class SendRecipeController {

    @FXML
    private AnchorPane ap;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<User> reciever;

    @FXML
    private TextArea textArea;

    @FXML
    private Button sendButton;

    private Recipe recipe;
   
    private ObservableList<User> userObservableList;

    public void initialize() throws SQLException {
      QueryMaker queryMaker;
      queryMaker = new QueryMaker();
      userObservableList = queryMaker.getAllusers();
  
      reciever.setItems(userObservableList);
      reciever.setConverter(new StringConverter<User>() {
          @Override
          public String toString(User user) {
            return user != null && user.getUsername() != null ? user.getUsername() : "";
          }
  
          @Override
          public User fromString(String string) {
              return null; 
          }
      }); 
    }

    public void setRecipe(Recipe selectedRecipe) {
      recipe = selectedRecipe;
      System.out.println(recipe.getName());
    }

    @FXML
    void cancel(ActionEvent event) {
      Stage stage = (Stage) cancelButton.getScene().getWindow();
      stage.close();
    }

    @FXML
    void send(ActionEvent event) {
      User sender = Session.getCurrentUser();
      User receiver = reciever.getValue();
      if (receiver != null) {
        int receiverId = receiver.getUserId();
        int recipeId = recipe.getId();
        String message = textArea.getText();
    
        DataQuery sendQuery = new DataQuery();
        sendQuery.addMessage(sender.getUserId(), receiverId, recipeId, message);

        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
        }
      }
}
