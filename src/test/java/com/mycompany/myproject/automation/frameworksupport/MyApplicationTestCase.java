package com.mycompany.myproject.automation.frameworksupport;

import com.mycompany.myproject.automation.data.Constants;
import com.pwc.core.framework.WebTestCase;
import com.pwc.core.framework.command.WebServiceCommand;
import com.pwc.core.framework.data.Credentials;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.List;

public abstract class MyApplicationTestCase extends WebTestCase {

    private Credentials credentials;
    private boolean headlessMode = false;

    @BeforeClass(alwaysRun = true)
    public void login() {
        credentials = new Credentials(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        if (!isHeadlessMode()) {
            webAction(credentials);
        }
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
    }

    /**
     * Send a REST ws action to a service End Point
     *
     * @param command BaseGetCommand command type
     */
    protected Object webServiceAction(final WebServiceCommand command) {
        return super.webServiceAction(credentials, command, null);
    }

    /**
     * Send a REST ws action to a service End Point
     *
     * @param command     BaseGetCommand command type
     * @param requestBody POST request body
     */
    protected Object webServiceAction(final WebServiceCommand command, final Object requestBody) {
        if (requestBody instanceof HashMap || requestBody instanceof List) {
            return super.webServiceAction(credentials, command, null, requestBody);
        } else {
            return super.webServiceAction(credentials, command, requestBody, null);
        }
    }

    /**
     * Send a REST ws action to a service End Point
     *
     * @param command      BaseGetCommand command type
     * @param parameterMap Name-Value pair filled map of parameters to send in HTTP request
     */
    protected Object webServiceAction(final WebServiceCommand command, final HashMap<String, Object> parameterMap) {
        return super.webServiceAction(credentials, command, null, parameterMap);
    }

    /**
     * Perform HttpClient based actions with the application under test
     *
     * @param url HTTP end-point
     * @return HTTP response map
     */
    protected Object httpAction(final String url) {
        return httpAction(credentials, url);
    }

    public boolean isHeadlessMode() {
        return headlessMode;
    }

    public void setHeadlessMode(boolean headlessMode) {
        this.headlessMode = headlessMode;
    }

}
