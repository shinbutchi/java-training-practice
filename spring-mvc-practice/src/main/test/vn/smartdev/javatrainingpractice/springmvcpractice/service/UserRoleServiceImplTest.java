package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.Role;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserRole;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IRoleRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRoleRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.impl.UserRoleServiceImpl;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.UserServiceTestUtil;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceImplTest {
    @Mock
    IUserRoleRepository userRoleRepository;

    @Mock
    IRoleRepository roleRepository;

    @Mock
    IUserRepository userRepository;

    private IUserRoleService userRoleService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        userRoleService = new UserRoleServiceImpl(userRoleRepository, roleRepository);
    }

    @Test
    public void shouldAddUserRoleSuccessfully() {
        UserRole userRole = UserServiceTestUtil.prepareUserRole();
        userRoleService.add(userRole);

        ArgumentCaptor<UserRole> userRoleArgument = ArgumentCaptor.forClass(UserRole.class);
        verify(userRoleRepository, times(1)).save(userRoleArgument.capture());

        UserRole actualUserRole = userRoleArgument.getValue();

        Assertions.assertThat(actualUserRole.getRoleByRoleId()).isEqualTo(userRole.getRoleByRoleId());
        Assertions.assertThat(actualUserRole.getUserByUserId()).isEqualTo(userRole.getUserByUserId());
    }

    @Test
    public void shouldSetRoleForUserSuccessfully() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        when(roleRepository.findFirstByRoleName("ROLE_USER")).thenReturn(role);
        UserRole userRole = new UserRole(user, role);
        when(userRoleRepository.save(any(UserRole.class))).thenReturn(userRole);
        userRole = userRoleService.setRoleForUser(user);

        Assertions.assertThat(userRole.getUserByUserId()).isEqualTo(user);
        Assertions.assertThat(userRole.getRoleByRoleId().getRoleName()).isEqualTo("ROLE_USER");
    }
}
