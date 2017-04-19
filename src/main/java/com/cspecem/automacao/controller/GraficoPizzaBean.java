package com.cspecem.automacao.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class GraficoPizzaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PieChartModel graficoPizzaModelo;
	   
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getGraficoPizzaModelo() {
        return graficoPizzaModelo;
    }
          
    private void createPieModels() {
        createPieModel();
    }
 
    private void createPieModel() {
        graficoPizzaModelo = new PieChartModel();
        
        graficoPizzaModelo.set("Atendido", 57);
        graficoPizzaModelo.set("Pendente", 48);
         
        graficoPizzaModelo.setTitle("Status Spare Parts na CSP");
        graficoPizzaModelo.setLegendPosition("w");
    }

}
