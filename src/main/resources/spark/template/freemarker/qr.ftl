<!DOCTYPE html>
<html>
<head>
    <title>Demo page</title>

</head>
<body>
<p>
    TODO make a nice looking pure client qrcode generator
    even allow download of the image
</p>

<div id="output"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="/MDB-Free/ajax/src/jquery.qrcode.min.js"></script>

<script>
    jQuery(function(){
        jQuery('#output').qrcode("http://jetienne.com");
    })
</script>

</body>
</html>