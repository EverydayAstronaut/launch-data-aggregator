package com.launchAggregator.aggregator.config

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import org.springframework.web.filter.OncePerRequestFilter


@Component
class Cors : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token")
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token")
        when(request.method) {
            "OPTIONS" -> HttpServletResponse.SC_OK
            else -> filterChain.doFilter(request, response)
        }
    }
}