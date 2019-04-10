/**
 * 
 */
var AjaxUtil = function() {
	var xhr = new XMLHttpRequest();
	this.open = function(config) {
		config.method = config.method ? config.method : 'GET';
		config.async = config.async ? config.async : true;
		xhr.open(config.method, config.url, config.async);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					this.callback(xhr.response);
					//이건 에러 풀어주려면 xhr 하위에 callback 선언 해야한다.
				}
			}
		}
	}
	xhr.callback = function(res) {
		console.log(res);
	}
	this.setCallback = function(call) {
		xhr.callback = call;
	}
	this.send = function() {
		xhr.send();
	}
}