$(document).ready(function ()
{
    var offset = 0;

    var slotTemporaliSize = $('#slotTemporaliSize').val();
    $("#nextPrenotaz").prop("disabled", slotTemporaliSize < 10); //se ci sono meno di 10 slot disabilito tasto next

    $('#prePrenotaz').click(function ()
    {
        if (offset > 0)
            offset--;
        $.ajax({
            url: "home",
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
            url: "home",
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
            $("#nextPrenotaz").prop("disabled", slotTemporaliSize < 10); //disabilito tasto next se non ci sono altre prenotazioni

            if (slotTemporaliSize < 10)
                $("#myTableRow-" + slotTemporaliSize).remove();
        });
    }

    $("#modificaInfo").submit(function () {
        var formData = new FormData(this);
        $.ajax({
            url: "informazioniPersonali",
            type: "POST",
            method: "POST",
            processData: false,
            contentType: false,
            dataType: "JSON",
            data: formData,
            success: function (data) {
                if (data.isValid) {
                    $("#displayUpdate").html("Modifica informazioni personali avvenuta con successo!");
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

    $("#insertFattura").submit(function () {
        $.ajax({
            url: "insertFattura",
            type: "POST",
            dataType: "JSON",
            data: $("#insertFattura").serialize(),
            success: function (data) {
                if (data.isValid) {
                    $("#submit-button").prop("disabled", "disabled");
                    $("#displayUpdate").html("Fattura emessa con successo!");
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

    $('#corpoFattura').keyup(function () {
        $('#caratteriRimanenti').text("Caratteri: " + $(this).val().length + "/200");
    });

    $('#corpoFattura').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 200)
            if (!(key === 46 || key === 8))
                e.preventDefault();
    });

    $(function () {
        //funzione per verificare nella servlet nel formReminderSconto quale tasto ho premuto dei 2

        var buttonpressed;
        $(".submit-button").click(function () {
            buttonpressed = $(this).attr("name");
        });

        $(".formReminderSconto").submit(function () {

            var that = $(this),
                    data = {};

            that.find('[name]').each(function (index, value) {
                var that = $(this),
                        name = that.attr('name'),
                        value = that.val();
                data[name] = value;
            });

            if (buttonpressed === 'reminder')
            {
                delete data.sconto;
            }
            if (buttonpressed === 'sconto')
            {
                delete data.reminder;
            }

            var indexG = data['index'];

            $.ajax({
                url: "actionPrenotazioniFuture",
                type: "POST",
                dataType: "JSON",
                data: data,
                success: function (data) {
                    if (data.isValid) {
                        if (buttonpressed === 'reminder')
                        {
                            $("#displayUpdate").html("Reminder inviato con successo!");
                            $("#updatePopUp").removeClass("none");
                            $("#updatePopUp").addClass("block");
                        }
                        if (buttonpressed === 'sconto')
                        {
                            $("#displayUpdate").html("Sconto inviato con successo!");
                            $("#updatePopUp").removeClass("none");
                            $("#updatePopUp").addClass("block");
                        }
                    } else
                    {
                        alert("Errore nell'invio messaggio! " + data.e);
                    }
                }
            });
            return false;
        });
    });

    /*----------------------------------LOGIN-------------------------------------*/
    $("#loginForm").submit(function () {
        $.ajax({
            url: "login",
            type: "POST",
            dataType: "JSON",
            data: $("#loginForm").serialize(),
            success: function (data) {
                if (!data.isValid) {
                    alert("Verifica i dati! " + data.e);
                } else
                {
                    window.location.replace("user");
                }
            }
        });
        return false;
    });

    $('#pass').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 20)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    $('#user').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 20)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    function verificaLunghezzaUser() {
        if ($('#user').val().length < 2 && $('#user').val().length !== 0) {
            $('#user').addClass("error");
            $('#errUser').html(mapAlert[$('#errUser')[0].id]);
            $('#errUser').show();
        } else {
            $('#user').removeClass("error");
            $('#errUser').hide();
        }
    }

    function verificaLunghezzaPass() {
        if ($('#pass').val().length < 5 && $('#pass').val().length !== 0) {
            $('#pass').addClass("error");
            $('#errPass').html(mapAlert[$('#errPass')[0].id]);
            $('#errPass').show();

        } else {
            $('#pass').removeClass("error");
            $('#errPass').hide();
        }
    }

    $('#user').on('change input', verificaLunghezzaUser); //anche per register.jsp
    $('#pass').on('change input', verificaLunghezzaPass); //anche per register.jsp

    /*-----------------------------REGISTRAZIONE----------------------------------*/

    var mapAlert = {
        errUser: 'La lunghezza deve essere tra 2 e 20 caratteri',
        errPass: 'La lunghezza deve essere tra 5 e 20 caratteri',
        errConfirm: 'Le password non coincidono',
        errNome: 'La lunghezza deve essere tra 1 e 50 caratteri',
        errCognome: 'La lunghezza deve essere tra 1 e 50 caratteri',
        errDateN: 'Impossibile impostare una data superiore a oggi',
        errCf: 'Questo cf non è valido',
        errEmail: 'Questa email non è valida',
        errCell: 'Il numero di cellulare deve contenere solo numeri'
    };

    $("#register").submit(function () {
        var formData = new FormData(this);
        $.ajax({
            url: "register",
            type: "POST",
            method: "POST",
            processData: false,
            contentType: false,
            dataType: "JSON",
            data: formData,
            success: function (data) {
                if (data.isValid) {
                    $("#register").hide();
                    $("#displayUpdate").html("Registrazione avvenuta con successo! Vai al <u><a href\=\"login.jsp\">login</a></u> per autenticarti!");
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

    function checkPasswords() {
        if ($('#pass').val() !== $('#confirmPass').val())
        {
            $('#confirmPass').addClass("error");
            $('#errConfirm').html(mapAlert[$('#errConfirm')[0].id]);
            $('#errConfirm').show();
        } else {
            $('#confirmPass').removeClass("error");
            $('#errConfirm').hide();
        }
    }

    $('#confirmPass').on('change input', checkPasswords);
    $('#pass').on('change input', checkPasswords);

    $('#confirmPass').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 20)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    $('#nome').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 50)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    function verificaLunghezzaNome() {
        if ($('#nome').val().length < 2 && $('#nome').val().length !== 0) {
            $('#nome').addClass("error");
            $('#errNome').html(mapAlert[$('#errNome')[0].id]);
            $('#errNome').show();

        } else {
            $('#nome').removeClass("error");
            $('#errNome').hide();
        }
    }

    $('#nome').on('change input', verificaLunghezzaNome);

    $('#cognome').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 50)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    function verificaLunghezzaCognome() {
        if ($('#cognome').val().length < 2 && $('#cognome').val().length !== 0) {
            $('#cognome').addClass("error");
            $('#errCognome').html(mapAlert[$('#errCognome')[0].id]);
            $('#errCognome').show();

        } else {
            $('#cognome').removeClass("error");
            $('#errCognome').hide();
        }
    }

    $('#cognome').on('change input', verificaLunghezzaCognome);

    $('#CF').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 16)
            if (!(key === 46 || key === 8)) //delete = 46 backspace = 8
                e.preventDefault();
    });

    function verificaCF() {
        var condition = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/.test($('#CF').val());

        if ($('#CF').val().length < 16 || !condition) {
            $('#CF').addClass("error");
            $('#errCf').html(mapAlert[$('#errCf')[0].id]);
            $('#errCf').show();

        } else {
            $('#CF').removeClass("error");
            $('#errCf').hide();
        }
    }

    $('#CF').on('change input', verificaCF);

    $('#email').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 50)
            if (!(key === 46 || key === 8)) //delete = 46 bacakspace = 8
                e.preventDefault();
    });

    function verificaEmail() {
        var condition = /^$|[a-zA-Z0-9]+@{1}[a-zA-Z0-9]+\.[a-z]{2,3}$/.test($('#email').val());
        if (!condition) {
            $('#email').addClass("error");
            $('#errEmail').html(mapAlert[$('#errEmail')[0].id]);
            $('#errEmail').show();
        } else {
            $('#email').removeClass("error");
            $('#errEmail').hide();
        }
    }

    $('#email').on('change input', verificaEmail);

    $('#cellulare').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 20)
            if (!(key === 46 || key === 8)) //delete = 46 bacakspace = 8
                e.preventDefault();
    });

    function verificaCell() {
        var condition = !isNaN(Number($('#cellulare').val()));

        if (!condition) {
            $('#cellulare').addClass("error");
            $('#errCell').html(mapAlert[$('#errCell')[0].id]);
            $('#errCell').show();
        } else {
            $('#cellulare').removeClass("error");
            $('#errCell').hide();
        }
    }

    $('#cellulare').on('change input', verificaCell);

    $('#dataDiNascita').on('change input', verificaDataDiNascita);

    function verificaDataDiNascita() {

        var today = new Date();
        var inputDate = new Date($('#dataDiNascita').val());

        if (inputDate >= today) {
            $('#dataDiNascita').addClass("error");
            $('#errDateN').html(mapAlert[$('#errDateN')[0].id]);
            $('#errDateN').show();
        } else {
            $('#dataDiNascita').removeClass("error");
            $('#errDateN').hide();
        }
    }

    $('#register').submit(function () {
        if ($("input[name='sesso']:checked").val() === undefined || $("input[name='fattura']:checked").val() === undefined)
        {
            alert('Non hai inserito il sesso o la fattura');
            return false;
        }
    });

    /*-----------------------------INSERT PRENOTAZIONE----------------------------------*/
    $("#insertPrenotazione").submit(function () {
        $.ajax({
            url: "prenotazione",
            type: "POST",
            dataType: "JSON",
            data: $("#insertPrenotazione").serialize(),
            success: function (data) {
                if (data.isValid) {
                    $("#displayUpdate").html("Prenotazione inserita con successo!");
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

    /*chiusura pop-up per tutti gli insert*/
    $(".close-update").eq(0).click(function () {
        if(window.location.pathname !== "/Cavalluccio/jspadmin/emettiFattura") //nell'inserimento fattura non voglio refresh
            setTimeout(location.reload.bind(location)); //refresh pagina
        $("#updatePopUp").addClass("none");
    });

    $("#personePrenotate").keypress(function (e) {
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

    $('#dataPrenotazione').on('change input', verificaDataPrenotazione);

    function verificaDataPrenotazione() {

        var today = new Date();
        today.setHours(0, 0, 0, 0);

        var inputDate = new Date($('#dataPrenotazione').val());

        if (inputDate < today) {
            $('#dataPrenotazione').addClass("error");
            $('#errDataPrenotazione').html("La data deve essere maggiore o uguale ad oggi");
            $('#errDataPrenotazione').show();
        } else {
            $('#dataPrenotazione').removeClass("error");
            $('#errDataPrenotazione').hide();
        }
    }

    /*-----------------------------INSERT BAGNINO----------------------------------*/
    $("#insertBagnino").submit(function () {
        $.ajax({
            url: "bagnino",
            type: "POST",
            dataType: "JSON",
            data: $("#insertBagnino").serialize(),
            success: function (data) {
                if (data.isValid) {
                    $("#displayUpdate").html("Bagnino inserito con successo!");
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

    $('#attestati').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 200)
            if (!(key === 46 || key === 8))
                e.preventDefault();
    });

    $('#attestati').keyup(function () {
        $('#caratteriRimanenti').text("Caratteri: " + $(this).val().length + "/200");
    });

    /*-----------------------------INVIA MESSAGGIO----------------------------------*/
    $('#corpoMessaggio').keydown(function (e) {
        var key = e.keyCode || e.charCode;
        if (this.value.length >= 2000)
            if (!(key === 46 || key === 8))
                e.preventDefault();
    });

    $('#corpoMessaggio').keyup(function () {
        $('#caratteriRimanenti').text("Caratteri: " + $(this).val().length + "/2000");
    });

    //le chat le scrollo fino alla fine in modo che aprendo la chat appaiaono gli ultimi messaggi
    if (window.location.pathname === "/Cavalluccio/messaggi" || window.location.pathname === "/Cavalluccio/jspadmin/messaggiAdmin") {
        window.scroll(0, 100000);
    }

});