## 文件上传
### 一、依赖
```xml
<dependency>
	<groupId>javax.servlet.jsp</groupId>
	<artifactId>javax.servlet.jsp-api</artifactId>
	<version>2.3.3</version>
	<scope>provided</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<!-- 文件上传 -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

### 二、spring-mvc.xml文件配置
springMVC为文件上传提供了直接的支持，这种支持是用即插即用的MultipartResolver实习
```xml

```