>基于arouter实现Android模块化方案
# 一、模块化优势：
- 多团队并行开发测试；
- 模块间解耦、重用；
- 可单独编译打包某一模块，提升开发效率

# 二、模块化架构图
![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/1.png)
使用方式：
- 各模块作为Module，所有模块代码都在同一Project
- 各模块都在独立的Project内

# 三、模块化需求介绍
### 1、模块隔离，去除强依赖
- 每个模块都需要指定一个资源前缀resourcePrefix，以避免集成后资源名冲突的问题，包名也需要添加模块名，避免class重名
- runtimeOnly 避免在模块调试时强引用其他模块
### 2、页面跳转
### 3、模块调用

## 思路
- 服务化：注册服务、查找服务
- 使用反射进行调用，但不推荐这样做，这样模块就没有暴露服务的契约了，当被调用者发生改变，则使用反射的调用方无法使用。

# 四、注册服务
>各个模块如果想对外提供某些功能，则需要向服务中心注册自己的提供的服务
![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/2.png)

- 自动注册：router是服务注册中心，这里的path-class的map表是router帮我们自动生成的。
- 手动注册：在通过引用provider方式使用时，需要将provider接口手动放在base或router module里，我们称之为手动注册
![](https://raw.githubusercontent.com/MasonLiuChn/ArouterModular/master/app/doc/3.png)

# 五、发现服务(都需要强依赖Class，无法应用在模块化解耦上)
>一个模块想使用其他模块的功能则需要到服务中心查找服务
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

# 六、去除依赖发现服务，实现模块化需求
```java
                // 1. 简单跳转
                RouterUtil.go("/test/activity");
                // 2. 跳转携带参数
                RouterUtil.goWith("/test/activity")
                        .withLong("longKey", 0x555555L)
                        .withString("stringKey", "66666")
                        .navigation();
                // 3. 跳转携带参数并有ActivityResult
                RouterUtil.goWith("/test/activity")
                        .withString("data", "app传过来的内容")
                        .navigation(MainActivity.this, 100);
                // 4. 同步调用
                Map<String, Object> res = RouterUtil.exec(MainActivity.this, "/service/hello");
                String resV = (String) res.get("one");
                Toast.makeText(view.getContext(), resV, Toast.LENGTH_LONG).show();

                // 5. 异步调用
                RouterUtil.execAsync(MainActivity.this, "/service/hello",
                        new RouterAsyncCallback() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(@Nullable Map<String, Object> result) {

                            }

                            @Override
                            public void onFailed(@Nullable Map<String, Object> error) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
```
# 七、模块中Application初始化

# 八、重复依赖问题

模块化的过程中我们常常会遇到重复依赖的问题，如果是通过 maven 依赖， gradle 会自动帮我们找出新版本，而抛弃老版本的重复依赖。重复的代码需要抽离到下层组件中。如果是通过jar、aar依赖 则需要上传maven统一管理，或者抽离到下层组件中。



---

# Demo地址

https://github.com/MasonLiuChn/ArouterModular






