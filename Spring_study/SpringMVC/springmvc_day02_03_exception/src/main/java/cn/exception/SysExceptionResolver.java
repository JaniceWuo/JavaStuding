package cn.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    //处理异常业务逻辑
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        //获取异常对象
        SysException e = null;
        if(ex instanceof SysException){
            e = (SysException)ex;
        }else {
            e = new SysException("系统正在维护");
        }
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",e.getMessage());  //相当于request
        mv.setViewName("error");  //往error.jsp跳
        return mv;
    }
}
