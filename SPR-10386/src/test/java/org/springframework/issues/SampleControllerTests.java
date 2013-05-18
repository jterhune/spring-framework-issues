package org.springframework.issues;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:WEB-INF/spring/root-context.xml",
        "classpath:WEB-INF/spring/servlet-context.xml"})
public class SampleControllerTests {

    @Autowired
    RequestMappingHandlerAdapter adapter;

    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private SampleController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        controller = new SampleController();
    }

    @Test
    public void testValidUri() throws Exception {
        request.setRequestURI("/simple");
        assertNotNull(handlerMapping.getHandler(request));
    }

    @Test
    public void testInvalidUri() throws Exception {
        request.setRequestURI("/invalid");
        assertNull(handlerMapping.getHandler(request));
    }

    @Test
    public void testWithoutSemicolon() throws Exception {
        request.setRequestURI("/site/Home/123.p");
        assertNotNull(handlerMapping.getHandler(request));
    }

    @Test
    public void testWithSemicolon() throws Exception {
        request.setRequestURI("/site/Home/123.p;jsessionid=999");
        assertNotNull(handlerMapping.getHandler(request));
    }
}
