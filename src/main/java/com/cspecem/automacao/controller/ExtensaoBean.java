package com.cspecem.automacao.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

import com.cspecem.automacao.model.LocalInstalacao;
import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.LocaisInstalacao;
import com.cspecem.automacao.repository.Produtos;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

public abstract class ExtensaoBean {
	
	@Inject
	private Produtos produtos;
	private List<Produto> produtosLista;
	
	@Inject
	private LocaisInstalacao locais;
	private List<LocalInstalacao> locaisLista;
	
	public List<LocalInstalacao> getLocaisLista() {
		if (this.locaisLista == null) {
			this.locaisLista = locais.listar("area");
		}
		return locaisLista;
	}
	
	public List<Produto> getProdutosLista() {
		if (this.produtosLista == null) {
			this.produtosLista = produtos.listar("descricao");
		}
		
		return produtosLista;
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {

		//cria documento pdf
        Document pdf = (Document) document;        

        //seta as margens da página, precisa estar antes da abertura do documento: pdf.open()
        pdf.setMargins(20f, 20f, 20f, 20f);
        pdf.setPageSize(PageSize.A4);
        pdf.addTitle("CSP- Companhia Siderúrgica do Pecém");
        pdf.open();
        
        //aqui pega o contexto para formar a url da imagem
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources/images" + File.separator + "logo.png";
        
        //cria a imagem alinhando ao centro
        Image image = Image.getInstance(logo);
        image.setAlignment(Image.ALIGN_CENTER);
        
        //adciona a imgem ao pdf
        pdf.add(image);
        
        //adciona data atual
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Phrase data = new Phrase("Emitido em " + formato.format(new Date()));
        ((Paragraph) data).setAlignment("rigth");
        pdf.add(data);
        
        //adiciona paragrafo ao pdf, alinhando ao centro
        /*
        Paragraph p = new Paragraph("teste parágrafo");
        p.setAlignment("center");
        pdf.add(p); */
    }
	
	
	public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
