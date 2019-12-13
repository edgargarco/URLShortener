<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DashBoard URL</title>

    <!-- Custom fonts for this template-->
    <link href="/MDB-Free/dashboard/startbootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/MDB-Free/dashboard/startbootstrap/css/sb-admin-2.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>

    <script type="text/javascript" src="/MDB-Free/dashboard/startbootstrap/googleChart/charts.js"></script>
    <link href="/MDB-Free/dashboard/startbootstrap/calendar/dist/css/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="/MDB-Free/dashboard/startbootstrap/calendar/dist/js/datepicker.min.js"></script>
    <script src="/MDB-Free/dashboard/startbootstrap/calendar/dist/js/i18n/datepicker.en.js"></script>




</head>


<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">URL <sup>2</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/dashBoard">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Servicios
        </div>

       <#if user??>
           <#if user.isAdministrator() == true>
               <li class="nav-item">
                   <a class="nav-link" href="/listUsers">
                       <i class="fas fa-fw fa-table"></i>
                       <span>Usuarios</span></a>
               </li>

               <li class="nav-item">
                   <a class="nav-link" href="/listAllUrl">
                       <i class="fas fa-list-ol"></i>
                       <span>Listado URL</span></a>
               </li>
           </#if>
       </#if>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->



                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <#if user??>
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.name}</span>
                            </#if>
                            <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                </div>



                <div class="row">

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-lg-4">
                        <#if demographicsURL?? >
                            <div class=" mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">

                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Usuarios Linux</div>
                                                <#if LinuxCant??>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${LinuxCant}</div>

                                                </#if>

                                            </div>
                                            <div class="col-auto">
                                                <i class="fab fa-linux fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-lg-4">
                        <#if demographicsURL?? >
                            <div class=" mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">

                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Usuarios Windows</div>
                                                <#if WindowsCant??>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${WindowsCant}</div>

                                                </#if>

                                            </div>
                                            <div class="col-auto">
                                                <i class="fab fa-windows fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>


                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-lg-4">
                        <#if demographicsURL?? >
                            <div class=" mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">

                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Usuarios IOS</div>
                                                <#if IOSCant??>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${IOSCant}</div>

                                                </#if>

                                            </div>
                                            <div class="col-auto">
                                                <i class="fab fa-apple fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>

                    <div class="col-xl-3 col-lg-4">
                        <#if demographicsURL?? >
                            <div class=" mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">

                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Usuarios Android</div>
                                                <#if AndroidCant??>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${AndroidCant}</div>

                                                </#if>

                                            </div>
                                            <div class="col-auto">
                                                <i class="fab fa-android fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>



                <!-- Content Row -->

                <div class="row">
                    <#if demographicsURL??>
                        <input type="hidden" id="hashKEY" class="hashKEY" value="${demographicsURL.hash}"></input>
                    </#if>
                    <!-- Area Chart -->
                    <div class="col-xl-8 col-lg-7">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Visitas el :</h6>

                                <div class="md-form form-sm d-flex flex-row">
                                    <input type='text' class='datepicker-here form-control form-control-sm mr-3' data-language='en' id="inputSMEx"/>
                                    <a class="select-date" id="select-date" href="#"><i class="fas fa-search fa-2x"></i></a>
                                </div>


                            </div>
                            <!-- Card Body -->
                            <div class="card-body">

                                    <canvas id="myChart" width="" height=""></canvas>


                            </div>
                        </div>
                    </div>

                    <!-- Pie Chart -->
                    <div class="col-xl-4 col-lg-5">
                        <#if demographicsURL?? >
                            <div class=" mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Total de visitas</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">${demographicsURL.getVisits()?size}</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-glasses fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                        <div>

                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#basicExampleModal">
                                Informaciones sobre Ips
                            </button>


                        </div>

                        <div class="card shadow mb-4 mt-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <#if demographicsURL??>
                                    <input type="hidden" value="${demographicsURL.hash}" class="url-qr" id="url-qr">
                                </#if>
                                <h6 class="m-0 font-weight-bold text-primary">Qr Code</h6>

                            </div>
                            <!-- Card Body -->

                            <div class="card-body">
                                <style>
                                    .chart-pie{
                                        text-align: center;
                                        display: block;
                                    }
                                </style>
                                <div class="chart-pie pt-4 pb-2" >
                                    <div id='qrcode'></div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>



                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2019</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Seguro que quieres Cerrar sesión?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Seleccione "Cerrar sesión" a continuación si está listo para finalizar su sesión actual.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                        <a class="btn btn-primary" href="/closeSession">Cerrar sesión</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="basicExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Lista de Ips</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Ip publica</th>
                                <th scope="col">Dispositivo</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#if ips??>
                                <#list ips as ip>
                                    <tr>
                                        <th scope="row">${ip.ip}</th>
                                        <td>${ip.device}</td>

                                    </tr>
                                </#list>
                            </#if>


                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                    </div>
                </div>
            </div>
        </div>



        <!-- Bootstrap core JavaScript-->
        <script src="/MDB-Free/dashboard/startbootstrap/vendor/jquery/jquery.min.js"></script>
        <script src="/MDB-Free/dashboard/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="/MDB-Free/dashboard/startbootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="/MDB-Free/dashboard/startbootstrap/js/sb-admin-2.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="/MDB-Free/src/jquery.qrcode.js"></script>
        <script type="text/javascript" src="/MDB-Free/src/qrcode.js"></script>
        <script>
            // Initialize
            var qr = $('input.url-qr').val();
            jQuery('#qrcode').qrcode("${domain}/"+qr);
        </script>

        <!-- Page level plugins -->


</body>

</html>
