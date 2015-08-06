<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ipush-${pageTitle!"首页"}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${baseDir!""}res/css/bootstrap.min.css" media="screen" />
    <link rel="stylesheet" href="${baseDir!""}res/css/bootstrap-responsive.min.css" media="screen" />
    <link rel="stylesheet" href="${baseDir!""}res/css/f.style.css" media="screen" />
	<script type="text/javascript" src='${baseDir!""}res/js/utils.js'></script>
    <script type="text/javascript" src='${baseDir!""}res/js/jquery.min.js'></script>
	<script type="text/javascript" src='${baseDir!""}res/js/bootstrap.min.js'></script>
	<script type="text/javascript" src="${baseDir!""}res/js/bootstrap-select.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${baseDir!""}res/css/bootstrap-select.min.css">
	<script type="text/javascript" src="${baseDir!""}res/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${baseDir!""}res/js/bootstrap-datetimepicker.zh.js"></script>
	<script type="text/javascript" src="${baseDir!""}res/js/bootstrap-paginator.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${baseDir!""}res/css/bootstrap-datetimepicker.min.css">
	<style>
		#left-view{position:fixed}
		#right-view{padding-left: 15%; width: 95%;}
	</style>
	<script>
		var IndexContentHandle={
			message:function(content){
				alert(content);//todo
			}
		}
	</script>
</head>
<body style="overflow-y:scroll;">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="${baseDir!""}index.jhtml">iPush凤凰推送</a>
            <div class="nav-collapse collapse">
                <ul class="nav" id="appLoc">
						<#if app??>
							<#assign appId=app.id>
						<#elseif message??>
							<#assign appId=message.appId>
						<#else>
							<#assign appId=0>
						</#if>
					<#if appList??>					
					<#assign viewCount=3>
					<#list appList?sort_by("id") as item>
					<#if item_index lt viewCount>
                    <li <#if item.id==appId>class="active"</#if>><a href="app!detail.jhtml?app.id=${item.id}">${item.name!""}</a></li>
					</#if>	
                    <#if item_index==viewCount>
					<li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">更多<b class="caret"></b></a>
                        <ul class="dropdown-menu">
					</#if>
					<#if item_index gte viewCount>		
                            <li <#if item.id==appId>class="active"</#if>><a href="app!detail.jhtml?app.id=${item.id}">${item.name!""}</a></li>
						<#if !item_has_next>		
							</ul>
						</li>
						<#assign appName=item.name>
						</#if>
                    </#if>						
					</#list>
					</#if>
					<li <#if  !nav?? && 0==appId>class="active"</#if>><a href="app!create.jhtml"><i class="icon-plus"></i>创建应用</a></li>
					<li <#if -1==appId>class="active"</#if>><a href="app.jhtml"><i class="icon-list"></i>所有应用</a></li>
					<li <#if nav??&&"cluster"== nav>class="active"</#if>><a href="cluster!newConfig.jhtml"><i class="icon-tasks"></i>集群管理</a></li>
                </ul>
				<ul class="nav pull-right right-menu">
					<li class="divider-vertical"></li>
					<li><a id="login-name">欢迎您:程邓时</a></li>
					<li <#if nav?? && nav=="user">class="active"</#if>><a href="${baseDir!""}user.jhtml">用户管理</a></li>
				</ul>
                <ul class="nav pull-right right-menu">

                    <li><a href="${baseDir!""}noauthority.jhtml">申请权限</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
var user = Utils.Cookie.get('ipush_user');
if(user){
	$('#login-name').html('欢迎您:' + user);
}else{
	$('#login-name').html('您尚未登录');
}
</script>