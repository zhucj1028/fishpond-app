---
description: Java项目开发规范 - 适用于当前项目，包含分层架构、命名规范、异常处理、多数据源配置、异步任务、定时任务、分页查询等完整开发规范
globs: ["**/*.java", "**/*.xml", "**/*.yml", "**/*.yaml"]
alwaysApply: true
---

# 项目开发规范
> Java项目开发规范 - 适用于当前项目，包含分层架构、命名规范、异常处理、多数据源配置、异步任务、定时任务、分页查询等完整开发规范
>

---

## 项目结构规范
+ **分层架构**（领域驱动设计 DDD）：
  - `controller` 层：负责处理 HTTP 请求、参数校验和路由分发
  - `service` 层：定义业务接口
  - `impl` 层：包含具体的实现类，这些类继承抽象类或实现接口
  - `mapper` 层：负责数据访问与持久化，使用 MyBatis-Plus 进行映射
  - `entity` 层：数据库实体对象（PO）
  - `dto` 层：数据传输对象（DTO），用于参数和数据传递
  - `vo` 层：视图对象（VO），用于接口响应
  - `enums` 层：枚举类型
  - 其他通用基础设施包括 `common`、`constant`、`exception`、`util`、`config`、`interceptor` 等
+ **依赖方向**
  - 严格遵循 controller → service → impl → mapper → entity
  - 禁止循环依赖，上层不依赖下层实现细节，推荐接口隔离
+ **架构原则**
  - 这个结构遵循领域驱动设计（DDD）的原则，有助于实现清晰的职责分离和可维护的代码结构
+ **资源文件**
  - Spring 配置、MyBatis 映射等资源统一放于 `src/main/resources`

## 命名规范
+ **包名**：
    - 全小写，按领域/职责分层（如 `business.basic.controller`）
+ **类名/接口名**：
    - 帕斯卡命名法（PascalCase），与文件名一致
+ **文件名**：
    - 与主类名一致，DTO/VO/PO/Enum 需有后缀
+ **变量名**：
    - 小驼峰（userId），常量全大写下划线分隔（MAX_SIZE）
+ **DTO/VO/PO/Enum**：
    - 分别放在 `dto`、`vo`、`po`、`enums` 子包
+ **Service 命名**：
    - 单层业务类用 `Service` 后缀，如 `UserService`、`OrderService`
    - 跨层业务聚合类用 `{业务前缀}BusinessService` 后缀，如 `FishpondBusinessService`、`MallBusinessService`

## 代码组织与 import
+ **结构体字段顺序**：先导出字段，后私有字段，相关字段分组
+ **方法顺序**：类型/常量 → 构造/初始化 → 主要业务方法 → 辅助方法
+ **import 分组**：标准库、第三方、内部包，组间空行，推荐 IDE 自动排序

## 异常与错误处理规范
+ **统一异常体系**：
    - 业务异常继承 `Err`，全局异常处理用 `GlobalException`
    - 错误码统一用 `RCode` 枚举
+ **错误传播**：
    - 业务层抛出自定义异常，controller 层捕获并返回标准响应
    - 参数校验失败用 `Errs.notBlank`、`Errs.notNull` 等工具
+ **日志记录**：
    - 只在错误源头记录日志，避免重复
    - 日志内容包含上下文信息，使用 `Slf4j` 日志框架

```java
// 错误处理示例
try {
    // ...业务逻辑
} catch (Exception e) {
    log.error("操作失败: {}", e.getMessage(), e);
    throw new Err(RCode.ERROR, "操作失败");
}
```

## 日志规范
+ 统一使用 `Slf4j`，日志级别分明（info/warn/error）
+ 关键流程节点记录日志，避免无效/重复日志
+ 日志配置见 `logback-spring-*.xml`

## 并发与事务处理
+ 线程池配置集中于 `ThreadPoolConfig`
+ 事务统一用 `@Transactional` 注解，或全局 AOP 配置（见 `TransactionConfig`）
+ 只读操作建议加只读事务，增删改操作强制事务

