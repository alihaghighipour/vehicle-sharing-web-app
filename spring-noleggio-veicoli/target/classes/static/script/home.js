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