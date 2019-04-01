<div class="container m-container"  style="position: relative;height: 460px;padding-top: 0px;margin-top: 7px;">
<!------------------------------------轮播图------------------------------------>
<div id="lunbo">
    <ul id="one">
        <li><a href=""><img src="https://bootcdn.xuexi.cn/dyxx_5a1d70322da1335610ea6a87/1552620657482/8cb6f33f8d76e643bde4b678f0f7855e.jpg?x-oss-process=image/resize,w_590,h_470"></a></li>
        <li><a href=""><img src="http://media.people.com.cn/NMediaFile/2019/0304/MAIN201903040810553940275526231.jpg" alt="一带一路”建设的成就与挑战"></a></li>
    </ul>
    <ul id="two">
        <#list [1,2] as img>
            <li class="<#if img_index==0>on</#if>" >${img_index+1}</li>
        </#list>
    </ul>
    <!------------------------------------轮播图左右箭头------------------------>
    <div class="slider-page">
        <a href="javascript:void(0)" id="left"><</a>
        <a href="javascript:void(0)" id="right">></a>
    </div>
</div>
<div class="m1" style="">
    <div id="J_rec" class="J_rec rec">
        <div class="rec_inner" style="padding-left: 25%;">
            <div class="rec_item">
                <a class="rec_lk mod_loading" href="javascript:void(0)" target="_blank" >
                    <div class="counter  red">
                        <div class="counter-content" title="收录文章数量">
                            <i class="fa fa-archive"></i>
                            <span class="counter-value">
                                <#if articles??> ${articles.totalElements!'0'}</#if>
                            </span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="rec_item">
                <a class="rec_lk mod_loading" href="javascript:void(0)" target="_blank" >
                    <div class="counter blue">
                        <div class="counter-content" title="文档数量">
                            <i class="fa fa-book"></i>
                            <span class="counter-value">
                                <#if docs??> ${docs.totalElements!'0'}</#if>
                            </span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="rec_item">
                <a class="rec_lk mod_loading" href="javascript:void(0)" target="_blank" >
                    <div class="counter">
                        <div class="counter-content" title="用户数量">
                            <i class="fa fa-group"></i>
                            <span class="counter-value">18163</span>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<!------------------------------------轮播图右侧一栏------------------------>
<div class="m">
    <div class="mt" style="padding-top: 5px;">
        <h3>天天快报</h3>
        <div class="extra"><a href="${appHost}/articles.jhtml">更多 ></a></div>
    </div>
    <#if articles??&&articles.content??>
        <#list articles.content as article>
            <div class="mc">
                <ul>
                    <li><a href="${appHost}/article/${article.id}.jhtml" ><span>[${article.type}]</span>${article.shortTitle}</a></li>
                </ul>
            </div>
        </#list>
    </#if>
</div>
</div>