## 性能优化建议
+ 批量操作优先，避免 N+1 查询
+ 合理使用缓存（如 Ehcache），避免重复 DB 查询
+ 连接池、分页、懒加载等优化
+ 日志、异常、IO 操作注意性能影响

## 测试规范
+ 单元测试类放于 `src/test/java`，与主包结构一致
+ 测试类以 `*Tests` 结尾，使用 JUnit5
+ 重要业务逻辑需有单元测试，建议 mock 外部依赖
+ 测试用例需覆盖正常/异常/边界场景

## 组件使用建议
+ **配置管理**：统一用 Spring `@Value` 或 `@ConfigurationProperties`，避免硬编码
+ **缓存**：优先用 Spring Cache/Ehcache
+ **HTTP 客户端**：推荐 Forest，设置超时/重试
+ **分页**：统一用 PageHelper/MyBatis-Plus 分页
+ **DTO/VO/PO**：严格分层，避免混用
+ **API 文档**：使用 OpenAPI 3 注解，统一 API 文档规范

## OpenAPI 3 注解使用规范
+ **Controller 类注解**：
  - 使用 `@Tag(name = "模块名称", description = "模块描述")` 替代 `@Api(tags = "模块名称")`
+ **接口方法注解**：
  - 使用 `@Operation(summary = "接口摘要", description = "接口详细描述")` 描述接口功能
  - 使用 `@Parameter(description = "参数描述", example = "参数示例")` 描述方法参数
+ **DTO/VO/PO 类注解**：
  - 使用 `@Schema(description = "类描述")` 替代 `@ApiModel("类描述")`
+ **字段注解**：
  - 使用 `@Schema(description = "字段描述", example = "字段示例")` 替代 `@ApiModelProperty("字段描述")`
+ **常用注解示例**：
  ```java
  // Controller 类
  @Tag(name = "用户管理", description = "用户相关接口")
  
  // 接口方法
  @Operation(summary = "创建用户", description = "创建新用户信息")
  @PostMapping("/create")
  public R<UserVO> createUser(@Parameter(description = "用户信息") @RequestBody UserDTO userDTO) {
      // 方法实现
  }
  
  // DTO/VO 类
  @Schema(description = "用户数据传输对象")
  public class UserDTO {
      @Schema(description = "用户ID", example = "123456")
      private String id;
      
      @Schema(description = "用户名", example = "张三")
      private String username;
  }
  ```

## Controller 层示例
```java
@Tag(name = "示例", description = "示例相关接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {
    
    private final ExampleService exampleService;

    @Operation(summary = "获取列表", description = "获取示例列表数据")
    @PostMapping("/list")
    public R<List<OrganizationVO>> list() {
        return R.ok(exampleService.selectAllOrg());
    }
}
```

## Service 层规范
+ **Service 分层结构**：
  - `Service` 接口：定义业务方法接口，位于 `service` 包
  - `impl` 实现类：继承 `BaseServiceImpl` 或 `BaseService`，实现具体业务逻辑，位于 `impl` 包
+ **依赖注入原则**：
  - 如果是操作当前的实现层，一般不需要注入其他 Service
  - 如果是操作多个实现层，需要新建 `BusinessService-BusinessServiceImpl` 来处理跨层业务逻辑
+ **Controller 注入**：Controller 注入 Service 接口而非实现类
+ **实现类命名**：实现类以 `Impl` 结尾，如 `ExampleServiceImpl`
+ **实现类注解**：必须包含 `@Slf4j`、`@Service`、`@RequiredArgsConstructor` 注解

```java
// 单层 Service 接口示例（操作当前实现层）
public interface UserService {
    List<UserVO> selectAllUsers();
}

// 单层 Service 实现类示例（无需注入其他 Service）
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    
    @Override
    public List<UserVO> selectAllUsers() {
        log.info("查询所有用户信息");
        // 直接操作当前层的数据
        return null;
    }
}

// 业务 Service 接口示例（操作多个实现层）
public interface FishpondBusinessService {
    UserOrderVO createUserWithOrder(UserOrderDTO userOrderDTO);
}

// 业务 Service 实现类示例（需要注入多个 Service）
@Slf4j
@Service
@RequiredArgsConstructor
public class FishpondBusinessServiceImpl implements FishpondBusinessService {
    
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    
    @Override
    @Transactional
    public UserOrderVO createUserWithOrder(UserOrderDTO userOrderDTO) {
        log.info("创建用户并生成订单");
        // 跨多个 Service 的业务逻辑
        // 1. 创建用户
        // 2. 生成订单
        // 3. 处理支付
        return null;
    }
}
```

