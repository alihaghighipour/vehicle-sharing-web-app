if (Modernizr.localstorage) {
   const utente = JSON.parse(localStorage.getItem("utente"));
   if (utente == null) {
      window.location.href = 'errore.html';
   }

   $('#login').addClass('d-none');
   $('#logout').removeClass('d-none');
   $('#logout').click(logout);

   const formData = new FormData();
   formData.append("token", utente.token);
   formData.append("ruolo", "Admin");

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/utenti/autorizzato',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function () {
         var links = document.getElementById('links');

         var inserimento = document.createElement('a');
         var inserimentoText = document.createTextNode('Aggiungi veicoli');
         inserimento.appendChild(inserimentoText);

         inserimento.setAttribute('href', 'inserimento.html');
         inserimento.setAttribute('class', 'nav-link mx-1');

         links.insertBefore(inserimento, links.children[2]);

         var view = document.getElementById('view');
         view.setAttribute('href', 'view-admin.html');
      },
      error: function () {
         window.location.href = 'errore.html';
      }
   })
}

function logout() {

   if (Modernizr.localstorage) {

      const utente = JSON.parse(localStorage.getItem("utente"));
      const formData = new FormData();
      formData.append('token', utente.token);

      $.ajax({

         url: 'http://localhost:9020/noleggio-veicoli/api/utenti/logout',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function () {
            localStorage.removeItem("utente");
            window.location.href = 'home.html';
         }
      })
   }
}


$('#form-categoria').change(function () {

   switch ($('#form-categoria option:selected').text()) {
      case "Monopattino":
      case "Bicicletta":
         $('#form-alimentazione').addClass('d-none');
         $('#form-cilindrata').addClass('d-none');
         $('#cilindrata-label').addClass('d-none');
         break;
      default:
         $('#form-alimentazione').removeClass('d-none');
         $('#form-cilindrata').removeClass('d-none');
         $('#cilindrata-label').removeClass('d-none');
   }
})


$('#form-inserimento').submit(function (e) {

   e.preventDefault();

   const immagine = document.getElementById("immagine").files[0];

   const categoriaText = $('#form-categoria option:selected').text();
   var alimentazioneText = $('input[name="btnradio"]:checked + label').text();

   switch (categoriaText) {
      case "Bicicletta":
         alimentazioneText = "Non motorizzato";
         break;
      case "Monopattino":
         alimentazioneText = "Elettrico";
         break;
   }

   const veicolo = {
      categoria: categoriaText,
      alimentazione: alimentazioneText,
      descrizione: $('#form-descrizione').val(),
      modello: $('#form-modello').val(),
      colore: $('#form-colore').val(),
      cilindrata: $('#form-cilindrata').val(),
      posizione: $('#form-coordinate').val(),
      disponibile: true,
      dataPrenotazione: null
   };

   const formData = new FormData();
   formData.append("veicolo", JSON.stringify(veicolo));
   formData.append("immagine", immagine);

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function () {
         $("#demo").removeClass("d-none");
         $('#form-descrizione').val("");
         $('#form-modello').val("");
         $('#form-cilindrata').val("");
         $('#form-coordinate').val("");
         $('#form-colore').val("");
         $("#form-categoria option:selected").val([0]);
         $('input[name="btnradio"]').checked = false;
      },
      error: function (e) {
         alert('Errore: ' + e);
      }
   })
})

$("#form-categoria").focus(function () {
   $("#demo").addClass("d-none");
})