package admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;  

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/18 下午12:02.
 * @blog http://blog.didispace.com
 */
@Configuration
@EnableSwagger2
@Profile("test")
public class Swagger2 {
//	https://blog.csdn.net/u010466329/article/details/78522992
//	https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md
//		https://www.ibm.com/developerworks/cn/web/wa-use-swagger-to-document-and-define-restful-apis/index.html?lnk=hm
//	http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/
//	http://blog.didispace.com/page/5/
	//http://blog.didispace.com/spring-boot-starter-swagger-1.1.0/

    private static final List<String> excludedPathPrefix = Arrays.asList(
            "/jsf"
    );
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();    
        ticketPar.name("token").description("user ticket")  
        .modelRef(new ModelRef("string")).parameterType("header")   
        .required(false).build(); //header中的ticket参数非必填，传空也可以  
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数  
    	   
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("admin.modules"))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//                .globalOperationParameters(pars);
        
        return new Docket(DocumentationType.SWAGGER_2).
                useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("^(?!auth).*$"))
//                .paths(new PredicateImp())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }
    
    public static class PredicateImp implements Predicate<String> {
    
        @Override
        public boolean apply(String s) {
            for (String pathPrefix : excludedPathPrefix) {
                if (s.startsWith(pathPrefix)) {
                    return true;
                }
            }
            return  false;
        }
    }

    private List<ApiKey> securitySchemes() {
    	ArrayList arr= new ArrayList();
//    	arr.add(new ApiKey("Authorization", "token", "header"));
    	arr.add(new ApiKey("Authorization", "token", "header"));
    	return arr;
    }

    private List<SecurityContext> securityContexts() {
    	ArrayList arr= new ArrayList();
        SecurityContext build = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        arr.add(build);
        return arr;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SecurityReference("Authorization", authorizationScopes));
        return  arrayList;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://www.java1to3.cn/")
                .termsOfServiceUrl("http://www.java1to3.cn/")
                .contact("王洪会")
                .version("1.0")
                .build();
    }

}
