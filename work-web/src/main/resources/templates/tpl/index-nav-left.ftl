<div class="container m-container"  style="position: relative;height: 400px;padding-top: 0px;margin-top: 7px;">
<!------------------------------------轮播图------------------------------------>
<div id="lunbo">
    <ul id="one">
        <li><a href=""><img src="https://static.gongwk.com/images/20190426005.jpg" alt="加入会员" ></a></li>
        <li><a href=""><img src="https://static.gongwk.com/images/20190426006.jpg" alt="" ></a></li>
        <li><a href=""><img src="https://static.gongwk.com/images/20190426007.jpg" alt="" ></a></li>
    </ul>
    <ul id="two">
        <#list [1,2,3] as img>
            <li class="<#if img_index==0>on</#if>" >${img_index+1}</li>
        </#list>
    </ul>
    <!------------------------------------轮播图左右箭头------------------------>
    <div class="slider-page">
        <a href="javascript:void(0)" id="left"><</a>
        <a href="javascript:void(0)" id="right">></a>
    </div>
</div>
<!------------------------------------轮播图右侧一栏------------------------>
<div class="m">
    <div class="mt" style="padding-top: 5px;">
        <h3>天天快报</h3>
        <div class="extra"><a href="${appHost}/articles.jhtml">更多 ></a></div>
    </div>
    <#if articles??&&articles.content??>
        <div class="mc">
        <ul>
        <#list articles.content as article>
              <li><a href="${appHost}/article/${article.id}.jhtml" ><span>[${article.type}]</span>${article.shortTitle}</a></li>
        </#list>
        </ul>
        </div>
    </#if>
</div>
</div>