# 校园二手物品转让交流平台

## 简介

这是一个校园二手物品转让交流平台，旨在方便校内成员买卖二手物品并进行交流。

## 功能特性

- **管理人员功能**：
    - 管理人员身份识别登入。
    - 可以对用户的增删改查。
    - 可以对商品的增删改查。
    - 可以对帖子的删除查询功能。

- **用户功能**：
    - 提供用户登录注册的功能。
    - 提供交流论坛功能，用户可以在他人发布的帖子下进行回复和提问。
    - 发布个人的商品信息和帖子。
    - 浏览平台上其他用户的商品。
    - 浏览自己的主页。
    - 浏览自己发布过的帖子。
    - 浏览自己发布过的商品。

## 使用方法

1. **数据库创建语句**：

   ```sql
    CREATE SCHEMA todaysnewsthing;
    CREATE TABLE `admin` (`aid` int NOT NULL AUTO_INCREMENT,`aname` varchar(45) DEFAULT NULL,`apass` varchar(45) DEFAULT NULL,PRIMARY KEY (`aid`));
    INSERT INTO `admin` VALUES (1,'admin','123');
    CREATE TABLE `user` (`uid` int NOT NULL AUTO_INCREMENT,`uname` varchar(45) DEFAULT NULL,`upass` varchar(45) DEFAULT NULL,`utel` varchar(45) DEFAULT NULL,`uemail` varchar(45) DEFAULT NULL,PRIMARY KEY (`uid`));
    INSERT INTO `user` VALUES (1,'pyh233','123','18863040717','wwdzpyh@163.com'),(2,'lj','1234','1662718','rjau@123.com'),(3,'zs','879','1892013215','wjak@qq.com'),(4,'ww','123','17898766','wwapq@outlook.com'),(8,'zwj','123','666666','zwj@163.com'),(9,'xln','8765','12345678910','xln@qqmc.com'),(12,'ash','123','1283716371','13331231546@qq.com');
    CREATE TABLE `goods` (`gid` int NOT NULL AUTO_INCREMENT,`gname` varchar(100) DEFAULT NULL,`gprofile` varchar(300) DEFAULT NULL,`gprice` double DEFAULT NULL,`gimg` varchar(100) DEFAULT NULL,`uid` int DEFAULT NULL, PRIMARY KEY (`gid`), KEY `uid_idx` (`gimg`), KEY `uid_idx1` (`uid`), CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`));
    INSERT INTO `goods` VALUES (4,'拖鞋','非常好的拖鞋',100.5,'/img/OIP-C.jpg',1),(5,'凳子','破凳子亟待出售',10,'/img/R.jpg',2),(6,'水杯','崭新的水杯',109,'/img/OIP.jpg',1),(7,'平板','新买的平板',8999,'/img/Rpad.jpg',4),(9,'bunanananananan','大香蕉',1000,'/img/OIP-C.jpg',1),(11,'拖鞋ad','非常好的拖鞋',24,'/img/OIP-C.jpg',9);
    CREATE TABLE `post` (`pid` int NOT NULL AUTO_INCREMENT,`ptitle` varchar(50) DEFAULT NULL,`profile` varchar(100) DEFAULT NULL,`pcontent` varchar(400) DEFAULT NULL,`puid` int DEFAULT NULL, PRIMARY KEY (`pid`), KEY `puid_idx` (`puid`), CONSTRAINT `puid` FOREIGN KEY (`puid`) REFERENCES `user` (`uid`));
    INSERT INTO `post` VALUES (1,'重金求表','想买一块手表','这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长相当的长',1),
                            (2,'重金求鞋','我需要一双鞋来应对即将到来的体育测试！！！','这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长这是一段很长的内容很长很长',2),
                            (3,'买个台灯','想买台等','alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容',1),
                            (4,'买个台灯','想买台等','alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容',2),
                            (5,'买个台灯','想买台等','alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容',4),
                            (6,'重金求鞋','想买台等','alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容',3),
                            (8,'买和茶叶','我想买茶叶TTT','alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongPage这是很长的一段内容alongP很内容',9);
    CREATE TABLE `comments` (`cid` int NOT NULL AUTO_INCREMENT,`cmcontent` varchar(300) DEFAULT NULL,`cmuid` int DEFAULT NULL,`cmpid` int DEFAULT NULL, PRIMARY KEY (`cid`), KEY `uid_idx` (`cmuid`), KEY `cmpid_idx` (`cmpid`), CONSTRAINT `cmpid` FOREIGN KEY (`cmpid`) REFERENCES `post` (`pid`), CONSTRAINT `cmuid` FOREIGN KEY (`cmuid`) REFERENCES `user` (`uid`));
    INSERT INTO `comments` VALUES (1,'看起来不错哦！',3,4),(2,'可以可以',4,3),(3,'wohenxihuanou真不错',2,3);
   ```


2. **部署和运行**：

    - Clone 项目到本地。
    - 配置并启动数据库。
    - 配置项目的数据库连接信息。
    - 运行平台，确保服务器和数据库正常运行。

## 技术栈

### 前端

- **JavaScript**：用于实现客户端逻辑和交互。
- **Vue.js**：现代化的 JavaScript 框架，用于构建单页面应用（SPA）。
- **HTML**：网页的结构化标记语言。
- **ElementUI**：基于 Vue.js 的 UI 组件库，用于快速构建用户界面。
- **CSS**：用于样式设计和页面布局。

### 后端

- **Java**：主要编程语言。
- **Spring MVC**：Java 的 Web 框架，处理后端业务逻辑和请求。
- **Thymeleaf**：现代化的服务器端 Java 模板引擎，用于生成动态 HTML。
- **MySQL**：关系型数据库，存储和管理数据。

## 联系方式

如有任何问题或建议，请联系：wwdzpyh@163.com

# 附录 

## Java 文件解析



### 1. `CorsConfig.java`

- **作用**：`CorsConfig` 类是一个 Spring MVC 配置类，用于配置跨源资源共享（CORS）策略。 
        CORS 是一种浏览器安全机制，用于控制前端页面在浏览器中与不同源（域、协议、端口）的后端 API 进行通信的权限。
- **关键类**：
    - `CorsConfig`：实现了 WebMvcConfigurer 接口，覆盖了其中的 addCorsMappings 方法，用于配置跨域请求的允许规则。

### 2. `WebConfig.java`

- **作用**：`WebConfig` 类是一个 Spring MVC 配置类，用于配置 Web 应用程序的拦截器（Interceptor）。
      拦截器在请求被处理之前或之后执行预处理和后处理任务，例如身份验证、日志记录等。
- **关键类**：
    - `WebConfig`：实现了 WebMvcConfigurer 接口，通过重写 `addInterceptors `方法来配置拦截器。

### 3. `AdminController.java`

- **作用**：`AdminController` 类是一个控制器类，处理与管理员相关的 HTTP 请求，包括管理员登录和管理界面的入口。
- **关键类**：
    - `AdminController`：控制器类，负责处理前端发送的管理员登录和管理操作请求。
- **功能说明**：
    - `adminEnter`: 处理后台入口请求，返回管理员登录页面。
      ```java
      @GetMapping({"/Admin/adminLogin.html","/Admin/adminLogin"})
      public String adminEnter(){
          return "/Admin/adminLogin";
      }
      ```
    - `getAdmin`: 处理管理员登录请求，验证管理员身份，将登录信息存储在会话中，并根据验证结果重定向到相应页面。
      ```java
      @PostMapping("adminlogin")
      public String getAdmin(Admin admin, Model model, HttpSession session){
          try {
              admin = adminDao.showByAname(admin);
              if(admin != null){
                  session.setAttribute("adminAccessID", admin);
                  return "redirect:/Admin/adminMain.html";
              }
          } catch (MyBatisSystemException e){
              e.printStackTrace();
          } catch (Exception e){
              e.printStackTrace();
          }
          model.addAttribute("msg", "登录失败！");
          return "/Admin/adminLogin";
      }
      ```

### 4. `GoodsController.java`

- **作用**：`GoodsController` 类是一个控制器类，处理与商品管理相关的 HTTP 请求，包括展示商品列表、添加商品、删除商品和修改商品信息。
- **关键类**：
    - `GoodsController`：控制器类，负责处理前端发送的商品管理操作请求。
- **功能说明**：
    - `showAllGoods`: 处理展示所有商品的请求，支持分页功能。
      ```java
      @GetMapping("/Admin/goodManagement")
      public String showAllGoods(Integer page, Model model){
          if(page == null || page == 0){
              page = 1;
          }
          PageHelper.startPage(page, 2);
          PageInfo pi = new PageInfo(goodsDao.show());
          model.addAttribute("pageInfo", pi);
          return "/Admin/goodManagement";
      }
      ```
    - `toAddGoodPage`: 重定向到添加商品页面。
      ```java
      @GetMapping("/Admin/Goods/toAddGoodPage")
      public String toAddGoodPage(){
          return "/Admin/Goods/addGoodsPage";
      }
      ```
    - `addGoods`: 处理添加商品的请求，将商品信息和图片存储到服务器和数据库中。
      ```java
      @PostMapping("/Admin/Goods/addGoods")
      public String addGoods(GoodsHelper goodsHelper,
                             @RequestParam(value = "file", required = true) MultipartFile multipartFile, HttpSession session){
          String path = session.getServletContext().getRealPath("img");
          File file = new File(path, multipartFile.getOriginalFilename());
          if(!file.exists()){
              file.mkdirs();
          }
          try{
              multipartFile.transferTo(file);
          } catch (IOException e){
              throw new RuntimeException(e);
          }
          goodsHelper.setGimg("/img/" + multipartFile.getOriginalFilename());
          GoodsTransfer goodsTransfer = new GoodsTransfer(goodsDao.selectUserByUname(goodsHelper.getUname()));
          Goods goods = goodsTransfer.transferToGoods(goodsHelper);
          try{
              goodsDao.add(goods);
              int finalPage = goodsDao.count() / 2 + goodsDao.count() % 2;
              return "redirect:/Admin/goodManagement?page=" + finalPage;
          } catch (Exception e){
              e.printStackTrace();
          }
          session.setAttribute("addGoodsFailure", "Sorry! Add Goods message failure!");
          return "redirect:/Admin/goodManagement";
      }
      ```
    - `cancel`: 处理取消添加或修改商品操作的请求，重定向到商品管理页面。
      ```java
      @GetMapping({"/Admin/Goods/addCancel", "/Admin/Goods/setCancel"})
      public String cancel(){
          return "redirect:/Admin/goodManagement";
      }
      ```
    - `deleteGoods`: 处理删除商品的请求。
      ```java
      @GetMapping("/Admin/Goods/deleteGoods")
      public String deleteGoods(int gid){
          goodsDao.delete(gid);
          return "redirect:/Admin/goodManagement";
      }
      ```
    - `setGoods`: 处理修改商品信息的请求，将商品信息传递给修改页面。
      ```java
      @GetMapping("/Admin/Goods/setGoods")
      public String setGoods(int gid, Model model, HttpSession session){
          Goods modifyGood = goodsDao.selectByGid(gid);
          modifyGood.setGid(gid);
          model.addAttribute("modifyGood", modifyGood);
          session.setAttribute("modifyGood", modifyGood);
          return "/Admin/Goods/modifyGoodsPage";
      }
      ```
    - `modify`: 处理修改商品信息的请求，更新商品信息并保存修改后的图片（如果有）。
      ```java
      @PostMapping("/Admin/Goods/modify")
      public String modify(HttpServletRequest request, HttpSession session,
                           @RequestParam(value = "file", required = false) MultipartFile multipartFile){
          Goods modifyGood = (Goods) session.getAttribute("modifyGood");
          modifyGood.setGname(request.getParameter("gname"));
          modifyGood.setGprofile(request.getParameter("gprofile"));
          modifyGood.setGprice(Double.parseDouble(request.getParameter("gprice")));
          if(!multipartFile.getOriginalFilename().equals("")){
              String path = session.getServletContext().getRealPath("img");
              File file = new File(path, multipartFile.getOriginalFilename());
              if(!file.exists()){
                  file.mkdirs();
              }
              try{
                  multipartFile.transferTo(file);
              } catch (IOException e){
                  throw new RuntimeException(e);
              }
              modifyGood.setGimg("/img/" + multipartFile.getOriginalFilename());
          }
          goodsDao.set(modifyGood);
          return "redirect:/Admin/goodManagement";
      }
      ```


### 5. `PostController.java`

- **作用**：`PostController` 类是一个控制器类，处理与帖子管理相关的 HTTP 请求，包括展示帖子列表和删除帖子。
- **关键类**：
    - `PostController`：控制器类，负责处理前端发送的帖子管理操作请求。
- **功能说明**：
    - `showAllPosts`: 处理展示所有帖子的请求，支持分页功能。
      ```java
      @GetMapping("/Admin/postManagement")
      public String showAllPosts(Integer page, Model model) {
          if (page == null || page == 0) {
              page = 1;
          }
          PageHelper.startPage(page, 6);
          PageInfo pi = new PageInfo(postDao.showAllPosts());
          model.addAttribute("pageInfo", pi);
          return "/Admin/postManagement";
      }
      ```
    - `removePost`: 处理删除帖子的请求。
      ```java
      @GetMapping("/Admin/Post/deletePost")
      public String removePost(int pid){
          postDao.delete(pid);
          return "redirect:/Admin/postManagement";
      }
      ```


### 6. `UserController.java`

- **作用**：`UserController` 类是一个控制器类，处理与用户管理相关的 HTTP 请求，包括展示用户列表、添加用户、删除用户和修改用户信息。
- **关键类**：
    - `UserController`：控制器类，负责处理前端发送的用户管理操作请求。
- **功能说明**：
    - `showAllUser`: 处理展示所有用户的请求，支持分页功能。
      ```java
      @GetMapping("/Admin/userManagement")
      public String showAllUser(Integer page, Model model){
          if(page==null||page==0){
              page=1;
          }
          PageHelper.startPage(page,2);
          PageInfo pi = new PageInfo(userDao.show());
          model.addAttribute("pageInfo",pi);
          return "/Admin/userManagement";
      }
      ```
    - `toAddUserPage`: 处理跳转到添加用户页面的请求。
      ```java
      @GetMapping("/Admin/toAddUserPage")
      public String toAddUserPage(){
          return "/Admin/addUserPage";
      }
      ```
    - `addOneUser`: 处理添加用户的请求。
      ```java
      @PostMapping("/Admin/addOneUser")
      public String addOneUser(User user){
          userDao.add(user);
          return "redirect:/Admin/userManagement";
      }
      ```
    - `deleteOneUser`: 处理删除用户的请求。
      ```java
      @GetMapping("/Admin/deleteOneUser")
      public String deleteOneUser(int uid){
          int result = userDao.remove(uid);
          return "redirect:/Admin/userManagement";
      }
      ```
    - `setOneUser`: 处理跳转到修改用户页面的请求。
      ```java
      @GetMapping("/Admin/setOneUser")
      public String setOneUser(int uid, Model model, HttpSession session){
          model.addAttribute("modifyUser",userDao.selectByUID(uid));
          session.setAttribute("modifyUserUID",uid);
          return "/Admin/modifyPage";
      }
      ```
    - `modifyUser`: 处理修改用户信息的请求。
      ```java
      @PostMapping("/Admin/modifyUser")
      public String modifyUser(User user, HttpSession session){
          int modifyUserUID = Integer.parseInt(session.getAttribute("modifyUserUID").toString());
          user.setUid(modifyUserUID);
          userDao.set(user);
          return "redirect:/Admin/userManagement";
      }
      ```
    - `cancel`: 处理取消修改或取消添加用户的请求。
      ```java
      @GetMapping({"/Admin/modifyCancel","/Admin/addCancel"})
      public String cancel(){
          return "redirect:/Admin/userManagement";
      }
      ```


### 7. `UserUsageController.java`

- **作用**：`UserUsageController` 类是一个控制器类，处理与用户操作相关的 HTTP 请求，包括用户注册、登录、浏览商品和帖子、发帖、发布商品、修改和删除自己的帖子和商品、以及留言等功能。
- **关键类**：
    - `UserUsageController`：控制器类，负责处理前端发送的用户操作请求。
- **功能说明**：
    - `registerCheck`: 检查用户名是否已存在。
      ```java
      @PostMapping("registerCheck")
      public @ResponseBody int registerCheck(String uname){
          return userUseDao.registerCheck(uname);
      }
      ```
    - `userRegister`: 处理用户注册请求。
      ```java
      @PostMapping("userRegister")
      public @ResponseBody int userRegister(@RequestBody User user){
          return userUseDao.add(user);
      }
      ```
    - `userLoginIn`: 处理用户登录请求，返回用户信息或登录失败。
      ```java
      @PostMapping("userLoginIn")
      public @ResponseBody User userLoginIn(@RequestBody User user){
          return userUseDao.userLoginSelect(user);
      }
      ```
    - `userBrowserPosts`: 用户浏览所有帖子，支持分页功能。
      ```java
      @GetMapping("userBrowserPosts")
      public @ResponseBody PageInfo userBrowserPosts(int page){
          PageHelper.startPage(page, 2);
          PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserPosts());
          return pageInfo;
      }
      ```
    - `userBrowserGoods`: 用户浏览所有商品，支持分页功能。
      ```java
      @GetMapping("userBrowserGoods")
      public @ResponseBody PageInfo userBrowserGoods(int page){
          PageHelper.startPage(page, 2);
          PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserGoods());
          return pageInfo;
      }
      ```
    - `browserMySelf`: 用户浏览自己的信息。
      ```java
      @PostMapping("BrowserSelf")
      public @ResponseBody User browserMySelf(int uid){
          return userUseDao.selectSelf(uid);
      }
      ```
    - `browserSelfsPosts`: 用户浏览自己发布的所有帖子。
      ```java
      @PostMapping("browserSelfsPosts")
      public @ResponseBody List<Post> browserSelfsPosts(int uid){
          return userUseDao.selectSelfPosts(uid);
      }
      ```
    - `browserSelfsGoods`: 用户浏览自己发布的所有商品。
      ```java
      @PostMapping("browserSelfsGoods")
      public @ResponseBody List<Goods> browserSelfsGoods(int uid){
          return userUseDao.selectSelfGoods(uid);
      }
      ```
    - `postPost`: 用户发布帖子。
      ```java
      @PostMapping("postPost")
      public @ResponseBody int postPost(@RequestBody Post post){
          return userUseDao.postPost(post);
      }
      ```
    - `postGood`: 用户发布商品。
      ```java
      @PostMapping("postGood")
      public @ResponseBody int postGood(@RequestBody Goods goods){
          return userUseDao.postGood(goods);
      }
      ```
    - `setMyPost`: 用户修改自己发布的帖子。
      ```java
      @PostMapping("setMyPost")
      public @ResponseBody int setMyPost(@RequestBody Post post){
          return userUseDao.setMyPost(post);
      }
      ```
    - `setMyGood`: 用户修改自己发布的商品信息。
      ```java
      @PostMapping("setMyGood")
      public @ResponseBody int setMyGood(@RequestBody Goods goods){
          return userUseDao.setMyGood(goods);
      }
      ```
    - `deleteMyPost`: 用户删除自己的帖子。
      ```java
      @PostMapping("deleteMyPost")
      public @ResponseBody int deleteMyPost(int pid){
          return userUseDao.deleteMyPost(pid);
      }
      ```
    - `deleteMyGood`: 用户删除自己的商品。
      ```java
      @PostMapping("deleteMyGood")
      public @ResponseBody int deleteMyGood(int gid){
          return userUseDao.deleteMyGood(gid);
      }
      ```
    - `postComment`: 用户在帖子下留言。
      ```java
      @PostMapping("postComment")
      public @ResponseBody int postComment(@RequestBody CommentHelper commentHelper){
          return userUseDao.giveCommemts(commentHelper);
      }
      ```
    - `commentsShowOnPost`: 用户浏览他人帖子下的留言。
      ```java
      @PostMapping("commentsShowOnPost")
      public @ResponseBody List<Comment> commentsShowOnPost(int pid){
          return userUseDao.getCommentsByPid(pid);
      }
      ```


### 8. `AdminDao.java`

- **作用**：提供管理员相关的数据访问操作。
- **关键类**：
    - `AdminDao`：数据访问接口，定义了与管理员表相关的数据库操作方法。
- **功能说明**：
    - `showByAname(Admin admin)`：通过用户名和密码查询管理员实现管理员登陆。
    ```java
    @Select("select * from admin where aname=#{aname} and apass=#{apass}")
    public Admin showByAname(Admin admin);
    ```


### 9. `GoodsDao.java`

- **作用**：提供商品相关的数据访问操作。
- **关键类**：
    - `GoodsDao`：数据访问接口，定义了与商品表（goods）相关的数据库操作方法。
- **功能说明**：
    - `show()`：查询所有商品信息及其对应的用户信息。
    ```java
    @Select("SELECT g.gid, g.gname, g.gprofile, g.gprice, g.gimg, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM goods g " +
            "JOIN User u ON g.uid = u.uid")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "gprofile", column = "gprofile"),
            @Result(property = "gprice", column = "gprice"),
            @Result(property = "gimg", column = "gimg"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public List<Goods> show();
    ```

    - `selectByGid(int gid)`：根据商品ID查询特定商品信息及其对应的用户信息。
    ```java
    @Select("SELECT g.gid, g.gname, g.gprofile, g.gprice, g.gimg, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM goods g " +
            "JOIN User u ON g.uid = u.uid "+
            "WHERE g.gid=#{gid}")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "gprofile", column = "gprofile"),
            @Result(property = "gprice", column = "gprice"),
            @Result(property = "gimg", column = "gimg"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public Goods selectByGid(int gid);
    ```

    - `add(Goods goods)`：添加商品信息到数据库。
    ```java
    @Insert("insert into goods values(0,#{gname},#{gprofile},#{gprice},#{gimg},#{user.uid})")
    public int add(Goods goods);
    ```

    - `delete(int gid)`：根据商品ID删除商品信息。
    ```java
    @Delete("delete from goods where gid=#{gid}")
    public int delete(int gid);
    ```

    - `count()`：统计商品总数。
    ```java
    @Select("select count(*) from goods")
    public int count();
    ```

    - `set(Goods goods)`：更新商品信息。
    ```java
    @Update("update goods set gname=#{gname}, gprofile=#{gprofile}, gprice=#{gprice}, gimg=#{gimg} where gid=#{gid}")
    public int set(Goods goods);
    ```

    - 其他表相关方法(未扩展，未完善)：
        - `selectUserByUname(String uname)`：根据用户名查询用户信息。
        ```java
        @Select("select * from user where uname=#{uname}")
        public User selectUserByUname(String uname);
        ```


### 10. `PostDao.java`
- **作用**：提供帖子相关的数据访问操作。
- **关键类**：
    - `PostDao`：数据访问接口，定义了与帖子表（post）相关的数据库操作方法。
- **功能说明**：

    - `showAllPosts()`：查询所有帖子信息及其对应的用户信息。
    ```java
    @Select("SELECT p.pid, p.ptitle, p.profile, p.pcontent, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM post p " +
            "JOIN User u ON p.puid = u.uid")
    @Results({
            @Result(property = "pid", column = "pid"),
            @Result(property = "ptitle", column = "ptitle"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "pcontent", column = "pcontent"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public List<Post> showAllPosts();
    ```

    - `delete(int pid)`：根据帖子ID删除帖子信息。
    ```java
    @Delete("delete from post where pid=#{pid}")
    public int delete(int pid);
    ```


### 11. `UserDao.java`
- **作用**：提供用户相关的数据访问操作。
- **关键类**：
    - `UserDao`：数据访问接口，定义了与用户表（user）相关的数据库操作方法。
- **功能说明**：

    - `show()`：查询所有用户信息。
    ```java
    @Select("select * from user")
    public List<User> show();
    ```

    - `selectByUID(int uid)`：根据用户ID查询用户信息。
    ```java
    @Select("select * from user where uid=#{uid}")
    public User selectByUID(int uid);
    ```

    - `selectByUname(String uname)`：根据用户名查询用户信息。
    ```java
    @Select("select * from user where uname=#{uname}")
    public User selectByUname(String uname);
    ```

    - `add(User user)`：添加用户信息。
    ```java
    @Insert("insert into user values (0,#{uname},#{upass},#{utel},#{uemail})")
    public int add(User user);
    ```

    - `set(User user)`：更新用户信息。
    ```java
    @Update("update user set uname=#{uname},upass=#{upass},utel=#{utel},uemail=#{uemail} where uid=#{uid}")
    public int set(User user);
    ```

    - `remove(int uid)`：根据用户ID删除用户信息。
    ```java
    @Delete("delete from user where uid=#{uid}")
    public int remove(int uid);
    ```

    - `count()`：统计用户总数。
    ```java
    @Select("select count(*) from user")
    public int count();
    ```


### 12. `UserUseDao.java`
- **作用**：提供用户操作相关的数据访问操作。
- **关键类**：
    - `UserUseDao`：数据访问接口，定义了与用户操作相关的数据库操作方法。
- **功能说明**：

    - `userLoginSelect(User user)`：用户登录验证，根据用户名和密码查询用户信息。
    ```java
    @Select("select * from user where uname=#{uname} and upass=#{upass}")
    public User userLoginSelect(User user);
    ```

    - `registerCheck(String uname)`：注册时检查用户名是否已被使用。
    ```java
    @Select("select count(*) from user where uname=#{uname}")
    public int registerCheck(String uname);
    ```

    - `add(User user)`：注册成功后添加用户信息。
    ```java
    @Insert("insert into user values (0,#{uname},#{upass},#{utel},#{uemail})")
    public int add(User user);
    ```

    - `UserBrowserPosts()`：查询所有帖子信息。
    ```java
    @Select("select * from post")
    public List<Post> UserBrowserPosts();
    ```

    - `UserBrowserGoods()`：查询所有商品信息。
    ```java
    @Select("select * from Goods")
    public List<Goods> UserBrowserGoods();
    ```

    - `selectSelf(int uid)`：根据用户ID查询个人主页信息。
    ```java
    @Select("select * from user where uid=#{uid}")
    public User selectSelf(int uid);
    ```

    - `selectSelfPosts(int uid)`：根据用户ID查询用户发布的所有帖子。
    ```java
    @Select("select * from post where puid=#{uid}")
    public List<Post> selectSelfPosts(int uid);
    ```

    - `selectSelfGoods(int uid)`：根据用户ID查询用户发布的所有商品。
    ```java
    @Select("select * from goods where uid=#{uid}")
    public List<Goods> selectSelfGoods(int uid);
    ```

    - `postPost(Post post)`：用户发布帖子。
    ```java
    @Insert("insert into post values (0,#{ptitle},#{profile},#{pcontent},#{user.uid})")
    public int postPost(Post post);
    ```

    - `postGood(Goods goods)`：用户发布商品。
    ```java
    @Insert("insert into goods values(0,#{gname},#{gprofile},#{gprice},#{gimg},#{user.uid})")
    public int postGood(Goods goods);
    ```

    - `giveCommemts(CommentHelper commentHelper)`：用户发表评论。
    ```java
    @Insert("insert into comments values(0,#{cmcontent},#{uid},#{pid})")
    public int giveCommemts(CommentHelper commentHelper);
    ```

    - `getCommentsByPid(int pid)`：根据帖子ID查询帖子的所有评论。
    ```java
    @Select("SELECT c.cmcontent, " +
            "u.uid AS uid, u.uname AS uname,  u.utel AS utel, u.uemail AS uemail, " +
            "p.pid AS pid, p.ptitle AS ptitle, p.profile AS profile, p.pcontent AS pcontent "+
            "FROM comments c " +
            "JOIN user u ON c.cmuid = u.uid "+
            "JOIN post p ON c.cmpid = p.pid "+
            "WHERE pid=#{pid}")
    @Results({
            @Result(property = "cmcontent", column = "cmcontent"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail"),
            @Result(property = "post.pid", column = "pid"),
            @Result(property = "post.ptitle", column = "ptitle"),
            @Result(property = "post.profile", column = "profile"),
            @Result(property = "post.pcontent", column = "pcontent")
    })
    public List<Comment> getCommentsByPid(int pid);
    ```

    - `setMyPost(Post post)`：用户修改自己的帖子信息。
    ```java
    @Update("update post set ptitle=#{ptitle},profile=#{profile},pcontent=#{pcontent} where pid=#{pid} and uid=#{user.uid}")
    public int setMyPost(Post post);
    ```

    - `setMyGood(Goods goods)`：用户修改自己的商品信息。
    ```java
    @Update("update goods set gname=#{gname},gprofile=#{gprofile},gprice=#{gprice} where gimg=#{gimg} and uid=#{user.uid}")
    public int setMyGood(Goods goods);
    ```

    - `deleteMyPost(int pid)`：用户删除自己的帖子。
    ```java
    @Delete("delete from post where pid=#{pid}")
    public int deleteMyPost(int pid);
    ```

    - `deleteMyGood(int gid)`：用户删除自己的商品。
    ```java
    @Delete("delete from post where gid=#{gid}")
    public int deleteMyGood(int gid);
    ```


### 13. `CommentHelper.java`

- **作用**：用户评论数据传输类。
- **关键类**：
    - `CommentHelper`：cid，cmcomment，uid，pid四个属性。

### 14. `GoodsHelper.java`

- **作用**：商品信息数据传输类。
- **关键类**：
    - `GoodsHelper`：gname，gprofile，gprice，gim，guname五个属性。

### 15. `GoodsTransfer.java`

- **作用**：商品信息数据转化类。
- **关键类**：
    - `GoodsTransfer`：将GoodsHelper转化为Goods类。
  - **功能说明**：
      -`public Goods transferToGoods(GoodsHelper goodsHelper)`:转化方法，可以将`GoodsHelper`转化为`Goods`
      ```java
      public Goods transferToGoods(GoodsHelper goodsHelper){
          this.goods.setGname(goodsHelper.getGname());
          this.goods.setGprofile(goodsHelper.getGprofile());
          this.goods.setGprice(goodsHelper.getGprice());
          this.goods.setGimg(goodsHelper.getGimg());
          this.goods.setUser(this.user);
          return this.goods;
      }
     ```

### 16. `AdminInterceptor.java`

- **作用**：应用程序的入口点，启动 Spring Boot 应用。
- **关键类**：
    - `AdminInterceptor`：实现了 `HandlerInterceptor` 接口，用于在处理请求之前拦截并验证管理员的访问权限。
- **功能说明**：
  `preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)`：在处理请求之前执行的方法，用于验证管理员访问权限。
    ```java
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object adminAccessID = session.getAttribute("adminAccessID");
    
        if (adminAccessID == null) {
            // 如果没有管理员信息，重定向到管理员登录页面
            response.sendRedirect(request.getContextPath() + "/Admin/adminLogin.html");
            return false;
        }
        return true;
    }
    ```
### 17. `Admin.java`

- **作用**：定义管理员实体类，用于表示管理员的基本信息。
- **关键类**：
    - `Admin`：包含管理员的编号(`aid`)、用户名(`aname`)和密码(`apass`)属性。


### 18. `Comment.java`

- **作用**：定义评论实体类，用于表示帖子的评论信息。
- **关键类**：
    - `Comment`：包含评论的编号(`cid`)、评论内容(`cmcontent`)，以及关联的用户(`user`)和帖子(`post`)属性。


### 19. `Goods.java`

- **作用**：定义商品实体类，用于表示系统中的商品信息。
- **关键类**：
    - `Goods`：包含商品的编号(`gid`)、名称(`gname`)、简介(`gprofile`)、价格(`gprice`)、图片(`gimg`)，以及关联的用户(`user`)属性。


### 20. `Post.java`

- **作用**：定义帖子实体类，用于表示系统中的帖子信息。
- **关键类**：
    - `Post`：包含帖子的编号(`pid`)、标题(`ptitle`)、简介(`profile`)、内容(`pcontent`)，以及关联的用户(`user`)属性。


### 21. `User.java`

- **作用**：定义用户实体类，表示系统中的用户信息。
- **关键类**：
    - `User`：包含用户的编号(`uid`)、用户名(`uname`)、密码(`upass`)、电话(`utel`)、邮箱(`uemail`)属性。


### 22. `YeldPracticeApplication.java`

- **作用**：应用程序的入口点，启动 Spring Boot 应用。
- **关键类**：
    - `YeldPracticeApplication`：包含 `main` 方法，启动 Spring Boot 应用程序。


