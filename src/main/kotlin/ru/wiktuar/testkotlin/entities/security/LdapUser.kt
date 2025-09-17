package ru.wiktuar.testkotlin.entities.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class LdapUser (
    username: String,
    password: String,
    var info: MutableMap<String, List<String>>,
    authorities: Collection<GrantedAuthority>
): User(username, password, authorities) {
    // Можно добавить геттеры для email, fullName и т.д.
}