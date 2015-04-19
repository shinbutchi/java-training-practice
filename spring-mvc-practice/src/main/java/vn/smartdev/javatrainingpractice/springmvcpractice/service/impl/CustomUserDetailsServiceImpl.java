package vn.smartdev.javatrainingpractice.springmvcpractice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;
import vn.smartdev.javatrainingpractice.springmvcpractice.exception.WrongUsernameException;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserRoleService;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    IUserRoleService userRoleService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.smartdev.javatrainingpractice.springmvcpractice.entities.User user = userService.findByUsername(username);
        if(user == null) {
            throw new WrongUsernameException("error");
        }

        List<GrantedAuthority> authorities =
                buildUserAuthority(userRoleService.getUserRoleByUser(user));
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(vn.smartdev.javatrainingpractice.springmvcpractice.entities.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRoleByRoleId().getRoleName()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(setAuths);

        return grantedAuthorities;
    }
}
