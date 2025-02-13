package com.ticarum.apirest.login;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioServicio {//implements UserDetailsService {
 
    @Autowired
    private UsuarioRepositorio usuarioRepo;
     
    /*
    @Override
    public DetallesUsuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.getUsuarioByNombre(username);
         
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new DetallesUsuario(usuario);
    }
*/
}
