package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			Database db = new Database();

			primaryStage.setTitle("Database Manipulation");

			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10.0);

			Scene scene = new Scene(grid, 300, 250);
			primaryStage.setScene(scene);

			Text scenetitle = new Text("Database Manipulation");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 3, 0);

			Button insertDataBtn = new Button("Insert Data into Database");
			HBox insertHB = new HBox();
			insertHB.setAlignment(Pos.CENTER);
			insertHB.getChildren().add(insertDataBtn);
			grid.add(insertHB, 3, 1);

			Button deleteDataBtn = new Button("Delete Data from Database");
			HBox deleteHB = new HBox();
			deleteHB.setAlignment(Pos.CENTER);
			deleteHB.getChildren().add(deleteDataBtn);
			grid.add(deleteHB, 3, 2);

			Button searchAllBtn = new Button("Print out database to console");
			HBox searchHB = new HBox();
			searchHB.setAlignment(Pos.CENTER);
			searchHB.getChildren().add(searchAllBtn);
			grid.add(searchHB, 3, 3);

			Button findPersonBtn = new Button(
					"Find Person in Database by Last Name");
			HBox findPersonLastNameHB = new HBox();
			findPersonLastNameHB.setAlignment(Pos.CENTER);
			findPersonLastNameHB.getChildren().add(findPersonBtn);
			grid.add(findPersonLastNameHB, 3, 4);

			insertDataBtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Stage insertDataStage = new Stage();
					insertDataStage.setTitle("Data Insertion");

					GridPane insertDataGrid = new GridPane();
					insertDataGrid.setAlignment(Pos.CENTER);
					insertDataGrid.setHgap(5.0);
					insertDataGrid.setVgap(5.0);

					Scene insertDataScene = new Scene(insertDataGrid, 300, 250);
					insertDataStage.setScene(insertDataScene);

					insertDataStage.show();

					Label firstNameLabel = new Label("First Name:");
					HBox firstNameLabelHB = new HBox();
					firstNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					firstNameLabelHB.getChildren().add(firstNameLabel);
					insertDataGrid.add(firstNameLabelHB, 3, 1);

					TextField firstNameText = new TextField();
					HBox firstNameTextHB = new HBox();
					firstNameTextHB.setAlignment(Pos.CENTER_LEFT);
					firstNameTextHB.getChildren().add(firstNameText);
					insertDataGrid.add(firstNameTextHB, 4, 1);

					Label lastNameLabel = new Label("Last Name:");
					HBox lastNameLabelHB = new HBox();
					lastNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					lastNameLabelHB.getChildren().add(lastNameLabel);
					insertDataGrid.add(lastNameLabelHB, 3, 2);

					TextField lastNameText = new TextField();
					HBox lastNameTextHB = new HBox();
					lastNameTextHB.setAlignment(Pos.CENTER_LEFT);
					lastNameTextHB.getChildren().add(lastNameText);
					insertDataGrid.add(lastNameTextHB, 4, 2);

					Label ageLabel = new Label("Age:");
					HBox ageLabelHB = new HBox();
					ageLabelHB.setAlignment(Pos.CENTER_RIGHT);
					ageLabelHB.getChildren().add(ageLabel);
					insertDataGrid.add(ageLabelHB, 3, 3);

					TextField ageText = new TextField();
					HBox ageTextHB = new HBox();
					ageTextHB.setAlignment(Pos.CENTER_LEFT);
					ageTextHB.getChildren().add(ageText);
					insertDataGrid.add(ageTextHB, 4, 3);

					Label ssnLabel = new Label("Social Security Number:");
					HBox ssnLabelHB = new HBox();
					ssnLabelHB.setAlignment(Pos.CENTER_RIGHT);
					ssnLabelHB.getChildren().add(ssnLabel);
					insertDataGrid.add(ssnLabelHB, 3, 4);

					TextField ssnText = new TextField();
					HBox ssnTextHB = new HBox();
					ssnTextHB.setAlignment(Pos.CENTER_LEFT);
					ssnTextHB.getChildren().add(ssnText);
					insertDataGrid.add(ssnTextHB, 4, 4);

					Label ccLabel = new Label("Credit Card:");
					HBox ccLabelHB = new HBox();
					ccLabelHB.setAlignment(Pos.CENTER_RIGHT);
					ccLabelHB.getChildren().add(ccLabel);
					insertDataGrid.add(ccLabelHB, 3, 5);

					TextField ccText = new TextField();
					HBox ccTextHB = new HBox();
					ccTextHB.setAlignment(Pos.CENTER_LEFT);
					ccTextHB.getChildren().add(ccText);
					insertDataGrid.add(ccTextHB, 4, 5);

					Button enterInsertDataBtn = new Button("Enter Data");
					HBox enterInsertDataBtnHB = new HBox();
					enterInsertDataBtnHB.setAlignment(Pos.CENTER);
					enterInsertDataBtnHB.getChildren().add(enterInsertDataBtn);
					insertDataGrid.add(enterInsertDataBtnHB, 3, 6);

					Button closeBtn = new Button("Close");
					HBox closeBtnHB = new HBox();
					closeBtnHB.setAlignment(Pos.CENTER);
					closeBtnHB.getChildren().add(closeBtn);
					insertDataGrid.add(closeBtnHB, 4, 6);

					enterInsertDataBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String firstName = firstNameText.getText();
									String lastName = lastNameText.getText();
									int age = Integer.parseInt(ageText
											.getText());
									long ssn = Long.parseLong(ssnText.getText());
									long cc = Long.parseLong(ccText.getText());

									Person personInfo = new Person(firstName,
											lastName, age, ssn, cc);
									try {

										System.out.println(db
												.insertPerson(personInfo));

										firstNameText.setText("");
										lastNameText.setText("");
										ageText.setText("");
										ssnText.setText("");
										ccText.setText("");

									} catch (SQLException e1) {
										e1.printStackTrace();
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
					deleteDataGrid.setHgap(5.0);
					deleteDataGrid.setVgap(5.0);

					Scene deleteDataScene = new Scene(deleteDataGrid, 300, 250);
					deleteDataStage.setScene(deleteDataScene);

					deleteDataStage.show();

					Text scenetitle = new Text("Enter the first and last name");
					scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,
							12));
					deleteDataGrid.add(scenetitle, 4, 0);

					Label firstNameLabel = new Label("First Name:");
					HBox firstNameLabelHB = new HBox();
					firstNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					firstNameLabelHB.getChildren().add(firstNameLabel);
					deleteDataGrid.add(firstNameLabelHB, 3, 1);

					TextField firstNameText = new TextField();
					HBox firstNameTextHB = new HBox();
					firstNameTextHB.setAlignment(Pos.CENTER_LEFT);
					firstNameTextHB.getChildren().add(firstNameText);
					deleteDataGrid.add(firstNameTextHB, 4, 1);

					Label lastNameLabel = new Label("Last Name:");
					HBox lastNameLabelHB = new HBox();
					lastNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					lastNameLabelHB.getChildren().add(lastNameLabel);
					deleteDataGrid.add(lastNameLabelHB, 3, 2);

					TextField lastNameText = new TextField();
					HBox lastNameTextHB = new HBox();
					lastNameTextHB.setAlignment(Pos.CENTER_LEFT);
					lastNameTextHB.getChildren().add(lastNameText);
					deleteDataGrid.add(lastNameTextHB, 4, 2);

					Button enterDeleteDataBtn = new Button("Delete Data");
					HBox enterDeleteDataBtnHB = new HBox();
					enterDeleteDataBtnHB.setAlignment(Pos.CENTER);
					enterDeleteDataBtnHB.getChildren().add(enterDeleteDataBtn);
					deleteDataGrid.add(enterDeleteDataBtnHB, 3, 3);

					Button closeBtn = new Button("Close");
					HBox closeBtnHB = new HBox();
					closeBtnHB.setAlignment(Pos.CENTER);
					closeBtnHB.getChildren().add(closeBtn);
					deleteDataGrid.add(closeBtnHB, 4, 3);

					enterDeleteDataBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String firstName = firstNameText.getText();
									String lastName = lastNameText.getText();

									try {
										System.out.println(db.deletePerson(
												firstName, lastName));

										firstNameText.setText("");
										lastNameText.setText("");
									} catch (SQLException e1) {
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

					try {
						System.out.println(db.findAllPeople());
						System.out.println("\n\n\n");
					} catch (Exception e1) {
						e1.printStackTrace();
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
					scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,
							12));
					findPersonGrid.add(scenetitle, 4, 0);

					Label lastNameLabel = new Label("Last Name:");
					HBox lastNameLabelHB = new HBox();
					lastNameLabelHB.setAlignment(Pos.CENTER_RIGHT);
					lastNameLabelHB.getChildren().add(lastNameLabel);
					findPersonGrid.add(lastNameLabelHB, 3, 2);

					TextField lastNameText = new TextField();
					HBox lastNameTextHB = new HBox();
					lastNameTextHB.setAlignment(Pos.CENTER_LEFT);
					lastNameTextHB.getChildren().add(lastNameText);
					findPersonGrid.add(lastNameTextHB, 4, 2);

					Button enterFindPersonBtn = new Button("Find Data");
					HBox enterFindPersonBtnHB = new HBox();
					enterFindPersonBtnHB.setAlignment(Pos.CENTER);
					enterFindPersonBtnHB.getChildren().add(enterFindPersonBtn);
					findPersonGrid.add(enterFindPersonBtnHB, 3, 3);

					Button closeBtn = new Button("Close");
					HBox closeBtnHB = new HBox();
					closeBtnHB.setAlignment(Pos.CENTER);
					closeBtnHB.getChildren().add(closeBtn);
					findPersonGrid.add(closeBtnHB, 4, 3);

					enterFindPersonBtn
							.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {

									String lastName = lastNameText.getText();

									try {
										System.out.println(db
												.findPersonByLastName(lastName));
									} catch (SQLException e1) {
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
