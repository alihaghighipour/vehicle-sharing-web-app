$().ready(function () {
   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {
            console.log(element);

            $("#lista").append(`<div class="col" id="card-` + element.id + `">
               <img src="https://source.unsplash.com/400x200/?auto" class="img-top" alt="...">
               <div class="card-body">
                 <h5 class="title">` + element.modello + `</h5>
                 <p class="text">` + element.cilindrata + `</p>
                 <p class="text">` + element.colore + `</p>
                 <a href="http://localhost:9020/noleggio-veicoli/api/veicoli/` + element.id + `" class="btn btn-success">View more</a>
                 <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</i></button>  
                 <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>               
               </div>
             </div>`)

            $("#elimina-" + element.id).click(function () {

               $.ajax({
                  url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/' + element.id,
                  type: "DELETE",
                  dataType: "json",
                  complete: function () {

                     //$("#card-" + element.id).removeClass("card");
                     $("#card-" + element.id).html("");

                     //document.getElementById("lista").removeChild("#card-" + element.id)

                  }
               })

            })

            //   $("#modifica").click(function(){
            //     $.ajax({
            //     url: 'http://localhost:9020/noleggio-veicoli/api/veicoli',
            //     type: "PUT",
            //     cotentType: "application/json",
            //     dataType: "json",

            //   })
            // })


         });

      }
   })

})

$("#form-categoria").change(function () {

   $("#lista").html("");

   var selected = $('#form-categoria option:selected').text();

   $.ajax({
      url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/categoria/' + selected,
      type: 'GET',
      dataType: 'json',
      success: function (data) {

         data.forEach(element => {


            $("#lista").append(`<div class="col" id="card-` + element.id + `">
                 <img src="https://source.unsplash.com/400x200/?auto" class="img-top" alt="...">
                 <div class="card-body">
                   <h5 class="title">` + element.modello + `</h5>
                   <p class="text">` + element.cilindrata + `</p>
                   <p class="text">` + element.colore + `</p>
                   <a href="http://localhost:9020/noleggio-veicoli/api/veicoli/` + element.id + `" class="btn btn-success">View more</a>
                   <button id="modifica-` + element.id + `" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Modifica</i></button>
                   <button id="elimina-` + element.id + `" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>               
                 </div>
               </div>`)

            $("#elimina-" + element.id).click(function () {

               $.ajax({
                  url: 'http://localhost:9020/noleggio-veicoli/api/veicoli/' + element.id,
                  type: "DELETE",
                  dataType: "json",
                  complete: function () {

                     // $("#card-" + element.id).removeClass("card");
                     $("#card-" + element.id).html("");

                     //document.getElementById("lista").removeChild("#card-" + element.id)

                  }
               })

            })

         });

      }
   })

})