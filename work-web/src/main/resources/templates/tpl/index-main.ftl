<div class="clearfix"></div>
<div class="container m-container"  >
    <div class="row">
        <div class="col-sm-3 col-md-4">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                        精选文章
                </div>
                <div class="panel-body">
                     <@media.periodical categoryCode="010" >
                         <div class="mc">
                             <ul>
                              <#if docs??&&docs.content??>
                                  <#list docs.content as model>
                                     <li><i class="fa fa-${model.fileHeader!''}" style="margin-right: 10px;"></i> <a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                  </#list>
                              </#if>
                             </ul>
                         </div>
                     </@media.periodical>
                </div>
            </div>


        </div>
        <div class="col-sm-3 col-md-4">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    精选文章
                </div>
                <div class="panel-body">
                     <@media.periodical categoryCode="010" >
                         <div class="mc">
                             <ul>
                              <#if docs??&&docs.content??>
                                  <#list docs.content as model>
                                     <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i> <a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                  </#list>
                              </#if>
                             </ul>
                         </div>
                     </@media.periodical>
                </div>
            </div>


        </div>
        <div class="col-xs-6 col-md-4">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading"  style="padding: 0px 2px 0px 0px">
                    <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                        <li role="presentation" style="cursor:pointer" class="active">
                            <a id="my-tab-free" data-key="my-tab-free">
                                免费榜
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="">
                            <a id="my-tab-hot" data-key="">
                                畅销榜
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="free">

                            <#if freeDocs??&&freeDocs.content??>
                                <div class="mc">
                                    <ul>
                                        <#list freeDocs.content as model>
                                            <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i> <a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                        </#list>
                                    </ul>
                                 </div>
                            </#if>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="hot">
                            <@media.periodical >
                                <div class="mc">
                                    <ul>
                                     <#if docs??&&docs.content??>
                                        <#list docs.content as model>
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
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="container m-container"  style="margin-top: -20px;">
    <div class="row">
        <div class="col-sm-6 col-md-4 doc-col">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    民政工作
                </div>
                <div class="panel-body">
                    <@media.periodical categoryCode="010001" >
                        <div class="mc">
                            <ul>
                                <#if docs??&&docs.content??>
                                    <#list docs.content as model>
                                        <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </@media.periodical>
                </div>
            </div>
            <!--- -->
        </div>
        <div class="col-sm-6 col-md-4 doc-col">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    统战工作
                </div>
                <div class="panel-body">
                    <@media.periodical categoryCode="010003">
                        <div class="mc">
                            <ul>
                                <#if docs??&&docs.content??>
                                    <#list docs.content as model>
                                        <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </@media.periodical>
                </div>
            </div>
            <!--- -->
        </div>
        <div class="col-sm-6 col-md-4 doc-col">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    统计工作
                </div>
                <div class="panel-body">
                    <@media.periodical categoryCode="010004" >
                        <div class="mc">
                            <ul>
                                <#if docs??&&docs.content??>
                                    <#list docs.content as model>
                                        <li><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </@media.periodical>
                </div>
            </div>
            <!--- -->
        </div>
    </div>
</div>
<div class="clearfix"></div>