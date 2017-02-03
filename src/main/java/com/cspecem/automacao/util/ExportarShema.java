package com.cspecem.automacao.util;

import javax.persistence.Persistence;

public class ExportarShema {

	public static void main(String[] args) {
		try {
			Persistence.createEntityManagerFactory("InfraPU");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
