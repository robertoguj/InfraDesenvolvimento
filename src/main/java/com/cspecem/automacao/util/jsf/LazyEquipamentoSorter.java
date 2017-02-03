package com.cspecem.automacao.util.jsf;

import java.awt.geom.Area;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.cspecem.automacao.model.Equipamento;

public class LazyEquipamentoSorter implements Comparator<Equipamento> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazyEquipamentoSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Equipamento equipamento1, Equipamento equipamento2) {
        try {
            Object value1 = Area.class.getField(this.sortField).get(equipamento1);
            Object value2 = Area.class.getField(this.sortField).get(equipamento2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }

}
