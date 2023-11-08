package com.sistema.blog.security;

public class JWTAuthResonceDTO {

	
	private String tokenAcceso;
	private String tipoToken = "Bearer";
	
	
	
	
	public JWTAuthResonceDTO(String tokenAcceso) {
		super();
		this.tokenAcceso = tokenAcceso;
	}


	public JWTAuthResonceDTO(String tokenAcceso, String tipoToken) {
		super();
		this.tokenAcceso = tokenAcceso;
		this.tipoToken = tipoToken;
	}


	public String getTokenAcceso() {
		return tokenAcceso;
	}


	public void setTokenAcceso(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}


	public String getTipoToken() {
		return tipoToken;
	}


	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}
	
	
}
