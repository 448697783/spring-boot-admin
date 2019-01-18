package admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
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
@Profile("dev")
public class Swagger2 {
//	https://blog.csdn.net/u010466329/article/details/78522992
//	https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md
//		https://www.ibm.com/developerworks/cn/web/wa-use-swagger-to-document-and-define-restful-apis/index.html?lnk=hm
//	http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/
//	http://blog.didispace.com/page/5/
	//http://blog.didispace.com/spring-boot-starter-swagger-1.1.0/
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();    
        ticketPar.name("token").description("user ticket")  
        .modelRef(new ModelRef("string")).parameterType("header")   
        .required(false).build(); //header中的ticket参数非必填，传空也可以  
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数  
    	   
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("admin.modules"))
                .paths(PathSelectors.any())
                .build();
//                .globalOperationParameters(pars);
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
