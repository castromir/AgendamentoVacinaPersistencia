package com.projetoCJ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoCJ.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
