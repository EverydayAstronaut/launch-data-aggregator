package com.launchAggregator.aggregator.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.access.channel.ChannelProcessingFilter


@Configuration
@EnableWebSecurity
class SpringSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .addFilterBefore(Cors(), ChannelProcessingFilter::class.java).authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("everydayastronaut").password("{noop}launchaggregator").roles("USER")
    }
}