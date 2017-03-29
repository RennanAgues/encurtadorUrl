package br.com.encurtadorurl.DTO;

import java.util.Date;

import br.com.encurtadorurl.entity.EncurtadorUrl;

public class EncurtadorUrlDTO implements java.io.Serializable{
	
	private static final long serialVersionUID = -2208765655944236904L;
	
	private long 	encurtadorUrlId;
	private String 	url;
	private String 	urlEncurtadaWithAlias;
	private String 	urlEncurtada;
	private String 	alias;
	private String 	descriptionReturn;
	private Date 	dataCriacao;
	private int 	codeReturn;
	private boolean ativo;
	
	/**
	 * 
	 */
	public EncurtadorUrlDTO(){}
	
	/**
	 * 
	 * @param encurtadorUrlID
	 * @param url
	 * @param urlEncurtadaWithAlias
	 * @param urlEncurtada
	 * @param alias
	 * @param descriptionReturn
	 * @param codeReturn
	 * @param dataCriacao
	 * @param ativo
	 */
	public EncurtadorUrlDTO(long 	encurtadorUrlId,
							String 	url,
							String 	urlEncurtadaWithAlias,
							String 	urlEncurtada,
							String 	alias,
							String 	descriptionReturn,
							int 	codeReturn,
							boolean ativo){

		this.encurtadorUrlId = encurtadorUrlId;
		this.url = url;
		this.urlEncurtadaWithAlias = urlEncurtadaWithAlias;
		this.urlEncurtada = urlEncurtada;
		this.alias = alias;
		this.descriptionReturn = descriptionReturn;
		this.codeReturn = codeReturn;
		this.ativo = ativo;		
	}
	
	/**
	 * 
	 * @param encurtadorUrl
	 */
	public EncurtadorUrlDTO(EncurtadorUrl encurtadorUrl){

		if(encurtadorUrl != null){				
			this.encurtadorUrlId = encurtadorUrl.getEncurtadorUrlId();
			this.url = encurtadorUrl.getUrl();
			this.urlEncurtadaWithAlias = encurtadorUrl.getUrlEncurtadaWithAlias();
			this.urlEncurtada = encurtadorUrl.getUrlEncurtada();
			this.alias = encurtadorUrl.getAlias();
			this.descriptionReturn = encurtadorUrl.getDescriptionReturn();
			this.codeReturn = encurtadorUrl.getCodeReturn();
			this.dataCriacao = encurtadorUrl.getDataCriacao();
			this.ativo = encurtadorUrl.isAtivo();
		}	
	}
		
	public long getEncurtadorUrlId() {
		return encurtadorUrlId;
	}
	public void setEncurtadorUrlId(long encurtadorUrlId) {
		this.encurtadorUrlId = encurtadorUrlId;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getDescriptionReturn() {
		return descriptionReturn;
	}
	public void setDescriptionReturn(String descriptionReturn) {
		this.descriptionReturn = descriptionReturn;
	}
	
	public int getCodeReturn() {
		return codeReturn;
	}
	public void setCodeReturn(int codeReturn) {
		this.codeReturn = codeReturn;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getUrlWithAlias() {
		return urlEncurtadaWithAlias;
	}

	public void setUrlWithAlias(String urlWithAlias) {
		this.urlEncurtadaWithAlias = urlWithAlias;
	}
}
