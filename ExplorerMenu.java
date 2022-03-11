package explorer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ExplorerMenu extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	private static HttpURLConnection connection;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Action to handle closing program and saving data when close X button is used

		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("Exit Program");
			confirm.setHeaderText("Exit the program?");
			if (confirm.showAndWait().get() == ButtonType.OK) {
				System.exit(0);
			}
		});

		// Setting up Stage to include Radio button menu
		primaryStage.setTitle("Bitcoin Explorer");

		RadioButton radioButton1 = new RadioButton("Bitcoin Price");
		RadioButton radioButton2 = new RadioButton("Transaction Status");
		RadioButton radioButton3 = new RadioButton("Wallet Summary");
		RadioButton radioButton4 = new RadioButton("User Access");
		RadioButton radioButton5 = new RadioButton("Exit");

		ToggleGroup radioGroup = new ToggleGroup();

		radioButton1.setToggleGroup(radioGroup);
		radioButton2.setToggleGroup(radioGroup);
		radioButton3.setToggleGroup(radioGroup);
		radioButton4.setToggleGroup(radioGroup);
		radioButton5.setToggleGroup(radioGroup);

		// Adding radio buttons and formatting the menu's style
		VBox menu = new VBox(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5);
		menu.setSpacing(15);
		menu.setStyle("-fx-padding: 50;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-color: black;");

		// Pane for handling Bitcoin Prices
		GridPane bitcoinPrice = new GridPane();
		bitcoinPrice.setPadding(new Insets(50, 50, 50, 50));
		bitcoinPrice.setVgap(8);
		bitcoinPrice.setHgap(6);

		// Labels and fields for currencies

		Label quantity = new Label("Quantity:");
		GridPane.setConstraints(quantity, 0, 3);

		TextField quantityEntry = new TextField();
		quantityEntry.setMaxWidth(100);
		quantityEntry.setPromptText("10.99");
		GridPane.setConstraints(quantityEntry, 1, 3);

		Button submitButton1 = new Button("Submit");
		GridPane.setConstraints(submitButton1, 1, 4);

		Label usd = new Label("USD:");
		GridPane.setConstraints(usd, 0, 6);

		TextField usdPrice = new TextField();
		usdPrice.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(usdPrice, 1, 6);

		Label eur = new Label("EUR:");
		GridPane.setConstraints(eur, 0, 7);

		TextField eurPrice = new TextField();
		eurPrice.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(eurPrice, 1, 7);

		Label gbp = new Label("GDP:");
		GridPane.setConstraints(gbp, 0, 8);

		TextField gbpPrice = new TextField();
		gbpPrice.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(gbpPrice, 1, 8);

		Label xau = new Label("XAU:");
		GridPane.setConstraints(xau, 0, 9);

		TextField xauPrice = new TextField();
		xauPrice.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(xauPrice, 1, 9);

		Label statusLabel1 = new Label("Processing Errors:");
		GridPane.setConstraints(statusLabel1, 0, 11);

		TextArea errorMessage1 = new TextArea();
		GridPane.setConstraints(errorMessage1, 1, 11);
		errorMessage1.setMaxHeight(100);
		errorMessage1.setEditable(false);

		bitcoinPrice.getChildren().addAll(quantity, quantityEntry, usd, usdPrice, eur, eurPrice, gbp, gbpPrice, xau,
				xauPrice, statusLabel1, errorMessage1, submitButton1);

		// Pane for Transaction Status
		GridPane tranStatus = new GridPane();
		tranStatus.setPadding(new Insets(50, 50, 50, 50));
		tranStatus.setVgap(8);
		tranStatus.setHgap(10);

		Label transaction = new Label("Transaction:");
		GridPane.setConstraints(transaction, 0, 3);

		TextField ref = new TextField();
		ref.setPromptText("4a6581bbf6566599f1fff05261f894cc3fda5aa0ab6dd93944e5f482dcd13840\r\n");
		GridPane.setConstraints(ref, 1, 3);

		Label size = new Label("Size:");
		GridPane.setConstraints(size, 0, 7);

		TextField sizeResponse = new TextField();
		sizeResponse.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(sizeResponse, 1, 7);

		Label confirmations = new Label("Confirmations:");
		GridPane.setConstraints(confirmations, 0, 8);

		TextField confirmResponse = new TextField();
		confirmResponse.setStyle("-fx-background-color: transparent;");
		GridPane.setConstraints(confirmResponse, 1, 8);

		Label statusLabel2 = new Label("Processing Errors:");
		GridPane.setConstraints(statusLabel2, 0, 10);

		TextArea errorMessage2 = new TextArea();
		GridPane.setConstraints(errorMessage2, 1, 10);
		errorMessage2.setMaxHeight(100);
		errorMessage2.setEditable(false);

		Button submitButton2 = new Button("Submit");
		GridPane.setConstraints(submitButton2, 1, 4);

		tranStatus.getChildren().addAll(transaction, ref, size, sizeResponse, confirmations, confirmResponse,
				statusLabel2, errorMessage2, submitButton2);

		// Pane for Wallet Status
		GridPane walletStatus = new GridPane();
		walletStatus.setPadding(new Insets(50, 50, 50, 50));
		walletStatus.setVgap(8);
		walletStatus.setHgap(10);

		Label address = new Label("Address:");
		GridPane.setConstraints(address, 0, 3);

		TextField walletAddress = new TextField();
		walletAddress.setPromptText("34rng4QwB5pHUbGDJw1JxjLwgEU8TQuEqv\r\n");
		GridPane.setConstraints(walletAddress, 1, 3);

		Label validAddress = new Label("Valid Address:");
		GridPane.setConstraints(validAddress, 0, 5);

		TextField addressResponse = new TextField();
		GridPane.setConstraints(addressResponse, 1, 5);

		Label tranCount = new Label("Transaction Count:");
		GridPane.setConstraints(tranCount, 0, 6);

		TextField tranCountResponse = new TextField();
		GridPane.setConstraints(tranCountResponse, 1, 6);

		Label satsBalance = new Label("SATS Balance:");
		GridPane.setConstraints(satsBalance, 0, 7);

		TextField satsBalResponse = new TextField();
		GridPane.setConstraints(satsBalResponse, 1, 7);

		Label bitcoinBalance = new Label("Bitcoin Balance:");
		GridPane.setConstraints(bitcoinBalance, 0, 8);

		TextField bitcoinBalResponse = new TextField();
		GridPane.setConstraints(bitcoinBalResponse, 1, 8);

		Label statusLabel3 = new Label("Processing Errors:");
		GridPane.setConstraints(statusLabel3, 0, 10);

		TextArea errorMessage3 = new TextArea();
		GridPane.setConstraints(errorMessage3, 1, 10);
		errorMessage3.setMaxHeight(100);
		errorMessage3.setEditable(false);

		Button submitButton3 = new Button("Submit");
		GridPane.setConstraints(submitButton3, 1, 4);

		walletStatus.getChildren().addAll(address, walletAddress, validAddress, addressResponse, tranCount,
				tranCountResponse, satsBalance, satsBalResponse, bitcoinBalance, bitcoinBalResponse, statusLabel3,
				errorMessage3, submitButton3);

		// Pane for handling access
		GridPane userAccess = new GridPane();
		userAccess.setPadding(new Insets(50, 50, 50, 50));
		userAccess.setVgap(8);
		userAccess.setHgap(10);

		Label userName = new Label("User Name:");
		GridPane.setConstraints(userName, 0, 3);

		TextField userNameEntry = new TextField();
		userNameEntry.setMaxWidth(200);
		userNameEntry.setPromptText("nsmith");
		GridPane.setConstraints(userNameEntry, 1, 3);

		CheckBox bitcoinPriceBox = new CheckBox("Bitcoin Price");
		bitcoinPriceBox.setIndeterminate(false);
		GridPane.setConstraints(bitcoinPriceBox, 1, 5);

		CheckBox tranStatusBox = new CheckBox("Transaction Status");
		tranStatusBox.setIndeterminate(false);
		GridPane.setConstraints(tranStatusBox, 1, 6);

		CheckBox walletSummaryBox = new CheckBox("Wallet Summary");
		walletSummaryBox.setIndeterminate(false);
		GridPane.setConstraints(walletSummaryBox, 1, 7);

		CheckBox adminAccessBox = new CheckBox("Administrator");
		adminAccessBox.setIndeterminate(false);
		GridPane.setConstraints(adminAccessBox, 1, 8);

		Label statusLabel4 = new Label("Processing Errors:");
		GridPane.setConstraints(statusLabel4, 0, 11);

		TextArea errorMessage4 = new TextArea();
		GridPane.setConstraints(errorMessage4, 1, 11);
		errorMessage4.setMaxHeight(100);
		errorMessage4.setEditable(false);

		Button submitButton4 = new Button("Submit");
		GridPane.setConstraints(submitButton4, 1, 9);

		userAccess.getChildren().addAll(userName, userNameEntry, bitcoinPriceBox, tranStatusBox, walletSummaryBox,
				adminAccessBox, statusLabel4, errorMessage4, submitButton4);

		// Radio Button Exit Pane
		TextArea txtOutput = new TextArea();
		txtOutput.setMaxWidth(500);
		txtOutput.setMaxHeight(500);
		txtOutput.setEditable(false);

		// Formatting the radio button menu's location
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(50, 50, 50, 50));
		borderPane.setLeft(menu);

		// Radio dial button actions
		radioGroup.selectedToggleProperty().addListener((observableValue, old_toggle, new_toggle) -> {

			// Radio Button 1 = Bitcoin Price
			if (radioGroup.getSelectedToggle() == radioButton1) {
				borderPane.setCenter(bitcoinPrice);
				errorMessage1.clear();

				submitButton1.setOnAction(e -> {
					// Exception handling for mismatch of field requirements using Try/ Catch
					try {

					} catch (NumberFormatException er) {
						errorMessage1.setText("Bitcoin error");
					}

					// API Call for Bitcoin Price at bitcoinexplorer.com
					BufferedReader reader;
					String line;
					StringBuffer responseContent = new StringBuffer();
					try {
						URL url = new URL("https://bitcoinexplorer.org/api/price");

						connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");
						connection.setConnectTimeout(5000);
						connection.setReadTimeout(5000);

						int status = connection.getResponseCode();

						if (status > 299) {
							reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
							while ((line = reader.readLine()) != null) {
								responseContent.append(line);
							}
							reader.close();
						} else {
							reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
							while ((line = reader.readLine()) != null) {
								responseContent.append(line);
							}
							reader.close();
						}
						
					} catch (MalformedURLException g) {
						g.printStackTrace();
					} catch (IOException h) {
						h.printStackTrace();
					} finally {
						connection.disconnect();
					}
					
					JSONObject myResponse = new JSONObject(responseContent.toString());
					
//					String usd1 = myResponse.getString("usd");
//					usd1 = usd1.replace(",","");
//					double usdConvert = Double.parseDouble(usd1);
//					double usdbitcoin= usdConvert*4;
//					System.out.println(""+usdbitcoin);
					
					// Read JSON response and print
					usdPrice.setText("" + myResponse.getString("usd"));
					gbpPrice.setText("" + myResponse.getString("gbp"));
					eurPrice.setText("" + myResponse.getString("eur"));
					xauPrice.setText("" + myResponse.getString("xau"));

				});
			}

			// Radio Button 2 = Tran Status
			if (radioGroup.getSelectedToggle() == radioButton2) {
				borderPane.setCenter(tranStatus);
				errorMessage2.clear();
				ref.clear();
				sizeResponse.clear();
				confirmResponse.clear();

				submitButton2.setOnAction(e -> {
					// Exception handling for mismatch of field requirements using Try/ Catch
					try {

					} catch (NumberFormatException er) {
						errorMessage2.setText("Error.");
					}
				});
			}

			// Radio Button 3 = Wallet Summary
			if (radioGroup.getSelectedToggle() == radioButton3) {
				borderPane.setCenter(walletStatus);
				errorMessage3.clear();
				walletAddress.clear();

				submitButton3.setOnAction(e -> {
					// Exception handling for mismatch of field requirements using Try/ Catch
					try {
						
						walletAddress.clear();

					} catch (NumberFormatException er) {
						errorMessage3.setText("error.");

					}
				});
			}

			if (radioGroup.getSelectedToggle() == radioButton4) {
				borderPane.setCenter(userAccess);
				errorMessage3.clear();
				walletAddress.clear();

				submitButton4.setOnAction(e -> {
					// Exception handling for mismatch of field requirements using Try/ Catch
					try {

						userNameEntry.clear();

					} catch (NumberFormatException er) {
						errorMessage4.setText("error.");

					}
				});
			}

			// Radio Button 5 = Confirms exit of the program. Closes program and saves data
			// upon confirmation
			if (radioGroup.getSelectedToggle() == radioButton5) {
				radioButton5.setOnAction(e -> {

					Alert confirm = new Alert(AlertType.CONFIRMATION);
					confirm.setTitle("Exit Program");
					confirm.setHeaderText("Exit the program?");
					if (confirm.showAndWait().get() == ButtonType.OK) {
						System.exit(0);
					}
				});
			}
		});

		Scene scene = new Scene(borderPane, 1100, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
