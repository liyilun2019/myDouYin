# 迷你抖音APP

### TODO:

​	作业要求pdf在根目录下

​	这个APP目前还没有被关联起来，各个包基本都是独立的，也是从以网代码拷贝而来的模板。

​	视图方面，应当实现 ```message.MessageFragment``` 和 ```showVideo.VideoPlayFragment```，在```MainActivity```中通过下方按钮替换这些Fragment。

​	然后```database```方面还没有建好，主要就是从网上拿下数据然后建成数据库，遍历数据库得到所有的```Message```(暂且这么称呼)，这个得到的```List```也为```VideoPlay```服务。

​	网络方面，基本和之前的网络读取差不多，```beans```和```network```拿现成的，改动预计不大，主要是应用。 

在beans或者其他地方，应该把网络的```Feed```封装成一个元素类```???```，然后给交给数据库管理，从数据库中请求数据也应该照这个封装好的元素类进行填充。

​	我想的基本上就是这样，有啥不可能实现的，或者有啥方便加上的创新点，到时候灵活修改就是了。





​	现在可以放视频了，但是有时有bug



​	增加了展示板的fragment，下方导航键切换