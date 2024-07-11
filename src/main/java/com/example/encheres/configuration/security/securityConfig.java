package com.example.encheres.configuration.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

	/*
	 * Profil récupérés dans la BDD
	 */
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsEnchereManager users = new JdbcUserDetailsEnchereManager(dataSource);
		users.setUsersByUsernameQuery("select pseudo, mot_de_passe, 'true' as enabled from UTILISATEURS where pseudo = ? OR email = ? AND date_histo IS NULL");
		users.setAuthoritiesByUsernameQuery("select pseudo, role from UTILISATEURS INNER JOIN ROLE ON ROLE.is_admin = UTILISATEURS.administrateur WHERE pseudo = ?");
		return users;
	}

	/*
	 * Définition des accès en fonction des profils
	 */
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authorize) -> authorize
	        .requestMatchers("/administrateur").permitAll()
	        .requestMatchers("/administrateur/liste").permitAll()
		    .requestMatchers("/utilisateurs/afficher").permitAll()
		    .requestMatchers("/utilisateurs/modifier").permitAll()
		    .requestMatchers("/utilisateurs/creer").permitAll()
		    .requestMatchers("/vendre-article").permitAll()
		    .requestMatchers("/view-resultat-gagnant").permitAll()
		    .requestMatchers("/view-resultat-retrait").permitAll()
		    .requestMatchers("/css/**").permitAll() //Accès au CSS pour tous le monde
		    .requestMatchers("/images/**").permitAll() //Accès aux images pour tous le monde
		    .requestMatchers("/image/**").permitAll() //Accès aux images pour tous le monde
		    .requestMatchers("/").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/home").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/session").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/login").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/encheres").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/view-test-connexion").permitAll() //Accès à l'index pour tous le monde
	            .anyRequest().authenticated()
	        );

	    //Formulaire de connexion par défaut
	    http.formLogin(form -> {
	    			form.loginPage("/login"); //url permettant d'afficher la page de login
	    			form.permitAll();
	    			form.defaultSuccessUrl("/session", true); //url appelée si connexion ok
	    });

	    http.logout(form -> {
			    	form.invalidateHttpSession(true);
			    	form.clearAuthentication(true);
			    	form.deleteCookies("JSESSIONID"); //Suppression du cookie de session
			    	form.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
			    	form.logoutSuccessUrl("/encheres").permitAll();
	    });


	    return http.build();
	}

}
