package com.launchAggregator.aggregator.config

import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


@EnableWebSecurity
@Order(1000)
class SecurityConfig : WebSecurityConfigurerAdapter()