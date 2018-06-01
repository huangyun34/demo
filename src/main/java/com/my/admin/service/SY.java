package com.my.admin.service;

import io.swagger.models.auth.In;

import java.lang.reflect.Field;
import java.util.*;

public class SY {
    static String s0 = "org.springframework.context.annotation.internalConfigurationAnnotationProcessor";
    static String s1 = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor";
    static String s2 = "org.springframework.context.annotation.internalRequiredAnnotationProcessor";
    static String s3 = "org.springframework.context.annotation.internalCommonAnnotationProcessor";
    static String s4 = "org.springframework.context.event.internalEventListenerProcessor";
    static String s5 = "org.springframework.context.event.internalEventListenerFactory";
    static String s6 = "adminApplication";
    static String s7 = "org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory";
    static String s8 = "demoConfig";
    static String s9 = "redisConfig";
    static String s10 = "redisProperties";
    static String s11 = "swagger2";
    static String s12 = "helloActuatorController";
    static String s13 = "testController";
    static String s14 = "versionController";
    static String s15 = "account";
    static String s16 = "accountService";
    static String s17 = "OBA";
    static String s18 = "userService";
    static String s19 = "swagger2Controller";
    static String s20 = "licenseMapperImpl";
    static String s21 = "modelMapperImpl";
    static String s22 = "parameterMapperImpl";
    static String s23 = "securityMapperImpl";
    static String s24 = "serviceModelToSwagger2MapperImpl";
    static String s25 = "vendorExtensionsMapperImpl";
    static String s26 = "apiDescriptionLookup";
    static String s27 = "apiDescriptionReader";
    static String s28 = "apiDocumentationScanner";
    static String s29 = "apiListingReader";
    static String s30 = "apiListingReferenceScanner";
    static String s31 = "apiListingScanner";
    static String s32 = "apiModelReader";
    static String s33 = "cachingOperationReader";
    static String s34 = "mediaTypeReader";
    static String s35 = "apiOperationReader";
    static String s36 = "cachingOperationNameGenerator";
    static String s37 = "defaultOperationReader";
    static String s38 = "defaultTagsProvider";
    static String s39 = "operationDeprecatedReader";
    static String s40 = "operationModelsProvider";
    static String s41 = "operationParameterHeadersConditionReader";
    static String s42 = "operationParameterReader";
    static String s43 = "operationParameterRequestConditionReader";
    static String s44 = "operationResponseClassReader";
    static String s45 = "operationTagsReader";
    static String s46 = "responseMessagesReader";
    static String s47 = "expandedParameterBuilder";
    static String s48 = "modelAttributeParameterExpander";
    static String s49 = "parameterDataTypeReader";
    static String s50 = "parameterDefaultReader";
    static String s51 = "parameterMultiplesReader";
    static String s52 = "parameterNameReader";
    static String s53 = "parameterRequiredReader";
    static String s54 = "parameterTypeReader";
    static String s55 = "documentationPluginsBootstrapper";
    static String s56 = "documentationPluginsManager";
    static String s57 = "webMvcRequestHandlerProvider";
    static String s58 = "operationPathDecorator";
    static String s59 = "pathMappingDecorator";
    static String s60 = "pathSanitizer";
    static String s61 = "queryStringUriTemplateDecorator";
    static String s62 = "cachingModelDependencyProvider";
    static String s63 = "cachingModelProvider";
    static String s64 = "defaultModelDependencyProvider";
    static String s65 = "defaultModelProvider";
    static String s66 = "schemaPluginsManager";
    static String s67 = "accessorsProvider";
    static String s68 = "cachingModelPropertiesProvider";
    static String s69 = "factoryMethodProvider";
    static String s70 = "fieldProvider";
    static String s71 = "objectMapperBeanPropertyNamingStrategy";
    static String s72 = "optimized";
    static String s73 = "typeNameExtractor";
    static String s74 = "apiModelBuilder";
    static String s75 = "apiModelPropertyPropertyBuilder";
    static String s76 = "apiModelTypeNameProvider";
    static String s77 = "operationAuthReader";
    static String s78 = "operationHiddenReader";
    static String s79 = "operationHttpMethodReader";
    static String s80 = "operationImplicitParameterReader";
    static String s81 = "operationImplicitParametersReader";
    static String s82 = "operationNicknameIntoUniqueIdReader";
    static String s83 = "operationNotesReader";
    static String s84 = "operationPositionReader";
    static String s85 = "operationSummaryReader";
    static String s86 = "swaggerMediaTypeReader";
    static String s87 = "swaggerOperationModelsProvider";
    static String s88 = "swaggerOperationResponseClassReader";
    static String s89 = "swaggerOperationTagsReader";
    static String s90 = "swaggerResponseMessageReader";
    static String s91 = "vendorExtensionsReader";
    static String s92 = "swaggerParameterDescriptionReader";
    static String s93 = "swaggerExpandedParameterBuilder";
    static String s94 = "apiResourceController";
    static String s95 = "classOrApiAnnotationResourceGrouping";
    static String s96 = "inMemorySwaggerResourcesProvider";
    static String s97 = "swaggerApiListingReader";
    static String s98 = "com.my.admin.annotation.importannotation.DemoService";
    static String s99 = "redisTemplate";
    static String s100 = "springfox.documentation.schema.configuration.ModelsConfiguration";
    static String s101 = "typeResolver";
    static String s102 = "modelBuilderPluginRegistry";
    static String s103 = "modelPropertyBuilderPluginRegistry";
    static String s104 = "typeNameProviderPluginRegistry";
    static String s105 = "springfox.documentation.spring.web.SpringfoxWebMvcConfiguration";
    static String s106 = "defaults";
    static String s107 = "resourceGroupCache";
    static String s108 = "objectMapperConfigurer";
    static String s109 = "jsonSerializer";
    static String s110 = "documentationPluginRegistry";
    static String s111 = "apiListingBuilderPluginRegistry";
    static String s112 = "operationBuilderPluginRegistry";
    static String s113 = "parameterBuilderPluginRegistry";
    static String s114 = "expandedParameterBuilderPluginRegistry";
    static String s115 = "resourceGroupingStrategyRegistry";
    static String s116 = "operationModelsProviderPluginRegistry";
    static String s117 = "defaultsProviderPluginRegistry";
    static String s118 = "pathDecoratorRegistry";
    static String s119 = "springfox.documentation.swagger.configuration.SwaggerCommonConfiguration";
    static String s120 = "springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration";
    static String s121 = "swagger2Module";
    static String s122 = "createRestApi";
    static String s123 = "org.springframework.amqp.rabbit.annotation.RabbitBootstrapConfiguration";
    static String s124 = "org.springframework.amqp.rabbit.config.internalRabbitListenerAnnotationProcessor";
    static String s125 = "org.springframework.amqp.rabbit.config.internalRabbitListenerEndpointRegistry";
    static String s126 = "org.springframework.scheduling.annotation.ProxyAsyncConfiguration";
    static String s127 = "org.springframework.context.annotation.internalAsyncAnnotationProcessor";

    public static void main(String[] args) {
        Class<SY> ds = SY.class;
        Class<SY1> ds1 = SY1.class;
        Field[] dd = ds.getDeclaredFields();
        Field[] dd1 = ds1.getDeclaredFields();
        List<String> l1 = getList(dd);
        List<String> l2 = getList(dd1);
        for (int i = 0; i < l1.size();) {
            boolean match = false;
            for (int j = 0; j < l2.size();) {
                if (l2.get(j) == l1.get(i)) {
                    l2.remove(j);
                    l1.remove(i);
                    match = true;
                    break;
                }
                j++;
            }
            if (!match) {
                i++;
            }
        }
        System.out.println(l1.size());
        System.out.println(l2.size());
        l2.forEach(s -> {
            System.out.println(s);
        });
    }

    public static List getList(Field[] fields) {
        List<String> l = new LinkedList<>();
        Arrays.stream(fields).forEach(field -> {
            try {
                l.add(field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return l;
    }
}
