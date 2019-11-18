$(document).on('click','#shorten-url',function () {
    var url = $('input#urlInput').val();
    $.post("/url",{url:url},function (data) {
        //window.location = data;

        $('input#urlInput').val("outURL.com/"+data)
    })

})

