<script id="doc-carousel-template-js" type="text/html">
    {{each result.content }}
    <div class="panel panel-default" style="margin-bottom: 5px;">
        <div class="panel-body" style="padding: 0px 0px; ">
            <img   data-original="{{$value.imgUrl}}"  width="765" height="574" class="lazy img-responsive" alt=" {{$value.pageNum}}"/>
        </div>
        <div class="panel-footer text-right"  style="padding: 5px 15px;" >第{{$value.pageNum}}页</div>
    </div>
    {{/each}}
</script>

<div id="doc-carousel-generic"  data="${result.id}">

</div>
<div class="alert alert-info" role="alert">
    <a type="button" class="btn btn-danger" href="${appHost}/order/getOrderInfo.jhtml?id=${result.id!''}" ><i class="fa  fa-exclamation">举报</i></a>
    <a type="button" class="btn btn-danger" href="${appHost}/order/getOrderInfo.jhtml?id=${result.id!''}" ><i class="fa  fa-exclamation-triangle">版权申诉</i></a>
    <a href="#" class="alert-link">word格式文档无特别注明外均可编辑修改；预览文档经过压缩，下载后原文更清晰！ </a>
    <a type="button" class="btn btn-danger pull-right" href="${appHost}/order/getOrderInfo.jhtml?id=${result.id!''}" ><i class="fa  fa-cloud-download">立即下载</i></a>
</div>
<div class="alert alert-warning" role="alert">
   <i class="fa  fa-exclamation-triangle"></i> <a href="#" class="alert-link">本站所有资源均是用户自行上传分享，仅供网友学习交流，未经上传用户书面授权，请勿作他用。 </a>
</div>