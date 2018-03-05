

>基于arouter实现Android模块化方案

#一、模块化架构图

![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/1.png)

#二、路由功能
###1、页面跳转
通过指定path实现
###2、模块调用

1、通过在Router Module里注册Provider实现
3、为避免繁琐的增加Provider，可以封装CommonProvider
4、还可以使用反射进行调用，但不推荐这样做，这样模块就没有暴露服务的契约了，当被调用者发生改变，则使用反射的调用方无法使用。

#三、注册服务
![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/2.png)

- 自动注册：router是服务注册中心，这里的path-class的map表是router帮我们自动生成的。
- 手动注册：在通过引用provider方式使用时，需要将provider接口手动放在base或router module里，我们称之为手动注册
![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/3.png)

#四、发现服务
- 使用依赖注入的方式发现服务,通过注解标注字段,即可使用，无需主动获取
```java
@Autowired
HelloProvider helloProvider;
@Autowired(name = "/service/hello")
HelloProvider helloProvider2;
// Autowired注解中标注name之后，将会使用byName的方式注入对应的字段，不设置name属性，会默认使用byType的方式发现服务(当同一接口有多个实现的时候，必须使用byName的方式发现服务)
ARouter.getInstance().inject(this);
helloProvider.sayHello("Vergil");
helloProvider2.sayHello("Vergil");
```

- 使用依赖查找的方式发现服务，主动去发现服务并使用，下面两种方式分别是byName和byType
```java
helloProvider3 = ARouter.getInstance().navigation(HelloProvider.class);
helloProvider4 = (HelloProvider) ARouter.getInstance().build("/service/hello").navigation();
```

---

#Demo地址

https://github.com/MasonLiuChn/ArouterModular






