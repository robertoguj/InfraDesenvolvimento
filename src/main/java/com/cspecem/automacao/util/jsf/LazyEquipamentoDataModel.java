package com.cspecem.automacao.util.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.cspecem.automacao.model.Equipamento;

public class LazyEquipamentoDataModel extends LazyDataModel<Equipamento> {

	private static final long serialVersionUID = 1L;

	private List<Equipamento> datasource;

	public LazyEquipamentoDataModel(List<Equipamento> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Equipamento getRowData(String rowKey) {
		for (Equipamento equipamento : datasource) {
			if (equipamento.getId().equals(rowKey))
				return equipamento;
		}

		return null;
	}

	@Override
	public Object getRowKey(Equipamento equipamento) {
		return equipamento.getId();
	}

	@Override
	public List<Equipamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Equipamento> data = new ArrayList<Equipamento>();

		// filter
		for (Equipamento equipamento : datasource) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(equipamento.getClass().getField(filterProperty).get(equipamento));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(equipamento);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazyEquipamentoSorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

}