## 业务服务层设计原则
+ **单层服务原则**：
  - 如果业务逻辑只涉及当前实现层的数据操作，直接在该层的 Service 中实现
  - 无需注入其他 Service，保持单一职责
+ **跨层业务原则**：
  - 如果业务逻辑涉及多个实现层的协作，需要创建专门的 `BusinessService`
  - 命名规范：`{业务前缀}BusinessService` 和 `{业务前缀}BusinessServiceImpl`
  - 业务前缀示例：`FishpondBusinessService`、`MallBusinessService`、`CommunityBusinessService` 等
+ **事务管理**：
  - 跨层业务操作必须使用 `@Transactional` 注解确保数据一致性
  - 在 BusinessService 中统一管理事务边界
+ **依赖注入**：
  - BusinessService 可以注入多个相关的 Service
  - 避免在单层 Service 中注入其他 Service（除非必要）
+ **设计示例**：
  ```java
  // 场景1：单层操作 - 直接在当前 Service 中实现
  @Service
  public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
      public List<UserVO> selectAllUsers() {
          // 只操作用户表，无需注入其他 Service
          return mapper.selectList(new QueryWrapper<>());
      }
  }
  
  // 场景2：跨层操作 - 创建 BusinessService
  @Service
  public class FishpondBusinessServiceImpl implements FishpondBusinessService {
      private final UserService userService;
      private final OrderService orderService;
      private final PaymentService paymentService;
      
      @Transactional
      public UserOrderVO createUserWithOrder(UserOrderDTO dto) {
          // 跨多个 Service 的复杂业务逻辑
          User user = userService.createUser(dto.getUserInfo());
          Order order = orderService.createOrder(dto.getOrderInfo());
          Payment payment = paymentService.processPayment(dto.getPaymentInfo());
          return new UserOrderVO(user, order, payment);
      }
  }
  ```

## Mapper 层示例
```java
@Mapper
public interface ExampleMapper extends BaseMapper<Example> {
    // ...自定义方法，SQL写在ExampleMapper.xml中
}
```

## DTO/VO 示例
```java
@Data
@Schema(description = "组织数据传输对象")
public class OrganizationDTO {
    @Schema(description = "ID", example = "123456")
    private String id;
    @Schema(description = "名称", example = "示例组织")
    private String name;
    @Schema(description = "类型", example = "企业")
    private String type;
}
```

## PO 示例
```java
@Data
@TableName(value ="t_yz_area_org_examine_manage")
@Schema(description = "组织实体对象")
public class Organization {
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "主键ID", example = "123456")
    private String id;
    @TableField("name")
    @Schema(description = "名称", example = "示例组织")
    private String name;
    @TableField("type")
    @Schema(description = "类型", example = "企业")
    private String type;
    @TableField("create_time")
    @Schema(description = "创建时间", example = "2024-01-01 12:00:00")
    private Date createTime;
    @TableField("update_time")
    @Schema(description = "更新时间", example = "2024-01-01 12:00:00")
    private Date updateTime;
}
```

## 其他建议
+ 代码注释规范，类/方法/参数/返回值需有注释
+ 重要变更需更新 `README.md` 或相关文档
+ 严格代码评审，确保规范落地

---

## 异步任务规范
+ **线程池配置**：统一在 `ThreadPoolConfig` 中配置
    ```java
    @Bean(name = "platform_org_biz_license")
    public ThreadPoolTaskExecutor platformOrgBizLicense() {
        return createThreadPoolTaskExecutor("platform_org_biz_license");
    }
    ```
