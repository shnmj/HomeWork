package user;

import core.error.ex.ExceptionApi400;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

public class UserFilter implements Filter {

    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^\\w&=?://]");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String query = httpRequest.getQueryString();

        if (query != null && SPECIAL_CHAR_PATTERN.matcher(query).find()) {
            throw new ExceptionApi400("특수문자가 포함되어 있습니다.");
        }

        chain.doFilter(request, response);
    }
}
