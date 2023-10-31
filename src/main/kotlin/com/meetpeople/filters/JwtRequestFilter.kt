package com.meetpeople.filters

import com.meetpeople.utils.JWTTokenUtils
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.security.SignatureException

@Component
class JwtRequestFilter(private val jwtUtils: JWTTokenUtils) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerName = "Bearer "
        val authHeader = request.getHeader("Authorization")
        var phone: String? = null

        if (authHeader != null && authHeader.startsWith(headerName)) {
            val jwt = authHeader.substring(headerName.length)
            try {
                phone = jwtUtils.username(jwt)
            } catch (se: SignatureException) {
                logger.debug("Неправильная подпись токена")
            } catch (ee: ExpiredJwtException) {
                logger.debug("Время жизни токена истекло")
            }
        }
        if (phone != null && SecurityContextHolder.getContext().authentication == null) {
            val token = UsernamePasswordAuthenticationToken(
                phone, null, listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
            SecurityContextHolder.getContext().authentication = token
        }
        filterChain.doFilter(request, response)
    }

}