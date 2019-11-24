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

