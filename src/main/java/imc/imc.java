package imc;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class imc extends Application  {
	
		
    private TextField alturaText, masaText;	
	private Label etiqueta,etAltura,etMasa,etGrupo,etGrupo2,etGrupo3,etGrupo4;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		alturaText = new TextField("0");		
		alturaText.setPrefColumnCount(7);
		
		masaText = new TextField("0");
		masaText.setPrefColumnCount(7);
		
					
			
		 HBox alturahbox = new HBox(8);
		 alturahbox.setAlignment(Pos.BASELINE_CENTER);
		 alturahbox.setSpacing(7);
		 alturahbox.getChildren().addAll(alturaText);
		 
		 
		 HBox masahBox = new HBox(8);
		 masahBox.setAlignment(Pos.BASELINE_CENTER);
		 masahBox.setSpacing(7);
		 masahBox.getChildren().addAll(masaText);
		 
					
					
			NumberStringConverter converter = new NumberStringConverter();
			
			
			
			DoubleProperty  alturaD = new SimpleDoubleProperty();
			StringProperty altura = new SimpleStringProperty();
			
			altura.bindBidirectional(alturaText.textProperty());			
			Bindings.bindBidirectional(altura, alturaD, converter);
			
			
			DoubleProperty  masaD = new SimpleDoubleProperty();
			StringProperty masa = new SimpleStringProperty();
			
			masa.bindBidirectional(masaText.textProperty());			
			Bindings.bindBidirectional(masa, masaD, converter);
			
		
			
			etiqueta = new Label();
			
			 etiqueta.textProperty().bind(new SimpleStringProperty("IMC=") .concat((masaD.divide(alturaD.multiply(alturaD))).asString()));
			etiqueta.setTranslateY(10);
			
			etAltura = new Label();
			etAltura.setText("Altura (m)");
			//etAltura.setTranslateY(25);
			//etAltura.setTranslateX(-80);
			
			etMasa = new Label();
			etMasa.setText("Masa (Kg)");
			//etMasa.setTranslateY(25);
			//etMasa.setTranslateX(-80);
			
			
			etGrupo = new Label();
			
			StringBinding frase = new When(((masaD.divide(alturaD.multiply(alturaD))).greaterThanOrEqualTo(18.5)).and((masaD.divide(alturaD.multiply(alturaD))).lessThan(25)) )
	           .then("Peso Normal")
	               .otherwise("");	

			etGrupo.textProperty().bind(frase);
			etGrupo.setTranslateY(25);
			
			
			
			
		etGrupo2 = new Label();
					
			StringBinding frase2 = new When(((masaD.divide(alturaD.multiply(alturaD))).greaterThanOrEqualTo(25)).and((masaD.divide(alturaD.multiply(alturaD))).lessThan(30)) )
	           .then("Sobrepeso")
	               .otherwise("");	

			etGrupo2.textProperty().bind(frase2);
			//etGrupo2.setTranslateY(-5);
			
			
	etGrupo3 = new Label();
				
			StringBinding frase3 = new When(((masaD.divide(alturaD.multiply(alturaD))).greaterThanOrEqualTo(30)) )
	           .then("Obeso")
	               .otherwise("");	

			etGrupo3.textProperty().bind(frase3);
			etGrupo3.setTranslateY(-20);
			
			
	etGrupo4 = new Label();
	
			StringBinding frase4 = new When(((masaD.divide(alturaD.multiply(alturaD))).lessThan(18.5)) )
	           .then("Infrapeso")
	               .otherwise("");	

			etGrupo4.textProperty().bind(frase4);
			etGrupo4.setTranslateY(-40);
			
		
	
			
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(etAltura, alturahbox,etMasa, masahBox,etiqueta,etGrupo,etGrupo2,etGrupo3,etGrupo4);

		Scene escena = new Scene(root, 240, 220);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Calcuadora IMC");
		primaryStage.show();
	}

	

	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	
	
	
	
}