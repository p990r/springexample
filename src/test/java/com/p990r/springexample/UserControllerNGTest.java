package com.p990r.springexample;

import com.p990r.springexample.model.UserRepository;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class UserControllerNGTest {

    private BindingResult result_2 = Mockito.mock(BindingResult.class);
    private Model model = Mockito.mock(Model.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    public UserControllerNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
