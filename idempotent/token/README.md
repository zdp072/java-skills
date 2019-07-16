## 基于redis token实现
1. 用户进入提交页面前, 先生成一个token存入redis.
2. 当用户第一次点击提交时, 校验redis中是否存在.
3. 如果redis中存在则保存入库, 并将token从redis删除; 
4. 如果redis中不存在, 则表示为重复提交.

## 核心技术点
1. @ApiIdempotent注解+拦截器对请求进行拦截.
2. @ControllerAdvice全局异常处理.
