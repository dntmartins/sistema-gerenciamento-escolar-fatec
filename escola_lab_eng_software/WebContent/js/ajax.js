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
	    		setTimeout(function(){ $("#msgMatriculaSuccess").fadeOut(); }, 3000);
	    	}else{
	    		errorMessage(data.msg);
	    		setTimeout(function(){ $("#msgMatriculaError").fadeOut(); }, 3000);
	    	}
	    },
	    error: function(msg) {
	    	errorMessage("Ocorreu um erro ao matricular as disciplinas");
	    }
	});
}