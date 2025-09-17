package ru.wiktuar.testkotlin.entities.security

import org.springframework.security.core.GrantedAuthority

class CustomUser (
    var username: String,
    var password: String,
    var info: MutableMap<String, List<String>>,
    var authorities: Collection<GrantedAuthority>
)
