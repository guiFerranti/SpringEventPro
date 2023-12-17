package com.guiFerranti.SpringEventPro.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import com.guiFerranti.SpringEventPro.domain.event.Event;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataRegistration;
import com.guiFerranti.SpringEventPro.domain.user.record.UserUpdateInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpf;
    @Column(length = 255)
    private String senha;

    private String telefone;
    @Embedded
    private AddressData endereco;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private boolean ativo;

    @ManyToMany(mappedBy = "subscribedUsers")
    @JsonBackReference
    private List<Event> eventsToGo;


    public User(UserDataRegistration data) {
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.senha = data.senha();
        this.telefone = data.telefone();
        this.endereco = data.endereco();
        this.role = Roles.USER;
        this.ativo = true;
    }

    public void setSenha(String senha, PasswordEncoder passwordEncoder) {
        this.senha = passwordEncoder.encode(senha);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
