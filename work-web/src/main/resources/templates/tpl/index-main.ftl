<div class="clearfix"></div>
<div class="container m-container"  >
    <div class="row">
        <div class="col-sm-3 col-md-8">
            <div class="panel panel-default hot-topic" id="hot-topic" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    <i class="fa fa-th" style="margin-right: 10px"></i> 热门专栏
                    <a href="${appHost}/topic.jhtml" class="pull-right" >更多<i class="fa fa-angle-double-right"></i></a>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 hot-list">
                                <div class="thumbnail">
                                    <a href="${appHost}/topic.jhtml" target="_blank" alt="工作总结专区">
                                        <img src="https://static.gongwk.com/images/20190426001.png" alt="党建工作、办公室工作、专项工作、行政工作等总结范文。">
                                    </a>
                                    <div class="caption">
                                        <h4><a href="${appHost}/topic.jhtml" target="_blank" alt="工作总结专区">工作总结专区</a></h4>
                                        <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="工作总结专区">党建工作、办公室工作、专项工作、行政工作等总结范文。</a></p>
                                    </div>
                                </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="述职报告范文">
                                    <img src="https://static.gongwk.com/images/20190426002.png" alt="各个岗位年终述职、述廉、述责、述学等报告及相关范文合集。">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="述职报告范文">述职报告范文</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="述职报告范文">各个岗位年终述职、述廉、述责、述学等报告及相关范文合集。</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="2019两会专区">
                                    <img src="https://static.gongwk.com/images/20190426003.jpg" alt="2019年两会精神传达提纲、展板、学习资料等">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">2019两会专区</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">2019年两会精神传达提纲、展板、学习资料等</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="党建文秘素材">
                                    <img src="https://static.gongwk.com/images/20190426004.png" alt="范文、表格、模板、PPT等资料。">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml" target="_blank" alt="党建文秘素材">党建文秘素材</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="党建文秘素材">范文、表格、模板、PPT等资料。</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="col-sm-3 col-md-4">
            <div class="panel panel-default"  id="top-doc" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading"  style="padding: 0px 2px 0px 0px">
                    <ul id="my-tab-hot" class="myNavTab nav nav-tabs" role="tablist">
                        <li role="presentation" style="cursor:pointer" class="active">
                            <a id="my-tab-free" data-key="tab-pane-free">
                                免费榜
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="">
                            <a id="my-tab-hot" data-key="tab-pane-hot">
                                畅销榜
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab-pane-free">

                            <#if freeDocs??&&freeDocs??>
                                <div class="mc">
                                    <ul>
                                        <#list freeDocs as model>
                                            <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i> <a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                        </#list>
                                    </ul>
                                 </div>
                            </#if>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab-pane-hot">
                            <@media.periodical >
                                <div class="mc">
                                    <ul>
                                     <#if docs??>
                                        <#list docs as model>
                                            <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
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
<div class="container m-container" >
    <div class="row">
        <div class="col-sm-12 col-md-12 doc-col">
            <div class="panel panel-default"   id="work-doc"   style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="padding: 0px 2px 0px 0px;">
                    <ul id="my-tab-work" class="myNavTab nav nav-tabs" role="tablist">
                        <li role="presentation" style="cursor:pointer" class="active">
                            <a id="my-tab-010001" data-key="tab-pane-010001">
                                民政工作
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="">
                            <a id="my-tab-010002" data-key="tab-pane-010002">
                                残联工作
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="">
                            <a id="my-tab-010003" data-key="tab-pane-010003">
                                统战工作
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="">
                            <a id="my-tab-010004" data-key="tab-pane-010004">
                                统计工作
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <!--- begin -->
                        <div role="tabpanel" class="tab-pane active" id="tab-pane-010001">
                            <@media.periodical categoryCode="010001" >
                                <div class="mc">
                                    <ul>
                                        <#if docs??>
                                            <#list docs as model>
                                                <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </@media.periodical>
                        </div>
                        <!--- end -->
                        <!--- begin -->
                        <div role="tabpanel" class="tab-pane" id="tab-pane-010002">
                        <@media.periodical categoryCode="010002">
                            <div class="mc">
                                <ul>
                                    <#if docs??>
                                        <#list docs as model>
                                            <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                        </#list>
                                    </#if>
                                </ul>
                            </div>
                        </@media.periodical>
                        </div>
                        <!--- end -->
                        <!--- begin -->
                        <div role="tabpanel" class="tab-pane" id="tab-pane-010003">
                            <@media.periodical categoryCode="010003">
                                <div class="mc">
                                    <ul>
                                        <#if docs??>
                                            <#list docs as model>
                                                <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </@media.periodical>
                        </div>
                        <!--- end -->
                        <!--- begin -->
                        <div role="tabpanel" class="tab-pane" id="tab-pane-010004">
                            <@media.periodical categoryCode="010004">
                                <div class="mc">
                                    <ul>
                                        <#if docs??>
                                            <#list docs as model>
                                                <li title="${model.periodicalName!''}"><i class="fa fa-${model.fileHeader!''}"  style="margin-right: 10px;"></i><a href="${appHost}/doc/${model.id!''}.jhtml" >${model.shortTitle!''}</a> <a  class="pull-right" href="${appHost}/fd/${model.id!''}.jhtml"> <i class="fa fa-download"  ></i></a></li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </@media.periodical>
                        </div>
                        <!--- end -->

                    </div>

                </div>
            </div>
            <!--- -->
        </div>
    </div>
</div>
<div class="clearfix"></div>