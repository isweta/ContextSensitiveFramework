var req;
var active;
var res;
$(document).ready(function(){
		var iDiv = document.createElement('div');
	iDiv.id = 'dialog-confirm';
	iDiv.title='Do you need help';
	document.getElementsByTagName('body')[0].appendChild(iDiv);
	
	$(document).on('click', 'input', function () {
		/*if (gOptions.enabled) {
      	    for (var i = 0; i < gOptions.count; i++) {
      	    	if(this.id=gOptions.id){
      	    	var URL =  gOptions.temp;
      	    	var id1=this.id;
      	    }}
      	  };*/
		
		/*var url=onFoucsTextField(this.id);*/
		var getProperty = function (propertyName) {
		    return obj[propertyName];
		};

		url=getProperty(this.id);
		console.log(url);
		
	if(url!=null && url!="NoResource"){
		console.log("i am in");
  $( "#dialog-confirm" ).dialog({
	  
    resizable: false,
    height:140,
    modal: true,
    buttons: {
      "Play Video": function() {
    	  
      	var strWindowFeatures = "location=yes,height=570,width=520,scrollbars=yes,status=yes";
      	    	var win = window.open(url, "_blank", strWindowFeatures);
      	    	
      	    	$( this ).dialog( "close" );
      	
      },
      "Chat": function() {
      	var strWindowFeatures = "location=yes,height=570,width=520,scrollbars=yes,status=yes";
      	var URL = "chatbox.html"
      	var win =window.open(URL, "_blank", strWindowFeatures);
        $( this ).dialog( "close" );
      },
      "Click to call": function() {
      	this.src="https://www.linkedin.com/cws/share?mini=true&amp;url=" + location.href;
          $( this ).dialog( "close" );
        },
    }
  }); }
}); }); 
function onFoucsTextField(name)
{
	console.log("Processing javascript");
	if(window.XMLHttpRequest)
		{
		req=new XMLHttpRequest();
		}
	else if(window.ActiveXObject)
		{
		req=new ActiveXObject("Microsoft.XMLHTTP");
		}
	//active=document.activeElement.name;
	var url="SuggestServlet?name="+name;
	
	req.onreadystatechange=function(){
		
		if(req.readyState==4 && req.status==200){
			//document.getElementById("product_suggest").innerHTML=req.responseText;
			console.log("changed value");
		console.log(req.responseText);
			res=req.responseText;
					}
	};
	req.open("GET",url,true);
	req.send();
	return res;
	
}
function getResponse()
{
	console.log("getting respose");
if(req.readyState==4)
	{
	if(req.status==200)
		{
		console.log("found it");
		document.getElementById("product_suggest").innerHTML=req.responseText;
		var link=req.responseText;
		return link;
		}
	}

}