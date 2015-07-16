	
<#if appMapper??>
	
	<table class="table table-striped table-bordered table-condensed">
		<thead align="center">
			<tr>
			  <th style="text-align:left;" width="100">权限</th>
			  <th style="text-align:left;" >分类</th>
			</tr>
		</thead>
		<tbody>
			<#if authInfo??>
			<#list authInfo?sort_by('authId') as authority>
			<tr>
				<input type="hidden" value="${authority.authId}">
				<td style="text-align:left;">${authority.authName}</td>
                <td style="text-align:left;">
                <#if authority.isApp==true>
                   <input type="checkbox" value="CHECK" <#if authority.perm.CHECK??>checked="checked" </#if>> 查看
                   <input type="checkbox" value="PUSH" <#if authority.perm.PUSH??>checked="checked"</#if>>推送
                    <#else >
                    <#list authority.perm?keys as key>
                        <input type="checkbox" value="${key}" <#if authority.perm[key]>checked="checked" </#if>>
                    </#list>
                </#if>
                </td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
<#else>

bbbb
ccc


</#if>

