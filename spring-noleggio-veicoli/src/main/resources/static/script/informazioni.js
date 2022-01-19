var tipologie = [];
var risparmi = [];
var totale = 0;

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
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/statistica',
      type: 'GET',
      dataType: 'json',
      success: function (data) {
         data.forEach(element => {
            tipologie.push(element.tipologiaVeicolo);
            risparmi.push(element.risparmio);
            totale += element.risparmio;
         })
      },
      error: function (e) {
         alert('Errore' + e);
      },
      complete: function () {
         const ctx = document.getElementById('statistica').getContext('2d');
         const myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
               labels: tipologie,
               datasets: [{
                  label: '# of Votes',
                  data: risparmi,
                  backgroundColor: [
                     'orange',
                     'blue',
                     'red',
                     'yellow'
                  ],
                  borderColor: [
                     'rgba(255, 99, 132, 1)',
                     'rgba(54, 162, 235, 1)',
                     'rgba(255, 206, 86, 1)',
                     'rgba(75, 192, 192, 1)'

                  ],
                  borderWidth: 1
               }]
            },

         });
      }
   })
})