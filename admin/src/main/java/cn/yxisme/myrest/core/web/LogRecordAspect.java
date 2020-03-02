package cn.yxisme.myrest.core.web;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangxiong on 2019/12/9.
 */
@Aspect
@Configuration
public class LogRecordAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* cn.yxisme.*.*(..))")
    public void excudeService() {}

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra == null) {
            return null;
        }
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURI();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = point.getArgs();
        String params = "";

        if(args.length>0){
            if("POST".equals(method)){
                Object object = args[0];
                JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(object));
                params = json.toString();
            }else if("GET".equals(method)){
                params = queryString;
            }
        }

        logger.info("请求开始===地址：【{}】", url);
        logger.info("请求开始===类型：【{}】", method);
        logger.info("请求开始===参数：【{}】", params);

        Object result = point.proceed();
        logger.info("请求结束===返回值：【{}】", JSONObject.toJSONString(result));
        return result;
    }
}
