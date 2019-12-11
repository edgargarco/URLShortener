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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Custom styles for this template-->
    <link href="/MDB-Free/dashboard/startbootstrap/css/sb-admin-2.min.css" rel="stylesheet">
    <script src="/MDB-Free/ajax/ajax.js" type="text/javascript"></script>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
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





        <!-- Nav Item - Tables -->
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

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
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





                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <#if user?? ><span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.name}</span></#if>
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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Listado de usuarios</h1>
                <#if (error??) >
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </#if>
                <div class="card">

                    <!--Card content-->
                    <div class="card-body">

                        <!-- Table  -->
                        <table class="table table-hover" id="tableUSERS">
                            <!-- Table head -->
                            <thead class="blue-grey lighten-4">
                            <tr>

                                <th>Nombre De Usuario</th>
                                <th>Nombre Completo</th>
                                <th>Administrador</th>
                                <#if user??>
                                    <#if user.administrator == true >
                                        <th>Editar/Borrar</th>
                                    </#if>
                                </#if>

                            </tr>
                            </thead>
                            <!-- Table head -->

                            <!-- Table body -->
                            <tbody>
                            <#if users?? >
                                <#list users as u>
                                    <tr>

                                        <td  ><a target="_blank" href="" id="">${u.username}</a></td>
                                        <td  ><a target="_blank" href="">${u.name}</a></td>
                                        <#if u.isAdministrator() == true >
                                            <td  ><i class="fas fa-check-double"></i></td>
                                            <#else >
                                                <td  ><i class="fas fa-minus-circle"></i></td>
                                        </#if>
                                        <#if user??>
                                            <#if user.administrator == true >
                                                <td id=""><button type="button" class="btn btn-primary btn-sm px-3 editUser" id="editUser" data-toggle="modal" data-target="#RegisterUserModal"><i class="far fa-edit" id="12"></i></button><button type="button" id="deleteUser" class="btn btn-primary btn-sm px-3 ml-2" data-toggle="modal" data-target="#modalConfirmDelete"><i class="fas fa-trash-alt"> </i></button></td>
                                            <#else >
                                            </#if>
                                        </#if>
                                    </tr>
                                </#list>
                            </#if>

                            </tbody>
                            <!-- Table body -->
                        </table>
                        <!-- Table  -->

                    </div>

                </div>

            </div>
            <!-- /.container-fluid -->

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

<!--UPDATE USER-->
<form action="/editUser" method="post">
<div class="modal fade" id="RegisterUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">

    <!-- Change class .modal-sm to change the size of the modal -->
    <div class="modal-dialog modal-md" role="document">


        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title w-100" id="titleModalRegister-update">Modificar Usuario</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="md-form">
                    <input type="text" id=" " class="form-control input-full-name-modal-register-update" name="username" readonly>
                    <label for="inputMDEx" class="input-full-name-modal-register-update">Nombre de usuario</label>
                </div>
                <div class="md-form">
                    <input type="text" id=" " class="form-control name-input" name="name" required>
                    <label for="inputMDEx" class="name-input">Nombre completo</label>
                </div>
                <div class="md-form">
                    <input type="password" id=" " class="form-control password-input" name="password">
                    <label for="inputMDEx" class="password-input">Password</label>
                </div>

                <!-- Material unchecked -->
                <div class="d-flex p-2 col-example">
                    <div class="form-check mr-auto">
                        <input type="checkbox" class="form-check-input" id="materialUncheckedAdmin" name="materialUncheckedAdmin">
                        <label class="form-check-label" for="materialUnchecked">Administrador</label>
                    </div>

                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn- btn-sm" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-primary btn-sm">Salvar</button>
            </div>
        </div>
    </div>
</div>
</form>

<!--Modal confirm delete-->
<!--Modal: modalConfirmDelete-->
<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm modal-notify modal-danger" role="document">
        <!--Content-->
        <div class="modal-content text-center">
            <!--Header-->
            <div class="modal-header d-flex justify-content-center">
                <p class="heading" id="confirmTitle">Eliminar usuario</p>
            </div>

            <!--Body-->
            <div class="modal-body">

                <i class="fas fa-times fa-4x animated rotateIn"></i>
                <div class="row">
                    <div class="col">
                        <span id="messageDelete">Seguro de eleminar el usuario <strong id="userID"></strong> </span>
                    </div>
                </div>


            </div>

            <!--Footer-->
            <div class="modal-footer flex-center">
                <a class="btn  btn-outline-danger" type="submit" id="delete-user">Si!</a>
                <a type="button" class="btn  btn-danger waves-effect" data-dismiss="modal" style="color: white;">Cancelar</a>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>
<!-- Start your project here-->
<!-- Bootstrap core JavaScript-->
<script src="/MDB-Free/dashboard/startbootstrap/vendor/jquery/jquery.min.js"></script>
<script src="/MDB-Free/dashboard/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/MDB-Free/dashboard/startbootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/MDB-Free/dashboard/startbootstrap/js/sb-admin-2.min.js"></script>

</body>

</html>
