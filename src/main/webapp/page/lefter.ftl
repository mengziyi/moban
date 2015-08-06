        <div class="span2" id="left-view">
            <div class="accordion f-accordion">
                <div class="accordion-heading f-heading"><i class="icon-star-empty"></i> 工具</div>
                <div class="accordion-body f-body">
                    <div id="accordion" class="accordion f-accordion">
                        <div class="accordion-group f-group">
                            <div class="accordion-heading f-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                    <i class="icon-share"></i> 推送消息
                                </a>
                            </div>
							<div id="collapse1" class="accordion-body f-body <#if nav!='newMsg'>collapse</#if>">
                                <div class="accordion-inner">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li <#if nav=='newMsg'> class="active"</#if>><a href="#" _href="message!newMsg2.jhtml?message.appId=${appId!""}">新建消息</a></li>
                                        <li><a href="#" _href="message!scheduled_new.jhtml?message.appId=${appId!""}" id="scheduled">预订的消息</a></li>
                                        <li <#if nav='copyMsg'> class='active'</#if>><a href="#" _href="message!history.jhtml?message.appId=${appId!""}" id="history">推送历史</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-group f-group">
                            <div class="accordion-heading f-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                    <i class="icon-list-alt"></i> 统计与报表
                                </a>
                            </div>
                            <div id="collapse2" class="accordion-body f-body <#if nav!='report' && nav!='userReport'>collapse</#if>">
                                <div class="accordion-inner">
                                    <ul class="nav nav-pills nav-stacked">
                                        <!-- li <#if nav=='report'> class="active"</#if>><a href="#" _href="app!pushReport.jhtml?app.id=${appId!""}">推送报表</a></li -->
                                        <li <#if nav=='userReport'> class="active"</#if>><a href="#" _href="message!userStatistics.jhtml?app.id=${appId!""}">用户统计</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion f-accordion">
                <div class="accordion-heading f-heading"><i class="icon-cog"></i> ${app.name!"应用信息"}</div>
                <div class="accordion-body f-body">
                    <div class="accordion-inner">
                        <ul class="nav nav-pills nav-stacked">
                            <li<#if nav=='detail'> class="active" </#if>><a href="app!detail.jhtml?app.id=${appId!""}"><i class="icon-info-sign"></i> 应用详情</a></li>
                            <li><a href="#" _href="app!edit.jhtml?app.id=${appId!""}"><i class="icon-pencil"></i> 编辑应用</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
(function(){

	$("ul.nav-pills>li>a").on("click", function(){
		var me = $(this);
		$("ul.nav-pills>li.active").removeClass("active");
		me.parent().addClass("active");
		var url = me.attr("_href");
		if(url){
			var viewer = $("#right-view");
			viewer.html("loading...");
			viewer.load(url,{},function(response,result,ret){
				if(ret.status==500){
					$(this).html('<h2>未实现.todo...</h2><hr /><div class="row-fluid"></div>');
				}
			});
		}
		
	});
	
	$('#collapse1').on('show', function () {
		$(this).find('a:first').trigger('click');
	});
	$('#collapse2').on('show', function () {
		$(this).find('a:first').trigger('click');
	});
})();
</script>
		