<h2><#if app??>创建<#else>编辑</#if>应用</h2>
<hr />
<div class="row-fluid">
    <form class="form-horizontal" id="form"  method="post" enctype="multipart/form-data" target="iframefile">
        <input type="hidden" name="app.id" value="<#if app??>#{app.id!0}<#else>0</#if>"/>
        <div class="control-group">
            <label class="control-label" for="content">应用名称:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" name="app.name" check-type="required" required-message="请填写应用名称" <#if app??>value="${app.name!""}"</#if> /><font color="red">*</font>
                <p class="help-block"></p>
            </div>
        </div>
        <hr class="dashed" />
        <div class="control-group">
            <label class="control-label" for="content">应用图标:</label>
            <div class="controls">
                <button type="button" id="btnUpload" class="btn">浏览</button>
                <input name="image" type="file" style="position: absolute;opacity: 0;cursor: pointer;top: -1000px;left:-1000px;z-index: 100;" accept="image/*" />
                <span style="position:absolute;top:150px;left:660px;<#if app??><#if app.logo??>background:url(../${app.logo}) no-repeat;</#if></#if> background-size:100% 100%;" class="app_icon"></span>
            </div>
        </div>
        <hr class="dashed" />
        <div class="control-group">
            <label class="control-label" for="departApprover">部门审批人:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" name="app.departApprover" check-type="required" required-message="请填写部门审批人" <#if app??>value="${app.departApprover!""}"</#if> /><font color="red">*</font>
                <p class="help-block"></p>
            </div>
        </div>
        <hr class="dashed" />
        <div class="control-group">
            <label class="control-label" for="appApprover">应用审批人:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" name="app.appApprover"<#if app??>value="${app.appApprover!""}"</#if> />
                <p class="help-block"></p>
            </div>
        </div>

        <div class="control-group">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" href="#collapse_Approver"><i class="icon-chevron-down"></i><strong> Android </strong></a>
                </div>
                <div id="collapse_Approver" class="accordion-body in">
                    <div class="accordion-inner">
                        <div class="control-group">
                            <label class="control-label" for="content">应用包名<a href="#" data-toggle="tooltip" data-html="true" title="<h3>应用包名</h3><p>Android应用程序的包名（Package Name）。在 AndroidManifest.xml 里配置使用的。</p>"><i class="icon-question-sign"></i></a>:</label>
                            <div class="controls">
                                <input class="input-xlarge" type="text" <#if app.packName ??&&app.packName?trim?length!=0>disabled="disabled" </#if> name="app.packName" <#if app.id!=0>value="${(app.packName!"")?js_string}"</#if> />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="control-group">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" href="#collapse_Apple"><i class="icon-chevron-down"></i><strong> Apple </strong></a>
                </div>
                <div id="collapse_Apple" class="accordion-body in">
                    <div class="accordion-inner">
                        <div class="control-group">
                            <label class="control-label" for="content">Bundle identifier<a href="#" data-toggle="tooltip" data-html="true" title="<h3>应用包名</h3><p>IOS应用程序的包名（Bundle identifier）。</p>"><i class="icon-question-sign"></i></a>:</label>
                            <div class="controls">
                                <input class="input-xlarge" type="text" <#if app.packName_ios??&&app.packName_ios?trim?length!=0>disabled="disabled" </#if> name="app.packName_ios" <#if app.id!=0>value="${(app.packName_ios!"")?js_string}"</#if> />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="content">iOS生产证书文件:</label>
                            <div class="controls">
                                <input name="certificate" type="file" />
                                <span class="label label-warning<#if app.certificateFile?? || app.id==0> hide</#if>">未上传</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="content">iOS生产证书密码:</label>
                            <div class="controls">
                                <input class="input-xlarge" type="password" name="app.certificatePassword"/>
                                <span class="label label-warning<#if app.certificatePassword?? || app.id==0> hide</#if>">未设置</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <hr class="dashed" />

        <div class="form-actions text-right">
            <button type="submit" id="btnSubmit" class="btn btn-primary"><#if app.id!=0>修改<#else>创建</#if>应用</button>
        </div>
        <iframe id='iframefile' name='iframefile' style="display:none"></iframe>
    </form>
</div>
<script type="text/javascript" src="${baseDir!""}res/js/bootstrap-validation.js"></script>
<script type="text/javascript" src="${baseDir!""}res/js/jquery.form.min.js"></script>
<script>
    $('#btnUpload').click(function(){
        $(this).next("input[type=file]").trigger("click");
    });
    $('#btnUpload + input[type=file]').change(function(e){
        //搜集文件
        var files = e.target.files||[];
        if(files.length==0){
            return false;
        }

        console.log(files.length);
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function(e) {
          //  console.log("-----"+e.target.result);
            $('form .app_icon').css({
                "background-image":'url('+ e.target.result +')'
            });
        }
        reader.readAsDataURL(file);
    });

    $("input[name='certificateFile']").change(function(e){
        var label = $(this).next('.label');
        var value = $(this).val();
        if (!/^.*\.p12$/.test(value)) {
            $(this).val('')
            label.show().html("只支持后缀为.p12的文件");
        } else {
            label.hide().html('');
        }

    });

    $('#form').validation({
        callbacksuccess:function(){
            $('#form').ajaxSubmit({
                target:'#right-view',
                url:"app!save.jhtml",
                type:"post",
                enctype:'multipart/form-data',
                dataType:'json',
                success:function(data, statusText) {
                    if(data.success){
                        var id = data.message;
                        location.href = "app!detail.jhtml?app.id=" + id;
                    }else{
                        alert("出错！" + data.message);
                    }
                },
                error:function(data) {
                    alert("出错！");
                }
            });
        }
    });

    (function(){
        var tooltips = $('form a[data-toggle="tooltip"]');
        tooltips.each(function(index,domEle){
            var item = $(domEle);
            var title = item.attr("title");
            var tooltip = item.tooltip({
                title:title,
                html:true,
                placement:"right",
                animation:true
            });
        });

    })()


</script>