+ **异步方法注解**：使用 `@Async` 指定线程池
    ```java
    @Async("platform_org_biz_license")
    public void platformOrgBizLicense() {
        // 异步业务逻辑
    }
    ```
+ **异步任务原则**：
    - 长时间运行的任务使用异步处理
    - 避免在异步方法中使用 `@Transactional`
    - 异步方法异常需要单独处理

## 定时任务规范
+ **任务类命名**：以 `Task` 结尾，如 `DangerScanForPlatformTask`
+ **定时任务注解**：使用 `@Scheduled` 配置执行时间
    ```java
    @Scheduled(cron = "0 0/5 * * * ?")
    public void orgBizLicense() {
        // 定时任务逻辑
    }
    ```
+ **任务开关控制**：使用 `@ConditionalOnProperty` 控制任务启用
    ```java
    @ConditionalOnProperty(value = "ruogu.task.enable", havingValue = "true")
    public class ExampleTask {
        // 任务实现
    }
    ```
+ **定时任务原则**：
    - 任务执行时间要合理，避免过于频繁
    - 任务异常需要记录日志，不影响其他任务
    - 长时间任务考虑使用异步处理

## 统一响应格式规范
+ **响应类**：统一使用 `R<T>` 包装响应数据
    ```java
    public class R<T> {
        private int code = -1;      // 响应码
        private String msg;         // 响应信息
        private T data;            // 响应数据
    }
    ```
+ **响应码枚举**：使用 `RCode` 定义标准响应码
    ```java
    public enum RCode {
        OK(0, "成功"),
        ERROR(1, "失败"),
        BAD_TOKEN(2, "登录已过期，请重新登录"),
        ILLEGALITY(3, "非法请求");
    }
    ```
+ **Controller 响应示例**：
    ```java
    @PostMapping("/list")
    public R<List<OrganizationVO>> list() {
        try {
            List<OrganizationVO> data = service.selectAllOrg();
            return R.ok(data);
        } catch (Exception e) {
            return R.error(RCode.ERROR, "查询失败");
        }
    }
    ```

## 基础服务类规范
+ **继承关系**：Service 实现类统一继承 `BaseServiceImpl` 或 `BaseService`
+ **必要注解**：必须包含 `@Slf4j`、`@Service`、`@RequiredArgsConstructor` 注解
    ```java
    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class ExampleServiceImpl extends BaseServiceImpl<ExampleMapper, Example> implements ExampleService {
        // 业务逻辑
    }
    ```
+ **基础方法**：使用 `BaseServiceImpl` 提供的通用 CRUD 方法
    - `add(T entity)` - 新增
    - `deleteById(Serializable id)` - 根据ID删除
    - `modifyById(T entity)` - 根据ID修改
    - `searchById(Serializable id)` - 根据ID查询
    - `searchPage(Pager page, Search<T> search)` - 分页查询

## 分页查询规范
+ **分页参数**：使用 `Pager` 类封装分页参数
+ **分页结果**：使用 `PageResult<T>` 封装分页结果
+ **查询构建**：使用 `Search<T>` 类构建查询条件
+ **分页查询示例**：
    ```java
    /**
     * 分页查询示例
     */
    public PageResult<YhpcInspectItemVO> searchPage(Pager pager, YhpcInspectItemSearchDTO searchDTO) {
        // 构建查询条件
        Search<YhpcInspectItem> search = new Search.create(YhpcInspectItem.class)
                .eq(YhpcInspectItem::getDeleted, DeletedConstants.FALSE)
                .eq(StringUtil.isNotEmpty(dto.getBusinessScopeCode()), 
                    YhpcInspectItem::getBusinessScopeCode, dto.getBusinessScopeCode())
                .like(StringUtil.isNotEmpty(dto.getContent()), 
                    YhpcInspectItem::getContent, dto.getContent())
                .orderByDesc(YhpcInspectItem::getCreateTime);
        // 执行分页查询
        return searchPage(pager, mapper -> mapper.selectList(search));
    }
    ```
