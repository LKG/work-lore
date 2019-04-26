<div class="box-cate">
    <div class="line-cate clearfix">
        <div class="key-cate"><span class="text"><i class="fa fa-tags"></i>分类</span></div>
        <div class="value-cate">
            <div class="list-cate listTm-cate">
                <div class="clearfix">
                    <a href="javascript:void(0);"><span class="num">01</span>案件审理</a>
                    <a href="javascript:void(0);"><span class="num">02</span>案管信访</a>
                    <a href="javascript:void(0);"><span class="num">03</span>党纪法规</a>
                    <a href="javascript:void(0);"><span class="num">04</span>党风政风</a>
                    <a href="javascript:void(0);"><span class="num">05</span>领导讲话</a>
                    <a href="javascript:void(0);"><span class="num">06</span>巡视巡察</a>
                    <a href="javascript:void(0);"><span class="num">07</span>审查调查</a>
                    <a href="javascript:void(0);"><span class="num">08</span>材料荟萃</a>
                    <a href="javascript:void(0);"><span class="num">09</span>廉政教育</a>
                    <a href="javascript:void(0);"><span class="num">10</span>以案说纪</a>
                </div>
                <div class="list-cate">
                    <div class="clearfix">
                        <span class="listLabel-cate">二级分类<!-- -->:</span>
                        <div class="listCnt-cate">
                            <a href="/010001.html">010001<!-- -->民政工作</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="line-cate clearfix">
        <div class="key-cate"><span class="text"><i class="fa fa-group"></i>资源格式<!-- -->:</span></div>
        <div class="value-cate">
            <div class="list-cate">
                <div class="clearfix">
                           <#list ["doc","ppt","pdf","图片"] as model>
                               <a href="#">${model!''}</a>
                           </#list>
                </div>
            </div>
        </div>
    </div>
    <div class="line-cate clearfix">
        <div class="key-cate"><span class="text"><i class="fa fa-briefcase"></i>上架时间<!-- -->:</span></div>
        <div class="value-cate">
            <div class="list-cate">
                <div class="clearfix">
                            <#list ["三天内","一周内","一个月内","最近半年","最近一年","最近三年"] as model>
                                <a href="#">${model!''}</a>
                            </#list>
                </div>
            </div>
        </div>
    </div>
</div>