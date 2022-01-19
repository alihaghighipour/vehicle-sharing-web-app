if (Modernizr.localstorage) {
   const utente = JSON.parse(localStorage.getItem("utente"));
   if (utente != null) {
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
         }
      })
   }
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

         data.forEach(element => {
            $("#lista").append(`<li class="veicolo col-lg-3 mt-3 hover-shadow " id="li-` + element.id + `">
            <img src="` + element.urlImmagine + `" alt="">
            <h4>` + element.modello + `</h4>
			<p>` + element.alimentazione + ` - ` + element.cilindrata + ` cc</p>
            <button class="btn btn-primary" id="mostra-` + element.id + `">Mostra</button>
        
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

         });
      }
   })
})

$("#btnDisp").click(function () {

   $("#lista").html("");

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/disponibili',
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {
            $("#lista").append(`<li class="veicolo col-lg-3 mt-3 hover-shadow " id="li-` + element.id + `">
            <img src="` + element.urlImmagine + `" alt="">
            <h4>` + element.modello + `</h4>
			<p>` + element.alimentazione + ` - ` + element.cilindrata + ` cc</p>
            <button class="btn btn-primary" id="mostra-` + element.id + `">Mostra</button>
        
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

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
			<p>` + element.alimentazione + ` - ` + element.cilindrata + ` cc</p>
            <button class="btn btn-primary" id="mostra-` + element.id + `">Mostra</button>
            
            
            </li>`);

            $("#mostra-" + element.id).click({
               veicolo: element
            }, mostraVeicolo);

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