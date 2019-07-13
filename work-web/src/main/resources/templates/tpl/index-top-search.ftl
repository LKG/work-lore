<div class="w" style="height: 77px;">
    <!-- -->
    <div id="logo" class="logo">
        <h1 class="logo_tit">
            <a href="${appHost}/" class="logo_tit_lk" ><@spring.message  code="label.system.name" /></a>
        </h1>
        <h2 class="logo_subtit"><@spring.message  code="label.system.name" />,为您提供愉悦的资料分享体验!</h2>
        <div class="logo_extend" ></div>
        <div class="tip">
            <p>拖动LOGO到书签栏，立即收藏</p>
        </div>
        <a href="${appHost}/" class="logo_scene logo_scene_hide" target="_blank" >
            <span class="logo_scene_text" style="color: rgb(142, 81, 63);">公文库-好资料不容错过</span>
         </a>
    </div>
    <!-- --->
    <div class="search">
        <form  id="search-form"  style="margin-left: 260px;">
            <div class="row">
                <div class="col-lg-7">
                    <div class="input-group" >
                        <input name="cc" value="${appHost}" type="hidden"/>
                        <div class="input-group-btn">
                            <select class="form-control" id="qu" data-val="${qu!'pq'}"  name="qp" style="width: 80px;padding:6px 10px;">
                                <option value="pq"  selected >文档</option>
                                <option value="q" >文章</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" id="q"  name="q"  value="${q!''}" aria-label="请输入搜索关键词">
                        <div class="input-group-btn">
                            <select class="form-control" id="qt" data-val="${qt!'1'}"  name="qt" style="width: 100px;padding:6px; 10px;">
                                <#--<option value="0" >主题</option>-->
                                <option value="1" >关键词</option>
                                <option value="2"  >篇名</option>
                                <option value="3" >全文</option>
                                <option value="4" >作者</option>
                     <#--           <option value="5" >单位</option>-->
                                <option value="6" >摘要</option>
     <#--                           <option value="7" >被引文献</option>
                                <option value="8" >中图分类号</option>-->
                                <option value="9" >文献来源</option>
                            </select>
                            <button type="button"  id="btn-search" class="btn btn-primary">搜索</button>
                        </div>
                    </div><!-- /.input-group -->
                </div><!-- /.col-lg-6 -->
                <div class="hotwords">
                    <a href="#" class="red">毕业答辩</a>
                    <a href="#">个人简历</a>
                    <a href="#">财务报表</a>
                    <a href="#">总结计划</a>
                    <a href="#">购销存</a>
                </div>
                <div class="right" style="margin-left: 10px;">
                    <i class="gw-left fa fa-lg fa-cart-arrow-down"></i>
                    <i class="gw-right">></i>
                    <i class="gw-count">0</i>
                    <a href="#">我的购物车</a>
                    <div class="dorpdown-layer">
                        <img src="https://static.gongwk.com/images/settleup-nogoods.png">
                        购物车中还没有商品，赶紧选购吧！
                    </div>
                </div>
                <a type="button" id="btn-desk"class="btn btn-primary pull-right" href="${appHost}/userinfo/desk.jhtml" >
                    <i class="fa fa-cloud-upload">上传我的文档</i>
                </a>
            </div><!-- /.row -->
        </form>
    </div>
</div>