$(document).ready(function ()
{
    var offset = 0;

    var slotTemporaliSize = $('#slotTemporaliSize').val();
    $("#nextPrenotaz").prop("disabled", slotTemporaliSize < 10);

    $('#prePrenotaz').click(function ()
    {
        if (offset > 0)
            offset--;
        $.ajax({
            url: "listabagnini",
            data: {
                offsetId: offset
            },
            dataType: "json",
            success: function (data, state) {
                aggiornaSlotTemporale(data);
            },
            error: function (data, state) {
            }
        });
    });

    $('#nextPrenotaz').click(function ()
    {
        offset++;
        $.ajax({
            url: "listabagnini",
            data: {
                offsetId: offset
            },
            dataType: "json",
            success: function (data, state) {
                if (data.user === "")
                {
                    offset--;
                } else
                    aggiornaSlotTemporale(data);

            },
            error: function (data, state) {
            }
        });
    });

    function aggiornaSlotTemporale(slotTemporali)
    {
        slotTemporali.forEach((slotTemporale, index) => {
            $("#data-" + index).html(slotTemporale.data);
            $("#fasciaOraria-" + index).text(slotTemporale.fasciaOraria);
            $("#postiDisponibili-" + index).html(slotTemporale.postiDisponibili + " / " + slotTemporale.postiTotali);

            slotTemporaliSize = slotTemporali.length;
            $("#nextPrenotaz").prop("disabled", slotTemporaliSize < 10);

            if (slotTemporaliSize < 10)
                $("#myTableRow-" + slotTemporaliSize).remove();
        });
    }

    $(function () {
        //funzione per verificare nella servlet quale tasto ho premuto dei due
        var buttonpressed;
        $(".submit-button").click(function () {
            buttonpressed = "&" + $(this).attr("name") + "=" + $(this).val();
        })

        $("#insertSlot").submit(function () {

            var form_data = $("#insertSlot").serialize();
            form_data = form_data + buttonpressed;

            $.ajax({
                url: "inserisciSlot",
                type: "POST",
                dataType: "JSON",
                data: form_data,
                success: function (data) {
                    if (data.isValid) {
                        if (buttonpressed === "&inserisci=Inserisci")
                        {
                            $("#displayUpdate").html("Slot temporale inserito con successo!");
                        }
                        if (buttonpressed === "&modifica=Modifica")
                        {
                            $("#displayUpdate").html("Slot temporale modificato con successo!");

                        }
                        $("#updatePopUp").removeClass("none");
                        $("#updatePopUp").addClass("block");
                    } else
                    {
                        alert("Verifica i dati! " + data.e);
                    }
                }
            });
            return false;
        });
    });

    /*chiusura pop-up per tutti gli insert*/
    $(".close-update").eq(0).click(function () {
        setTimeout(location.reload.bind(location)); //refresh pagina
        $("#updatePopUp").addClass("none");
    });

    $("#postiTotali").keypress(function (e) {
        var key = e.keyCode || e.charCode;

        /*
         8 - (backspace)
         32 - (space)
         48-57 - (0-9)Numbers
         */

        if ((key !== 8 || key === 32) && (key < 48 || key > 57)) {
            return false;
        }
    });

    $('#dataSlot').on('change input', verificaDataSlot);

    function verificaDataSlot() {

        var today = new Date();
        today.setHours(0, 0, 0, 0);

        var inputDate = new Date($('#dataSlot').val());

        if (inputDate < today) {
            $('#dataSlot').addClass("error");
            $('#errDataSlot').html("La data deve essere maggiore o uguale ad oggi");
            $('#errDataSlot').show();
        } else {
            $('#dataSlot').removeClass("error");
            $('#errDataSlot').hide();
        }
    }

});



   