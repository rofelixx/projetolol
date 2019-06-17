/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author ALM4CT
 */
import Classe.Cliente;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Cliente user = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);
        String contextPath = ((HttpServletRequest) request)
                .getContextPath();
        String loginUrl = contextPath + "/faces/index.xhtml";
        if (sess != null) {
            user = (Cliente) sess.getAttribute("usuarioLogado");
        }

        if (user == null) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/index.xhtml");
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
