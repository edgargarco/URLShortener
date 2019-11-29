$(document).ready(function () {
    //alert(":J")
    $("#tableUSERS tbody tr button#editUser").on('click',function () {
        alert("HOLA")
        var curRow = $(this).closest('tr');
        var username = curRow.find('td:eq(0)').text();
        var name = curRow.find('td:eq(1)').text();
        var admin = curRow.find('td:eq(2)').html();
        var status = admin.localeCompare("<i class=\"fas fa-check-double\"></i>");
        $('.input-full-name-modal-register-update').attr('value', username);
        $('.name-input').attr('value',name);
        if(status == 0){
            $('#materialUncheckedAdmin').attr('value',true);
            $('#materialUncheckedAdmin').prop('checked',true);
        }else{
            $('#materialUncheckedAdmin').attr('value',false);
            $('#materialUncheckedAdmin').prop('checked',false);
        }
        checkBox();

    })

    $("#tableUSERS tbody tr button#deleteUser").on('click',function () {

        var curRow = $(this).closest('tr');
        var username = curRow.find('td:eq(0)').text();
        $('strong#userID').text(username);
        $('a#delete-user').attr("href","/deleteUser/"+username);


    })


})

function checkBox(){
    $('#materialUncheckedAdmin').click(function () {
        if ($(this).is(":checked")){
            $('#materialUncheckedAdmin').attr('value',true);
        }else if ($(this).is(":not(:checked)")){
            $('#materialUncheckedAdmin').attr('value',false);
        }
    })
}






















/*$(document).on('click','#shorten-url',function () {
    var url = $('input#urlInput').val();
    $.post("/url",{url:url},function (data) {
        var x = JSON.parse(data);
        console.log(x);
        $('input#urlInput').val("outURL.com/"+x.hash)
        addLink(x.url,x.hash);
    })

})
target="_blank"
function addLink(url,hash) {
    var ul = document.getElementById("list-url");
    var li = document.createElement("li");
    var urlspan = document.createElement("span");
    var hashSpan = document.createElement("span");
    var linkURL = document.createElement("a");
    var linkShort = document.createElement("a");

    linkURL.setAttribute("href",url);
    linkShort.setAttribute("href",hash);


    linkURL.innerHTML = url;
    linkShort.innerHTML = hash;

    urlspan.appendChild(linkURL);
    hashSpan.appendChild(linkShort);

    li.appendChild(urlspan);
    li.appendChild(hashSpan);
    ul.appendChild(li);
}*/

