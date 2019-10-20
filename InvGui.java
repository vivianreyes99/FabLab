


package HIP_Inventory_Project;

import HIP_Inventory_Project.Item;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.lang.Object;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class InvGui extends Application {

    private TableView<Item> table;
    //private TableView<Item> table1;
    private ObservableList<Item> data;
    private ChoiceDialog<Integer> dialog;
    private Text actionStatus;
    private List<Integer> quantityDropDownList;

    private final TableView<Person> table1 = new TableView<>();
    private final ObservableList<Person> data1 =
            FXCollections.observableArrayList( new Person ("Baadal", "battery", "2", "5/16/17"));



    public static void main(String[] args) {
        launch(args);
        Application.launch(args);
    }


    public void start(Stage primaryStage) {
        //The Stage class is used to construct the main window of the application.
        //peep top left of run window


        primaryStage.setTitle("Inventory");

        // Items label
        Label label = new Label("FABLAB Inventory"); //the title at the top
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));

        VBox labelHb = new VBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties
        //The following code creates table view for Book data and populates it from an ObservableList collection:
        table = new TableView<>();
        data = getInitialTableData();
        table.setItems(data);
        table.setEditable(false); //mark true if u want the user to be able to edit the table (which we don't want)

        TableColumn titleCol = new TableColumn("Item Name");
        titleCol.setCellValueFactory(new PropertyValueFactory<Item, String>("title")); //DISPLAYS FIRST COLUMN
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());


        // 4/2/17 This approach is for Number type.  Does doesn't look required:  setCellFactory.
        TableColumn<Item, Number> authorCol = new TableColumn<Item, Number>("Quantity");
        authorCol.setCellValueFactory(new PropertyValueFactory<Item, Number>("quantity"));


        //TableColumn locCol = new TableColumn("Location");
        //locCol.setCellValueFactory(new PropertyValueFactory<Item, String>("location"));
        //locCol.setCellFactory(TextFieldTableCell.forTableColumn());

        table.getColumns().setAll(titleCol, authorCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Checkin and checkout buttons
        Button checkOutBtn = new Button("Checkout");
        checkOutBtn.setOnAction(new CheckOutButtonListener()); //when the button is clicked

        Button checkInBtn = new Button("Checkin");
        checkInBtn.setOnAction(new CheckInButtonListener());
        HBox buttonHb = new HBox(10);







        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(checkOutBtn, checkInBtn); //this is how it knows checkOut is the left button and checkIn is the right button

        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);


        table.getColumns().setAll(titleCol, authorCol);
        table.setPrefWidth(200);
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Vbox
        VBox vbox = new VBox(50);
        vbox.setPadding(new Insets(25, 25, 25, 25));;
        vbox.getChildren().addAll(labelHb, table, buttonHb, actionStatus);

        // Scene
        Scene scene = new Scene(vbox, 1000, 1000); // w x h
        primaryStage.setScene(scene);
        primaryStage.show();

        // Select the first row
        table.getSelectionModel().select(0);
        Item item = table.getSelectionModel().getSelectedItem();
        actionStatus.setText(item.toString());


        //////////////////////////////////////

        primaryStage.setTitle("hi");

        // Items label
        Label label1 = new Label("Check Out History"); //the title at the top
        label1.setTextFill(Color.DARKBLUE);
        label1.setFont(Font.font("Calibri", FontWeight.BOLD, 36));

        VBox labelHb1 = new VBox();
        labelHb1.setAlignment(Pos.CENTER);
        labelHb1.getChildren().add(label1);

        // Table view, data, columns and properties
        //The following code creates table view for Book data and populates it from an ObservableList collection:

        table1.setItems(data1);
        table1.setEditable(true); //mark true if u want the user to be able to edit the table (which we don't want)

        TableColumn nameCol = new TableColumn("Name");
        //setCellValueFactory method specifies a cell factory for each column
        nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Name")); //DISPLAYS FIRST COLUMN
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn itemCol = new TableColumn("Item");
        itemCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Item"));
        itemCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn amountCol = new TableColumn("Quantity");
        amountCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Item"));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Item"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());







        //the setItems() or setAll() methods add the data to the table
        table1.getColumns().setAll(nameCol, itemCol, amountCol, dateCol);
        table1.setPrefWidth(200);
        table1.setPrefHeight(200);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table1.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        Label label5 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        hb.getChildren().addAll(label5, textField);
        hb.setSpacing(10);



        // Select the first row
        table1.getSelectionModel().select(0);
        Person item2 = table1.getSelectionModel().getSelectedItem();
        actionStatus.setText(item2.toString());

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(nameCol.getPrefWidth());

        final TextField addItem = new TextField();
        addItem.setMaxWidth(itemCol.getPrefWidth());
        addItem.setPromptText("Item");

        final TextField addAmount = new TextField();
        addAmount.setMaxWidth(amountCol.getPrefWidth());
        addAmount.setPromptText("Quantity");

        final TextField addDate = new TextField();
        addDate.setMaxWidth(amountCol.getPrefWidth());
        addDate.setPromptText("Date");


        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data1.add(new Person(
                    addFirstName.getText(),
                    addItem.getText(),
                    addAmount.getText(), addDate.getText()));
            addFirstName.clear();
            addItem.clear();
            addAmount.clear();
            addDate.clear();
        });

        HBox hbox1 = new HBox(50);
        hbox1.setPadding(new Insets(25));
        hbox1.getChildren().addAll(addFirstName, addItem, addAmount,addDate, addButton);


        // Vbox
        VBox vbox1 = new VBox(50);
        vbox1.setPadding(new Insets(25, 25, 25, 25));;
        vbox1.getChildren().addAll(hb, labelHb, table, buttonHb, actionStatus, labelHb1, table1, hbox1);




        // Scene
        Scene scene1 = new Scene(vbox1, 1000, 550);// w x h
        primaryStage.setScene(scene1);
        primaryStage.show();
    } // start()

    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }

            Item item = data.get(ix);
            actionStatus.setText(item.toString());
        }
    }

    //////////////
    private List<Integer> buildQuantityList(int highestNum) {
        List<Integer> list = new ArrayList<>();
        //adding the numbers from 1 to highestNum to the list
        for (int k = 1; k <= highestNum; k++)
        {
            list.add(k);
        }
        return list;
    }

    private ObservableList<Item> getInitialTableData() {
        //The table view is populated with data from an ObservableList collection. The table’s data is created within the program. The
        // getInitialTableData() method creates Book instances and returns them as ObservableList<Book>.
        List<Item> list = new ArrayList<>();

        list.add(new Item("batteries", 10, 8, "top")); //2nd parameter is the max quantity, 3rd param is current quantity (changeable)
        list.add(new Item("propellers", 35, 34, "bottom"));
        list.add(new Item("chips", 5, 5, "middle"));
        list.add(new Item("USB ports", 3, 3, "bottom"));
        ObservableList<Item> data = FXCollections.observableList(list);
        return data;
    }

    //returns the number the user chooses
    private int displayChoiceDialog(String title, String msg)
    {
        //the first parameter displays as the starting option the first number in the list, (which will be 1)
        dialog = new ChoiceDialog<Integer>(quantityDropDownList.get(0), quantityDropDownList);
        dialog.setTitle(title);
        dialog.setHeaderText(msg);

        Optional<Integer> result = dialog.showAndWait(); //showAndWait() takes care of displaying the whole text box
        Integer selected = -1; //represents the cancel option
        //if they have made a selection and didn't click cancel
        if (result.isPresent()) {

            selected = result.get(); //selected gets whatever number the user clicked
        }
        return selected; //autounboxing the Integer selected to an int
    }


    ////////////
    private class CheckOutButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e) {
            // Get selected row and
//            int ix = table.getSelectionModel().getSelectedIndex();
            Item item = table.getSelectionModel().getSelectedItem(); //gets the item because table is a list of items

//
            //if the quantity of that item is zero (no more of that item) do not allow them to check out an item.
            //display an error message
            if (item.getQuantity() <= 0)
            {
                displayErrorDialogueMessage("Sorry! There are no more " + item.getTitle() + " in stock");
            }
            //otherwise there are still available items so decrease the quantity of that item so that they will have checked out an item
            else
            {

                //Creating a GridPane container
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setVgap(5);
                grid.setHgap(5);
//Defining the Name text field
                final TextField name = new TextField();
                name.setPromptText("Enter your first name.");
                name.setPrefColumnCount(10);
                name.getText();
                GridPane.setConstraints(name, 0, 0);
                grid.getChildren().add(name);
//Defining the Last Name text field
                final TextField lastName = new TextField();
                lastName.setPromptText("Enter your last name.");
                GridPane.setConstraints(lastName, 0, 1);
                grid.getChildren().add(lastName);
//Defining the Comment text field
                final TextField comment = new TextField();
                comment.setPrefColumnCount(15);
                comment.setPromptText("Enter your comment.");
                GridPane.setConstraints(comment, 0, 2);
                grid.getChildren().add(comment);
//Defining the Submit button
                Button submit = new Button("Submit");
                GridPane.setConstraints(submit, 1, 0);
                grid.getChildren().add(submit);
//Defining the Clear button
                Button clear = new Button("Clear");
                GridPane.setConstraints(clear, 1, 1);
                grid.getChildren().add(clear);

                // To make this foolproof, display drop down options that show only the valid or available quantities for that item
                //ie: if there are 5 batteries, then for batteries drop down menu, only show 1, 2, 3, 4, 5 not the numbers greater than that
                //parameter: the biggest possible number out of a list of numbers ranging from 1 through that number
                quantityDropDownList = buildQuantityList(item.getQuantity()); //the list we're working with
                //checkOutQuantity is the number of items the user chooses to check out
                int checkOutQuantity = displayChoiceDialog("Check Out (Take out)", "Select the CHECKOUT quantity");

//                Button nextBtn = new Button("Next");
//                nextBtn.setOnAction(new CheckOutButtonListener()); //when the button is clicked
//
//                HBox buttonHb = new HBox(10);
//
//                buttonHb.setAlignment(Pos.BOTTOM_LEFT);
//                buttonHb.getChildren().addAll(nextBtn); //this is how it knows checkOut is the left button and checkIn is the right button

                //if the user chooses cancel, exit this method
                if (checkOutQuantity == -1)
                {
                    return;
                }
                //OTHERWISE


                //set the quantity of the item to be the current quantity minus the number of items they wanted to check out
                //now there should be checkOutQuantity less in the inventory for that item
                item.setQuantity(item.getQuantity()- checkOutQuantity);
                refreshTable();
                actionStatus.setText(item.getTitle() + " checked out: " + checkOutQuantity); //displays the little message in red at bottom left of screen
                if (table.getItems().size() == 0) {
                    actionStatus.setText("No data in table !");
                    return;
                }
            }
        }
    }


    //////////////
    private class CheckInButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            // Get selected row
