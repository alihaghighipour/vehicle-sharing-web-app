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

var idVeicolo = 0;

if (Modernizr.localstorage) {
   idVeicolo = localStorage.getItem("id-veicolo");
}

var iconUrl;
var coordinate;


$.ajax({
   url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/' + idVeicolo,
   type: 'GET',
   dataType: 'json',
   success: function (data) {
      $('#immagine').append(`<img src="` + data.urlImmagine + `" alt="">`);
      $('#info').append(`<h2>` + data.modello + `</h2>
                <p>` + data.descrizione + `</p>
                <table class="table table-borderless tabella">
                    <thead>
                        <tr>
                            <th><i class="fab fa-font-awesome-flag"></i> Modello:</th>
                            <td>` + data.modello + `</td>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <th><i class="fas fa-info-circle"></i> Categoria:</th>
                            <td>` + data.categoria + `</td>
                        </tr>
                        <tr>
                            <th><i class="fas fa-gas-pump"></i> Alimentazione:</th>
                            <td>` + data.alimentazione + ` - ` + data.cilindrata + `cc</td>
                        </tr>
                        <tr>
                            <th><i class="fas fa-palette"></i> Colore:</th>
                            <td>` + data.colore + `</td>
                        </tr>
                    </tbody>
                </table>`);
      iconUrl = data.urlImmagine;
      coordinate = data.posizione;
   },
   complete: function () {
      mostraMappa(iconUrl, coordinate);
   }

})

function mostraMappa(iconUrl, coordinate) {

   const coordArray = coordinate.split(", ");

   var Icon = L.icon({
      iconUrl: iconUrl,
      iconSize: [80, 40],
      iconAnchor: [35, 10],
      popupAnchor: [0, 0]
   });

   var map = L.map('map').setView([coordArray[0], coordArray[1]], 15);
   var marker = L.marker([coordArray[0], coordArray[1]], {
      icon: Icon
   }).addTo(map);
   marker.bindPopup("<button type='button' class='btnLogin btn rounded-pill' id='btnPrenota'>Prenotami</button>").openPopup();
   //  $ curl "https://api.mapbox.com/styles/v1/mapbox/satellite-v9/tiles/1/1/0?access_token=YOUR_MAPBOX_ACCESS_TOKEN"


   $("#btnPrenota").click(function () {
      const utente = JSON.parse(localStorage.getItem("utente"));
      if (utente == null) {
         window.location.href = "login.html";
      }
      $("#demoSuccess").addClass("d-none");
      $("#demoFail").addClass("d-none");

      $.ajax({
         url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/prenota?id=' + idVeicolo,
         type: 'POST',
         success: function () {

            $("#demoSuccess").removeClass("d-none");

         },
         error: function () {

            $("#demoFail").removeClass("d-none");

         }
      })
   })

   L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      maxZoom: 18,
      id: 'mapbox/streets-v11',
      tileSize: 512,
      zoomOffset: -1,
      accessToken: 'pk.eyJ1IjoiZXJhbmlvciIsImEiOiJja3liZnlvbWswZjJuMndwOGZtanR1dWJ1In0.vabUElrB3-OTMKYK2pIXjw'
   }).addTo(map);

   var popup = L.popup();

   function onMapClick(e) {
      popup
         .setLatLng(e.latlng)
         .setContent("You clicked the map at " + e.latlng.toString())
         .openOn(map);
   }

   map.on('click', onMapClick);

}

$("#veicolo-body").click(function () {
   $("#demoSuccess").addClass("d-none");
   $("#demoFail").addClass("d-none");
})