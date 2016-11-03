function makeAPIcall(){
	var geoarray = [];
	$.ajax({
        type: "GET",
        dataType: "json",
        url:"http://localhost:8080/api/padsets"
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