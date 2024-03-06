package io.github.raul.service.impl;

import io.github.raul.domain.entity.Usuario;
import io.github.raul.domain.repository.UsuarioRepository;
import io.github.raul.rest.dto.InformacoesUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public InformacoesUsuarioDTO salvar(Usuario usuario){
        InformacoesUsuarioDTO dto = new InformacoesUsuarioDTO();
        dto.setUsername(usuario.getLogin());
        dto.setAdmin(usuario.isAdmin());
        usuarioRepository.save(usuario);
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario
                .isAdmin() ? new String[]{"USER", "ADMIN"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
