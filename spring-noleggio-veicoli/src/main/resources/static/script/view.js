$().ready(function () {
   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {
            $("#lista").append(`<div class="col" id="card-` + element.id + `">
               <img src="`+ element.urlImmagine +`" class="img-top" alt="...">
               <div class="card-body">
                 <h5 class="title">` + element.modello + `</h5>
                 <p class="text">` + element.cilindrata + `</p>
                 <p class="text">` + element.colore + `</p>
                 <a href="http://localhost:9020/noleggio-veicoli/api/veicoli/` + element.id + `" class="btn btn-success">View more</a>
                 <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</i></button>  
                 <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>               
               </div>
             </div>`)

            $("#elimina-" + element.id).click({veicolo: element}, eliminaVeicolo);

            $("#modifica-" + element.id).click({veicolo: element}, modificaVeicolo);
         });
      }
   })
})

$("#form-categoria-filtro").change(function () {

   $("#lista").html("");

   var selected = $('#form-categoria-filtro option:selected').text();

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/categoria/' + selected,
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {

            $("#lista").append(`<div class="col" id="card-` + element.id + `">
                 <img src="`+ element.urlImmagine +`" class="img-top" alt="...">
                 <div class="card-body">
                   <h5 class="title">` + element.modello + `</h5>
                   <p class="text">` + element.cilindrata + `</p>
                   <p class="text">` + element.colore + `</p>
                   <a href="http://localhost:9020/noleggio-veicoli/api/veicoli/` + element.id + `" class="btn btn-success">View more</a>
                   <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</i></button>
                   <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>               
                 </div>
               </div>`)

            $("#elimina-" + element.id).click({veicolo: element}, eliminaVeicolo);

            $("#modifica-" + element.id).click({veicolo: element}, modificaVeicolo);

         });
      }
   })
})

function eliminaVeicolo(event) {
   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/' + event.data.veicolo.id,
      type: "DELETE",
      dataType: "json",
      complete: function () {
         $("#card-" + event.data.veicolo.id).html("");
      }
   })
}

function modificaVeicolo(event) {
   switch (event.data.veicolo.categoria) {
      case "Automobile":
         $("#form-categoria").val("1");
         break;
      case "Monopattino":
         $("#form-categoria").val("2");
         break;
      case "Bicicletta":
         $("#form-categoria").val("3");
         break;
      case "Speciale":
         $("#form-categoria").val("4");
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

   $('#form-modello').val(event.data.veicolo.modello);
   $('#form-colore').val(event.data.veicolo.colore);
   $('#form-cilindrata').val(event.data.veicolo.cilindrata);
   $('#form-coordinate').val(event.data.veicolo.posizione);

   $("#form-modifica").submit(function (e) {

      e.preventDefault();
	  
	  const immagine = document.getElementById("immagine").files[0];

      var veicolo = {
         id: event.data.veicolo.id,
         categoria: $('#form-categoria option:selected').text(),
         alimentazione: $('input[name="btnradio"]:checked + label').text(),
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
            alert("Veicolo modificato");
         },
         error: function (e) {
            alert('Errore' , e);
         }
      })
   })
}