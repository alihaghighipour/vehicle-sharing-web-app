// evento -> recuperare i campi -> chiamare api -> salvare veicolo
// to do : controlli diversi sui campi (regex per coordianti)
$('#form-inserimento').submit(function(e){
   
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
      immagineUrl: "url"
   } 

   $.ajax({
      url: 'http://localhost:8080/noleggio-veicoli/api/veicoli',
      type: 'POST',
      data: JSON.stringify(nuovoVeicolo),
      dataType: 'application/json',
      success: function(msg) {
         alert('Data Saved: ' + msg);
      },
      error: function(e) {
         alert('Error: ' + e);
      }
   })
})