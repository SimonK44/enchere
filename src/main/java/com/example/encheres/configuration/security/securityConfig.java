package com.example.encheres.configuration.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfig {

	/*
	 * Profil récupérés dans la BDD
	 */
	@Bean
	UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("select pseudo, mot_de_passe, 'true' as enabled from UTILISATEURS where pseudo = ?");
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
		    .requestMatchers("/utilisateurs/afficher").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/utilisateurs/modifier").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/utilisateurs/creer").permitAll()
		    .requestMatchers("/vendre-article").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/view-resultat-gagnant").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/view-resultat-retrait").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/vendre-article").hasAnyRole("UTILISATEUR", "ADMIN")
		    .requestMatchers("/css/*").permitAll() //Accès au CSS pour tous le monde
		    .requestMatchers("/images/*").permitAll() //Accès aux images pour tous le monde
		    .requestMatchers("/image/*").permitAll() //Accès aux images pour tous le monde
		    .requestMatchers("/").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/home").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/session").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/login").permitAll() //Accès à l'index pour tous le monde
		    .requestMatchers("/encheres").permitAll() //Accès à l'index pour tous le monde
	            .anyRequest().authenticated()
	        );

	    //Formulaire de connexion par défaut
	    http.formLogin(form -> {
	    			form.loginPage("/login"); //url permettant d'afficher la page de login
	    			form.permitAll();
	    			form.defaultSuccessUrl("/session"); //url appelée si connexion ok
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
