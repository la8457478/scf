////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.fkhwl.scf.web.shiro;
//
//import com.fkhwl.scf.UserDubboClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextClosedEvent;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.ContextStoppedEvent;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApplicationEventHandler implements ApplicationListener, ApplicationContextAware {
//    private static final Logger log = LoggerFactory.getLogger(ApplicationEventHandler.class);
//
//    private static ApplicationContext applicationContext;
//    private String applicationName;
//    private String baseNode;
//    public static boolean isShutdown = false;
//
//    @Autowired
//    public ApplicationEventHandler() {
//    }
//
//    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
//        applicationContext = applicationContext;
//
//    }
//
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        if (applicationEvent instanceof ContextRefreshedEvent) {
//            log.debug("spring 容器初始化完成: {}", applicationEvent.getClass());
//            //初始化dubbo
//            ContextRefreshedEvent contextRefreshedEvent = (ContextRefreshedEvent)applicationEvent;
//            if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
//                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboRegistry.xml");
//                context.start();
//                UserDubboClient demoService = context.getBean("userDubboClient", UserDubboClient.class);
//                demoService.findInfo(1l);
//
//                DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
//                beanFactory.applyBeanPostProcessorsAfterInitialization(rate, rate.getClass().getName());
//                beanFactory.registerSingleton(demoService.getClass().getName(), rate);
//            } else {
//                this.printBanner();
//            }
//        } else {
//            if (applicationEvent instanceof ContextStoppedEvent) {
//                isShutdown = true;
//                log.debug("应用停止事件: {}", applicationEvent.getClass());
////                if (!"prod".equals(this.traceConfig.getEnv())) {
////                    this.cleanNode(this.zkClient, this.applicationName, this.serverHost, this.serverHost);
////                }
////
////                loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
////                loggerContext.stop();
////                this.zkClient.close();
//            } else if (applicationEvent instanceof ContextClosedEvent) {
//                log.debug("应用关闭事件: {}", applicationEvent.getClass());
////                if (!"prod".equals(this.traceConfig.getEnv())) {
////                    this.cleanNode(this.zkClient, this.applicationName, this.serverHost, this.serverHost);
////                }
////
////                loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
////                loggerContext.stop();
////                this.zkClient.close();
//            }
//        }
//
//    }
//
//
////    private void handleLoglevelNodeAndStartSymbol(String applicationName) {
////        this.printBanner(applicationName);
////        log.error("=================================");
////        log.error("system [{}] start finished", applicationName);
////        log.error("=================================");
////
////        String loglevelNode;
////        try {
////            loglevelNode = this.baseNode + "/loglevel";
////            Map<String, String> logLevelInfoMap = LogLevelHandler.getAllLevelInfo(applicationName, new LogNode());
////            logLevelInfoMap.put("changeDataType", "start");
////            String logLevelInfo = DataAnalyzingUtil.analyzingMapToString(logLevelInfoMap);
////            if (this.zkClient.exists(loglevelNode) == null) {
////                this.zkClient.createPersitentNode(loglevelNode, logLevelInfo);
////            } else {
////                log.error("started and update loglevel data");
////                this.zkClient.setNodeData(loglevelNode, logLevelInfo);
////            }
////        } catch (Exception var5) {
////            log.error("init node error or read node data error", var5);
////        }
////
////        loglevelNode = this.baseNode + "/running_man";
////        this.zkClient.createSymbol(loglevelNode);
////    }
//
//    private void printBanner() {
////        (new BannerPrinter()).print();
//    }
//}
