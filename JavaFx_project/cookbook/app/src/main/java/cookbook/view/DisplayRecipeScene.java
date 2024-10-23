package cookbook.view;

import cookbook.Cookbook;
import cookbook.controller.CommentController;
import cookbook.controller.SendRecipeController;
import cookbook.model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *  This class shows the recipe's information in a formatted shape for the user.
 */
public class DisplayRecipeScene implements Initializable {

  @FXML
  private Button deleteButton;
  @FXML
  private Button editButton;
  @FXML
  private TextArea mycomment;
  @FXML
  private Label myDate;
  @FXML
  private Label myusername;
  @FXML
  private Button CancelCommentButton;
  @FXML
  private Label CharactersLeftTextLabel;
  @FXML
  private TextArea CommentTextField;
  @FXML
  private GridPane CommentsGridPane;
  @FXML
  private Label NumberCharactersLeftLabel;
  @FXML
  private Label NumberOfCommentsLabel;
  @FXML
  private Circle ProfilePictureCircle;
  @FXML
  private Text RecipeDetails;
  @FXML
  private Text RecipeIngredients;
  @FXML
  private Text RecipeName;
  @FXML
  private Text RecipeShortDescription;
  @FXML
  private ImageView ReturnButton;
  @FXML
  private Text ServingsText;
  @FXML
  private Button SubmitCommentButton;
  @FXML
  private Text TimeCookText;
  @FXML
  private Text TimePrepareText;
  @FXML
  private Text RecipeTags;
  @FXML
  private Button addTagButton;
  @FXML
  private ImageView addTagIcon;
  @FXML
  private AnchorPane ap;
  @FXML
  private ScrollPane Scrollpane;
  @FXML
  private HBox CharacterCountHBox;

  private Recipe recipe;
  private AnchorPane parentAnchorPane;

  @FXML
  private Button addButton;
  @FXML
  private Button addPlan;
  @FXML
  private Button cancelButton;
  @FXML
  private ChoiceBox<String> dayBox;
  @FXML
  private Label dayLabel;
  @FXML
  private ChoiceBox<WeeklyDinnerList> weekBox;
  @FXML
  private Label weekLabel;
  @FXML
  private Button sendRecipe;
  private Node previousScene;

  @FXML
  private Button EditRecipeButton;
  @FXML
  private Button FavouriteRecipeButton;
  @FXML
  private ImageView FavButtonIcon;

  @FXML
  private Button setPersons;
  @FXML
  private ChoiceBox<Integer> numberOfPersons;

  private User user;

  private ObservableList<WeeklyDinnerList> weeklyList;
  private String[] days = {"Monday", "Tuesday", "Wednesday",
    "Thursday", "Friday", "Saturday", "Sunday"};

  private int recipe_id;
  private DisplayRecipeScene controller;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    for (int i = 2; i <= 16; i += 2) {
      numberOfPersons.getItems().add(i);
    }

    final double speed = 0.006;
    user = Session.getCurrentUser();
    dayBox.setItems(FXCollections.observableArrayList(days));

