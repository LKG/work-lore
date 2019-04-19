<!---面板begin--->
<div class="panel panel-default pin">
    <div class="panel-heading" role="tab" >
        <h4><i class="fa fa-fire text-danger" > </i>  热搜</h4>
    </div>
   <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel">
     <div class="panel-body">
         <div class="caption" >
             <#if result.seoKeywords??>
                 <#list result.seoKeywords?split(",") as keyword>
                     <a href="${appHost}/pq?v=${ver!'1'}&q=${keyword!''}&qt=1" title="${keyword!''}" target="_blank">${keyword!''}</a>
                 </#list>
             </#if>
         </div>
     </div>
   </div>
</div>
<!---面板end--->