package br.com.encurtadorurl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.encurtadorurl.utils.constantes.ConstantesDataBase;

@Entity
@Table(name = ConstantesDataBase.NAME_TABLE_ENCURTADOR_URL)
public class EncurtadorUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  encurtadorUrlId;	

	@Column(name= ConstantesDataBase.PARAM_URL, nullable = false)
	private String 	url;

	@Column(name= ConstantesDataBase.PARAM_URL_ENCURTADA, nullable = false)
	private String urlEncurtada;

	@Column(name= ConstantesDataBase.PARAM_URL_ENCURTADA_WITH_ALIAS, nullable = false)
	private String urlEncurtadaWithAlias;

	@Column(name= ConstantesDataBase.PARAM_ALIAS, nullable = false)
	private String 	alias;
	
	@Column(name= ConstantesDataBase.PARAM_DESCRIPTION_RETURN, nullable = false)
	private String 	descriptionReturn;
	
	@Column(name= ConstantesDataBase.PARAM_CODE_RETURN, nullable = false)
	private int 	codeReturn;
	
	@Column(name= ConstantesDataBase.PARAM_DATA_CRIACAO, nullable = false)
	private Date 	dataCriacao;
		
	@Column(name= ConstantesDataBase.PARAM_ATIVO, nullable = false)
	private boolean ativo;
	
	/**
	 * 
	 */
	public EncurtadorUrl(){}
	
	/**
	 * 
	 * @param encurtadorUrlID
	 * @param url
	 * @param urlEncurtada
	 * @param urlEncurtadaWithAlias
	 * @param alias
	 * @param descriptionReturn
	 * @param codeReturn
	 * @param dataCriacao
	 * @param ativo
	 */
	public EncurtadorUrl(	Integer  encurtadorUrlId,
							String 	 url,
							String 	 urlEncurtadaWithAlias,
							String 	 urlEncurtada,
							String 	 alias,
							String 	 descriptionReturn,
							int 	 codeReturn,
							Date dataCriacao,
							boolean  ativo){
		
		this.encurtadorUrlId = encurtadorUrlId;
		this.url = url;
		this.urlEncurtada = urlEncurtada;
		this.urlEncurtadaWithAlias = urlEncurtadaWithAlias;		
		this.alias = alias;
		this.descriptionReturn = descriptionReturn;
		this.codeReturn = codeReturn;
		this.dataCriacao = dataCriacao;
		this.ativo = ativo;
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
	
	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}
	
	public String getUrlEncurtadaWithAlias() {
		return urlEncurtadaWithAlias;
	}

	public void setUrlEncurtadaWithAlias(String urlEncurtadaWithAlias) {
		this.urlEncurtadaWithAlias = urlEncurtadaWithAlias;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	
	public boolean equals(Object object) {
        if (object instanceof EncurtadorUrl) {
        	EncurtadorUrl pk = (EncurtadorUrl)object;
            return encurtadorUrlId == pk.encurtadorUrlId;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int)(encurtadorUrlId);
    }	
}
