package com.omni.webapp.service;

import java.io.Serializable;

public interface UserDetails extends Serializable {
    String getUsername();

    String getPassword();

    // <3> more methods:
    // isAccountNonExpired,isAccountNonLocked,
    // isCredentialsNonExpired,isEnabled
}
