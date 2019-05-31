<!---面板begin--->
<div class="panel panel-default pin">
    <div class="panel-heading" role="tab">
        <h4>相关资源</h4>
    </div>
   <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel">
     <div class="panel-body">
         <div class="caption" >
              <@media.periodical categoryCode="${result.categoryCode}" >
                  <div class="mc">
                      <ul>
                        <#if docs??>
                            <#list docs as model>
                                <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                            </#list>
                        </#if>
                      </ul>
                  </div>
              </@media.periodical>
         </div>
     </div>
   </div>
</div>
<!---面板end--->