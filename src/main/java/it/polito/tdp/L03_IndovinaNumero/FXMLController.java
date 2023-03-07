/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.L03_IndovinaNumero;

import java.net.URL;
import javafx.scene.control.ProgressBar;

import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	LinkedList<Integer> listaNumeri = new LinkedList<Integer>();
	private int TMax;
	private int NMax;
	private int countTentativiFatti;
	private int numeroSegreto;
	
	@FXML
	private ProgressBar barTentativi;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonProva"
    private Button buttonProva; // Value injected by FXMLLoader

    @FXML // fx:id="button_NuovaPartita"
    private Button button_NuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txt_Commento"
    private TextArea txt_Commento; // Value injected by FXMLLoader

    @FXML // fx:id="txt_NMassimo"
    private TextField txt_NMassimo; // Value injected by FXMLLoader

    @FXML // fx:id="txt_Prova"
    private TextField txt_Prova; // Value injected by FXMLLoader

    @FXML // fx:id="txt_Risultato"
    private TextArea txt_Risultato; // Value injected by FXMLLoader

    @FXML // fx:id="txt_TMassimi"
    private TextField txt_TMassimi; // Value injected by FXMLLoader

    @FXML // fx:id="txt_TRimasti"
    private TextField txt_TRimasti; // Value injected by FXMLLoader

    @FXML
    private Button txt_abbandona;
    
    @FXML
    void abbandona(ActionEvent event) {
    	this.txt_Risultato.clear();
		this.txt_Prova.clear();
		this.txt_Commento.clear();
		this.barTentativi.setProgress(0.0);
		this.txt_NMassimo.clear();
		this.txt_TMassimi.clear();
		this.txt_TRimasti.clear();
    }
    
    @FXML
    void doNuovaPartita(ActionEvent event) {
    	this.countTentativiFatti=0;
    	boolean numeroGiaScelto=false;
    	try {
			this.TMax=Integer.parseInt(this.txt_TMassimi.getText());
		}catch(NumberFormatException e) {
			this.txt_Commento.setText("TMax deve essere un numero");
		}
		try {
			this.NMax=Integer.parseInt(this.txt_NMassimo.getText());
		}catch(NumberFormatException e) {
			this.txt_Commento.setText("NMax deve essere un numero");
		}
		for(Integer i:listaNumeri) {
			if(i!=null)
				if(i==NMax) {
					numeroGiaScelto=true;
				}
		}
		if(numeroGiaScelto==true) {
			this.txt_Commento.setText("Numero Gia Scelto");
			this.buttonProva.setDisable(true);
			this.barTentativi.setProgress(0.0);
			this.txt_Prova.clear();
			this.txt_Risultato.clear();
			return;
		}else {
			listaNumeri.add(NMax);
		}
		
		
    	this.numeroSegreto=(int)(Math.random()*this.NMax)+1;
    	this.txt_TRimasti.setText(Integer.toString(this.TMax-this.countTentativiFatti));
    	this.txt_NMassimo.setText(Integer.toString(this.NMax));
    	this.txt_TMassimi.setText(Integer.toString(this.TMax));
		this.buttonProva.setDisable(false);
		this.txt_Risultato.clear();
		this.txt_Prova.clear();
		this.txt_Commento.clear();
		this.barTentativi.setProgress(0.0);
		
		
		}

    @FXML
    void doProva(ActionEvent event) {
    	//leggere numero scelto dall'utente
    	int numeroScelto;
    	try {
    		numeroScelto= Integer.parseInt(this.txt_Prova.getText());
    	}catch(NumberFormatException e) {
    		this.txt_Commento.setText("Scrivere un numero");
    		return;
    	}
    	
    	//fare controlli sul numero

    	
    	// incrementare numero tentativi fatti
    	this.countTentativiFatti++;
    	
    	
    	this.txt_TRimasti.setText(Integer.toString(this.TMax-this.countTentativiFatti));
    	this.barTentativi.setProgress((double)this.countTentativiFatti/this.TMax);
    	
    	//giocare
    	if(numeroScelto==this.numeroSegreto) {
    		this.txt_Risultato.appendText("Hai Vinto! Il numero segreto è: "+numeroSegreto+"\n");
    		this.buttonProva.setDisable(true);
    		return;
    	}
    	
    	if(this.countTentativiFatti==this.TMax) {
    		this.txt_Risultato.appendText("Hai Perso! Il numero segreto è: "+numeroSegreto+"\n");
    		this.buttonProva.setDisable(true);
    		return;
    	}
    	
    	if (numeroScelto>this.numeroSegreto) {
    		this.txt_Risultato.appendText("Numero troppo alto.\n");
    	}else {
    		this.txt_Risultato.appendText("Numero troppo basso.\n");

    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonProva != null : "fx:id=\"buttonProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert button_NuovaPartita != null : "fx:id=\"button_NuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_Commento != null : "fx:id=\"txt_Commento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_NMassimo != null : "fx:id=\"txt_NMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_Prova != null : "fx:id=\"txt_Prova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_Risultato != null : "fx:id=\"txt_Risultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_TMassimi != null : "fx:id=\"txt_TMassimi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_TRimasti != null : "fx:id=\"txt_TRimasti\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
