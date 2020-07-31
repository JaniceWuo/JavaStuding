package com.leyou.gateway.filter;

import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@EnableConfigurationProperties({JwtProperties.class,FilterProperties.class})
public class LoginFilter extends ZuulFilter {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private FilterProperties filterProperties;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //获取白名单
        List<String> allowPaths = this.filterProperties.getAllowPaths();

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取请求的路径
        String url = request.getRequestURL().toString();
        for (String allowPath : allowPaths) {
            if(StringUtils.contains( url,allowPath )){
                return false;   //属于白名单的路径就不拦截
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = context.getRequest();
        String token = CookieUtils.getCookieValue( request,this.jwtProperties.getCookieName() );
        if(StringUtils.isBlank(token)){
            context.setSendZuulResponse( false );
            context.setResponseStatusCode( HttpStatus.UNAUTHORIZED.value() );
        }
        try {
            JwtUtils.getInfoFromToken( token,this.jwtProperties.getPublicKey() );
        } catch (Exception e) {
            e.printStackTrace();
            context.setSendZuulResponse( false );
            context.setResponseStatusCode( HttpStatus.UNAUTHORIZED.value() );
        }
        return null;
    }
}
