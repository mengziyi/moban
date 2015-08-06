<#assign pageTitle="PiscesM" baseDir="">
<#include "../page/header.ftl">
<link rel="stylesheet" href="/res/css/bootstrap.min.css" media="screen" />
<style>
	.view10 {
		max-width: 1024px;
		min-width: 1000px;
		margin: 0px auto;
	}
</style>
<#assign answer=42/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="view10" id="right-view">
		${answer?string.currency}
		${answer?string.percent}

<#assign user={"name":"hailang", "sex":"man"}>
	<#assign keys=user?keys>
	<#list keys as key>
			<br>
	<div>${key}=${user[key]}</div>
	</#list>


    <#if brandinfos??>
        <#list brandinfos?sort_by("id") as app>
		  <div style="color:red;float:left;width:30%">${app.id}
			  <#if app_index%2==0><span class="icon-plane"></span>
			  <#else>
							  <span class="icon-plus"></span>
		  </#if>
			<span>${app.brandNameCn}  ${app.brandNameEn}  ${.now?string("yyyy-MM-dd HH:mm:ss")}</span>
			  <div style="float:left;margin: auto">
			<img  src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2729371985,3761158996&fm=116&gp=0.jpg" class="img-polaroid">
						  </div>
		  </div>

        </#list>

    </#if>



		</div>
  <#include "../page/footer.ftl">
	</div>
</div>

<script>
	setCookie("")

</script>

</body>
</html>