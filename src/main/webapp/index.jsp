<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <title>PwnedPasswordChecker</title>
</head>
<body>
    <div class="page-header">
        <h1 align="center">PwnedPasswordChecker <small>Anonymous password checker</small></h1>
    </div>
    <div class="jumbotron">
        <div class="container-fluid">
            <center>
                <form class="form-inline" method="post" action="checkpass">
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Enter password to check">
                        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                    </div>
                </form>
            </center>
        </div>
    </div>
    <div class="fixed-footer">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 text-center"><a href="https://haveibeenpwned.com"><span class="glyphicon glyphicon-copyright-mark"></span> haveibeenpwned.com</a></div>
                <div class="col-md-3 text-center">This service doesn't collect any kind of personal infomation.<br/>We polite your privacy.</div>
                <div class="col-md-3 text-center">Extra credits to JavaCoursesBrovary Team.<br/>Guys, you rock!</div>
                <div class="col-md-3 text-center"><a href="https://javacoursesbrovary.slack.com/"><span class="glyphicon glyphicon-globe"></span> JavaCoursesBrovary</a></div>
            </div>
        </div>
    </div>
</body>
</html>