package vn.smartdev.javatrainingpractice.springmvcpractice.service;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import vn.smartdev.javatrainingpractice.springmvcpractice.dto.UserDTO;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.User;
import vn.smartdev.javatrainingpractice.springmvcpractice.repositories.IUserRepository;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.impl.UserServiceImpl;
import vn.smartdev.javatrainingpractice.springmvcpractice.util.UserServiceTestUtil;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    IUserRepository userRepository;

    private IUserService userService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldCreateUserSuccess() {
        UserDTO userDTO = UserServiceTestUtil.preparedUserDTO();
        userService.add(userDTO);
        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgument.capture());

        User user = userArgument.getValue();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(UserServiceTestUtil._USERNAME);
//        Assertions.assertThat(user.getPassword()).isEqualTo(UserServiceTestUtil._PASSWORD);
        Assertions.assertThat(user.getEmailAddress()).isEqualTo(UserServiceTestUtil._EMAILADDRESS);
        Assertions.assertThat(user.getFirstName()).isEqualTo(UserServiceTestUtil._FIRSTNAME);
        Assertions.assertThat(user.getLastName()).isEqualTo(UserServiceTestUtil._LASTNAME);
        Assertions.assertThat(user.getCountry()).isEqualTo(UserServiceTestUtil._COUNTRY);
        Assertions.assertThat(user.getCity()).isEqualTo(UserServiceTestUtil._CITY);
        Assertions.assertThat(user.getPhoneNumber()).isEqualTo(UserServiceTestUtil._PHONENUMBER);
        Assertions.assertThat(user.isEnabled()).isEqualTo(true);
    }

    @Test
    public void shouldEncodeMD5Password() {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword("admin", null);
        Assertions.assertThat(hashedPass).isEqualTo("21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    public void shouldReturnTrueWhenExistedEmailAddress() {
        User user = UserServiceTestUtil.preparedUser();
        when(userRepository.findFirstByEmailAddress(UserServiceTestUtil._EMAILADDRESS)).thenReturn(user);

        Assertions.assertThat(userService.isExistedEmailAddress(UserServiceTestUtil._EMAILADDRESS)).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueWhenExistedUserName() {
        User user = UserServiceTestUtil.preparedUser();
        when(userRepository.findFirstByUsername(UserServiceTestUtil._USERNAME)).thenReturn(user);

        Assertions.assertThat(userService.isExistedUsername(UserServiceTestUtil._USERNAME)).isEqualTo(true);
    }

    @Test
    public void shouldUpdateUserSuccessfully() {
        UserDTO userDTO = UserServiceTestUtil.preparedUserDTO();
        User user = UserServiceTestUtil.preparedUser();
        when(userRepository.findFirstByUsername(userDTO.getUsername())).thenReturn(user);

        userDTO.setFirstName("Edited First Name");
        userDTO.setLastName("Edited Last Name");

        userService.update(userDTO);
        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgument.capture());

        User actualUser = userArgument.getValue();

        Assertions.assertThat(userDTO.getFirstName()).isEqualTo(actualUser.getFirstName());
        Assertions.assertThat(userDTO.getLastName()).isEqualTo(actualUser.getLastName());
    }

}
