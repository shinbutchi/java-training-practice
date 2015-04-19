package vn.smartdev.javatrainingpractice.springmvcpractice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserRoleService;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/page/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    private MockMvc mockMvc;

    @Mock
    IUserService userServiceMock;

    @Mock
    IUserRoleService userRoleServiceMock;

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return viewResolver;
    }

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(new UserController(userServiceMock, userRoleServiceMock))
                .setViewResolvers(viewResolver()).build();
    }

    @Test
    public void shouldRenderListUserPageSuccessfully() throws Exception {
        mockMvc.perform(get("/list-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("listUser"));
    }

    @Test
    public void shouldShowSignUpPageSuccessfully() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }
}
