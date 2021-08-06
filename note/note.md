## SSM框架整合
目录结构
![img.png](img.png)
### 一、pom.xml文件
```xml
<!--依赖
    junit，数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring
-->
<dependencies>
    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.5</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
        <scope>provided</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.6</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.9</version>
    </dependency>
	<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
	<dependency>
		<groupId>org.aspectj</groupId>
		<artifactId>aspectjweaver</artifactId>
		<version>1.9.7</version>
		<scope>runtime</scope>
	</dependency>
</dependencies>

<!--静态资源导出问题-->
<!--在build中配置resources，来防止我们资源导出失败的问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

### 二、数据库连接文件：database.properties
```properties
#jdbc.driver=com.mysql.jdbc.Driver
# mysql8.0+
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?characterEncoding=utf-8&serverTimezone=GMT
jdbc.username=root
jdbc.password=123456
```

### 三、mybatis相关文件:
* mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
		PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 配置数据源 spring做-->
	<typeAliases>
		<package name="edu.dj.pojo"/>
	</typeAliases>

	<!--绑定mybatis配置文件-->
	<mappers>
		<mapper class="edu.dj.dao.BookMapper"/>
	</mappers>
</configuration>
```
* BookMapper.xml文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="edu.dj.dao.BookMapper">

	<insert id="addBook" parameterType="Books">
		insert into ssmbuild.books (bookName, bookCounts, detail)
		VALUES (#{bookName},#{bookCounts},#{detail});
	</insert>
	<delete id="deleteBookById" parameterType="int">
		delete from ssmbuild.books
		where bookID = #{bookId}
	</delete>
	<update id="updateBook" parameterType="Books">
		update ssmbuild.books
		set bookName=#{bookName},bookCounts=#{bookCounts},detail=#{detail}
	</update>
	<select id="queryBookById" parameterType="int" resultType="Books">
		select * from ssmbuild.books
		where bookID = #{bookId}
	</select>
	<select id="queryAllBook" resultType="Books">
		select * from ssmbuild.books
	</select>
</mapper> 
```
### 四、spring相关配置文件文件:
* spring-dao.xml文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">
	<!--
        spring-dao.xml
    -->
	<!--1.关联数据库配置文件-->
	<context:property-placeholder location="classpath:database.properties"/>
	<!--2.连接池-->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driver}"/>
		<property name="jdbcUrl" value="${jdbc.url}"/>
		<property name="user" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>

	<!--3.sqlSessionFactory-->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<!--绑定mybatis文件-->
		<property name="configLocation" value="classpath:mybatis-config.xml"/>
	</bean>

	<!--4.配置dao接口扫描包，动态实现了dao接口可以注入到Spring容器中-->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<!--注入sqlSessionFactory-->
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
		<!--扫描到dao包-->
		<property name="basePackage" value="edu.dj.dao"/>
	</bean>
</beans>
```
* spring-service.xml文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
	<!--1.扫描service下的包-->
	<context:component-scan base-package="edu.dj.service"/>

	<!--2.将我们所有的业务层，注入spring，可以通过配置，或者注解-->
	<bean id="BookServiceImpl" class="edu.dj.service.serviceImpl.BookServiceImpl">
		<property name="bookMapper" ref="bookMapper"/>
	</bean>

	<!--3.声明式事务-->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!--注入数据源-->
		<property name="dataSource" ref="dataSource"/>
	</bean>
	<!--4.aop事务注解-->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<tx:method name="*" propagation="REQUIRED"/>
		</tx:attributes>
	</tx:advice>
	<aop:config>
		<aop:pointcut id="txPointCut" expression="execution(* edu.dj.dao.*.*(..))"/>
		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
	</aop:config>
</beans>
```
* spring-mvc.xml文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/mvc 
       https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
	<!--1.注解驱动-->
	<mvc:annotation-driven/>
	<!--2.静态资源过滤-->
	<mvc:default-servlet-handler/>
	<!--3.扫描包：controller-->
	<context:component-scan base-package="edu.dj.controller"/>
	<!--4.视图解析器-->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/jsp/"/>
		<property name="suffix" value=".jsp"/>
	</bean>
</beans>
```
* applicationContext.xml文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd">

	<import resource="classpath:spring-dao.xml"/>
	<import resource="classpath:spring-service.xml"/>
	<import resource="classpath:spring-mvc.xml"/>
</beans> 
```

### 五、web.xml配置文件:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
	<!--前端控制器-->
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:applicationContext.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

	<!--乱码过滤-->
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<!--Session-->
	<session-config>
		<session-timeout>15</session-timeout>
	</session-config>
</web-app>
```

