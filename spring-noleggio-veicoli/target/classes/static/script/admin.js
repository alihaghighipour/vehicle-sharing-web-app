$('#form-inserimento').submit(function (e) {

   e.preventDefault();

   var nuovoVeicolo = {
      categoria: $('#form-categoria option:selected').text(),
      alimentazione: $('input[name="btnradio"]:checked + label').text(),
      modello: $('#form-modello').val(),
      colore: $('#form-colore').val(),
      cilindrata: $('#form-cilindrata').val(),
      posizione: $('#form-coordinate').val(),
      disponibile: true,
      dataPrenotazione: null,
      immagineUrl: "https://source.unsplash.com/400x200/?auto"
   }

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
      type: 'POST',
      data: JSON.stringify(nuovoVeicolo),
      contentType: 'application/json',
      dataType: 'json',
      success: function () {
         //myModal.show()
         $("#demo").removeClass("d-none");
         $('#form-modello').val("");
         $('#form-cilindrata').val("");
         $('#form-coordinate').val("");
         $('#form-colore').val("");
         $("#form-categoria option:selected").val([0]);
         $('input[name="btnradio"]').checked = false;
      },
      error: function (e) {
         alert('Error: ' + e);
      }
   })
})

$("#form-categoria").focus(function () {
   $("#demo").addClass("d-none");
})