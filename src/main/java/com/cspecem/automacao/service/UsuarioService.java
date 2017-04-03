package com.cspecem.automacao.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cspecem.automacao.model.Usuario;
import com.cspecem.automacao.repository.Usuarios;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	private PasswordEncoder passwordEncoder;
	
	
	public void salvarUsuario(Usuario usuario) {
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		this.usuarios.salvar(usuario);
	}
	
	
	// Verifica se usuário existe ou se pode logar
	public Usuario checarLogin(String email, String senha) throws NegocioException {
		
		//List retorno = usuarios.listar();
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private String converteStringParaMD5(String valor) {
        MessageDigest mDigest;
        try { 
              //Instanciamos o nosso HASH MD5, poderíamos usar outro como
              //SHA, por exemplo, mas optamos por MD5.
              mDigest = MessageDigest.getInstance("MD5");
                     
              //Convert a String valor para um array de bytes em MD5
              byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
              
              //Convertemos os bytes para hexadecimal, assim podemos salvar
              //no banco para posterior comparação se senhas
              StringBuffer sb = new StringBuffer();
              for (byte b : valorMD5){
                     sb.append(Integer.toHexString((b & 0xFF) |
                     0x100).substring(1,3));
              }
  
              return sb.toString();
                     
        } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
              return null;
              
        } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
              return null;
        }
 }


}
