package test.http;

import http.req;
import org.junit.Test;

/**
 * req Tester.
 *
 * @author <TimJ name>
 * @version 1.0
 * @since <pre>Juli 13, 2021</pre>
 */
public class reqTest {

    /**
     * Method: getImpfPortal()
     */
    @Test
    public void testGetImpfPortal() throws Exception {
        assert (req.getImpfPortal().length() > 0);

    }


} 
