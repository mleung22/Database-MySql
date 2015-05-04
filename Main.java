package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			Database database = new Database();

			primaryStage.setTitle("Database Manipulation");

			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10.0);

			Scene scene = new Scene(grid, 300, 250);
			primaryStage.setScene(scene);

			Text scenetitle = new Text("Database Manipulation");
			Button insertDataBtn = new Button("Insert Data into Database");
			Button deleteDataBtn = new Button("Delete Data from Database");
			Button searchAllBtn = new Button("View Database");
			Button findPersonBtn = new Button(
					"Find Person in Database by Last Name");

			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

			VBox mainGridButtonsVBox = new VBox();
			mainGridButtonsVBox.setAlignment(Pos.CENTER);
			mainGridButtonsVBox.setSpacing(5);
			mainGridButtonsVBox.getChildren().addAll(insertDataBtn,
					deleteDataBtn, searchAllBtn, findPersonBtn);

			grid.add(scenetitle, 3, 0);
			grid.add(mainGridButtonsVBox, 3, 1);

			insertDataBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					Stage insertDataStage = new Stage();
					insertDataStage.setTitle("Data Insertion");

					GridPane insertDataGrid = new GridPane();
					insertDataGrid.setAlignment(Pos.CENTER);
					insertDataGrid.setVgap(2);

					Scene insertDataScene = new Scene(insertDataGrid, 300, 250);
					insertDataStage.setScene(insertDataScene);

					insertDataStage.show();

					Label firstNameLabel = new Label("First Name:");
					Label lastNameLabel = new Label("Last Name:");
					Label ageLabel = new Label("Age:");
					Label ssnLabel = new Label("Social Security Number:");
					Label ccLabel = new Label("Credit Card:");
					TextField firstNameText = new TextField();
					TextField lastNameText = new TextField();
					TextField ageText = new TextField();
					TextField ssnText = new TextField();
					TextField ccText = new TextField();
					Button enterInsertDataBtn = new Button("Enter Data");
					Button closeBtn = new Button("Close");

					VBox labelVBox = new VBox();
					VBox textFieldVBox = new VBox();
					HBox buttonHBox = new HBox();

					labelVBox.setAlignment(Pos.CENTER_RIGHT);
					textFieldVBox.setAlignment(Pos.CENTER_LEFT);
					buttonHBox.setAlignment(Pos.CENTER);

					labelVBox.setSpacing(8);
					buttonHBox.setSpacing(30);

					labelVBox.getChildren().addAll(firstNameLabel,
							lastNameLabel, ageLabel, ssnLabel, ccLabel);
					textFieldVBox.getChildren().addAll(firstNameText,
							lastNameText, ageText, ssnText, ccText);
					buttonHBox.getChildren().addAll(enterInsertDataBtn,
							closeBtn);

					insertDataGrid.add(labelVBox, 3, 1);
					insertDataGrid.add(textFieldVBox, 4, 1);
					insertDataGrid.add(buttonHBox, 4, 6);

					enterInsertDataBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String firstName = firstNameText.getText();
									String lastName = lastNameText.getText();
									int age = Integer.parseInt(ageText
											.getText());
									String ssn = ssnText.getText();
									String cc = ccText.getText();

									try {

										Person personInfo = new Person(
												firstName, lastName, age, ssn,
												cc);

										System.out.println(database
												.insertPerson(personInfo));

										firstNameText.setText("");
										lastNameText.setText("");
										ageText.setText("");
										ssnText.setText("");
										ccText.setText("");

									} catch (SQLException e1) {
										e1.printStackTrace();
									} catch (InvalidSsnException e1) {
										Alert ssnAlert = new Alert(
												AlertType.INFORMATION);
										ssnAlert.setTitle("Invalid Input");
										ssnAlert.setHeaderText("Invalid Social Security Number"
												+ "\nYour input: "
												+ ssnText.getText());
										ssnAlert.setContentText("Re-enter with atleast 9 digits");
										ssnAlert.showAndWait();
									} catch (InvalidCCException e1) {
										Alert ccAlert = new Alert(
												AlertType.INFORMATION);
										ccAlert.setTitle("Invalid Input");
										ccAlert.setHeaderText("Invalid Credit Card Info"
												+ "\nYour input: "
												+ ccText.getText());
										ccAlert.setContentText("Re-enter with atleast 16 digits");
										ccAlert.showAndWait();
									}
								}

							});

					closeBtn.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							insertDataStage.close();
						}

					});

				}
			});

			deleteDataBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Stage deleteDataStage = new Stage();
					deleteDataStage.setTitle("Delete Data");

					GridPane deleteDataGrid = new GridPane();
					deleteDataGrid.setAlignment(Pos.CENTER);
					deleteDataGrid.setVgap(10);
					deleteDataGrid.setHgap(5);

					Scene deleteDataScene = new Scene(deleteDataGrid, 300, 250);
					deleteDataStage.setScene(deleteDataScene);

					deleteDataStage.show();

					Text scenetitle = new Text("Enter the first and last name");
					Label firstNameLabel = new Label("First Name:");
					Label lastNameLabel = new Label("Last Name:");
					TextField firstNameText = new TextField();
					TextField lastNameText = new TextField();
					Button enterDeleteDataBtn = new Button("Delete Data");
					Button closeBtn = new Button("Close");

					scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,
							12));

					VBox labelVBox = new VBox();
					VBox textFieldVBox = new VBox();
					HBox buttonHBox = new HBox();

					labelVBox.setAlignment(Pos.CENTER_RIGHT);
					textFieldVBox.setAlignment(Pos.CENTER_LEFT);
					buttonHBox.setAlignment(Pos.CENTER);

					labelVBox.setSpacing(8);
					buttonHBox.setSpacing(30);

					labelVBox.getChildren().addAll(firstNameLabel,
							lastNameLabel);
					textFieldVBox.getChildren().addAll(firstNameText,
							lastNameText);
					buttonHBox.getChildren().addAll(enterDeleteDataBtn,
							closeBtn);

					deleteDataGrid.add(scenetitle, 4, 0);
					deleteDataGrid.add(labelVBox, 3, 1);
					deleteDataGrid.add(textFieldVBox, 4, 1);
					deleteDataGrid.add(buttonHBox, 4, 3);

					enterDeleteDataBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String firstName = firstNameText.getText();
									String lastName = lastNameText.getText();

									try {
										System.out.println(database
												.deletePerson(firstName,
														lastName));

										firstNameText.setText("");
										lastNameText.setText("");
									} catch (SQLException e1) {
										e1.printStackTrace();
									} catch (InvalidSsnException e1) {
										e1.printStackTrace();
									} catch (InvalidCCException e1) {
										e1.printStackTrace();
									}

								}

							});

					closeBtn.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							deleteDataStage.close();
						}

					});

				}
			});

			searchAllBtn.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					Stage searchAllStage = new Stage();
					searchAllStage.setTitle("Database");

					GridPane searchAllGrid = new GridPane();
					searchAllGrid.setAlignment(Pos.CENTER);
					searchAllGrid.setHgap(5.0);
					searchAllGrid.setVgap(5.0);

					Scene searchAllScene = new Scene(searchAllGrid, 950, 450);
					searchAllStage.setScene(searchAllScene);

					searchAllStage.show();

					Label firstNameLabel = new Label("First Name");
					Label lastNameLabel = new Label("Last Name");
					Label ageLabel = new Label("Age");
					Label ssnLabel = new Label("Social Security");
					Label ccLabel = new Label("Credit Card");
					ListView<Person> firstNameListView = new ListView<Person>();
					ListView<Person> lastNameListView = new ListView<Person>();
					ListView<Person> ageListView = new ListView<Person>();
					ListView<Person> ssnListView = new ListView<Person>();
					ListView<Person> ccListView = new ListView<Person>();

					HBox firstNameLabelHBox = new HBox();
					HBox lastNameLabelHBox = new HBox();
					HBox ageLabelHBox = new HBox();
					HBox ssnLabelHBox = new HBox();
					HBox ccLabelHBox = new HBox();

					firstNameLabelHBox.setAlignment(Pos.CENTER);
					lastNameLabelHBox.setAlignment(Pos.CENTER);
					ageLabelHBox.setAlignment(Pos.CENTER);
					ssnLabelHBox.setAlignment(Pos.CENTER);
					ccLabelHBox.setAlignment(Pos.CENTER);

					firstNameLabelHBox.getChildren().add(firstNameLabel);
					lastNameLabelHBox.getChildren().add(lastNameLabel);
					ageLabelHBox.getChildren().add(ageLabel);
					ssnLabelHBox.getChildren().add(ssnLabel);
					ccLabelHBox.getChildren().add(ccLabel);

					searchAllGrid.add(firstNameLabelHBox, 1, 1);
					searchAllGrid.add(lastNameLabelHBox, 2, 1);
					searchAllGrid.add(ageLabelHBox, 3, 1);
					searchAllGrid.add(ssnLabelHBox, 4, 1);
					searchAllGrid.add(ccLabelHBox, 5, 1);
					searchAllGrid.add(firstNameListView, 1, 2);
					searchAllGrid.add(lastNameListView, 2, 2);
					searchAllGrid.add(ageListView, 3, 2);
					searchAllGrid.add(ssnListView, 4, 2);
					searchAllGrid.add(ccListView, 5, 2);

					ObservableList<Person> firstNameOL;
					ObservableList<Person> lastNameOL;
					ObservableList<Person> ageOL;
					ObservableList<Person> ssnOL;
					ObservableList<Person> ccOL;
					try {
						firstNameOL = FXCollections.observableList(database
								.findAllPeopleByFirstName());
						lastNameOL = FXCollections.observableList(database
								.findAllPeopleByLastName());
						ageOL = FXCollections.observableList(database
								.findAllPeopleByAge());
						ssnOL = FXCollections.observableList(database
								.findAllPeopleBySsn());
						ccOL = FXCollections.observableList(database
								.findAllPeopleByCC());

						firstNameListView.setItems(firstNameOL);
						lastNameListView.setItems(lastNameOL);
						ageListView.setItems(ageOL);
						ssnListView.setItems(ssnOL);
						ccListView.setItems(ccOL);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			});

			findPersonBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					Stage findPersonStage = new Stage();
					findPersonStage.setTitle("Find person by last name");

					GridPane findPersonGrid = new GridPane();
					findPersonGrid.setAlignment(Pos.CENTER);
					findPersonGrid.setHgap(5.0);
					findPersonGrid.setVgap(5.0);

					Scene findPersonScene = new Scene(findPersonGrid, 300, 250);
					findPersonStage.setScene(findPersonScene);

					findPersonStage.show();

					Text scenetitle = new Text("Enter the last name");
					Label lastNameLabel = new Label("Last Name:");
					TextField lastNameText = new TextField();
					Button enterFindPersonBtn = new Button("Find Data");
					Button closeBtn = new Button("Close");

					scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,
							12));

					HBox lastNameLabelHB = new HBox();
					HBox lastNameTextHB = new HBox();
					HBox buttonHBox = new HBox();

					buttonHBox.setSpacing(35);

					lastNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					lastNameTextHB.setAlignment(Pos.CENTER_LEFT);

					lastNameLabelHB.getChildren().add(lastNameLabel);
					lastNameTextHB.getChildren().add(lastNameText);
					buttonHBox.getChildren().addAll(enterFindPersonBtn,
							closeBtn);

					findPersonGrid.add(scenetitle, 4, 0);
					findPersonGrid.add(lastNameLabelHB, 3, 2);
					findPersonGrid.add(lastNameTextHB, 4, 2);
					findPersonGrid.add(buttonHBox, 4, 3);

					enterFindPersonBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String lastName = lastNameText.getText();

									try {
										System.out.println(database
												.findPersonByLastName(lastName));
									} catch (SQLException e1) {
										e1.printStackTrace();
									} catch (InvalidSsnException e1) {
										e1.printStackTrace();
									} catch (InvalidCCException e1) {
										e1.printStackTrace();
									}
								}

							});

					closeBtn.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							findPersonStage.close();
						}

					});

				}
			});

			grid.setGridLinesVisible(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
