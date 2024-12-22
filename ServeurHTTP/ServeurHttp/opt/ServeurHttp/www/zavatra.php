<!DOCTYPE html>
<html lang="en"><!-- Basic -->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Site Metas -->
    <title>Christmas - Log In</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="#" type="image/x-icon" />
    <link rel="apple-touch-icon" href="#" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- Pogo Slider CSS -->
    <link rel="stylesheet" href="assets/css/pogo-slider.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="assets/css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="assets/css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="assets/css/custom.css">
    <link rel="stylesheet" href="assets/css/Mine.css">


    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body class="fond-int">

    <!-- LOADER -->
    <div id="preloader">
        <div class="loader">
            <div class="box"></div>
            <div class="box"></div>
        </div>
    </div><!-- end loader -->
    <!-- END LOADER -->





    <div id="contact" class="contact-box form-init" style="background: #f7f7f7;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title-box">
                        <h2>Bienvenue</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-lg-8 col-sm-8 col-xs-12">
                    <div class="contact-block">
                        <form id="" action="php/bienvenue.php" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" name="name"
                                            placeholder="Your Name" required data-error="Please enter your name">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" placeholder="Your PassWord" id="email" class="form-control"
                                            name="mdp" required data-error="Please enter a PassWord">
                                        <div class=" help-block with-errors">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12">

                                    <div class="submit-button text-center">
                                        <button class="btn btn-common" id="submit" type="submit">Se Connecter</button>
                                        <div id="msgSubmit" class="h3 text-center hidden"></div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-5 col-sm-5 col-xs-12">

            </div>
        </div>
    </div>

    <!-- ALL JS FILES -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
    <script src="assets/js/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/jquery.pogo-slider.min.js"></script>
    <script src="assets/js/slider-index.js"></script>
    <script src="assets/js/smoothscroll.js"></script>
    <script src="assets/js/form-validator.min.js"></script>
    <script src="assets/js/contact-form-script.js"></script>
    <script src="assets/js/isotope.min.js"></script>
    <script src="assets/js/images-loded.min.js"></script>
    <script src="assets/js/custom.js"></script>
</body>

</html>