    // Writing comments initialization functions
    try {
      writeCommentsInitialize();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // This fixes the pages scroll speed
    Scrollpane.getContent().setOnScroll(scrollEvent -> {
      double deltaY = scrollEvent.getDeltaY() * speed;
      Scrollpane.setVvalue(Scrollpane.getVvalue() - deltaY);
    });
    // This is the return button
    ReturnButton.setOnMouseClicked(event -> {
      transitionPreviousScene();
    });

    try {
      QueryMaker queryMaker = new QueryMaker();
      weeklyList = queryMaker.retrieveWeeklyListObjects(user);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    weekBox.setItems(weeklyList);
    weekBox.setConverter(new StringConverter<WeeklyDinnerList>() {
      @Override
      public String toString(WeeklyDinnerList week) {
        return week != null && week.getWeekName() != null ? week.getWeekName() : "";
      }

      @Override
      public WeeklyDinnerList fromString(String string) {
        return null;
      }
    });

    weekLabel.setVisible(false);
    dayLabel.setVisible(false);
    weekBox.setVisible(false);
    dayBox.setVisible(false);
    addButton.setVisible(false);
    cancelButton.setVisible(false);

    FavouriteRecipeButton.setOnMouseClicked(event -> {
      User user = Session.getCurrentUser();
      int user_id = user.getUserId();

      // check whether it is a favorite recipe or not
      DataQuery db = new DataQuery();
      boolean fav = false;
      try {
        fav = db.isFavorite(user_id, recipe_id);
      } catch (SQLException e) {
      }


      // set icon
      Image image;
      db = new DataQuery();
      if (!fav) {
        db.insertFavorite(user_id, recipe_id);
        image = new Image(getClass().getResource("/menuIcons/star-gold.png").toExternalForm());
      } else {
        db.removeFavorite(user_id, recipe_id);
        image = new Image(getClass().getResource("/menuIcons/star.png").toExternalForm());
      }
      FavButtonIcon.setImage(image);
    });
  }

  /**
   * This initializes the field where you can write comments and submit to the database.

   * @throws SQLException Failed to make a query using QueryMaker or failed to check if favorite exists.
   */
  private void writeCommentsInitialize() throws SQLException {
    QueryMaker qm = new QueryMaker();
    Color noMoreCharactersAvailableColor = Color.web("#ff845f");
    Color thereAreCharactersAvailableColor = Color.web("#ffffff");

    // Responsive comment field:
    // Updates the character count in the comment writing section
    // Updates disabled buttons
    // Changes the color of character count to red if it is using an invalid amount
    // of characters
    CommentTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      int length = CommentTextField.textProperty().length().get();
      final int limit = 1000;
      NumberCharactersLeftLabel.setText(Integer.toString(limit - length));

      // Make buttons visible if there are characters inside the text field
      if (length > 0) {
        if (length <= limit) {
          SubmitCommentButton.setDisable(false);
          CancelCommentButton.setDisable(false);
          CharacterCountHBox.setDisable(false);
          NumberCharactersLeftLabel.setTextFill(thereAreCharactersAvailableColor);
          CharactersLeftTextLabel.setTextFill(thereAreCharactersAvailableColor);
        } else {
          SubmitCommentButton.setDisable(true);
          NumberCharactersLeftLabel.setTextFill(noMoreCharactersAvailableColor);
          CharactersLeftTextLabel.setTextFill(noMoreCharactersAvailableColor);
        }
      } else {
        SubmitCommentButton.setDisable(true);
        CancelCommentButton.setDisable(true);
        CharacterCountHBox.setDisable(true);
      }
    });

    // Remark button: Sends the comment to be saved to the database
    SubmitCommentButton.setOnMouseClicked(event -> {
      // Get original message
      String comment = CommentTextField.getText();

      // Blank the comment field
      CommentTextField.setText("");

      // Update button responsiveness
      SubmitCommentButton.setDisable(true);
      CancelCommentButton.setDisable(true);
      CharacterCountHBox.setDisable(true);

      qm.sendComment(comment, this.recipe);
      reloadComments();
    });
    SubmitCommentButton.setOnMouseEntered(event -> {
      SubmitCommentButton.setStyle("-fx-background-radius: 20; -fx-background-color: #d4ea7b");
    });
    SubmitCommentButton.setOnMouseExited(event -> {
      SubmitCommentButton.setStyle("-fx-background-radius: 20; -fx-background-color: #BBDD2C");
    });
    FavouriteRecipeButton.setOnMouseClicked(event -> {
      User user = Session.getCurrentUser();
      int user_id = user.getUserId();

      // check weather it is a favorite recipe or not
      DataQuery db = new DataQuery();
      boolean fav = false;
      try {
        fav = db.isFavorite(user_id, recipe_id);
      } catch (SQLException e) {
        throw new RuntimeException();
      }


      // set icon
      Image image;
      db = new DataQuery();
      if (!fav) {
        db.insertFavorite(user_id, recipe_id);
        image = new Image(getClass().getResource("/menuIcons/star-gold.png").toExternalForm());
      } else {
        db.removeFavorite(user_id, recipe_id);
        image = new Image(getClass().getResource("/menuIcons/star.png").toExternalForm());
      }
      FavButtonIcon.setImage(image);
    });

