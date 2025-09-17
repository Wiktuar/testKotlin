package ru.wiktuar.testkotlin.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.wiktuar.testkotlin.entities.security.LdapUser

@Component
class CustomAuthenticationProvider : AuthenticationProvider {
    val admins = mutableListOf("obi\\nikitoshkin.an", "obi\\gusev.va")

    override fun authenticate(authentication: Authentication): Authentication? {
        val username = authentication.name;

        val password = authentication.credentials.toString()

        // ldap проверка
//        val env = Hashtable<String, String>()
//        env[Context.INITIAL_CONTEXT_FACTORY] = "com.sun.jndi.ldap.LdapCtxFactory"
//        env[Context.PROVIDER_URL] = "ldap://prd-obi-dc3.obi.ru"
//        env[Context.SECURITY_AUTHENTICATION] = "simple"
//        env[Context.SECURITY_PRINCIPAL] = username
//        env[Context.SECURITY_CREDENTIALS] = password

        var description: String?

//        try {
//            val ctx: DirContext = InitialDirContext(env)
//            description = ctx.toString()
//
//            // Пример поиска пользователей в LDAP
//            val controls = SearchControls()
//            controls.searchScope = SearchControls.SUBTREE_SCOPE
//
//            val userFilter: List<String> = username.split("\\")
//            val uf = if (userFilter.size > 1) userFilter[1] else userFilter[0]
//
//            val results = ctx.search("OU=Obibank,DC=obi,DC=ru", "(SamAccountName=${uf})", controls)
//            while (results.hasMore()) {
//                val sr: SearchResult = results.next()
//                val attrs = sr.attributes
//
//                val attrMap = mutableMapOf<String, List<String>>()
//
//                val attrsEnum = attrs.all
//                while (attrsEnum.hasMore()) {
//                    val attr = attrsEnum.next()
//                    val id = attr.id
//
//                    // Собираем все значения атрибута в список строк
//                    val values = mutableListOf<String>()
//                    val valsEnum = attr.all
//                    while (valsEnum.hasMore()) {
//                        val value = valsEnum.next()
//                        values.add(value.toString())
//                    }
//
//                    attrMap[id] = values
//                }
//
//                val user = LdapUser(
//                    username = username,
//                    password = password,
//                    info = attrMap,
//                    authorities = getListOfAuthtorities(username)
//                )
//
//                ctx.close()
//                return UsernamePasswordAuthenticationToken(user, null, user.authorities)
//            }
//        } catch (e: Exception) {
//            throw BadCredentialsException("Неправильный логин или пароль");
//        }

        // ldap проверка
        val attrMap = mutableMapOf<String, List<String>>();
        val user = LdapUser(
                    username = username,
                    password = "default",
                    info = attrMap,
                    authorities = getListOfAuthorities(username)
                )

        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

    private fun getListOfAuthorities(username: String): List<GrantedAuthority> {
        val name = if(!username.contains("obi\\"))"obi\\$username" else username

        if (admins.contains(username))
            return listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        else
            return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }
}