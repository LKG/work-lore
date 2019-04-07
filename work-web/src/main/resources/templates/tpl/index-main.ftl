<div class="clearfix"></div>
<div class="container m-container"  style="position: relative;">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-8">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">

                </div>
                <div class="panel-body">

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
                                        <#list freeDocs.content as freeDoc>
                                            <li><a href="${appHost}/doc/${freeDoc.id!''}.jhtml" >${freeDoc.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${freeDoc.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                        </#list>
                                    </ul>
                                 </div>
                            </#if>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="hot">
                            <#if freeDocs??&&freeDocs.content??>
                                <div class="mc">
                                    <ul>
                                        <#list freeDocs.content as freeDoc>
                                            <li><a href="${appHost}/doc/${freeDoc.id!''}.jhtml" >#######</a> <a  class="pull-right" href="${appHost}/fd/${freeDoc.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                        </#list>
                                    </ul>
                                </div>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="container m-container"  style="position: relative;">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-default" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    领导讲话稿
                </div>
                <div class="panel-body">

                </div>
            </div>


        </div>
    </div>
</div>
<div class="clearfix"></div>