+ **Search 类使用规范**：
    - 使用 `Search.create(EntityClass.class)` 创建查询对象
    - 使用 Lambda 表达式指定字段，如 `Entity::getFieldName`
    - 条件方法：
        - `eq(condition, column, value)` - 等值查询，支持条件判断
        - `like(condition, column, value)` - 模糊查询，支持条件判断
        - `in(condition, column, values)` - 包含查询
        - `between(condition, column, start, end)` - 范围查询
        - `orderByDesc(column)` - 降序排序
        - `orderByAsc(column)` - 升序排序
        - `limit(rows)` - 限制返回行数
    - 条件判断：使用 `StringUtil.isNotEmpty()` 等方法进行空值判断
+ **分页查询最佳实践**：
    - 查询条件构建方法命名为 `buildSearch`
    - 使用 Lambda 表达式避免字段名硬编码
    - 合理使用条件判断，避免无效查询条件
    - 统一添加软删除条件 `eq(Entity::getDeleted, DeletedConstants.FALSE)`
    - 查询结果按创建时间倒序排列

## 枚举使用规范
+ **枚举命名**：以 `Enum` 结尾，如 `DataTypeEnums`
+ **枚举结构**：包含 code 和 codeDescr 字段
    ```java
    public enum DataTypeEnums {
        PLATFORM("platform", "平台数据"),
        YZ_BASIC("yz_basic", "运政基础数据");
        
        public final String code;
        public final String codeDescr;
    }
    ```
+ **枚举使用**：在业务代码中优先使用枚举而非字符串常量

## 工具类使用规范
+ **日期处理**：统一使用 `DateUtil`（Hutool）
+ **字符串处理**：统一使用 `StringUtil`
+ **JSON 处理**：统一使用 `JsonUtil`
+ **集合操作**：使用 `CollectionUtil`（Hutool）
+ **避免重复造轮子**：优先使用成熟的工具类

## 配置管理规范
+ **配置文件**：按环境分离配置文件
    - `application-dev-local.yml` - 本地开发
    - `application-dev-dcc.yml` - 开发环境
    - `application-pro-1.yml` - 生产环境1
    - `application-pro-2.yml` - 生产环境2
+ **配置类**：使用 `@ConfigurationProperties` 绑定配置
    ```java
    @ConfigurationProperties(prefix = "ruogu")
    @Data
    public class YmlConfig {
        private boolean dev;
        private int snowflakeWorkerId;
    }
    ```

## 代码注释规范
+ **类注释**：包含作者、创建时间、功能描述
    ```java
    /**
     * @author zhucj
     * @since 2025/01/01
     * @description 登录控制器
     */
    ```
+ **方法注释**：包含参数说明、返回值说明、异常说明
    ```java
    /**
     * 用户登录
     * @param dto 登录参数
     * @return 登录结果
     * @throws Err 登录失败异常
     */
    ```
+ **重要业务逻辑**：添加行内注释说明业务规则

## 版本控制规范
+ **提交信息**：使用规范的提交信息格式
    - `feat: 新功能`
    - `fix: 修复bug`
    - `docs: 文档更新`
    - `style: 代码格式调整`
    - `refactor: 重构`
    - `test: 测试相关`
    - `chore: 构建过程或辅助工具的变动`
+ **分支管理**：
    - `main` - 主分支，用于生产环境
    - `develop` - 开发分支
    - `feature/*` - 功能分支
    - `hotfix/*` - 热修复分支

## 安全规范
+ **参数校验**：所有外部输入必须进行校验
+ **SQL 注入防护**：使用 MyBatis-Plus 的参数化查询
+ **敏感信息**：不在日志中输出敏感信息
+ **权限控制**：接口必须进行权限校验
+ **数据脱敏**：敏感数据在传输和存储时进行脱敏处理

## 性能监控规范
+ **关键指标监控**：
    - 接口响应时间
    - 数据库查询性能
    - 内存使用情况
    - 线程池使用情况
+ **日志监控**：关键业务操作记录详细日志
+ **异常监控**：异常信息及时上报和告警

---

如有疑问或特殊场景，请先沟通后再实现。

