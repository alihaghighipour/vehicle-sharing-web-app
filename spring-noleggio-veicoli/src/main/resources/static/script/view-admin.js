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


$().ready(function () {
   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
      type: 'GET',
      dataType: 'json',
      success: function (data) {
		 $("#lista").html("");
         data.forEach(element => {
            $("#lista").append(`<li class="veicolo col-lg-3 mt-3 hover-shadow " id="li-` + element.id + `">
            <img src="` + element.urlImmagine + `" alt="">
            <h4>` + element.modello + `</h4>
			<p>`+ element.alimentazione +` - `+ element.cilindrata +` cc</p>
            <button class="btn btn-primary" id="mostra-`+ element.id +`">Mostra</button>
            <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</button>  
            <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button> 
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

            $("#elimina-" + element.id).click({
               veicolo: element
            }, eliminaVeicolo);

            $("#modifica-" + element.id).click({
               veicolo: element
            }, modificaVeicolo);
         });
      }
   })
})


$("#form-categoria-filtro").change(function () {

   $("#lista").html("");
   var urlApi = 'http://localhost:9020/noleggio-veicoli/api/veicoli';
   var selected = $('#form-categoria-filtro option:selected').text();

   switch (selected) {
      case "Automobile":
         urlApi += '/categoria/Automobile';
         break;
      case "Monopattino":
         urlApi += '/categoria/Monopattino';
         break;
      case "Bicicletta":
         urlApi += '/categoria/Bicicletta';
         break;
      case "Motorino":
         urlApi += '/categoria/Motorino';
         break;
   }

   $.ajax({
      url: urlApi,
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {
            $("#lista").append(`<li class="veicolo col-lg-3 mt-3 hover-shadow " id="li-` + element.id + `">
            <img src="` + element.urlImmagine + `" alt="">
            <h4>` + element.modello + `</h4>
			<p>`+ element.alimentazione +` - `+ element.cilindrata +` cc</p>
            <button class="btn btn-primary" id="mostra-`+ element.id +`">Mostra</button>
            <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</button>  
            <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button> 
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

            $("#elimina-" + element.id).click({
               veicolo: element
            }, eliminaVeicolo);

            $("#modifica-" + element.id).click({
               veicolo: element
            }, modificaVeicolo);

         });
      }
   })
})

function mostraVeicolo(event) {
   
   if (Modernizr.localstorage) {
      localStorage.setItem("id-veicolo", event.data.veicolo.id);
   }
   window.location.href = 'veicolo.html';
}


function eliminaVeicolo(event) {

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/' + event.data.veicolo.id,
      type: "DELETE",
      dataType: "json",
      complete: function () {
         var liElement = document.getElementById('li-' + event.data.veicolo.id);
         //liElement.setAttribute('class', 'animate__animated animate__fadeOutLeft');

         liElement.remove();  
      }
   })
}

function modificaVeicolo(event) {
	console.log('ciao modifica');
   switch (event.data.veicolo.categoria) {
      case "Automobile":
         $("#form-categoria").val("1");
         $('#form-alimentazione').removeClass('d-none');
         $('#form-cilindrata').removeClass('d-none');
         $('#cilindrata-label').removeClass('d-none');
         break;
      case "Monopattino":
         $("#form-categoria").val("2");
         $('#form-alimentazione').addClass('d-none');
         $('#form-cilindrata').addClass('d-none');
         $('#cilindrata-label').addClass('d-none');
         break;
      case "Bicicletta":
         $("#form-categoria").val("3");
         $('#form-alimentazione').addClass('d-none');
         $('#form-cilindrata').addClass('d-none');
         $('#cilindrata-label').addClass('d-none');
         break;
      case "Motorino":
         $("#form-categoria").val("4");
         
         $('#form-alimentazione').removeClass('d-none');
         $('#form-cilindrata').removeClass('d-none');
         $('#cilindrata-label').removeClass('d-none');
         break;
      default:
         $("#form-categoria").val("0");
   }

   switch (event.data.veicolo.alimentazione) {
      case "Elettrico":
         $("#btnradio1").attr("checked", true);
         break;

      case "Hybrid":
         $("#btnradio2").attr("checked", true);
         break;

      case "Benzina":
         $("#btnradio3").attr("checked", true);
         break;

      case "Diesel":
         $("#btnradio4").attr("checked", true);
         break;
      default:
         $("#btnradio1").attr("checked", false);
         $("#btnradio2").attr("checked", false);
         $("#btnradio3").attr("checked", false);
         $("#btnradio4").attr("checked", false);
   }

   $('#form-descrizione').val(event.data.veicolo.descrizione);
   $('#form-modello').val(event.data.veicolo.modello);
   $('#form-colore').val(event.data.veicolo.colore);
   $('#form-cilindrata').val(event.data.veicolo.cilindrata);
   $('#form-coordinate').val(event.data.veicolo.posizione);

   $("#form-modifica").submit(function (e) {
      console.log("ciao form");
      e.preventDefault();

      const immagine = document.getElementById("immagine").files[0];

      var categoriaText = $('#form-categoria option:selected').text();
      var alimentazioneText = $('input[name="btnradio"]:checked + label').text();

      switch (categoriaText) {
         case "Bicicletta":
            alimentazioneText = "Non motorizzato";
            break;
         case "Monopattino":
            alimentazioneText = "Elettrico";
            break;
      }

      var veicolo = {
         id: event.data.veicolo.id,
         categoria: categoriaText,
         alimentazione: alimentazioneText,
         descrizione: $('#form-descrizione').val(),
         modello: $('#form-modello').val(),
         colore: $('#form-colore').val(),
         cilindrata: $('#form-cilindrata').val(),
         posizione: $('#form-coordinate').val(),
         disponibile: true,
         dataPrenotazione: null
      }

      const formData = new FormData();
      formData.append("veicolo", JSON.stringify(veicolo));
      formData.append("immagine", immagine);

      $.ajax({
         url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
         type: "PUT",
         data: formData,
         processData: false,
         contentType: false,
         success: function () {
            $("#demo").removeClass("d-none");
            setTimeout(refreshPagina, 1500);
         },
         error: function (e) {
            alert('Errore', e);
         }
      })
   })
}

function refreshPagina() {
   window.location.reload();
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

$("#form-categoria").focus(function () {
   $("#demo").addClass("d-none");
})

$("#btnDisp").click(function() {

   

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/disponibili',
      type: 'GET',
      dataType: 'json',
      success: function (data) {
		 $("#lista").html("");
         data.forEach(element => {
            $("#lista").append(`<li class="veicolo col-lg-3 mt-3 hover-shadow " id="li-` + element.id + `">
            <img src="` + element.urlImmagine + `" alt="">
            <h4>` + element.modello + `</h4>
			<p>`+ element.alimentazione +` - `+ element.cilindrata +` cc</p>
            <button class="btn btn-primary" id="mostra-`+ element.id +`">Mostra</button>
            <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</button>  
            <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button> 
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

            $("#elimina-" + element.id).click({
               veicolo: element
            }, eliminaVeicolo);

            $("#modifica-" + element.id).click({
               veicolo: element
            }, modificaVeicolo);

         });
      }
   })
})