function successMessage(msg){
	$("#msgMatriculaError").hide();
	$("#msgMatriculaSuccess").html(msg);
	$("#msgMatriculaSuccess").fadeIn();
}
function errorMessage(msg){
	$("#msgMatriculaSuccess").hide();
	$("#msgMatriculaError").html(msg);
	$("#msgMatriculaError").fadeIn();
}

function sendSelectedDisciplines(){
	var disciplinesId = { 'checkedRows[]' : []};
	$(":checked").each(function() {
		disciplinesId['checkedRows[]'].push($(this).val());
	});
	$.ajax({ 
	    type: 'POST', 
	    url: 'gradeHorario',
	    dataType: 'json',
	    data: disciplinesId, 
	    success: function (data) { 
	    	if(data.status){
	    		successMessage(data.msg);
	    	}else{
	    		errorMessage(data.msg);
	    	}
	    },
	    error: function(msg) {
	    	errorMessage("Ocorreu um erro ao matricular as disciplinas");
	    }
	});
	
	
}