/**
 * 
 */
 
 
 function IDfind(){
	
	let mname = document.querySelector('#mname').value
	let mphone = document.querySelector('#mphone').value
	let findID = document.querySelector('#findID')
	
	$.ajax({
		url : "/room/userID",
		data : {"mname" : mname , "mphone" : mphone},
		success : function(re){
			
			if(re !== 'null'){
				findID.innerHTML = '회원아이디 : '+re
			}else{
				findID.innerHTML = '동일한 정보가 없습니다'
			}
		}
	})
}
 