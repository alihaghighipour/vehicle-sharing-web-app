$('#form-login').submit(function(e) {
		
	e.preventDefault();
	
	const username = $('#username').val();
	const password = $('#password').val();

	const formData = new FormData();
	formData.append("username", username);
	formData.append("password", password);
	
	$.ajax({
		url: 'http://localhost:9020/noleggio-veicoli/api/utenti/login',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		dataType: 'json',
		success: function(utente){
			console.log(utente);
		},
		error: function(e) {
			alert('Errore' + e);
		}		
	})
})