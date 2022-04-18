package dgut.ljj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 Swagger 配置类
 * @author liangjinju
 * @date 2022/03/14/ 17:10
 */
@Configuration
@EnableSwagger2 //开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {

        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean flag = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("blog")
                .enable(flag) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("dgut.ljj.controller"))
                .build();
    }

    //配置分组
    @Bean
    public Docket docket1(Environment environment) {
        Profiles of = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("system")
                .enable(flag);
    }


    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("ljj", "", "");
        return new ApiInfo(
                "me_blog API 文档", // 标题
                "使用spring boot整合swagger管理API", // 描述
                "v1.0", // 版本
                "http://www.icodeme.com", // 组织链接
                contact, // 联系人信息
                "", // 许可
                "", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

    /*
    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

     */
}
