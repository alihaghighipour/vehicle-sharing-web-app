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
        window.location.reload();
      }
    })
  }
}

const swiper = new Swiper('.swiper', {
  direction: 'horizontal',
  loop: true,

  breakpoints: {
    640: {
      slidesPerView: 2,
      spaceBetween: 20,
    },
    768: {
      slidesPerView: 3,
      spaceBetween: 40,
    },
    1024: {
      slidesPerView: 3,
      spaceBetween: 50,
    },
  },

  autoHeight: true,

  pagination: {
    el: '.swiper-pagination',
  },

  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

  scrollbar: {
    el: '.swiper-scrollbar',
  },
});

$(".imgPresentazione").click(function () {
  window.location.href = "view-utente.html";
})


$().ready(function() {

  $.ajax({
    url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/categoria/Automobile',
    type: 'GET',
    dataType: 'json',
    success: function (data) {
      
      data.forEach(element => {
        aggiungiMappa(element.urlImmagine, element.posizione);
      })
    }
  })

})

var map = L.map('map').setView([45.0703, 7.6869], 13);

function aggiungiMappa (iconUrl, coordinate) {

  const coordArray = coordinate.split(", ");

  var Icon = L.icon({
    iconUrl: iconUrl,
    iconSize: [80, 40],
    iconAnchor: [35, 10],
    popupAnchor: [0, 0]
  });
  
  
  var marker = L.marker([coordArray[0], coordArray[1]], {
    icon: Icon
  }).addTo(map);
  
  
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

