<#list docs.content as content>
<li class="slider-panel fore${content_index+1} ui-switchable-panel-selected" style="float: left; display: list-item;">
    <#list content as doc>
<div class="fore${doc_index+1}">
    <a href="#"><img data-lazy-img="done" width="250" height="164" src="${doc.coverImgUrl}" class=""> </a>
</div>
    </#list>
</li>
</#list>