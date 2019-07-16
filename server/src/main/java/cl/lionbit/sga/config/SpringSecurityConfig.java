package cl.lionbit.sga.config;

import cl.lionbit.sga.error.CustomAccessDeniedHandler;
import cl.lionbit.sga.security.AuthenticationSuccessHandler;
import cl.lionbit.sga.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("cl.lionbit.sga.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandler SuccessHandler;

    private SimpleUrlAuthenticationFailureHandler FailureHandler = new SimpleUrlAuthenticationFailureHandler();

    public SpringSecurityConfig() {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs").authenticated()
                .and()
                .httpBasic();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/api/students/**").authenticated()
                .antMatchers("/api/teachers/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .successHandler(SuccessHandler)
                .failureHandler(FailureHandler)
                .and()
                .httpBasic()
                .and()
                .logout();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{

        PasswordEncoder encoder = passwordEncoder();
        UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));

        // Creamos un user con su password (y se codicara la clave usando encoder) y roles
        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("12345678").roles("ADMIN"))
                .withUser(users.username("user").password("12345678").roles("USER"));
    }
}
