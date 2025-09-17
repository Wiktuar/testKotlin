package ru.wiktuar.testkotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.ldap.DefaultSpringSecurityContextSource
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity(debug = false)
class SecurityConfig(
    private val customAuthProvider: AuthenticationProvider // ваш кастомный провайдер
) {

    @Bean
    fun contextSource(): DefaultSpringSecurityContextSource {
        return DefaultSpringSecurityContextSource(
            listOf("ldap://prd-obi-dc3.obi.ru/"), "DC=obi,DC=ru"
        ).apply {
            setUserDn("cn=Musorov.SA,DC=obi,DC=ru")  // если нужно
            setPassword("")
            afterPropertiesSet()
        }
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(customAuthProvider))
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/department/**", "/static/**", "/js/**", "/css/**", "/img/**", "/home", "/error").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login")
                    .failureHandler(authenticationFailureHandler())
                    .successHandler(authenticationSuccessHandler())
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
                    .logoutSuccessUrl("/")
            }
            .authenticationManager(authenticationManager()) // подключаем кастомный AuthenticationManager

        return http.build()
    }

    @Bean
    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return CustomAuthenticationFailureHandler()
    }

    @Bean
    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return CustomAuthenticationSuccessHandler();
    }
}