//            int ix = table.getSelectionModel().getSelectedIndex();
            Item item = table.getSelectionModel().getSelectedItem(); //gets the item because table is a list of items

            // check if we have reached the maximum and display an alert box
            if (item.getQuantity() >= item.getMaxQuantity()) {
                displayErrorDialogueMessage("The number of items is already at its max quantity of: " + item.getQuantity());
            }
            //otherwise there are still available items so decrease the quantity of that item so that they will have checked out an item
            else
            {
                quantityDropDownList = buildQuantityList(item.getMaxQuantity()-item.getQuantity());
                int checkInQuantity = displayChoiceDialog("Check In (Return item)", "Select the CHECKIN quantity");
                //if the user chooses cancel, exit this method
                if (checkInQuantity == -1)
                {
                    return;
                }
                item.setQuantity(item.getQuantity() + checkInQuantity); //now we are adding items to the list

                refreshTable();
                actionStatus.setText(item.getTitle() + " checked in: " + checkInQuantity); //displays the little message in red at bottom left of screen
                if (table.getItems().size() == 0) {
                    actionStatus.setText("No data in table !");
                    return;
                }
            }
        }
    }




    private void refreshTable() {
        //table.refresh() didn't work so we have to resort to this "hack"
        //This is a hack to display the updated quantity in tableview
        // The two lines below re-renders tableview to show updated quantity
        table.getColumns().get(1).setVisible(false); //make invisible
        table.getColumns().get(1).setVisible(true); //make visible again. analogous to refresh
    }

    public void displayErrorDialogueMessage(String txt)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.showAndWait(); //displays the text box and waits for you to click the ok button
    }
}
}
