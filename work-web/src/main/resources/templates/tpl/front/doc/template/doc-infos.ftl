<!---面板begin--->
<div class="panel panel-default">
   <div class="panel-heading" role="tab" id="headingOne">
      <h4> <i class="fa fa-book"> </i> 当前资源信息</h4>
   </div>
   <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
     <div class="panel-body">
         <div class="caption" >
            <p style="color: #bda837;" >
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star-half-o"></i>
                <i class="fa fa-star-o"></i>
            </p>
            <p >编号：<code>${result.periodicalCode!''}</code></p>
            <p >类型：<code>${result.periodicalType!''}</code></p>
            <p >格式：<code>${result.fileHeader!''}</code></p>
            <p >大小：<code>${result.dataSizeHuman!''}</code></p>
            <p >价格：<font class="cOrange originPrice" style="font-size: 18px;"><code>${result.originPrice!'0'}</code></font>元</p>
            <p >折扣价：<font class="cOrange finalPrice" style="font-size: 18px;font-weight:bold;color:#f60;"><code>${result.finalPrice!'0'}</code></font>元</p>
            <p >上传时间：<code>${(result.createTime?string("yyyy-MM-dd"))!}</code></p>
             <p >总页数：<code>${result.pageNum!'1'}</code></p>
         </div>
     </div>
   </div>
</div>
<!---面板end--->