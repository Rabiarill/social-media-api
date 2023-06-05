package ru.rabiarill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.rabiarill.filter.JwtFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final UserDetailsService userDetailsService;
   private final JwtFilter jwtFilter;
   @Value("${spring.profiles.active}")
   private String activeProfile;

   @Autowired
   public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
      this.userDetailsService = userDetailsService;
      this.jwtFilter = jwtFilter;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      if (activeProfile.equals("test")) {
         http.csrf().disable().authorizeRequests().anyRequest().permitAll();
      } else {
         http
                 .csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/social-media/v1/auth/**", "/swagger-resources/**",
                         "/swagger-ui/**", "/v2/api-docs", "/webjars/**").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
      }
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService)
              .passwordEncoder(getPasswordEncoder());
   }

   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

}