    addTagButton.setOnAction(event -> {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AddCustomTag editor;

        fxmlLoader.setLocation(Cookbook.class.getResource("AddCustomTag.fxml"));

        Parent window = fxmlLoader.load();
        editor = fxmlLoader.getController();
        editor.initialization(recipe);

        Stage stage = new Stage();
        stage.setTitle("Select Custom Tag");
        stage.setScene(new Scene(window, 400, 550));
        stage.show();

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    // Cancel button: Clears comment field if clicked
    CancelCommentButton.setOnMouseClicked(e -> {
      CommentTextField.setText("");
      SubmitCommentButton.setDisable(true);
      CancelCommentButton.setDisable(true);
      CharacterCountHBox.setDisable(true);
    });
  }

  /**
   * This updates the scene's controller data to contain the provided recipe in the params.

   * @param recipe Recipe object to provide data for the scene.
   * @param parentAnchorPane This allows the previous scene to be called and to be returned to.
   * @throws SQLException Failed to check favorites.
   */
  public void addRecipeObject(Recipe recipe, AnchorPane parentAnchorPane) throws SQLException {
    QueryMaker qm = new QueryMaker();
    this.recipe = recipe;
    this.parentAnchorPane = parentAnchorPane;
    this.previousScene = previousScene;

    // Always load comments after recipe exists
    // reloadComments();

    RecipeName.setText(recipe.getName());
    RecipeShortDescription.setText(recipe.getDescription());
    RecipeDetails.setText(recipe.getInstructions());
    ServingsText.setText(String.valueOf(recipe.getServings()));

    // Uncomment below if recipe class prep time and cook time attributes are float
    // type
    // TimePrepareText.setText(floatToMinutes(recipe.getPrepTime()));
    // TimeCookText.setText(floatToMinutes(recipe.getCookTime()));

    // For int type below
    TimePrepareText.setText(String.valueOf(recipe.getPrepTime()));
    TimeCookText.setText(String.valueOf(recipe.getCookTime()));

    User user = Session.getCurrentUser();
    int user_id = user.getUserId();
    int recipe_id = recipe.getId();
    this.recipe_id = recipe_id;
    // check weather it is a favorite recipe or not
    DataQuery db = new DataQuery();
    boolean fav = false;
    try {
      fav = db.isFavorite(user_id, recipe_id);
    } catch (SQLException e) {
    }

    // set icon
    Image image;
    if (fav) {
      image = new Image(getClass().getResource("/menuIcons/star-gold.png").toExternalForm());
    } else {
      image = new Image(getClass().getResource("/menuIcons/star.png").toExternalForm());
    }
    FavButtonIcon.setImage(image);

    // Eldaras, this is for tags, uses the method from QueryMaker that I made.
    try {
      List<String> customTags = qm.getCustomTagsForRecipe(recipe.getId(), user.getUserId());
      String tagsText = String.join(", ", customTags);
      RecipeTags.setText(tagsText);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return;
  }

  public void addIngredients() {
    try {
      QueryMaker qm = new QueryMaker();
      ObservableList<Ingredient> ingredientsList = qm.retrieveIngredients(recipe.getId());
      StringBuilder sb = new StringBuilder();

      for (Ingredient i : ingredientsList) {
        sb.append(i.getIngredientName() + " | " + i.getQty() + " | " + i.getMeasurement() + "\n");
      }

      RecipeIngredients.setText(sb.toString());

    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void addThisScenesController(DisplayRecipeScene controller) {
    this.controller = controller;
  }

  @FXML
  private void transitionEditScene(ActionEvent event) {
    FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("RecipeEditor.fxml"));
    RecipeEditor editor;
    Node n;

    System.out.println(Cookbook.class.getResource("DisplayRecipeScene.fxml"));

    // load first
    try {
      n = fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // then get controller
    editor = fxmlLoader.getController();
    editor.initialize(recipe);

    AnchorPane.setTopAnchor(n, 0.0);
    AnchorPane.setRightAnchor(n, 0.0);
    AnchorPane.setBottomAnchor(n, 0.0);
    AnchorPane.setLeftAnchor(n, 0.0);

    parentAnchorPane.getChildren().clear();
    parentAnchorPane.getChildren().add(n);

  }

  @FXML
  void sendRecipe(ActionEvent event) throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader(Cookbook.class.getResource("SendRecipe.fxml"));
    Parent sendRecipeRoot = loader.load();
    SendRecipeController sendController = loader.getController();

    sendController.setRecipe(recipe);

    Scene sendRecipeScene = new Scene(sendRecipeRoot);
    Stage sendRecipeStage = new Stage();
    sendRecipeStage.setScene(sendRecipeScene);
    sendRecipeStage.showAndWait();
  }

  // Return to previous scene
  @FXML
  private void transitionPreviousScene() {
    FXMLLoader fxmlLoader = new FXMLLoader(Cookbook.class.getResource("RecipesScene.fxml"));
    Node n;

    try {
      n = fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    AnchorPane.setTopAnchor(n, 0.0);
    AnchorPane.setRightAnchor(n, 0.0);
    AnchorPane.setBottomAnchor(n, 0.0);
    AnchorPane.setLeftAnchor(n, 0.0);

    ap.getChildren().clear();
    ap.getChildren().add(n);
  }

  /**
   * This method reloads the comments displayed in the comments section.
   */
  public void reloadComments() {
    int row = 1;
    int col = 0;
    // QueryMaker qm = null;

    // Clear the grid to update for new comments
    CommentsGridPane.getChildren().clear();

    try {
      // Get all comments from this recipe using QueryMaker
      // qm = new QueryMaker();
      QueryMaker qm = new QueryMaker();
      ObservableList<Comment> allComments = qm.getThisRecipesComments(recipe);
      NumberOfCommentsLabel.setText(Integer.toString(allComments.size()));

      System.out.println("We reached this point but i dont know whats happening " + allComments.size());

      // For all comments found, spawn the comment with a specific fxml design
      for (int i = 0; i < allComments.size(); i++) {
        // Load the fxml design onto a new AnchorPane
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Cookbook.class.getResource("viewComment.fxml"));
        AnchorPane anchorPane = null;
        anchorPane = fxmlLoader.load();

        // Obtain the controller from the respective fxml design and add the details of
        // the comments
        CommentController comController = fxmlLoader.getController();
        comController.setData(allComments.get(i), anchorPane, controller);

        // Grid pane commands
        CommentsGridPane.add(anchorPane, col, row++);
        CommentsGridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        CommentsGridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        CommentsGridPane.setMaxWidth(Region.USE_PREF_SIZE);

        CommentsGridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        CommentsGridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        CommentsGridPane.setMaxHeight(Region.USE_PREF_SIZE);
      }

    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  void add(ActionEvent event) {
    String day = dayBox.getValue();
    WeeklyDinnerList week = weekBox.getValue();
    if (day != null && week != null) {
      try {
        QueryMaker queryMaker = new QueryMaker();
        queryMaker.insertDailyRecipe(week.getWeekId(), day, recipe_id);
        cancel(event);
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @FXML
  void addPlan(ActionEvent event) {
    weekLabel.setVisible(true);
    dayLabel.setVisible(true);
    weekBox.setVisible(true);
    dayBox.setVisible(true);
    addButton.setVisible(true);
    cancelButton.setVisible(true);
  }

  @FXML
  void cancel(ActionEvent event) {
    weekLabel.setVisible(false);
    dayLabel.setVisible(false);
    weekBox.setVisible(false);
    dayBox.setVisible(false);
    addButton.setVisible(false);
    cancelButton.setVisible(false);

    dayBox.setValue(null);
    weekBox.setValue(null);
  }

  @FXML
  void setPersonsEvt(ActionEvent event) {
    Integer selectedItem = numberOfPersons.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
      try {
        QueryMaker qm = new QueryMaker();
        ObservableList<Ingredient> ingredientsList = qm.retrieveIngredients(recipe.getId());
        StringBuilder sb = new StringBuilder();

        for (Ingredient i : ingredientsList) {
          sb.append(i.getIngredientName() + " | " + i.getQty()*selectedItem + " | " + i.getMeasurement() + "\n");
        }

        RecipeIngredients.setText(sb.toString());

      } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}

