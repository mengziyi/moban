/**
 * <title>新建/发送消息</title>
 * 
 * <p></p>
 * Copyright © 2013 Phoenix New Media Limited All Rights Reserved. 
 * @author chengds
 * 2013-6-8
 */
$.pushManager = (function() {
	var pushManager = (function() {
		var ui = (function(){
			var hideAndroid = function(hide){
				if(!hide){
					$(".control-group[item-type='Android']").show();	
				}else{
					$(".control-group[item-type='Android']").hide();	
				}
			};
			var hideIOS = function(hide){
				if(!hide){
					$(".control-group[item-type='iOS']").show();	
				}else{
					$(".control-group[item-type='iOS']").hide();	
				}
			}
			var init = function(){
				if(!$("input[name='os_iOS']").attr("checked")){
					hideIOS(true);
				}
				if(!$("input[name='os_Android']").attr("checked")){
					hideAndroid(true);
				}
				$("#os-choices input[type='checkbox']").change(function() { 
					if($(this).attr("name")=="os_iOS"){
						$.pushManager.ui.hideIOS(!this.checked);	
					}else{
						$.pushManager.ui.hideAndroid(!this.checked);
					}
					
				});
			}
			return {
				init:init,
				hideIOS:hideIOS,
				hideAndroid:hideAndroid	
			}
		})();
		
		var message = (function() {
			// bind
			$("form textarea[name=content]").on('keyup', function(){
				var content = $(this).val();
				var pattern = /[^\x00-\x80]+/;
				var contentLength = 0;
				for (var i = 0; i < content.length; i++) {
					if (pattern.test(content.charAt(i))) {
						contentLength ++;
					} else {
						contentLength += 0.5;
					}
				}
				$('#contentLength').html(contentLength);
			});
			
			var init = function() {
				$("#btnSubmit").bind(
						"click",
						function() {
							var data = collectData();
                            console.log(data);
							$('#proccess-box').html("正在发送中...");
							$('#proccess-box').fadeIn("fast");


                            console.log(data);


                            var timeexpect=data["message.sendTimeStr"];



var send_url="message!send.jhtml";
if(timeexpect){

    var newfunc = (parseInt($('form input[name=info_ios][checked]').val())!=3)&&(parseInt($('form input[name=info][checked]').val())!=2);
    if(newfunc){
    send_url="message!sendScheduled.jhtml";
    }

}


                           console.log(send_url);


							$.post(send_url, data, function(data) {
								var ret = JSON.parse(data);
								if (ret.success) {
									$('#proccess-box').html(
											'<font color="green">发送成功</font>');
									$('#proccess-box').fadeOut("slow");
									setTimeout(function() {
										$('#myModal').modal('hide');
										//$('form')[0].reset();
										//$('#contentLength').html(0);
										$('#left-view #collapse1 li.active a').click();
									}, 1500);
									// todo

								} else {
									$('#proccess-box').html(
											'<font color="red">发送失败!'
													+ ret.message + '</font>');
									setTimeout(function() {
										$('#myModal').modal('hide');
									}, 3000);
								}
							});
						});
			};

			var validor = function(){
				var ios = $("input[name='os_iOS']").attr("checked");
				var android = $("input[name='os_Android']").attr("checked");
				var targetOS = ios && android ? 3 : (!android && ios ? 2 : (android ? 1 : 0));
				if(!targetOS){
					$("#os-choices .label-warning").show();
					$("form input[name='os_iOS']").focus();
					return false;
				}else{
					$("#os-choices .label-warning").hide();
				}
				return true;
			}	
			function collectData() {
				var data = {};
				//发送对象	
				var ios = $("input[name='os_iOS']").attr("checked");
				var android = $("input[name='os_Android']").attr("checked");
				data.targetOS = ios && android ? 3 : (!android && ios ? 2 : (android ? 1 : 0));

				var expired = $("form select[name=serverSideExpiredTime] option[selected]");
				var exTime = 0;
				if (expired.length == 0) {
					exTime = $("form select[name=serverSideExpiredTime] option:first").val();
				} else {
					exTime = expired.val();
				}
				if(exTime==-1) exTime = $('form input[name=serverSideExpiredTime]').val();
				data.serverSideExpiredTime = exTime;
				data.appId = $("form input[name=appId]").val();
				data.title = $("form input[name=title]").val();
				data.content = $("form textarea[name=content]").val();
				data.styleId = $("form input[name=styleId]").val();
				data.type = $("form input[name=type]").val();
				// 发送时间
				var sendtime = parseInt($("form input[name=rdSenttime][checked]").val());
				if (sendtime == 1) {
					sendTime = $("form input[name=sendtime]").val();
					if (sendTime)
						data.sendTimeStr = sendTime;
				}
				//是否为离线用户保留
				var isImportant = parseInt($("form input[name=rdImportant][checked]").val());
				data.important = isImportant==1?1:0;				
				
				var filter = parseInt($('form input[name=info][checked]').val());
				if (filter == 1) {
					var tagFilter = $("#tagFilter").val();
					data.deviceFilter = JSON.stringify({
						"tag" : tagFilter
					});
				} else if (filter == 2) {
					var snFilter = $("#snFilter").val();
					data.deviceFilter = JSON.stringify({
						"sn" : snFilter
					});
				}
				//ios的filter
				var filter_ios = parseInt($('form input[name=info_ios][checked]').val());
				if (filter_ios == 1) {
					var tagFilter = $("#tagFilter_ios").val();
					data.deviceFilter_ios = JSON.stringify({
						"tag" : tagFilter
					});
				}else if (filter_ios == 2) {
					var versionFilter = $("#versionFilter_ios").val();
					data.deviceFilter_ios = JSON.stringify({
						"version" : versionFilter
					});
				} else if (filter_ios == 3) {
					var snFilter = $("#snFilter_ios").val();
					data.deviceFilter_ios = JSON.stringify({
						"sn" : snFilter
					});
				} else if (filter_ios ==4) {
					var whiteList = $("#whiteList_ios").val();
					data.deviceFilter_ios = JSON.stringify({
						"whiteList" : whiteList
					});
				} else if (filter_ios == 5) {
					var blackList = $("#blackList_ios").val();
					data.deviceFilter_ios = JSON.stringify({
						"blackList" : blackList
					});
				}				
				//ios声音
				data.iosSound = $('form input[name=sound]').val();
				
				var keys =[],values=[];
				$("form input[name=key]").each(function(){
					keys.push($(this).val());
				})
				$("form input[name=value]").each(function(){
					values.push($(this).val());
				})
				var extra=null;
				for(var i=0;i<keys.length && i<values.length;i++){
					if(keys[i] && values[i]){
						if(!extra) extra={};
						if(keys[i]!="feedback") extra[keys[i]] = values[i];
					}
				}
				if(extra){
					data.extra = JSON.stringify(extra);
				}
                var selectedApp = new Array();
                $("#attachApp input[type=checkbox]").each(function(){
                    if($(this).attr("checked")=="checked"){
                        selectedApp.push($(this).val());
                    }
                });
                for ( var key in data) {
					data["message." + key] = data[key];
					delete data[key];
				}
                data["selectedApps"] = selectedApp.toString();
				return data;
			}

			return {
				init : init,
				validor : validor	
			};

		}());

		// 推送对象
		var recipients = (function() {

			var init = function() {
				$('#recipient-choices').find("input[type='radio']").bind('click',function(e) {
					$($(this).parents('.controls')[0]).find("input[type='radio']").removeAttr("checked");
					$(this).attr("checked", "checked");
					var val = parseInt($(this).val()) + 2;
					var rad = $($(this).parents('.control-group')[0]);
					rad.children("div.list-item").addClass("hide");
					rad.children("div:nth-child(" + val + ")").removeClass("hide");
				});
			};

			return {
				init : init
			};

		}());

		// 过期时间
		var expiredTimeSetting = (function() {

			var init = function() {
				$('#combox-expiredTime').selectpicker();
				
				$('#combox-expiredTime').bind('change',function(){
					var value = $(this).val();
					if(value==-1){
						$('#customExpiredTime').show('normal');	
					}else{
						$('#customExpiredTime').hide('normal');	
					}
					$('form input[name=serverSideExpiredTime]').val('');
				});
			};

			return {
				init : init
			};

		}());
		
		// 发送时间
		var sendtime = (function() {

			var init = function() {
				$('#sendtime-box').find("input[type='radio']").bind(
						'click',
						function(e) {
							$('#sendtime-box').find("input[type='radio']")
									.removeAttr("checked");
							$(this).attr("checked", "checked");
							var val = parseInt($(this).val());
							if (val == 1)
								$('#datepicker1').fadeIn('fast');
							else
								$('#datepicker1').fadeOut('fast');

				});
				$('#important-box').find("input[type='radio']").bind('click',function(e) {
					$('#important-box').find("input[type='radio']").removeAttr("checked");
					$(this).attr("checked", "checked");
				});
				$('#datepicker1').datetimepicker({
					format : "yyyy-MM-dd hh:mm:ss",
					language : 'zh',
					pickDate : true,
					pickTime : true,
					hourStep : 1,
					minuteStep : 15,
					secondStep : 30,
					inputMask : true
				});
			};

			return {
				init : init
			};

		}());

		var tooltip = (function() {
			var init = function() {
				var tooltips = $('form a[data-toggle="tooltip"]');
				tooltips.each(function(index, domEle) {
					var item = $(domEle);
					var title = item.attr("title");
					item.tooltip({
						title : title,
						placement : "right",
						animation : true
					});
				});

			}

			return {
				init : init
			};
		}());

		// 附加字段
		var keyvalue = (function() {
			var init = function() {
				// todo
			}
			var tpl = '\
				<div class="control-group keyvalue-item" style="height:0px;opacity:0">\
					<label class="control-label">附加字段：</label>\
					<div class="controls">\
						<label style="width:130px;" class="span2" for="key">键 <input type="text" name="key" class="input-small"  /></label>\
						<label style="width:130px;" class="span2" for="value">值 <input type="text" name="value" class="input-small" /></label>\
						<div class="btn-group">\
							  <button type="button" class="btn delete" onclick="$.pushManager.keyvalue.del(event)">删除</button>\
							  <button type="button" class="btn add" onclick="$.pushManager.keyvalue.add(event)">添加</button>\
						</div>\
					</div>\
				</div>';
			var del = function(e) {
				var len = $('div.keyvalue-item').length;
				if (len > 1) {
					var curItem = $(e.target).parents('.keyvalue-item');
					//curItem.fadeOut("fast", function() {
					//	$(this).remove();
					//});
					curItem.css("overflow","hidden").animate({ 
						height: "0px",
						opacity:0	
					}, 'fast','swing',function() {
						$(this).remove();
					});

					if (len == 2) {
						$('div.keyvalue-item').find('button.add').show();
					}
				}
			}
			var add = function(e) {
				var itemBox = $(e.target).parents('.keyvalue-item');
				itemBox.find('.add').hide();
				itemBox.after(tpl);
				itemBox.next().animate({ 
					height: "35px",
					opacity:1	
				}, 'fast','swing');
			}
			return {
				init : init,
				del : del,
				add : add
			};
		}());
		
		//button组模仿radio功能
		var radio = function(opts){
			var init = function(){
				$('div.btn-group[data-toggle-name]').each(function(){
					var group   = $(this);
					var form    = group.parents('form').eq(0);
					var name    = group.attr('data-toggle-name');
					var hidden  = $('input[property-data="' + name + '"]', form);
					$('button', group).each(function(){
						var button = $(this);
						button.live('click', function(){
						  hidden.val($(this).val());
						});
						if(button.val() == hidden.val()) {
							button.addClass('active');
						}
					});
				});
			};
			return {
				init : init
			}
		}();
		
		var init = function(opts) {
			ui.init();
			message.init();
			recipients.init();// 收件人相关
			expiredTimeSetting.init();
			sendtime.init();
			keyvalue.init();
			tooltip.init();
			radio.init();
			
			$('#myModal').on('show', function () {
				return $.pushManager.message.validor();
			})
		};

		return {
			init : init,
			ui:ui,	
			message : message,
			keyvalue : keyvalue
		};

	}());

	return pushManager

})();
