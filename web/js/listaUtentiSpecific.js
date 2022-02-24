$(document).ready(function ()
{
    var caricato = false;
    $('#nome, #cognome').keydown(function ()
    {
        if (!caricato)
        {
            $.ajax({
                url: "listaUtenti",
                data: {
                    cmd: "listaUtenti"
                },
                dataType: "JSON",
                success: function (data, state) {
                    caricato = true;
                    popolaAutoCompletamento(data);
                },
                error: function (data, state) {
                    alert(state);
                }
            });
        }
    });

    function popolaAutoCompletamento(lista)
    {
        $.each(lista, function (k, v) {
            var elemento = '<option value="' + v + '">';
            if (/^nome/.test(k))
                $("#listaDiNomi").append(elemento);
            if (/^cognome/.test(k))
                $("#listaDiCognomi").append(elemento);
        });
    }

    $("#ordina").change(function () {
        var id = $(this).find("option:selected").attr("id");

        switch (id) {
            case "cognomeCrescente":

                var rows = $('#tbl tr:not(:first)').detach();

                rows.sort(function (a, b) {

                    var A = $(a).children('td.cognome').eq(0).text().toUpperCase();
                    var B = $(b).children('td.cognome').eq(0).text().toUpperCase();

                    if (A < B) {
                        return -1;
                    }

                    if (A > B) {
                        return 1;
                    }

                    return 0;

                });

                $.each(rows, function (index, row) {
                    $('#tbl').children('tbody').append(row);
                });

                break;
            case "cognomeDecrescente":

                var rows = $('#tbl tr:not(:first)').detach();

                rows.sort(function (a, b) {

                    var A = $(a).children('td.cognome').eq(0).text().toUpperCase();
                    var B = $(b).children('td.cognome').eq(0).text().toUpperCase();

                    if (A < B) {
                        return 1;
                    }

                    if (A > B) {
                        return -1;
                    }

                    return 0;

                });

                $.each(rows, function (index, row) {
                    $('#tbl').children('tbody').append(row);
                });

                break;
            case "postiCrescente":
                var rows = $('#tbl tr:not(:first)').detach();

                rows.sort(function (row1, row2) {
                    return parseInt($(row1).find('td.postiPrenotati').text()) - parseInt($(row2).find('td.postiPrenotati').text());
                });

                rows.each(function () {
                    $(this).appendTo('#tbl');
                });

                break;
            case "postiDecrescente":

                var rows = $('#tbl tr:not(:first)').detach();

                rows.sort(function (row2, row1) {
                    return parseInt($(row1).find('td.postiPrenotati').text()) - parseInt($(row2).find('td.postiPrenotati').text());
                });

                rows.each(function () {
                    $(this).appendTo('#tbl');
                });

                break;
        }
    });

});
