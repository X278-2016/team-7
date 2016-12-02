function makeAPIcall2(){
	var root = location.protocol + '//' + location.host;
	root = root + "/api/padsets/"
	console.log(root)
	var geoarray = [];
	$.ajax({
        type: "GET",
        dataType: "json",
        url:root
    }).done(function(data){

       var jsonArr = data;
       for(var x in jsonArr){
       		var geoObj = {};
       		geoObj['name'] = jsonArr[x].mName;
       		geoObj['latitude'] = jsonArr[x].mLatitude;
       		geoObj['longitude'] = jsonArr[x].mLongitude;

       		console.log(geoObj)
       		geoarray.push(geoObj);
       } 
       console.log(geoarray)
    });
	return geoarray;
   
}

function makeAPIcall(id){
	console.log("Made api call with" + id)
	var root = location.protocol + '//' + location.host;
	root = root + "/api/padsets/"
	root = root + "/{" + id + "}"
	console.log(root)
	var geoarray = [];
	$.ajax({
        type: "GET",
        dataType: "json",
        url:root
    }).done(function(data){

       var jsonArr = data;
       for(var x in jsonArr){
       		var geoObj = {};
       		geoObj['name'] = jsonArr[x].mName;
       		geoObj['latitude'] = jsonArr[x].mLatitude;
       		geoObj['longitude'] = jsonArr[x].mLongitude;

       		geoarray.push(geoObj);
       }      
    });
    return geoarray;
}

