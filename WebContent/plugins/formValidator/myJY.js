//身份证格式的校验。
//身份号码位数及格式检验
function isIDCard(idcard,e){
	var jy=$(e).attr("myJY");
	jy = eval("({" +jy + "})");
	if(!jy.isIDCard){
		return true;
	}
	idcard_array = idcard.split("");
	switch(idcard.length){
		case 15:
			if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
			} else {
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
			}
			if(ereg.test(idcard)){
				return true;
			}else {
				return "出生日期错误";
			}
		break;
		case 18:
			//18位身份号码检测
			//出生日期的合法性检查 
			//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 &&  parseInt(idcard.substr(6,4))%4 == 0 )){
				ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的 合法性正则表达式
			} else {
				ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日 期的合法性正则表达式
			}
			//测试出生日期的合法性
			if(ereg.test(idcard)){
				//计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
				+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
				+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
				+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
				+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
				+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
				+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
				+ parseInt(idcard_array[7]) * 1 
				+ parseInt(idcard_array[8]) * 6
				+ parseInt(idcard_array[9]) * 3 ;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y,1);//判断校验位
				if(M == idcard_array[17]) return true; //检测ID的校验位
				else return "身份证号错误";
			}
			else return "出生日期错误";
		break;
		default:
			return "位数不对!";
		break;
	}
}
function text(){
	alert("aAAAAA");
}
//重定位校验框
function reTip(t,b){
	var f=$('#'+b);
	var offset =f.offset();
	var h=offset.top+f.height()/2-12;
 	$("#"+t).css('left',offset.left +f.width()  + 'px').css('top',h+'px');//css('top',offset.top - 1+'px');
}
//重定位所有校验输出框
function relocationAllTip(){
	$("[myJY]").each(function(i){
	try{
		var v=$(this).attr("myJY");
		if(v){
			var v = eval("({" +v + "})");
			if('fix' in v){
				var t;
				if('tipID' in v){
					t=v.tipID;
				}else{
					t=$(this).attr('name')+"Tip";
				}
				var f=$('#'+v.fix);
				var offset =f.offset();
				var h=offset.top+f.height()/2-12;
			 	$("#"+t).css('left',offset.left +f.width()  + 'px').css('top',h+'px');//css('top',offset.top - 1+'px');
			}
		}
	}catch(e){
		alert(this.id +'--'+e);
	}
	});
	}

//数字精度格式化
function precision(v,e){
	var jy=$(e).attr("myJY");
	jy = eval("({" +jy + "})");
	var v=parseFloat(v);
	if(isNaN(v)){
		return "必须填入数字";
	}
	$(e).val(v.toFixed(jy.precision));
	return true;
}
//校验是不是数字
function isNumeric(v,e){
	if(v==''||isNaN(v)){
		return "必须填入数字";	
	}
	return true;
}
//校验非空
function unEmpty(v,e){
	alert($(e).val());
	if($(e).val()==''){
		return "必填不能为空";	
	}
	return true;
}
//绑定校验事件
function myJY(){
	$("[myJY]").each(function(i){
		try{
		//获取校验列表
		var v=$(this).attr("myJY");
		if(v){
			var v = eval("({" +v + "})");
			//初始化样式
			var vs = {
				autoModify:false,
				onShow:"必填",
				onFocus:"必填",
				onCorrect:"OK",
				tipID:$(this).attr("name")+"Tip"
				//onEmpty:"不能为空",
				//empty:false	,
				//defaultValue:0
			};
			//data.field={sex:"true"} 
			//页面显示的三态文本 showText:String
			if('showText' in v){vs.onShow=v.showText;}
			if('foucsText' in v){vs.onFocus=v.foucsText;}
			if('okText' in v){vs.onCorrect=v.okText;}
			//自定义显示区域{fixTipID："String"}
			if('tipID' in v){vs.tipID=v.tipID;}
			//基本校验
			var vc = {onErrorMax:'数值越界',max:9999999999};
			//必填项 unEmpty:true|false
			if('unEmpty' in v){
				vc.min='1';
				vc.onError='必填';
			}
			//onError文本 {errText:'String'}
			if('errText' in v){vc.onError=v.errText;}
			//type  {type:'E'}
			//(默认: "size")     属性名:比较类型。    值有以下几个类型:
			//      "size":表示字符长度/(checkbox/radio)选择的个数
			//      "number":数值型比较
			//      "string":字符型比较
			//      "date":短日期类型
			//      "datetime":长日期类型
			if('type' in v){vc.type=v.type;}
			//min {min:'number'}
			if('min' in v){vc.min=v.min;}
			//max {max:'number'}
			if('max' in v){vc.max=v.max;}
			//配置校验时机
	        switch(this.type)
			{
				case "text":
				case "hidden":
				case "password":
				case "textarea":
				case "file":
				case "select-one":
			        break;
				case "checkbox":
					vs.triggerEvent="change";
					break;
				case "radio": 
					vs.triggerEvent="change";
					break;
				case "select-multiple":
					break;
		    }
	        //修改默认出发时机
	        if('event' in v){
	        	vs.triggerEvent=v.event;
	        }
	        //正则校验{regex:'正则表达式编码'[,regexErrText:'校验失败提示']}
	        var vr={
		        	dataType:"enum",    
			        onError:"格式不正确"	
		        };
	        if('regex' in v){
	        	vr.regExp=v.regex;
	        }
	        if('regexErrText' in v){
	        	vr.onError=v.regexErrText;
	        }
	        
	      
	        vobj=$("[name='" + this.name + "']");
	        //基本校验
	        vobj.formValidator(vs).inputValidator(vc);
			//非空校验
			//if(v.unEmpty){vobj.functionValidator({fun:unEmpty});}
			//校验数字
	        if('type' in v){if(v.type=='number'){vobj.functionValidator({fun:isNumeric});}}
			//格式化数字精度
			if('precision' in v){vobj.functionValidator({fun:precision});}
			//正则校验
			if('regex' in v){vobj.regexValidator(vr);}
			//自定义函数校验
			if('isIDcard' in v){vobj.functionValidator({fun:idNumForm});}
			//分组
			if('group' in v){
				vs.validatorGroup=v.group;
				//基本校验
		        vobj.formValidator(vs).inputValidator(vc);
		        //非空校验
				//if(v.unEmpty){vobj.functionValidator({fun:unEmpty});}
				//校验数字
				if('type' in v){if(v.type=='number'){vobj.functionValidator({fun:isNumeric});}}
				//格式化数字精度
				if('precision' in v){vobj.functionValidator({fun:precision});}
				//正则校验
				if('regex' in v){vobj.regexValidator(vr);}
				//自定义函数校验
				if('isIDCard' in v){vobj.functionValidator({fun:isIDCard});}
			}
		}
		}catch(e){
			alert(this.id +'--'+e);
		}
	});
}
//初始化校验
function initJY(){
	$("form.myJY").each(function(i){
		$.formValidator.initConfig({theme:"jan",formID:this.id,submitOnce:true});
		$.formValidator.initConfig({validatorGroup:"2",theme:"jan",formID:this.id,submitOnce:true});
	});
	
	
}
//初始化校验提示框
function initTip(){
	$("[myJY]").each(function(i){
		//var tipId=$("[name="+this.name+"]").eq(0).attr("id") + "Tip";
		var tipId=this.name+ "Tip";
		if($("body").find("#"+tipId).length<1){
			$(this).parent().append("<span id="+ tipId +" ></span>");
		}
	});
}
//初始化文本区域
//页面初始化
$(document).ready(function(){
	initJY();
	initTip();
	myJY();
});