package com.celesea.runtime.config;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * com.celesea.activiti.config.ActivitiConfiguration
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/26
 */
@Configuration
public class ActivitiConfiguration {

  private final Logger logger = LoggerFactory.getLogger(ActivitiConfiguration.class);

  @Bean
  public UserDetailsService myUserDetailsService() {

    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    String[][] usersGroupsAndRoles = {
        {"bob", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
        {"other", "password", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
        {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
    };

    for (String[] user : usersGroupsAndRoles) {
      List<String> authoritiesStrings = asList(Arrays.copyOfRange(user, 2, user.length));
      logger.info("> Registering new user: " + user[0] + " with the following Authorities["
          + authoritiesStrings + "]");
      inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
          authoritiesStrings.stream().map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList())));
    }

    return inMemoryUserDetailsManager;
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}