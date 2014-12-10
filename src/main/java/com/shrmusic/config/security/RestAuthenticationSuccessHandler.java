package com.shrmusic.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Component("successHandler")
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication)
            throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();

        if(params.containsKey("username") && params.containsKey("password")){
            String username = params.get("username")[0];
            String passwordMD5 = DigestUtils.md5DigestAsHex(params.get("password")[0].getBytes());

            String accessToken = Base64.getEncoder().encodeToString((username + "." + passwordMD5).getBytes());
            response.getWriter().write(accessToken);
        }
        clearAuthenticationAttributes(request);
    }
}
