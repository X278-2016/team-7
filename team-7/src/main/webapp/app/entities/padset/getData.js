function makeAPIcall2(){
	var geoarray = [];
	$.ajax({
        type: "GET",
        dataType: "json",
        url:"http://localhost:8080/api/padsets/"
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
	var geoarray = [];
	$.ajax({
        type: "GET",
        dataType: "json",
        url:"http://localhost:8080/api/padsets" + "/{" + id + "}"
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

