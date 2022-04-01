package view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.ViewController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import view.config.ViewConfig;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@Import(ViewConfig.class)
public class MainControllerTest {

    @Autowired
    public ViewController controller;

    @Test
    public void test_when_call_getView_then_return_result() {

        assertNotNull(controller.getView());

    }

}
