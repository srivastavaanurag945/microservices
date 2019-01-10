package com.Students.Students;
import javax.annotation.Resource.AuthenticationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.boot.actuate.security.*;

@Configurable
public class SpringSecurityConfig extends WebMvcConfigurerAdapter {
	@Autowired
    public void configureGlobal(AuthenticationType auth) throws Exception {
      //  auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
 
    }
}
