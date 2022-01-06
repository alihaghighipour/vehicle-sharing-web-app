$('#form-inserimento').submit(function (e) {

   e.preventDefault();

   const immagine = document.getElementById("immagine").files[0];

   const veicolo = {
      categoria: $('#form-categoria option:selected').text(),
      alimentazione: $('input[name="btnradio"]:checked + label').text(),
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