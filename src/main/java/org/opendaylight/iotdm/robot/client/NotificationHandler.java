package org.opendaylight.iotdm.robot.client;


import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.opendaylight.iotdm.robot.example.Example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenxshi on 3/13/15.
 */
public class NotificationHandler extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse
            response) throws IOException, ServletException {
        String payload = IOUtils.toString(baseRequest.getInputStream());
        System.out.println("server notification:");
        Example.prettyPrint(payload);
    }
}
