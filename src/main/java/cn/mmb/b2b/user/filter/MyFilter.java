package cn.mmb.b2b.user.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lb on 2016/2/27.
 */
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
