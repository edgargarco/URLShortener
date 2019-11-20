<!DOCTYPE html>
<html lang="en" class="full-height">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design for Bootstrap</title>
    <!-- MDB icon -->
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/MDB-Free/css/bootstrap.css">
    <!-- Material Design Bootstrap -->
    <link rel="stylesheet" href="/MDB-Free/css/mdb.min.css">
    <!-- Your custom styles (optional) -->
    <link rel="stylesheet" href="/MDB-Free/css/style.css">
</head>
<body>

<!--Main Navigation-->
<header>
    <!--Navbar-->
    <nav class="navbar navbar-expand-lg navbar-dark   fixed-top">

        <div class="container">

            <!-- Navbar brand -->
            <a class="navbar-brand" href="#">App Name</a>

            <!-- Collapse button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
                    aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Collapsible content -->
            <div class="collapse navbar-collapse" id="basicExampleNav">

                <!-- Links -->
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>



                </ul>
                <!-- Links -->

                <div class=" ">
                    <a class="nav-item" style="color: white;" href="/login"><i class="fas fa-sign-in-alt fa-0x"></i> Login</a>
                </div>

            </div>
            <!-- Collapsible content -->

        </div>

    </nav>
    <!--/.Navbar-->
    <!--Mask-->
    <!--Mask-->
    <div id="intro" class="view">

        <div class="mask rgba-black-strong">

            <div class="container-fluid d-flex align-items-center justify-content-center h-100 ">

                <div class="row   justify-content-center text-center w-50">

                    <div class="col-md-12">

                        <!-- Heading -->
                        <h4 class="display-4 font-weight-bold white-text pt-5 mb-2">Some Good Slogan</h4>

                        <!-- Divider -->
                        <hr class="hr-light">

                        <!-- Description -->
                        <form action="url" method="post">
                        <div class="input-group mb-3">

                               <input type="text" class="form-control" placeholder="URL a recortar" aria-label="Recipient's username"
                                      aria-describedby="button-addon2" name="url-to-shorter" id="urlInput">

                            <div class="input-group-append">
                                <button class="btn btn-md btn-outline-default m-0 px-3 py-2 z-depth-0 waves-effect " type="submit" id="shorten-url">Recortar</button>
                            </div>URL
                        </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>

    </div>
    <!--/.Mask-->
    <!--/.Mask-->

</header>
<!--Main Navigation-->

<!--Main layout-->
<!--Main layout-->
<main class="mt-1">
    <div class="container">

        <!--Section: Best Features-->
        <!--Section: Best Features-->
        <section id="best-features" class="text-center">

            <!-- Heading -->
            <h2 class="mb-5">Haz crecer tu marca con cada clic</h2>

            <!--Grid row-->
            <div class="row d-flex justify-content-center mb-4">

                <!--Grid column-->
                <div class="col-md-8">

                    <!-- Description -->
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi voluptate hic provident nulla repellat
                        facere esse molestiae ipsa labore porro minima quam quaerat rem, natus repudiandae debitis est
                        sit pariatur.</p>

                </div>
                <!--Grid column-->

            </div>
            <!--Grid row-->

            <!--Grid row-->
            <div class="row">

                <!--Grid column-->
                <div class="col-md-4 mb-1">
                    <i class="fas fa-link fa-4x orange-text"></i>
                    <h4 class="my-4">URL</h4>
                    <p class="grey-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit maiores nam, aperiam minima
                        assumenda deleniti hic.</p>
                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-md-4 mb-1">
                    <i class="fas fa-chart-line fa-4x orange-text"></i>
                    <h4 class="my-4">Data Analitics</h4>
                    <p class="grey-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit maiores nam, aperiam minima
                        assumenda deleniti hic.</p>
                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-md-4 mb-1">
                    <i class="fas fa-database fa-4x orange-text"></i>
                    <h4 class="my-4">Records</h4>
                    <p class="grey-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit maiores nam, aperiam minima
                        assumenda deleniti hic.</p>
                </div>
                <!--Grid column-->

            </div>
            <!--Grid row-->

        </section>

        <!--Section: Best Features-->


        <!--Section: Examples-->

        <hr class="my-5">
        <!--Section: Best Features-->
        <section>
            <div>
                <ul id="list-url">
                    <#if (url??)>
                        <#list url as x>
                            <li><span><a target="_blank" href="${x.hash}">${x.url}-----------------------</a></span><span><a>${x.hash}</a></span></li>
                        </#list>
                    </#if>

                </ul>
            </div>
        </section>


    </div>
</main>
<!--Main layout-->
<!--Main layout-->

<!--Footer-->
<!-- Footer -->
<footer class="page-footer font-small blue pt-4">

    <div class=" text-center py-3">© 2018 Copyright:
        <a href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a>
    </div>
    <!-- Copyright -->

</footer>
<!-- Footer -->
<!--Footer-->

<!-- End your project here-->

<!-- jQuery -->
<script type="text/javascript" src="/MDB-Free/js/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="/MDB-Free/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="/MDB-Free/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="/MDB-Free/js/mdb.min.js"></script>
<!-- Your custom scripts (optional) -->
<script type="text/javascript" src="/MDB-Free/ajax/ajax.js"></script>

</body>
</html>
