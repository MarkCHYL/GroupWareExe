＃GroupWareExe
组件化封装思想实战

##目前封装之后的组件：
* GlideManager组件
* ImageLoaderManager组件
* MarkLog组件
* CommonOkHttpClient组件

### CommonOkHttpClient组件

具体使用时，可以先写一个工具类统管项目网络请求代码：
```
 / **
     *根据参数发送所有post请求
     * /
    公共静态无效postRequest（String url，RequestParams params，
                                   DisposeDataListener侦听器，Class <？> clazz）{
        CommonOkHttpClient.sendRequest（CommonRequest.createPostRequest（url，params），
                new CommonJsonBack（new DisposeDataHandle（listener，clazz）））;
    }

    / **
     *主页数据
     * /
    公共静态无效requestRecommandData（DisposeDataListener监听器）{
        RequestCenter.postRequest（HttpConstants.HOME_RECOMMAND，null，监听器，BaseRecommandModel.class）;
    }
```
 然后在代码中具体调用时：
    ```
     RequestCenter.requestRecommandData（new DisposeDataListener（）{
            @Override
            公共无效onSuccess（Object responseObj）{
                MarkLog.d（TAG，“ onSuccess：” + responseObj.toString（））;
                mRecommandData =（BaseRecommandModel）responseObj;
                //更新UI
                showSuccessView（）;
            }

            @Override
            公共无效onFailure（Object reasonObj）{
                MarkLog.d（TAG，“ onFailure：” + reasonObj.toString（））;
            }
        }）;
    ```

### GlideManager组件
使用Glide-3.6.0.jar包
```
        //方式一
        GlideManager.showImageView（mContext，R.drawable.errorimg，
                “ https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4144473397,1642841325&fm=26&gp=0.jpg”，imgOne）；
        //方式二
        GlideManager.showImageViewToCircle（mContext，R.drawable.errorimg，
                “ https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2425727706,3350917988&fm=26&gp=0.jpg”，
                imgTwo）;

        //方式三
        GlideManager.showImageViewGone（mContext，
                “” https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWKhhy/it/u=4144473397,1642841325&fm=26&gp=0.jpg“，imgThree）；

        //设置高斯模糊
        GlideManager.showImageViewBlur（mContext，R.drawable.errorimg，
                “ llstest“ https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1462333184,2196733460&fm=26&gp=0.jpg”；
```

### MarkLog组件

组件初始化，其余的使用和正常的日志日志输出相同
```
 字符串logPath = Environment.getExternalStorageDirectory（）。getAbsolutePath（）+“ / groupWarelog”;
        MarkLog.getInstance（）//单例获取LogCook实例
                .setLogPath（logPath）//设置日志保存路径
                .setLogName（“ test.log”）//设置日志文件名
                .isOpen（true）//是否开启输出日志
                .isSave（true）//是否保存日志
                。初始化（）; //完成初始化崩溃监听
 ```
 
 ### ImageLoaderManager组件
 
 `ImageLoaderManager.getInstance（mContext）.displayImage（mViewHolder.mLogoView，value.getLogo（））;`